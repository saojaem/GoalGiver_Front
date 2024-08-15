package com.example.goalgiver.ui.main.goal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;

import com.example.goalgiver.R;

public class AddGoalTeamChoose extends AppCompatActivity {
    private ArrayList<AddGoalTeamList> users;
    private AddGoalTeamAdapter adapter;
    private Button completeButton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_goal_team_choose);

        ListView listView = findViewById(R.id.add_goal_team_choose_LV);
        completeButton = findViewById(R.id.add_goal_team_choose_complete);
        ImageView backButton = findViewById(R.id.add_goal_team_choose_backButton);


        users = new ArrayList<>();
        users.add(new AddGoalTeamList("레나", R.drawable.richard_addgoal, false));
        users.add(new AddGoalTeamList("쿠마", R.drawable.richard_addgoal, false));
        users.add(new AddGoalTeamList("리처드", R.drawable.add_goal_profile, false));
        users.add(new AddGoalTeamList("밀리", R.drawable.richard_addgoal, false));

        adapter = new AddGoalTeamAdapter(this, users);
        listView.setAdapter(adapter);


        completeButton.setOnClickListener(view -> {
            ArrayList<AddGoalTeamList> selectedUsers = new ArrayList<>();
            for (AddGoalTeamList user : users) {
                if (user.isChecked()) {
                    selectedUsers.add(user);
                }
            }

            Intent resultIntent = new Intent();
            resultIntent.putParcelableArrayListExtra("selectedUsers", selectedUsers);
            setResult(RESULT_OK, resultIntent);
            finish();
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });



    }

}

