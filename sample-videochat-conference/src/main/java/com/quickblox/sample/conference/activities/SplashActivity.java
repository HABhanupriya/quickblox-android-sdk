package com.quickblox.sample.conference.activities;

import android.os.Bundle;
import android.util.Log;

import com.quickblox.sample.core.ui.activity.CoreSplashActivity;
import com.quickblox.sample.core.utils.SharedPrefsHelper;
import com.quickblox.sample.conference.R;
import com.quickblox.sample.conference.services.CallService;
import com.quickblox.users.model.QBUser;

public class SplashActivity extends CoreSplashActivity {

    private SharedPrefsHelper sharedPrefsHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sharedPrefsHelper = SharedPrefsHelper.getInstance();

        if (sharedPrefsHelper.hasQbUser()) {
            startLoginService(sharedPrefsHelper.getQbUser());
            startDialogsActivity();
            return;
        }

        if (checkConfigsWithSnackebarError()) {
            proceedToTheNextActivityWithDelay();
        }
    }

    @Override
    protected String getAppName() {
        return getString(R.string.splash_app_title);
    }

    @Override
    protected void proceedToTheNextActivity() {
        LoginActivity.start(this);
        finish();
    }

    protected void startLoginService(QBUser qbUser) {
        CallService.start(this, qbUser);
    }

    private void startDialogsActivity() {
        DialogsActivity.start(SplashActivity.this);
        finish();
    }
}