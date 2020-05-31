package com.sahel.qrauth.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * The class BaseActivity
 *
 * @author Sahel
 * @version 1.0
 * @since 30 May 2020
 */

public abstract class BaseActivity extends AppCompatActivity {

    private Unbinder unbinder;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    protected void injectViews(Activity activity) {
        unbinder = ButterKnife.bind(activity);
    }

    protected void destroyViews() {
        unbinder.unbind();
    }


    protected void hideToolBar() {
        getSupportActionBar().hide();
    }

    protected void showToolBar() {
        getSupportActionBar().show();
    }


    //abstract function

    protected abstract void onMenuInflated();

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        onMenuInflated();
        return true;
    }

}
