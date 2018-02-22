package com.vikram.mvpsample.ui_presentation.landing;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vikram.mvpsample.R;
import com.vikram.mvpsample.ui_presentation.home.HomeActivity;
import com.vikram.mvpsample.ui_presentation.sign_in.SignInActivity;
import com.vikram.mvpsample.utils.AppSharedPreferenceManager;

import static com.vikram.mvpsample.utils.Constants.PreferenceKeys.PREF_KEY_LOGGED_IN;


/**
 * A simple {@link Fragment} subclass.
 */
public class SplashFragment extends Fragment {


    boolean isLoggedIn = false;

    public SplashFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        isLoggedIn = AppSharedPreferenceManager.read(PREF_KEY_LOGGED_IN, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (isLoggedIn)
                    startActivity(new Intent(getContext(), HomeActivity.class));
                else
                    startActivity(new Intent(getContext(), SignInActivity.class));

                getActivity().finish();
            }
        }, 1000);
    }
}
