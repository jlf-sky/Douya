/*
 * Copyright (c) 2018 Zhang Hai <Dreaming.in.Code.ZH@Gmail.com>
 * All Rights Reserved.
 */

package me.zhanghai.android.douya.item.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.zhanghai.android.douya.R;
import me.zhanghai.android.douya.network.api.info.frodo.Movie;
import me.zhanghai.android.douya.util.FragmentUtils;
import me.zhanghai.android.douya.util.TintHelper;

public class ItemIntroductionFragment extends Fragment {

    private static final String KEY_PREFIX = ItemIntroductionFragment.class.getName() + '.';

    private static final String EXTRA_TITLE = KEY_PREFIX + "title";
    private static final String EXTRA_MOVIE = KEY_PREFIX + "movie";

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.introduction)
    TextView mIntroductionText;

    private String mTitle;
    private Movie mMovie;

    public static ItemIntroductionFragment newInstance(String title, Movie movie) {
        //noinspection deprecation
        ItemIntroductionFragment fragment = new ItemIntroductionFragment();
        Bundle arguments = FragmentUtils.ensureArguments(fragment);
        arguments.putString(EXTRA_TITLE, title);
        arguments.putParcelable(EXTRA_MOVIE, movie);
        return fragment;
    }

    /**
     * @deprecated Use {@link #newInstance(String, Movie)} instead.
     */
    public ItemIntroductionFragment() {}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle arguments = getArguments();
        mTitle = arguments.getString(EXTRA_TITLE);
        mMovie = arguments.getParcelable(EXTRA_MOVIE);

        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.item_introduction_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this, view);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(mToolbar);
        TintHelper.onSetSupportActionBar(mToolbar);

        activity.setTitle(mTitle);

        mIntroductionText.setText(mMovie.introduction);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                getActivity().finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
