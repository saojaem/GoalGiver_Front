package com.example.goalgiver.ui.main.goal;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.goalgiver.R;

public class AddGoalRepeat extends AppCompatActivity {
    private LinearLayout addGoalRepeatDay;
    private LinearLayout addGoalRepeatWeek;
    private LinearLayout addGoalRepeatMonth;
    private String repeatType = "매일";  // 기본값을 매일로 설정
    private String selectedDays = "";   // 선택된 요일을 저장
    private int dailyInterval = 1;      // 매일 반복 간격
    private int selectedDayOfMonth = 1; // 매월 선택된 날짜

    private TextView dailyIntervalTextView;
    private Spinner monthDaySpinner;

    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.add_goal_repeat);

        Button goalAddRepeatEverydayBtn = findViewById(R.id.add_goal_repeat_day_BT);
        Button goalAddRepeatEveryweekBtn = findViewById(R.id.add_goal_repeat_week_BT);
        Button goalAddRepeatEverymonthBtn = findViewById(R.id.add_goal_repeat_month_BT);
        Button goalAddRepeatChooseCompleteBtn = findViewById(R.id.add_goal_repeat_choose_complete);
        Button goalAddRepeatCancelBtn = findViewById(R.id.add_goal_repeat_cencel);
        Button decreaseButton = findViewById(R.id.add_goal_repeat_button_decrease);
        Button increaseButton = findViewById(R.id.add_goal_repeat_button_increase);

        dailyIntervalTextView = findViewById(R.id.add_goal_repeat_text_quantity);
        monthDaySpinner = findViewById(R.id.add_goal_repeat_text_spinner_days);

        // 1부터 31까지의 값을 설정하는 어댑터
        ArrayAdapter<Integer> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, getDaysArray());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        monthDaySpinner.setAdapter(adapter);

        addGoalRepeatDay = findViewById(R.id.add_goal_repeat_day);
        addGoalRepeatWeek = findViewById(R.id.add_goal_repeat_week);
        addGoalRepeatMonth = findViewById(R.id.add_goal_repeat_month);

        goalAddRepeatEveryday();

        goalAddRepeatEverydayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goalAddRepeatEveryday();
            }
        });

        goalAddRepeatEveryweekBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goalAddRepeatEveryweek();
            }
        });

        goalAddRepeatEverymonthBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goalAddRepeatEverymonth();
            }
        });

        goalAddRepeatChooseCompleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (repeatType.equals("매주")) {
                    selectedDays = getSelectedDays();
                }
                // Spinner에서 선택된 값 가져오기
                selectedDayOfMonth = (int) monthDaySpinner.getSelectedItem();

                Intent resultIntent = new Intent();
                resultIntent.putExtra("repeatType", repeatType);
                resultIntent.putExtra("selectedDays", selectedDays);
                resultIntent.putExtra("dailyInterval", dailyInterval);
                resultIntent.putExtra("selectedDayOfMonth", selectedDayOfMonth);
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });

        goalAddRepeatCancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });

        decreaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (dailyInterval > 1) {
                    dailyInterval--;
                    dailyIntervalTextView.setText(String.valueOf(dailyInterval));
                }
            }
        });

        increaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dailyInterval++;
                dailyIntervalTextView.setText(String.valueOf(dailyInterval));
            }
        });
    }

    private void goalAddRepeatEveryday() {
        repeatType = "매일";
        selectedDays = "";
        addGoalRepeatMonth.setVisibility(View.GONE);
        addGoalRepeatWeek.setVisibility(View.GONE);
        addGoalRepeatDay.setVisibility(View.VISIBLE);

        setButtonStyles(R.id.add_goal_repeat_day_BT, R.id.add_goal_repeat_week_BT, R.id.add_goal_repeat_month_BT);
    }

    private void goalAddRepeatEveryweek() {
        repeatType = "매주";
        selectedDays = "";
        addGoalRepeatMonth.setVisibility(View.GONE);
        addGoalRepeatWeek.setVisibility(View.VISIBLE);
        addGoalRepeatDay.setVisibility(View.GONE);

        setButtonStyles(R.id.add_goal_repeat_week_BT, R.id.add_goal_repeat_day_BT, R.id.add_goal_repeat_month_BT);
    }

    private void goalAddRepeatEverymonth() {
        repeatType = "매월";
        selectedDays = "";
        addGoalRepeatMonth.setVisibility(View.VISIBLE);
        addGoalRepeatWeek.setVisibility(View.GONE);
        addGoalRepeatDay.setVisibility(View.GONE);

        setButtonStyles(R.id.add_goal_repeat_month_BT, R.id.add_goal_repeat_day_BT, R.id.add_goal_repeat_week_BT);
    }

    private void setButtonStyles(int selectedButtonId, int... otherButtonIds) {
        Button selectedButton = findViewById(selectedButtonId);
        selectedButton.setBackgroundResource(R.drawable.add_goal_repeat_plus);
        selectedButton.setTextColor(Color.parseColor("#0E9AFF"));

        for (int buttonId : otherButtonIds) {
            Button button = findViewById(buttonId);
            button.setBackgroundResource(R.drawable.add_goal_repeat_sub);
            button.setTextColor(Color.BLACK);
        }
    }

    private String getSelectedDays() {
        StringBuilder days = new StringBuilder();

        ToggleButton[] buttons = {
                findViewById(R.id.add_goal_repeat_button_sun),
                findViewById(R.id.add_goal_repeat_button_mon),
                findViewById(R.id.add_goal_repeat_button_tue),
                findViewById(R.id.add_goal_repeat_button_wed),
                findViewById(R.id.add_goal_repeat_button_thu),
                findViewById(R.id.add_goal_repeat_button_fri),
                findViewById(R.id.add_goal_repeat_button_sat)
        };

        String[] dayNames = {"일", "월", "화", "수", "목", "금", "토"};

        for (int i = 0; i < buttons.length; i++) {
            if (buttons[i].isChecked()) {
                if (days.length() > 0) {
                    days.append(", ");
                }
                days.append(dayNames[i]);
            }
        }

        return days.toString();
    }

    private Integer[] getDaysArray() {
        Integer[] days = new Integer[31];
        for (int i = 0; i < 31; i++) {
            days[i] = i + 1;
        }
        return days;
    }
}
