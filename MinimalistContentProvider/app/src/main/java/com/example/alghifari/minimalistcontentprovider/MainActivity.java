package com.example.alghifari.minimalistcontentprovider;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView = (TextView) findViewById(R.id.textview);
    }

    public void onClickDisplayEntries(View view) {
        Log.d (TAG, "Yay, I was clicked!");

        switch (view.getId()) {
            case R.id.button_display_all:
                Log.d (TAG, "Yay, " + R.id.button_display_all + " was clicked!");
                break;
            case R.id.button_display_first:
                Log.d (TAG, "Yay, " + R.id.button_display_first + " was clicked!");
                break;
            default:
                Log.d (TAG, "Error. This should never happen.");
        }
        mTextView.append("Thus we go! \n");
    }
}