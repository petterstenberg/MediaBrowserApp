package se.petterstenberg.mediabrowserapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;

import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import se.petterstenberg.mediabrowserapp.models.ProgramsResponse;
import se.petterstenberg.mediabrowserapp.models.submodels.Pagination;
import se.petterstenberg.mediabrowserapp.models.submodels.Program;
import se.petterstenberg.mediabrowserapp.rest.ApiClient;
import se.petterstenberg.mediabrowserapp.rest.ApiClientImpl;

public class MainActivity extends AppCompatActivity {

    private ApiClient mApiClient;
    private ProgramAdapter mProgramAdapter;

    @BindView(R.id.main_programs_list)
    RecyclerView mProgramsList;

    @BindView(R.id.main_progressbar)
    ProgressBar mProgressBar;

    private int mCurrentPage = 1;
    private int mTotalPages = 1;
    private boolean mIsLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        setSupportActionBar((Toolbar)findViewById(R.id.toolbar));
        setTitle(R.string.app_name);

        mProgramAdapter = new ProgramAdapter(getApplicationContext(), new ProgramAdapter.Listener() {
            @SafeVarargs
            @Override
            public final void onItemClicked(Program program, Pair<View, String>... sharedElements) {
                handleItemClicked(program, sharedElements);
            }
        });

        final LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());

        mProgramsList.setLayoutManager(layoutManager);
        mProgramsList.setAdapter(mProgramAdapter);
        mProgramsList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                int lastVisibleItem = layoutManager.findLastVisibleItemPosition();

                if(!mIsLoading && lastVisibleItem == mProgramAdapter.getItemCount()-1
                        && mCurrentPage < mTotalPages) {
                    loadPrograms(mCurrentPage+1);
                }
            }
        });

        mApiClient = new ApiClientImpl(getApplicationContext());

        loadPrograms(1);
    }

    private void handleItemClicked(Program program, Pair<View, String>[] sharedElements) {
        String programJsonString = new Gson().toJson(program);

        Intent intent = new Intent(MainActivity.this, DetailActivity.class);
        intent.putExtra(DetailActivity.KEY_PROGRAM_JSON_STRING, programJsonString);

        ActivityOptionsCompat activityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(
                MainActivity.this, sharedElements);

        startActivity(intent, activityOptions.toBundle());
    }

    private void loadPrograms(int page) {

        mIsLoading = true;
        mProgressBar.setVisibility(View.VISIBLE);

        mApiClient.getPrograms(page, mApiResponseCallback);
    }

    private ApiClient.Callback<ProgramsResponse> mApiResponseCallback = new ApiClient.Callback<ProgramsResponse>() {
        @Override
        public void onSuccess(@NonNull ProgramsResponse response) {

            mIsLoading = false;
            mProgressBar.setVisibility(View.GONE);

            Pagination pagination = response.getPagination();

            mCurrentPage = pagination != null ? pagination.getPage() : 1;
            mTotalPages = pagination != null ? pagination.getTotalPages() : 1;

            mProgramAdapter.addPrograms(response.getPrograms());
        }

        @Override
        public void onFailure(@NonNull Throwable t) {
            mIsLoading = false;
            mProgressBar.setVisibility(View.GONE);

            t.printStackTrace();
        }
    };
}
