package com.vikram.mvpsample.utils;

import android.view.View;
import android.widget.FrameLayout;

/**
 * Created by VIKRAM R on 20/02/2018.
 */

public class Utils {
    public static void progressView(boolean status, FrameLayout frameLayout) {
        if (status) {
            frameLayout.setVisibility(View.VISIBLE);
            frameLayout.setEnabled(false);
        } else {
            frameLayout.setVisibility(View.GONE);
        }
    }
}
