package com.example.goalgiver.ui.main.goal;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.goalgiver.R;
import com.example.goalgiver.ui.main.people.FriendItem;

import java.util.ArrayList;

public class AddGoalTeamChoose extends AppCompatActivity {
    private ArrayList<AddGoalTeamList> users;
    private AddGoalTeamAdapter adapter;
    private Button completeButton;

    @Override
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


        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);


        for (int i = 1; i <= 100; i++) { // 10은 최대 친구 수로 설정할 수 있습니다. 필요에 따라 조정 가능
            String friendName = sharedPreferences.getString("friend" + i + "_name", null);
            int friendImg = sharedPreferences.getInt("friend" + i + "_img", -1);

            if (friendName != null && friendImg != -1) {
                users.add(new AddGoalTeamList(friendName, friendImg, false));
            } else {
                // 더 이상 저장된 데이터가 없다면 반복문을 중단.
                break;
            }
        }


        adapter = new AddGoalTeamAdapter(this, users);
        listView.setAdapter(adapter);


        // 완료 버튼 클릭 시 선택된 사용자 목록 처리
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

        // 뒤로 가기 버튼 클릭 시 액티비티 종료
        backButton.setOnClickListener(view -> finish());
    }
}