package com.example.goalgiver.ui.main.goal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.goalgiver.R;

import java.util.ArrayList;
public class AddGoalTeamAdapter extends ArrayAdapter<AddGoalTeamList>{
    private Context context;
    private ArrayList<AddGoalTeamList> users;

    public AddGoalTeamAdapter(Context context, ArrayList<AddGoalTeamList> users) {
        super(context, 0, users);
        this.context = context;
        this.users = users;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.add_goal_team_list, parent, false);
        }

        AddGoalTeamList currentUser = users.get(position);

        ImageView imageView = convertView.findViewById(R.id.add_goal_teamL_imageView);
        TextView textView = convertView.findViewById(R.id.add_goal_teamL_name);
        CheckBox checkBox = convertView.findViewById(R.id.add_goal_teamL_CheckBox);

        imageView.setImageResource(currentUser.getImageResId());
        textView.setText(currentUser.getName());
        checkBox.setChecked(currentUser.isChecked());

        checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            currentUser.setChecked(isChecked);
        });

        return convertView;
    }

}
