package com.kpkdev.youlocaltask.ui.ui.fragments;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kpkdev.youlocaltask.R;
import com.kpkdev.youlocaltask.ui.navigation.NavigationListener;
import com.kpkdev.youlocaltask.ui.networking.APIClient;
import com.kpkdev.youlocaltask.ui.networking.events.UserDetailsReadyEvent;
import com.kpkdev.youlocaltask.ui.ui.fragments.dialogs.BaseDialog;
import com.kpkdev.youlocaltask.ui.ui.fragments.dialogs.BasicMessageDialog;
import com.kpkdev.youlocaltask.ui.ui.fragments.dialogs.NoConnectionDialog;
import com.kpkdev.youlocaltask.ui.utils.AsteriskTransformationMethod;
import com.kpkdev.youlocaltask.ui.utils.ConnectivityUtils;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.squareup.otto.Subscribe;
import java.util.List;


import butterknife.Bind;
/**
 * Created by krasimir.karamazov on 1/5/2016.
 */
public class LoginFragment extends BaseFragment implements Validator.ValidationListener{


    @Bind(R.id.iv_logo)
    ImageView ivLogo;

    @NotEmpty
    @Bind(R.id.et_username)
    EditText etUserName;

    @NotEmpty
    @Bind(R.id.et_password)
    EditText etPassword;

    @Bind(R.id.btn_login)
    Button loginButton;

    @Bind(R.id.input_layout_username)
    TextInputLayout usernameLayout;

    @Bind(R.id.input_layout_password)
    TextInputLayout passwordLayout;

    @Bind(R.id.tv_forgotten_password)
    TextView forgottenPassword;

    @Bind(R.id.form_holder)
    LinearLayout formHolder;

    @Bind(R.id.username_for_password)
    TextInputLayout userNameForResetLayout;

    @Bind(R.id.et_username_for_pass)
    EditText etUserNameForPass;

    private com.mobsandgeeks.saripaar.Validator validator;

    private boolean mForgottenState = false;

    public static LoginFragment getInstance(Bundle args, NavigationListener listener) {
        LoginFragment fragment = new LoginFragment();
        fragment.setArguments(args);
        fragment.setNavigationListener(listener);
        return fragment;
    }

    @Override
    protected void retryQuery() {

    }

    @Override
    protected void initUI() {
        validator = new Validator(this);
        validator.setValidationListener(this);
        etPassword.setTransformationMethod(new AsteriskTransformationMethod());
        animateObject(ivLogo, 0);
        animateObject(usernameLayout, 600);
        animateObject(passwordLayout, 900);
        animateObject(loginButton, 1200);
        animateObject(forgottenPassword, 1500);
        forgottenPassword.setOnClickListener(getOnClickListener());
        loginButton.setOnClickListener(getOnClickListener());
    }

    private View.OnClickListener getOnClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch(view.getId()) {
                    case R.id.tv_forgotten_password:
                        if(!mForgottenState){
                            etUserName.setText("");
                            etPassword.setText("");
                            passwordLayout.setVisibility(View.GONE);
                            usernameLayout.setVisibility(View.GONE);
                            userNameForResetLayout.setVisibility(View.VISIBLE);
                            changeButtonParams(userNameForResetLayout.getId());
                            mForgottenState = true;
                        }else{
                            etUserNameForPass.setText("");
                            passwordLayout.setVisibility(View.VISIBLE);
                            usernameLayout.setVisibility(View.VISIBLE);
                            userNameForResetLayout.setVisibility(View.GONE);
                            changeButtonParams(formHolder.getId());
                            mForgottenState = false;
                        }
                        changeState(mForgottenState);
                        break;
                    case R.id.btn_login:
                        if(!mForgottenState) {
                            validator.validate();
                        }else{

                        }
                        break;
                }
            }
        };
    }

    private void changeState(boolean forgotState) {
        String loginButtonText;
        String forgotPassText;
        if(forgotState) {
            loginButtonText = getString(R.string.reset);
            forgotPassText = getString(R.string.back_to_login);
        }else{
            loginButtonText = getString(R.string.login_label);
            forgotPassText = getString(R.string.forgotten_password_label);
        }
        loginButton.setText(loginButtonText);
        forgottenPassword.setText(forgotPassText);
    }

    private void changeButtonParams(int belowViewId) {
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)loginButton.getLayoutParams();
        params.addRule(RelativeLayout.BELOW, belowViewId);
        loginButton.setLayoutParams(params);
    }

    private void animateObject(View view, long startOffset) {
        final Animation anim = AnimationUtils.loadAnimation(getActivity(), R.anim.login_logo_animation);
        anim.setStartOffset(startOffset);
        view.startAnimation(anim);
    }

    @Subscribe
    public void onUserDetailsReadyEvent(UserDetailsReadyEvent e) {
        hideProgress();
        if(e.getUser() != null && e.getError() == null) {
            if(getNavigationListener() != null) {
                Bundle args = new Bundle();
                args.putSerializable(UserDetailsFragment.USER_DETAILS_ARG_KEY, e.getUser());
                if(getNavigationListener() != null) {
                    BaseFragment fragment = UserDetailsFragment.getInstance(args);
                    String TAG = fragment.getBackstackTag();
                    getNavigationListener().navigateToFragment(fragment, TAG, false, true);
                    forgottenPassword.setOnClickListener(null);
                    loginButton.setOnClickListener(null);
                    loginButton.setEnabled(false);
                }
            }
        }else{
            Bundle argsError = new Bundle();
            String message = getString(R.string.server_error);

            if (e.getError() != null && !TextUtils.isEmpty(e.getError().getErrors().getEmail())) {
                message = e.getError().getErrors().getEmail();
            }else{
                if(e.getError() != null && !TextUtils.isEmpty(e.getError().getError())) {
                    message = e.getError().getError();
                }
            }
            argsError.putString(BaseDialog.MESSAGE_ARG_KEY, message);
            BasicMessageDialog.getInstance(argsError, null).show(getActivity().getFragmentManager(), BasicMessageDialog.TAG);
        }
    }



    @Override
    protected int getLayoutId() {
        return R.layout.fragment_login;
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

    @Override
    public void onValidationSucceeded() {
        if(ConnectivityUtils.isNetworkAvailable(getActivity().getApplicationContext())) {
            showProgress();
            APIClient.getInstance().getUserDetails(etUserName.getText().toString(), etPassword.getText().toString());
        }else{
            NoConnectionDialog.getInstance(null).show(getActivity().getFragmentManager(), NoConnectionDialog.TAG);
        }
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        for (ValidationError error : errors) {
            View view = error.getView();
            String message;
            switch(view.getId()) {
                case R.id.et_username:
                    message = String.format(getString(R.string.error_message_format), getString(R.string.username_label));
                    break;
                case R.id.et_password:
                    message = String.format(getString(R.string.error_message_format), getString(R.string.password_label));
                    break;
                default:
                    message = "";
                    break;
            }
            ((EditText)view).setError(message);
        }
    }
}
