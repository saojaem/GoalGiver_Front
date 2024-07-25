package com.example.goalgiver.ui.main.goal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.goalgiver.R;

public class AddGoalMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.goal_add);

        Button teamButton, certificationImage_Pbt, certificationLocation_Pbt;
        teamButton= (Button) findViewById(R.id.goal_add_team);

        certificationImage_Pbt = findViewById(R.id.goal_add_certification_camera);
        certificationLocation_Pbt = findViewById(R.id.goal_add_certification_location);

        imageCertificationP();

        teamButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(),AddGoalTeam.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }

        });

        certificationImage_Pbt.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                imageCertificationP();
            }
        });

        certificationLocation_Pbt.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                locationCertificationP();
            }
        });

    }
    private void imageCertificationP(){
        Button certificationImage_Pbt = findViewById(R.id.goal_add_certification_camera);
        Button certificationLocation_Pbt = findViewById(R.id.goal_add_certification_location);

        certificationImage_Pbt.setBackgroundResource(R.drawable.circle_certification_choose);
        certificationLocation_Pbt.setBackgroundResource(R.drawable.recode_btn);

    }
    private void locationCertificationP() {
        Button certificationImage_Pbt = findViewById(R.id.goal_add_certification_camera);
        Button certificationLocation_Pbt = findViewById(R.id.goal_add_certification_location);

        certificationImage_Pbt.setBackgroundResource(R.drawable.recode_btn);
        certificationLocation_Pbt.setBackgroundResource(R.drawable.circle_certification_choose);
    }
}
