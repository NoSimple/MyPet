package com.example.mypetnews.adapter;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;


import com.example.mypetnews.view.ArticleFragment;
import com.example.mypetnews.view.CommentsFragment;

import java.util.ArrayList;

public final class StorySlidePagerAdapter extends FragmentStatePagerAdapter {

    private final ArrayList<Fragment> mFragments;

    public StorySlidePagerAdapter(FragmentManager fm, String storyId) {
        super(fm);

        Bundle args = new Bundle();
        args.putString("STORY_ID", storyId);

        ArticleFragment article = new ArticleFragment();
        article.setArguments(args);
        CommentsFragment comments = new CommentsFragment();
        comments.setArguments(args);

        mFragments = new ArrayList<>();
        mFragments.add(article);
        mFragments.add(comments);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }
}