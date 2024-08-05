package com.example.goalgiver.ui.main.goal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.goalgiver.R;

public class AddGoalDonation extends AppCompatActivity {

    private RadioButton radioUnicef;
    private RadioButton radioSaveTheChildren;
    private RadioButton radioPlanInternational;
    private RadioButton radioGoodNeighbors;
    private RadioButton radioPurnuFoundation;
    private RadioButton radioChildFund;

    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.add_goal_donation);

        radioUnicef = findViewById(R.id.radioUnicef);
        radioSaveTheChildren = findViewById(R.id.radioSaveTheChildren);
        radioPlanInternational = findViewById(R.id.radioPlanInternational);
        radioGoodNeighbors = findViewById(R.id.radioGoodNeighbors);
        radioPurnuFoundation = findViewById(R.id.radioPurnuFoundation);
        radioChildFund = findViewById(R.id.radioChildFund);

        radioUnicef.setOnClickListener(radioButtonClickListener);
        radioSaveTheChildren.setOnClickListener(radioButtonClickListener);
        radioPlanInternational.setOnClickListener(radioButtonClickListener);
        radioGoodNeighbors.setOnClickListener(radioButtonClickListener);
        radioPurnuFoundation.setOnClickListener(radioButtonClickListener);
        radioChildFund.setOnClickListener(radioButtonClickListener);

        findViewById(R.id.add_goal_repeat_choose_complete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selectedOrganization = getSelectedOrganization();
                Intent resultIntent = new Intent();
                resultIntent.putExtra("selectedOrganization", selectedOrganization);
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });
    }

    private View.OnClickListener radioButtonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            clearRadioButtons();
            ((RadioButton) view).setChecked(true);
        }
    };

    private void clearRadioButtons() {
        radioUnicef.setChecked(false);
        radioSaveTheChildren.setChecked(false);
        radioPlanInternational.setChecked(false);
        radioGoodNeighbors.setChecked(false);
        radioPurnuFoundation.setChecked(false);
        radioChildFund.setChecked(false);
    }

    private String getSelectedOrganization() {
        if (radioUnicef.isChecked()) return "UNICEF";
        if (radioSaveTheChildren.isChecked()) return "Save the Children";
        if (radioPlanInternational.isChecked()) return "Plan International";
        if (radioGoodNeighbors.isChecked()) return "Good Neighbors";
        if (radioPurnuFoundation.isChecked()) return "Purme Foundation";
        if (radioChildFund.isChecked()) return "Green Umbrella";
        return "";
    }
}
