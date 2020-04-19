package com.example.mypetnews.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.mypetnews.R;
import com.example.mypetnews.model.Story;
import com.example.mypetnews.util.Constants;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public final class StoriesListAdapter extends RecyclerView.Adapter<StoriesListAdapter.ViewHolder> {

    private List<Story> storiesList;
    private ItemClickListener itemClickListener;

    public StoriesListAdapter(List<Story> storiesList, ItemClickListener itemClickListener) {
        this.storiesList = storiesList;
        this.itemClickListener = itemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new ViewHolder(inflater.inflate(R.layout.item_story_list, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Story story = storiesList.get(position);

        Picasso.get()
                .load(Constants.IMAGE_URL + story.getDomain() + Constants.IMAGE_SIZE)
                .resize(100, 100)
                .centerCrop()
                .error(R.drawable.ic_image)
                .into(holder.storyImage);

        holder.titleText.setText(story.getTitle());
        holder.sourceText.setText(story.getDomain());
        holder.timeText.setText(Constants.getRelativeTime(story.getTime()));
        holder.scoreText.setText(story.getScore().toString());
        holder.commentCountText.setText(story.getCommentCount().toString());
    }

    @Override
    public int getItemCount() {
        return storiesList.size();
    }

    public void updateData(List<Story> storiesList) {
        this.storiesList = storiesList;
        notifyDataSetChanged();
    }

    final class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.image_story)
        protected ImageView storyImage;

        @BindView(R.id.text_title)
        protected TextView titleText;

        @BindView(R.id.text_source)
        protected TextView sourceText;

        @BindView(R.id.text_time)
        protected TextView timeText;

        @BindView(R.id.text_score)
        protected TextView scoreText;

        @BindView(R.id.text_comment_count)
        protected TextView commentCountText;

        ViewHolder(View view) {
            super(view);

            ButterKnife.bind(this, view);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            itemClickListener.onClick(getAdapterPosition());
        }
    }
}