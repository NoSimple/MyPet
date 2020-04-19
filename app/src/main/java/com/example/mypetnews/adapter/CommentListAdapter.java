package com.example.mypetnews.adapter;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.mypetnews.R;
import com.example.mypetnews.model.Comment;
import com.example.mypetnews.util.Constants;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public final class CommentListAdapter extends RecyclerView.Adapter<CommentListAdapter.ViewHolder> {

    private Context context;

    private List<Comment> commentsList;

    public CommentListAdapter(List<Comment> commentsList) {
        this.commentsList = commentsList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        return new ViewHolder(inflater.inflate(R.layout.item_comment_list, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Comment comment = commentsList.get(position);

        if (comment.getMargin() != null) {
            ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) holder.itemView.getLayoutParams();
            layoutParams.setMarginStart(layoutParams.getMarginStart() + comment.getMargin());
        }

        holder.textUserName.setText(comment.getAuthor());
        holder.timeText.setText(Constants.getRelativeTime(comment.getTime()));
        holder.commentText.setText(Html.fromHtml(comment.getText()));
        if (comment.getCommentCount() <= 0) {
            holder.answerView.setVisibility(View.GONE);
        } else {
            holder.titleAnswerText.setText(context.getResources().getText(R.string.answer_text));
            holder.countAnswerText.setText(comment.getCommentCount().toString());
            holder.answerView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return commentsList.size();
    }

    final class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.text_user_name)
        protected TextView textUserName;

        @BindView(R.id.text_time)
        protected TextView timeText;

        @BindView(R.id.text_comment)
        protected TextView commentText;

        @BindView(R.id.view_answer)
        protected LinearLayout answerView;

        @BindView(R.id.text_title_answer)
        protected TextView titleAnswerText;

        @BindView(R.id.text_count_answer)
        protected TextView countAnswerText;

        ViewHolder(View view) {
            super(view);

            ButterKnife.bind(this, view);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (commentsList.get(getAdapterPosition()).getCommentCount() > 0) {
                for (Comment comment : commentsList.get(getAdapterPosition()).getComments()) {
                    if (comment.getCommentCount() > 0) {
                        if (comment.getMargin() == null) {
                            comment.setMargin(16);
                        } else {
                            comment.setMargin(comment.getMargin() + 20);
                        }
                        commentsList.add(getAdapterPosition() + 1, comment);
                    } else {
                        comment.setCommentCount(0);
                        if (comment.getMargin() == null) {
                            comment.setMargin(16);
                        } else {
                            comment.setMargin(comment.getMargin() + 20);
                        }
                        commentsList.add(getAdapterPosition() + 1, comment);
                    }
                }
                notifyDataSetChanged();
            }
        }
    }
}