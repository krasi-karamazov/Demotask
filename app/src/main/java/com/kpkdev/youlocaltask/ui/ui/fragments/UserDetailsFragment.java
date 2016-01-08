package com.kpkdev.youlocaltask.ui.ui.fragments;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.kpkdev.youlocaltask.R;
import com.kpkdev.youlocaltask.ui.models.User;
import com.kpkdev.youlocaltask.ui.ui.RoundedTransformation;
import com.squareup.picasso.Picasso;

import butterknife.Bind;

/**
 * Created by krasimir.karamazov on 1/7/2016.
 */
public class UserDetailsFragment extends BaseFragment {
    public static final String TAG = UserDetailsFragment.class.getSimpleName();
    public static final String USER_DETAILS_ARG_KEY = "user_details";
    @Bind(R.id.iv_avatar)
    ImageView ivAvatar;

    @Bind(R.id.tv_username)
    TextView tvUserName;

    @Bind(R.id.tv_description)
    TextView tvDescription;

    public static UserDetailsFragment getInstance(Bundle args) {
        UserDetailsFragment dialog = new UserDetailsFragment();
        dialog.setArguments(args);
        return dialog;
    }

    @Override
    protected void retryQuery() {

    }

    @Override
    protected void initUI() {

        final User user = (User)getArguments().getSerializable(USER_DETAILS_ARG_KEY);
        if(user != null) {
            Picasso.with(getActivity()).load(user.getAvatar()).transform(new RoundedTransformation()).into(ivAvatar);
            tvUserName.setText(user.getFullname());
            tvDescription.setText(user.getAboutMe());
            tvDescription.setMovementMethod(new ScrollingMovementMethod());
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_user_details;
    }

    @Override
    protected int getMenuId() {
        return 0;
    }

    @Override
    protected String getTitle() {
        return null;
    }

    @Override
    public String getScreenNameForAnalytics() {
        return null;
    }

    @Override
    protected boolean handleMenuItemClick(MenuItem item) {
        return false;
    }
}
