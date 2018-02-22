package com.vikram.mvpsample.ui_presentation.register;

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
import com.vikram.mvpsample.utils.AppSharedPreferenceManager;
import com.vikram.mvpsample.utils.Utils;

import static com.vikram.mvpsample.utils.Constants.PreferenceKeys.PREF_KEY_LOGGED_IN;
import static com.vikram.mvpsample.utils.Constants.PreferenceKeys.PREF_KEY_PASSWORD;
import static com.vikram.mvpsample.utils.Constants.PreferenceKeys.PREF_KEY_USER_ID;
import static com.vikram.mvpsample.utils.Constants.PreferenceKeys.PREF_KEY_USER_NAME;

/**
 * Created by VIKRAM R on 20/02/2018.
 */

public class RegistrationFragment extends Fragment implements RegisterUserContract.View {

    private FrameLayout mFlytProgress;
    private RegisterUserContract.Presenter mPresenter;
    User user;

    public RegistrationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_registration, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        new RegisterUserPresenter(this, getContext());
        initUI();
    }

    private void initUI() {
        if (getView() != null) {
            mFlytProgress = getView().findViewById(R.id.progress_loader);
            getView().findViewById(R.id.register).setOnClickListener(v -> validateSaveAction());
        }
    }

    private void validateSaveAction() {
        if (getView() != null) {
            String userName = ((EditText) getView().findViewById(R.id.edt_user_name)).getText().toString().trim();
            String email = ((EditText) getView().findViewById(R.id.edt_email)).getText().toString().trim();
            String password = ((EditText) getView().findViewById(R.id.edt_password)).getText().toString().trim();
            String confirmPassword = ((EditText) getView().findViewById(R.id.edt_confirm_password)).getText().toString().trim();

            if (userName.isEmpty()) {
                ((EditText) getView().findViewById(R.id.edt_user_name)).setError(getString(R.string.user_name_required));
            } else if (email.isEmpty()) {
                ((EditText) getView().findViewById(R.id.edt_email)).setError(getString(R.string.email_required));
            } else if (password.isEmpty()) {
                ((EditText) getView().findViewById(R.id.edt_password)).setError(getString(R.string.password_required));
            } else if (confirmPassword.isEmpty()) {
                ((EditText) getView().findViewById(R.id.edt_confirm_password)).setError(getString(R.string.confirm_password_required));
            } else {
                user = new User();
                user.setUserName(userName);
                user.setUserEmail(email);
                user.setUserPassword(password);
                mPresenter.checkUser(email);
            }
        }
    }

    @Override
    public void setPresenter(RegisterUserContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showLoadingIndicator(boolean show) {
        Utils.progressView(show, mFlytProgress);
    }

    @Override
    public void onUserRegistrationSuccess(@NonNull User user) {
        AppSharedPreferenceManager.write(PREF_KEY_LOGGED_IN, true);
        AppSharedPreferenceManager.write(PREF_KEY_USER_ID, user.getUserId());
        AppSharedPreferenceManager.write(PREF_KEY_USER_NAME, user.getUserEmail());
        AppSharedPreferenceManager.write(PREF_KEY_PASSWORD, user.getUserPassword());
        Toast.makeText(getContext(), "Successfully Registered!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUserRegistrationFail(@NonNull User user) {
        Toast.makeText(getContext(), "Registration failed!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void usernameExists(@NonNull String emailId) {
        Toast.makeText(getContext(), "User already exists!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void usernameNotExist(@NonNull String emailId) {
        mPresenter.registerUser(user);
    }
}
