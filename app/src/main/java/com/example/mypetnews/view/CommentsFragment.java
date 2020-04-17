package com.example.mypetnews.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.mypetnews.R;
import com.example.mypetnews.model.Comment;
import com.example.mypetnews.viewmodel.CommentViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public final class CommentsFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    //private Single mGetStory;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_comments, container,
                false);

        mLayoutManager = new LinearLayoutManager(getActivity());
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.commentList);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(mLayoutManager);

        //setupStoriesUnavailableView(rootView);
        //setupRefreshLayout(rootView);

        return rootView;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //mGetStory = getStory();

        //loadComments();
    }

    /*private Single getStory() {
        return Single.fromCallable(new Callable<Story>() {
            @Override
            public Story call() {
                HexApplication application = (HexApplication) getActivity().getApplication();
                StoryService service = new StoryService(application.getRequestQueue(), application.apiBaseUrl);
                return service.getStory(getStoryId());
            }

        });
    }

    private void loadComments() {
        mGetStory.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(createCommentsLoadedHandler());
    }

    private SingleObserver createCommentsLoadedHandler() {
        return new SingleObserver<Story>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onSuccess(Story story) {
                List<CommentViewModel> viewComments = new ArrayList<>();

                for (Comment comment : story.getComments()) {
                    addCommentToList(comment, viewComments, 0);
                }

                CommentListAdapter cla = new CommentListAdapter(getActivity(), viewComments);
                mLayoutManager = new LinearLayoutManager(getActivity());
                mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                mRecyclerView.setLayoutManager(mLayoutManager);
                mRecyclerView.setAdapter(cla);

                showCommentList();
                hideCommentsUnavailable();

                mSwipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onError(Throwable e) {
                mSwipeRefreshLayout.setRefreshing(false);
                hideCommentList();
                showCommentsUnavailable();
            }
        };
    }*/

    /*private String getStoryId() {
        return this.getArguments().getString("STORY_ID");
    }

    private void addCommentToList(Comment comment, List<CommentViewModel> list, int depth) {
        list.add(new CommentViewModel(comment.getUser(), comment.getText(), depth,
                comment.getCommentCount(), comment.getDate()));

        for (Comment childComment : comment.getChildComments()) {
            addCommentToList(childComment, list, depth + 1);
        }
    }

    private void setupStoriesUnavailableView(View rootView) {
        ((TextView) rootView.findViewById((R.id.loading_failed_text)))
                .setText(R.string.error_unableToLoadComments);
        Button tryAgain = (Button) rootView.findViewById(R.id.try_again);
        tryAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mGetStory.subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(createCommentsLoadedHandler());
            }
        });
    }

    private void setupRefreshLayout(View rootView) {
        mSwipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.refresh);
        mSwipeRefreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        mGetStory.subscribeOn(Schedulers.newThread())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(createCommentsLoadedHandler());
                    }
                }
        );
    }*/

    private void showCommentList() {
        getView().findViewById(R.id.commentList).setVisibility(View.VISIBLE);
    }

    private void hideCommentList() {
        getView().findViewById(R.id.commentList).setVisibility(View.GONE);
    }

    private void showCommentsUnavailable() {
        getView().findViewById(R.id.content_unavailable).setVisibility(View.VISIBLE);
    }

    private void hideCommentsUnavailable() {
        getView().findViewById(R.id.content_unavailable).setVisibility(View.GONE);
    }
}