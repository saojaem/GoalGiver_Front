package com.example.goalgiver.ui.main.goal;

import android.content.Intent;
import android.view.View;

import com.example.goalgiver.R;

import java.util.Locale;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.goalgiver.R;
import java.util.Locale;
public class AddGoalFinishTimeSpinner extends AppCompatActivity {
    private TimePicker startTimePicker;
    private Button st_cancelButton;
    private Button st_completeButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_goal_finish_time_spinner);

        startTimePicker = findViewById(R.id.finish_timePicker);
        st_cancelButton = findViewById(R.id.add_goal_finish_cencel);
        st_completeButton = findViewById(R.id.add_goal_finish_choose_complete);

        // 24시간 형식 사용 안함
        startTimePicker.setIs24HourView(false);

        st_cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        st_completeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int hour = startTimePicker.getHour();
                int minute = startTimePicker.getMinute();
                String time = formatTime(hour, minute);

                Intent resultIntent = new Intent();
                resultIntent.putExtra("selectedEndTime", time);
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });
    }

    private String formatTime(int hour, int minute) {
        String period = "오전";
        if (hour >= 12) {
            period = "오후";
            if (hour > 12) {
                hour -= 12;
            }
        }
        if (hour == 0) {
            hour = 12;
        }
        return String.format(Locale.KOREA, "%s %d:%02d", period, hour, minute);
    }
}
