package com.example.goalgiver.ui.main.goal;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.goalgiver.R;

public class AddGoalTeam extends AppCompatActivity {
    private LinearLayout goalAddTeamDateText;
    private LinearLayout goalAddTeamCalender2;
    private LinearLayout goalAddTeamtimeText;
    private LinearLayout goalAddTeamCalender;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.goal_add_team);

        Button personal_bt, timeAttack_bt, goalSuccess_bt, certificationImage_bt, certificationLocation_bt, certificationTeam_bt;




        personal_bt = (Button) findViewById(R.id.goal_add_team_personal);
        timeAttack_bt = (Button)findViewById(R.id.goal_add_team_TimeAttack);
        goalSuccess_bt = (Button)findViewById(R.id.goal_add_team_GoalSuccess);
        //시작 시간 & 종료 시간
        goalAddTeamDateText = findViewById(R.id.goal_add_team_date_text);
        goalAddTeamCalender2 = findViewById(R.id.goal_add_team_calender2);
        //시작 날짜 & 종료 날짜
        goalAddTeamtimeText = findViewById(R.id.goal_add_team_time_text);
        goalAddTeamCalender = findViewById(R.id.goal_add_team_calender);

        certificationImage_bt = findViewById(R.id.goal_add_team_certification_camera);
        certificationLocation_bt = findViewById(R.id.goal_add_team_certification_location);
        certificationTeam_bt = findViewById(R.id.goal_add_team_certification_team);

        setTimeAttack();
        imageCertification();


        personal_bt.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){


                Intent intent = new Intent(getApplicationContext(),AddGoalMain.class);
                startActivity(intent);

                overridePendingTransition(0, 0);
            }
        });

        timeAttack_bt.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                setTimeAttack();
            }
        });

        goalSuccess_bt.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                setGoalSuccess();
            }
        });

        certificationImage_bt.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                imageCertification();
            }
        });

        certificationLocation_bt.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                locationCertification();
            }
        });

        certificationTeam_bt.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                teamCertification();
            }
        });


    }
    private void setTimeAttack() {
        goalAddTeamDateText.setVisibility(View.GONE);
        goalAddTeamCalender2.setVisibility(View.GONE);

        goalAddTeamtimeText.setVisibility(View.VISIBLE);
        goalAddTeamCalender.setVisibility(View.VISIBLE);

        Button timeAttack_bt = findViewById(R.id.goal_add_team_TimeAttack);
        Button goalSuccess_bt = findViewById(R.id.goal_add_team_GoalSuccess);

        timeAttack_bt.setBackgroundResource(R.drawable.circle_choose_btn);
        timeAttack_bt.setTextColor(Color.parseColor("#FFFFFF"));

        goalSuccess_bt.setBackgroundResource(R.drawable.circle_btn);
        goalSuccess_bt.setTextColor(Color.parseColor("#0E9AFF"));
    }

    private void setGoalSuccess() {
        goalAddTeamDateText.setVisibility(View.VISIBLE);
        goalAddTeamCalender2.setVisibility(View.VISIBLE);

        goalAddTeamtimeText.setVisibility(View.GONE);
        goalAddTeamCalender.setVisibility(View.GONE);

        Button timeAttack_bt = findViewById(R.id.goal_add_team_TimeAttack);
        Button goalSuccess_bt = findViewById(R.id.goal_add_team_GoalSuccess);

        timeAttack_bt.setBackgroundResource(R.drawable.circle_btn);
        timeAttack_bt.setTextColor(Color.parseColor("#0E9AFF"));

        goalSuccess_bt.setBackgroundResource(R.drawable.circle_choose_btn);
        goalSuccess_bt.setTextColor(Color.parseColor("#FFFFFF"));
    }
    private void imageCertification(){
        Button certificationImage_bt = findViewById(R.id.goal_add_team_certification_camera);
        Button certificationLocation_bt = findViewById(R.id.goal_add_team_certification_location);
        Button certificationTeam_bt = findViewById(R.id.goal_add_team_certification_team);


        certificationImage_bt.setBackgroundResource(R.drawable.circle_certification_choose);
        certificationLocation_bt.setBackgroundResource(R.drawable.recode_btn);
        certificationTeam_bt.setBackgroundResource(R.drawable.recode_btn);
    }
    private void locationCertification(){
        Button certificationImage_bt = findViewById(R.id.goal_add_team_certification_camera);
        Button certificationLocation_bt = findViewById(R.id.goal_add_team_certification_location);
        Button certificationTeam_bt = findViewById(R.id.goal_add_team_certification_team);


        certificationImage_bt.setBackgroundResource(R.drawable.recode_btn);
        certificationLocation_bt.setBackgroundResource(R.drawable.circle_certification_choose);
        certificationTeam_bt.setBackgroundResource(R.drawable.recode_btn);
    }
    private void teamCertification(){
        Button certificationImage_bt = findViewById(R.id.goal_add_team_certification_camera);
        Button certificationLocation_bt = findViewById(R.id.goal_add_team_certification_location);
        Button certificationTeam_bt = findViewById(R.id.goal_add_team_certification_team);


        certificationImage_bt.setBackgroundResource(R.drawable.recode_btn);
        certificationLocation_bt.setBackgroundResource(R.drawable.recode_btn);
        certificationTeam_bt.setBackgroundResource(R.drawable.circle_certification_choose);
    }
}
