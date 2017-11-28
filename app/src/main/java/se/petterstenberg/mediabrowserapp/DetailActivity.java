package se.petterstenberg.mediabrowserapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import se.petterstenberg.mediabrowserapp.models.submodels.Program;
import se.petterstenberg.mediabrowserapp.models.submodels.ProgramCategory;

/**
 * The activity that is shown when a user clicks a list item in the {@link MainActivity}.
 */

@SuppressWarnings("ConstantConditions")
public class DetailActivity extends AppCompatActivity {

    public static final String KEY_PROGRAM_JSON_STRING = "KEY_PROGRAM_OBJECT_STRING";
    public static final String VIEW_NAME_PROGRAM_IMAGE = "VIEW_NAME_PROGRAM_IMAGE";
    public static final String VIEW_NAME_PROGRAM_TITLE = "VIEW_NAME_PROGRAM_TITLE";
    public static final String VIEW_NAME_PROGRAM_DESCRIPTION = "VIEW_NAME_PROGRAM_DESCRIPTION";

    @BindView(R.id.detail_thumbnail)
    ImageView mProgramImageView;

    @BindView(R.id.detail_title)
    TextView mTitleTextView;

    @BindView(R.id.detail_description)
    TextView mDescriptionTextView;

    @BindView(R.id.detail_channel)
    TextView mChannelTextView;

    @BindView(R.id.detail_responsible_editor)
    TextView mEditorTextView;

    @BindView(R.id.detail_category)
    TextView mCategoryTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_detail);

        ButterKnife.bind(this);

        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        ViewCompat.setTransitionName(mProgramImageView, VIEW_NAME_PROGRAM_IMAGE);
        ViewCompat.setTransitionName(mTitleTextView, VIEW_NAME_PROGRAM_TITLE);
        ViewCompat.setTransitionName(mDescriptionTextView, VIEW_NAME_PROGRAM_DESCRIPTION);

        Intent intent = getIntent();

        String programJsonString = intent.getStringExtra(KEY_PROGRAM_JSON_STRING);

        Program program = new Gson().fromJson(programJsonString, Program.class);

        // If this would have been a bigger project I would have used a more declarative approach here,
        // wrapping the image handling in some kind of ImageLoader interface.
        Picasso.with(this).load(program.getProgramImageWide()).into(mProgramImageView);

        mTitleTextView.setText(program.getName());
        mDescriptionTextView.setText(program.getDescription());
        mChannelTextView.setText(program.getChannel().getName());
        mEditorTextView.setText(program.getResponsibleEditor());

        ProgramCategory programCategory = program.getProgramCategory();
        mCategoryTextView.setText(programCategory != null ? programCategory.getName() : "");
    }
}
