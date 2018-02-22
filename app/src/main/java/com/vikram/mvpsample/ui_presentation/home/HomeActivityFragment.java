package com.vikram.mvpsample.ui_presentation.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.vikram.mvpsample.R;
import com.vikram.mvpsample.storage.local.entity.User;
import com.vikram.mvpsample.ui_presentation.sign_in.SignInActivity;
import com.vikram.mvpsample.utils.AppSharedPreferenceManager;
import com.vikram.mvpsample.utils.Utils;

import static com.vikram.mvpsample.utils.Constants.PreferenceKeys.PREF_KEY_LOGGED_IN;
import static com.vikram.mvpsample.utils.Constants.PreferenceKeys.PREF_KEY_PASSWORD;
import static com.vikram.mvpsample.utils.Constants.PreferenceKeys.PREF_KEY_USER_ID;
import static com.vikram.mvpsample.utils.Constants.PreferenceKeys.PREF_KEY_USER_NAME;

/**
 * Created by VIKRAM R on 20/02/2018.
 */
public class HomeActivityFragment extends Fragment implements HomeContract.View {

    private HomeContract.Presenter mPresenter;
    private FrameLayout mFlytProgress;

    public HomeActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        new HomePresenter(this, getContext());
        initUI();
    }

    private void initUI() {
        if (getView() != null) {
            mFlytProgress = getView().findViewById(R.id.progress_loader);
            getView().findViewById(R.id.btn_delete).setOnClickListener(v -> mPresenter.deleteUser(AppSharedPreferenceManager.read(PREF_KEY_USER_NAME, "")));
            mPresenter.getUser(AppSharedPreferenceManager.read(PREF_KEY_USER_NAME, ""));
        }
    }

    @Override
    public void setPresenter(HomeContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showLoadingIndicator(boolean show) {
        Utils.progressView(show, mFlytProgress);
    }

    @Override
    public void onGetUserDetailsSuccess(@NonNull User user) {
        if (getView() != null) {
            ((TextView) getView().findViewById(R.id.txt_name)).setText(user.getUserName());
            ((TextView) getView().findViewById(R.id.txt_email)).setText(user.getUserEmail());
            ((TextView) getView().findViewById(R.id.txt_mobile)).setText(user.getUserPassword());
        }

    }

    @Override
    public void onGetUserDetailsFailed() {
        Toast.makeText(getContext(), "No Data found", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUserDeletedSuccess(@NonNull String emailId) {
        AppSharedPreferenceManager.write(PREF_KEY_LOGGED_IN, false);
        AppSharedPreferenceManager.write(PREF_KEY_USER_ID, "");
        AppSharedPreferenceManager.write(PREF_KEY_USER_NAME, "");
        AppSharedPreferenceManager.write(PREF_KEY_PASSWORD, "");
        Toast.makeText(getContext(), "Account Deleted Successfully!", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getContext(), SignInActivity.class));
        getActivity().finish();
    }

    @Override
    public void onUserDeletedFailed() {
        Toast.makeText(getContext(), "Unable to delete user!", Toast.LENGTH_SHORT).show();
    }
}
