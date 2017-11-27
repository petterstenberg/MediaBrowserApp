package se.petterstenberg.mediabrowserapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
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

    private static final String TAG = MainActivity.class.getSimpleName();
    private ApiClient mApiClient;
    private ProgramsAdapter mProgramsAdapter;

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

        mProgramsAdapter = new ProgramsAdapter(getApplicationContext(), new ProgramsAdapter.Listener() {
            @SafeVarargs
            @Override
            public final void onItemClicket(Program program, Pair<View, String>... sharedElements) {
                String programJsonString = new Gson().toJson(program);

                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra(DetailActivity.KEY_PROGRAM_JSON_STRING, programJsonString);

                ActivityOptionsCompat activityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(
                        MainActivity.this, sharedElements);

                startActivity(intent, activityOptions.toBundle());
            }
        });

        final LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        mProgramsList.setLayoutManager(layoutManager);
        mProgramsList.setAdapter(mProgramsAdapter);
        mProgramsList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                int lastVisibleItem = layoutManager.findLastVisibleItemPosition();

                if(!mIsLoading && lastVisibleItem == mProgramsAdapter.getItemCount()-1
                        && mCurrentPage < mTotalPages) {
                    loadPrograms(mCurrentPage+1);
                }
            }
        });

        mApiClient = new ApiClientImpl(getApplicationContext());
        loadPrograms(1);
    }

    private void loadPrograms(int page) {
        Log.d(TAG, "loadPrograms() called with: page = [" + page + "]");

        mIsLoading = true;
        mProgressBar.setVisibility(View.VISIBLE);

        mApiClient.getPrograms(page, mApiResponseCallback);
    }

    private ApiClient.Callback<ProgramsResponse> mApiResponseCallback = new ApiClient.Callback<ProgramsResponse>() {
        @Override
        public void onSuccess(ProgramsResponse response) {
            Log.d("", "onSuccess: ");

            mIsLoading = false;
            mProgressBar.setVisibility(View.GONE);

            Pagination pagination = response.getPagination();
            mCurrentPage = pagination != null ? pagination.getPage() : 1;
            mTotalPages = pagination != null ? pagination.getTotalPages() : 1;

            mProgramsAdapter.addPrograms(response.getPrograms());
        }

        @Override
        public void onFailure(Throwable t) {
            mIsLoading = false;
            mProgressBar.setVisibility(View.GONE);

            t.printStackTrace();
        }
    };
}
