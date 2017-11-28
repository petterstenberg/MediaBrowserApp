package se.petterstenberg.mediabrowserapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.util.Pair;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import se.petterstenberg.mediabrowserapp.models.submodels.Program;

/**
 * Adapter class handling items on first page.
 */

@SuppressWarnings("unchecked")
public class ProgramAdapter extends RecyclerView.Adapter<ProgramAdapter.ViewHolder>{

    private List<Program> mPrograms = new ArrayList<>();
    private Context mContext;
    private Listener mListener;

    @SuppressWarnings("unchecked")
    public interface Listener {
        void onItemClicked(Program program, Pair<View, String>... sharedElements);
    }

    public ProgramAdapter(Context context, Listener listener) {
        mContext = context;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.list_item, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Program program = mPrograms.get(position);

        // If this would have been a bigger project I would have used a more declarative approach here,
        // wrapping the image handling in some kind of ImageLoader interface, that I would inject.
        Picasso.with(mContext).load(program.getProgramImageWide()).into(holder.mThumbnail);

        holder.mTitle.setText(program.getName());
        holder.mDescription.setText(program.getDescription());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onItemClicked(program,
                        Pair.create((View)holder.mThumbnail, DetailActivity.VIEW_NAME_PROGRAM_IMAGE),
                        Pair.create((View)holder.mTitle, DetailActivity.VIEW_NAME_PROGRAM_TITLE),
                        Pair.create((View)holder.mDescription, DetailActivity.VIEW_NAME_PROGRAM_DESCRIPTION));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mPrograms.size();
    }

    public void addPrograms(@NonNull List<Program> programs){
        mPrograms.addAll(programs);

        notifyDataSetChanged();
    }

    protected static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.listitem_thumbnail)
        ImageView mThumbnail;

        @BindView(R.id.listitem_title)
        TextView mTitle;

        @BindView(R.id.listitem_description)
        TextView mDescription;

        public ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}
