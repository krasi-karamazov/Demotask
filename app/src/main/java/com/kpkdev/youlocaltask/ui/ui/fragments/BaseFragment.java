package com.kpkdev.youlocaltask.ui.ui.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import com.kpkdev.youlocaltask.R;
import com.kpkdev.youlocaltask.ui.navigation.NavigationListener;
import com.kpkdev.youlocaltask.ui.networking.events.ErrorEvent;
import com.kpkdev.youlocaltask.ui.ui.fragments.dialogs.BaseDialog;
import com.kpkdev.youlocaltask.ui.ui.fragments.dialogs.BasicMessageDialog;
import com.kpkdev.youlocaltask.ui.ui.fragments.dialogs.ProgressDialog;
import com.kpkdev.youlocaltask.ui.utils.BusProvider;
import com.kpkdev.youlocaltask.ui.utils.Logger;
import com.squareup.otto.Subscribe;

import butterknife.ButterKnife;

/**
 * Created by krasimir.karamazov on 1/5/2016.
 */
public abstract class BaseFragment extends Fragment {

    private String title;
    private SharedPreferences mPrefs;
    private boolean tracked = false;
    private NavigationListener mNavigationListener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    protected final void setNavigationListener(NavigationListener navigationListener) {
        mNavigationListener = navigationListener;
    }

    protected final NavigationListener getNavigationListener() {
        return mNavigationListener;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        setRetainInstance(true);

        if (getTitle() != null) {
            title = getTitle();
        }
    }

    protected final void setTitle(String title) {
        if (getActivity() != null && getActivity().getActionBar() != null) {
            if (!TextUtils.isEmpty(title)) {
                getActivity().getActionBar().setTitle(title);
            }
        }
    }

    protected final void setTitle(int stringResourceId) {
        title = getString(stringResourceId);
        setTitle(title);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = null;
        if (getLayoutId() != 0) {
            rootView = inflater.inflate(getLayoutId(), container, false);
            ButterKnife.bind(this, rootView);
            initUI();
        }

        return rootView;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        ViewGroup viewGroup = (ViewGroup) getView();
        if (viewGroup != null) {
            viewGroup.removeAllViewsInLayout();
            View view = onCreateView(getActivity().getLayoutInflater(), viewGroup, null);
            viewGroup.addView(view);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        setTitle(title);
        BusProvider.getInstance().register(this);
        try {
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);

        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onPause() {
        super.onPause();
        BusProvider.getInstance().unregister(this);
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        int menuId = getMenuId();
        if (menuId != 0) {
            inflater.inflate(menuId, menu);
        }
    }

    protected final SharedPreferences getSharedPreferences() {
        if (mPrefs == null) {
            mPrefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        }
        return mPrefs;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return handleMenuItemClick(item);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(BaseFragment.this);
    }

    protected final void showProgress() {

        try {
            FragmentManager fm = getActivity().getFragmentManager();
            if (fm.findFragmentByTag(ProgressDialog.TAG) == null) {
                Bundle args = new Bundle();
                args.putString(BaseDialog.MESSAGE_ARG_KEY, getString(R.string.please_wait));
                BaseDialog progressDialog = ProgressDialog.getInstance(args);
                progressDialog.show(getActivity().getFragmentManager(), ProgressDialog.TAG);
            }
        } catch (Exception e) {
        }
    }

    protected final void showProgressCancellable() {

        try {
            FragmentManager fm = getActivity().getFragmentManager();
            if (fm.findFragmentByTag(ProgressDialog.TAG) == null) {
                Bundle args = new Bundle();
                args.putString(BaseDialog.MESSAGE_ARG_KEY, getString(R.string.please_wait));
                BaseDialog progressDialog = ProgressDialog.getInstance(args);
                progressDialog.setCancelable(false);
                progressDialog.show(getActivity().getFragmentManager(), ProgressDialog.TAG);
            }
        } catch (Exception e) {
        }
    }

    protected final void hideProgress() {
        try {
            BaseDialog dialog = (ProgressDialog) getActivity().getFragmentManager().findFragmentByTag(ProgressDialog.TAG);
            if (dialog != null) {
                Logger.d("Dialog dismissed");
                dialog.dismiss();
            }
        } catch (Exception e) {
        }
    }

    @Subscribe
    public void onErrorEvent(ErrorEvent e) {
        handleErrorEvent();
    }

    protected void handleErrorEvent() {
        hideProgress();
        BasicMessageDialog.getInstance(null, null).show(getActivity().getFragmentManager(), BasicMessageDialog.TAG);
    }

    public final String getBackstackTag() {
        return getClass().getSimpleName();
    }

    protected abstract void retryQuery();

    protected abstract void initUI();

    protected abstract int getLayoutId();

    protected abstract int getMenuId();

    protected abstract String getTitle();

    public abstract String getScreenNameForAnalytics();

    protected abstract boolean handleMenuItemClick(MenuItem item);
}
