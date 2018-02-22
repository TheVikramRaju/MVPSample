package com.vikram.mvpsample.ui_presentation.sign_in;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.vikram.mvpsample.R;
import com.vikram.mvpsample.storage.local.entity.User;
import com.vikram.mvpsample.ui_presentation.home.HomeActivity;
import com.vikram.mvpsample.ui_presentation.register.RegistrationActivity;
import com.vikram.mvpsample.utils.AppSharedPreferenceManager;
import com.vikram.mvpsample.utils.Utils;

import static com.vikram.mvpsample.utils.Constants.PreferenceKeys.PREF_KEY_LOGGED_IN;
import static com.vikram.mvpsample.utils.Constants.PreferenceKeys.PREF_KEY_PASSWORD;
import static com.vikram.mvpsample.utils.Constants.PreferenceKeys.PREF_KEY_USER_ID;
import static com.vikram.mvpsample.utils.Constants.PreferenceKeys.PREF_KEY_USER_NAME;

/**
 * Created by VIKRAM R on 20/02/2018.
 */

public class SignInFragment extends Fragment implements SignInUserContract.View {
    private SignInUserContract.Presenter mPresenter;
    private FrameLayout mProgress;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_signin, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        new SignInUserPresenter(this, getContext());
        initUI();
    }

    private void initUI() {
        if (getView() != null) {
            mProgress = getView().findViewById(R.id.progress_loader);

            getView().findViewById(R.id.sign_in).setOnClickListener(v -> {
                validate(((EditText) getView().findViewById(R.id.edt_username)).getText().toString(), ((EditText) getView().findViewById(R.id.edt_password)).getText().toString());
            });

            getView().findViewById(R.id.txt_forgot_pwd).setOnClickListener(v -> Toast.makeText(getContext(), "Under Construction!", Toast.LENGTH_SHORT).show());

            getView().findViewById(R.id.sign_up).setOnClickListener(v -> startActivity(new Intent(getContext(), RegistrationActivity.class)));
        }

    }

    private void validate(String userName, String password) {
        if (userName.equals("")) {
            ((EditText) getView().findViewById(R.id.edt_username)).setError("Username required!");
        } else if (password.equals("")) {
            ((EditText) getView().findViewById(R.id.edt_password)).setError("Password required!");
        } else {
            mPresenter.authendicateUser(userName, password);
        }
    }

    @Override
    public void setPresenter(SignInUserContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showLoadingIndicator(boolean show) {
        Utils.progressView(show, mProgress);
    }

    @Override
    public void onAuthendicateSuccess(@NonNull User user) {
        AppSharedPreferenceManager.write(PREF_KEY_LOGGED_IN, true);
        AppSharedPreferenceManager.write(PREF_KEY_USER_ID, user.getUserId());
        AppSharedPreferenceManager.write(PREF_KEY_USER_NAME, user.getUserEmail());
        AppSharedPreferenceManager.write(PREF_KEY_PASSWORD, user.getUserPassword());
        startActivity(new Intent(getContext(), HomeActivity.class));
        getActivity().finish();
    }

    @Override
    public void onAuthendicateFailure(@NonNull User user) {
        Toast.makeText(getContext(), "Please check your login credentials!", Toast.LENGTH_SHORT).show();
    }
}
