package com.example.mypetnews.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mypetnews.R;
import com.example.mypetnews.adapter.CommentListAdapter;
import com.example.mypetnews.model.Comment;
import com.example.mypetnews.model.Story;
import com.example.mypetnews.util.Constants;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public final class CommentsFragment extends Fragment {

    private int storyId;
    private Story story;
    private String error;

    private CommentListAdapter adapter;

    @BindView(R.id.recycler_comments)
    protected RecyclerView commentsRecycler;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_comments, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            storyId = bundle.getInt(Constants.ID);
            story = bundle.getParcelable(Constants.STORY);
            error = bundle.getString(Constants.ERROR);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initAdapter(story.getComments());
    }

    @Override
    public void onResume() {
        super.onResume();
        ((StoryActivity) getActivity()).setNewsFragment(null);
    }

    private void initAdapter(List<Comment> commentsList) {

        if (adapter == null) {
            adapter = new CommentListAdapter(commentsList);
        }

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        commentsRecycler.setLayoutManager(layoutManager);
        commentsRecycler.setHasFixedSize(true);
        commentsRecycler.setAdapter(adapter);
    }
}