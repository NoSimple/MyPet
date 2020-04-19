package com.example.mypetnews.adapter;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;


import com.example.mypetnews.model.Story;
import com.example.mypetnews.util.Constants;
import com.example.mypetnews.view.NewsFragment;
import com.example.mypetnews.view.CommentsFragment;

import java.util.ArrayList;

public final class StoryViewPagerAdapter extends FragmentStatePagerAdapter {

    private final ArrayList<Fragment> fragmentsArray = new ArrayList<>();

    public StoryViewPagerAdapter(FragmentManager fm, int storyId, Story story, String error) {
        super(fm, FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);

        Bundle args = new Bundle();
        args.putString(Constants.ID, Integer.toString(storyId));
        args.putParcelable(Constants.STORY, story);
        args.putString(Constants.ERROR, error);

        NewsFragment newsFragment = new NewsFragment();
        newsFragment.setArguments(args);

        CommentsFragment commentsFragment = new CommentsFragment();
        commentsFragment.setArguments(args);

        fragmentsArray.add(newsFragment);
        fragmentsArray.add(commentsFragment);
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentsArray.get(position);
    }

    @Override
    public int getCount() {
        return fragmentsArray.size();
    }
}