package com.kpkdev.youlocaltask.ui.ui.activities;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.kpkdev.youlocaltask.R;
import com.kpkdev.youlocaltask.ui.navigation.NavigationListener;
import com.kpkdev.youlocaltask.ui.ui.fragments.BaseFragment;
import com.kpkdev.youlocaltask.ui.ui.fragments.LoginFragment;

public class MainActivity extends AppCompatActivity implements NavigationListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        if(getActionBar() != null)
            getActionBar().hide();
        LoginFragment fragment = LoginFragment.getInstance(null, this);
        String TAG = fragment.getBackstackTag();
        navigateToFragment(fragment, TAG, true, false);
    }

    @Override
    public void navigateToFragment(BaseFragment fragment, String TAG, boolean shouldReplace, boolean shouldAnimateTransition) {
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        if(shouldAnimateTransition) {
            fragmentTransaction.setCustomAnimations(R.anim.fragmnent_animation, R.anim.fragmnent_animation);
        }
        if(shouldReplace) {
            fragmentTransaction.replace(R.id.fl_fragment_container, fragment, TAG);
        }else{
            fragmentTransaction.add(R.id.fl_fragment_container, fragment, TAG);
        }

        fragmentTransaction.addToBackStack(TAG);
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        if(getFragmentManager().getBackStackEntryCount() == 1) {
            finish();
        }else{
            super.onBackPressed();
        }
    }
}
