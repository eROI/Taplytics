package com.taplytics.example;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.View.OnClickListener;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        findViewById(R.id.code_experiments_button).setOnClickListener(getOnClickListener(this, CodeExperimentsActivity.class));
        findViewById(R.id.user_information_button).setOnClickListener(getOnClickListener(this, UserAttributesActivity.class));
        findViewById(R.id.events_button).setOnClickListener(getOnClickListener(this, EventsActivity.class));
        findViewById(R.id.visual_editor_button).setOnClickListener(getOnClickListener(this, VisualActivity.class));


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            toolbar.setTitle("");
            setSupportActionBar(toolbar);
        }
    }

    private OnClickListener getOnClickListener(final Context c, final Class<?> activityClass) {
        return new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(c, activityClass);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

            }
        };
    }
}
