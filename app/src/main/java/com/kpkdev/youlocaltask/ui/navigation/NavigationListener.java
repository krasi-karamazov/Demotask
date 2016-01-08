package com.kpkdev.youlocaltask.ui.navigation;

import com.kpkdev.youlocaltask.ui.ui.fragments.BaseFragment;

/**
 * Created by krasimir.karamazov on 1/7/2016.
 */
public interface NavigationListener {
    void navigateToFragment(BaseFragment fragment, String TAG, boolean shouldReplace, boolean shouldAnimateTransition);
}
