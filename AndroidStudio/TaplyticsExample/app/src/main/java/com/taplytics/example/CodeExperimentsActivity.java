package com.taplytics.example;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;

import com.taplytics.sdk.Taplytics;
import com.taplytics.sdk.TaplyticsCodeExperimentListener;

import java.util.Map;

/**
 * This Activity contains an example of a code block experiment.
 * <p/>
 * For this experiment, we will be testing multiple variations of dialog on a popup box.
 * <p/>
 * The dialog text, and title will be changed according to the variation chosen.
 *
 * @author vicv
 */
public class CodeExperimentsActivity extends AppCompatActivity {

    /**
     * The popup dialog text. Default it to the baseline variation *
     */
    private String DIALOG_TEXT = "This is the Baseline variation!";

    /**
     * The popup dialog title. Default it to the baseline variation *
     */
    private String DIALOG_TITLE = "Baseline";

    private Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.code_experiments_layout);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        // Run this code experiment. This triggers the experiment.
        runAnExperiment();

        // Make a popup when the button is clicked
        findViewById(R.id.code_experiment_dialog_button).setOnClickListener(dialogPopupClickListener());
    }

    /**
     * Run the experiment! This notifies taplytics that an experiment is running. The experiment listener checks to see if the experiment
     * has been updated, and it will re-instantiate the experiment with the new information if it has.
     */
    public void runAnExperiment() {
        Taplytics.runCodeExperiment("Code Experiment", new TaplyticsCodeExperimentListener() {

            // We have received a variation of this experiment! In this case, we change our text and title to the received variables.
            @Override
            public void experimentVariation(String variationName, Map<String, Object> variables) {
                DIALOG_TEXT = (String) variables.get("Dialog Text");
                DIALOG_TITLE = variationName;
            }

            @Override
            public void experimentUpdated() {
                runAnExperiment();
            }

            // We have recieved the baseline variation! In this case, we change our text and title to the original, baseline variables (also
            // contained in the map).
            @Override
            public void baselineVariation(Map<String, Object> variables) {
                DIALOG_TEXT = "This is the Baseline variation!";
                DIALOG_TITLE = "Baseline";

            }
        });
    }

    /**
     * Create a popup with the text and title received from Taplytics, set by the code experiment. \
     */
    private OnClickListener dialogPopupClickListener() {
        return new OnClickListener() {

            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);

                // Set the text and title to the variables we are changing
                builder.setMessage(DIALOG_TEXT).setTitle(DIALOG_TITLE).setCancelable(true)
                        .setPositiveButton("NEAT!", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
            }
        };
    }

    // Back button animation...
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
