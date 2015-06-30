package com.taplytics.example;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.taplytics.sdk.Taplytics;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * In this activity, we send some basic user attributes to taplytics.
 *
 * @author vicv
 */
public class UserAttributesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_attributes_layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        // Set user attributes for this user, this will allow Taplytics to segment your users based on: gender, age, and any customData that
        // you set
        JSONObject attributes = new JSONObject();

        // Here is an example block of attributes.
        try {
            attributes.put("email", "example_email_address@taplytics.com");
            attributes.put("name", "exampleName");
            attributes.put("age", 100);
            attributes.put("customData", new JSONObject());
        } catch (JSONException e) {
            // Do something with a json error..
        }

        // And thats it, just send the object!
        Taplytics.setUserAttributes(attributes);

    }

    // Animation overrides..
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                super.onBackPressed();
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        }
        return true;
    }
}
