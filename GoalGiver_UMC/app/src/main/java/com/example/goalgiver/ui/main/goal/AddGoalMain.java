package com.example.goalgiver.ui.main.goal;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.goalgiver.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.Calendar;

public class AddGoalMain extends AppCompatActivity {
    private int dailyInterval = 1;
    private String repeatType = "매일";

    private DatePickerDialog.OnDateSetListener startDateCallbackMethod;
    private DatePickerDialog.OnDateSetListener endDateCallbackMethod;

    private static final int REPEAT_REQUEST = 1;
    private static final int DONATION_REQUEST = 2;
    private static final int START_TIME_REQUEST = 3;
    private static final int END_TIME_REQUEST = 4;
    private static final int START_DATE_REQUEST = 5;
    private static final int END_DATE_REQUEST = 6;

    private TextView startDate_Ptv;
    private TextView endDate_Ptv;
    private TextView repeatRecordTv;
    private TextView donationRecordTv;
    private TextView startTimeTextView;
    private TextView endTimeTextView;
    private ImageButton startDate_Calender_Pbtn;
    private ImageButton endDate_Calender_Pbtn;
    private ImageButton startTimePickerButton;
    private ImageButton endTimePickerButton;
    private LinearLayout goalAddTeamTimeText;
    private LinearLayout goalAddTeamTimeCalender;
    private LinearLayout goalAddDateText;
    private LinearLayout goalAddDateCalender;
    private LinearLayout goalAddTeam;
    private LinearLayout target_lacation_L;
    private Calendar startDate;

    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.goal_add);

        Button teamButton, certificationImage_Pbt, certificationLocation_Pbt, certificationTeam_bt, personal_bt, repeatChoose_btn, timeAttack_bt, goalSuccess_bt, donation_choose_btn;

        EditText add_goal_choose_emotion = findViewById(R.id.add_goal_choose_emotion);
        add_goal_choose_emotion.setFilters(new InputFilter[]{new CustomInputFilter()});

        personal_bt = findViewById(R.id.goal_add_team_personal);
        teamButton = findViewById(R.id.goal_add_team_team);

        timeAttack_bt = findViewById(R.id.goal_add_team_TimeAttack);
        goalSuccess_bt = findViewById(R.id.goal_add_team_GoalSuccess);
        donation_choose_btn = findViewById(R.id.goal_add_donation_btn);

        goalAddTeam = findViewById(R.id.add_goal_team);

        goalAddTeamTimeText = findViewById(R.id.goal_add_team_time_text);
        goalAddTeamTimeCalender = findViewById(R.id.goal_add_team_calender);
        goalAddDateText = findViewById(R.id.goal_add_date_text);
        goalAddDateCalender = findViewById(R.id.goal_add_calender);

        startDate_Calender_Pbtn = findViewById(R.id.goal_add_Startdate_calender);
        endDate_Calender_Pbtn = findViewById(R.id.goal_add_EndDate_calender);
        startDate_Ptv = findViewById(R.id.goal_add_StartDate_date);
        endDate_Ptv = findViewById(R.id.goal_add_enddate_date);
        donationRecordTv = findViewById(R.id.goal_add_donation_tv2);

        repeatChoose_btn = findViewById(R.id.add_goal_repeat_btn);
        repeatRecordTv = findViewById(R.id.add_goal_repeat_record);

        startTimePickerButton = findViewById(R.id.goal_add_team_Starttime_calender);
        startTimeTextView = findViewById(R.id.goal_add_team_Starttime_date);
        endTimePickerButton = findViewById(R.id.goal_add_team_Endtime_calender);
        endTimeTextView = findViewById(R.id.goal_add_team_endtime_date);

        Calendar cal = Calendar.getInstance();
        startDate = Calendar.getInstance();
        startDate_Ptv.setText(cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1) + "-" + cal.get(Calendar.DATE));
        endDate_Ptv.setText(cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1) + "-" + cal.get(Calendar.DATE));

        String amPm = (cal.get(Calendar.AM_PM) == Calendar.AM) ? "오전" : "오후";
        startTimeTextView.setText(String.format("%s %02d:%02d", amPm, cal.get(Calendar.HOUR), cal.get(Calendar.MINUTE)));
        endTimeTextView.setText(String.format("%s %02d:%02d", amPm, cal.get(Calendar.HOUR), cal.get(Calendar.MINUTE)));

        certificationImage_Pbt = findViewById(R.id.goal_add_certification_camera);
        certificationLocation_Pbt = findViewById(R.id.goal_add_certification_location);
        target_lacation_L = findViewById(R.id.goal_add_target_location);
        certificationTeam_bt = findViewById(R.id.goal_add_team_certification_team);

        ImageView backButton = findViewById(R.id.add_goal_backButton);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        personal();
        imageCertificationP();

        personal_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                personal();
            }
        });

        teamButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setGoalSuccess();
                Button certificationTeam_bt = findViewById(R.id.goal_add_team_certification_team);

                goalAddTeam.setVisibility(View.VISIBLE);
                certificationTeam_bt.setVisibility(View.VISIBLE);

                teamButton.setBackgroundResource(R.drawable.circle_choose_btn);
                teamButton.setTextColor(Color.parseColor("#FFFFFF"));

                personal_bt.setBackgroundResource(R.drawable.circle_btn);
                personal_bt.setTextColor(Color.parseColor("#0E9AFF"));
            }
        });

        repeatChoose_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showRepeatBottomSheet();
            }
        });

        startDate_Calender_Pbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog(START_DATE_REQUEST);
            }
        });

        endDate_Calender_Pbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog(END_DATE_REQUEST);
            }
        });

        startTimePickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddGoalMain.this, AddGoalStartTimeSpinner.class);
                startActivityForResult(intent, START_TIME_REQUEST);
            }
        });

        endTimePickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddGoalMain.this, AddGoalFinishTimeSpinner.class);
                startActivityForResult(intent, END_TIME_REQUEST);
            }
        });

        timeAttack_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setTimeAttack();
                startDate = Calendar.getInstance();
                startDate_Ptv.setText(cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1) + "-" + cal.get(Calendar.DATE));
                endDate_Ptv.setText(cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1) + "-" + cal.get(Calendar.DATE));
            }
        });

        goalSuccess_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setGoalSuccess();
                String amPm = (cal.get(Calendar.AM_PM) == Calendar.AM) ? "오전" : "오후";
                startTimeTextView.setText(String.format("%s %02d:%02d", amPm, cal.get(Calendar.HOUR), cal.get(Calendar.MINUTE)));
                endTimeTextView.setText(String.format("%s %02d:%02d", amPm, cal.get(Calendar.HOUR), cal.get(Calendar.MINUTE)));
            }
        });

        certificationImage_Pbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageCertificationP();
            }
        });

        certificationLocation_Pbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                locationCertificationP();
            }
        });

        certificationTeam_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                teamCertification();
            }
        });

        donation_choose_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDonationBottomSheet();
            }
        });

        initializeListener();
    }

    private void showDatePickerDialog(int requestCode) {
        Intent intent = new Intent(AddGoalMain.this, datePickerActivity.class);
        if (requestCode == END_DATE_REQUEST) {
            intent.putExtra("minDate", startDate.getTimeInMillis());
        }
        startActivityForResult(intent, requestCode);
    }

    private void showDonationBottomSheet() {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        View bottomSheetView = LayoutInflater.from(this).inflate(R.layout.add_goal_donation, null);

        RadioButton radioUnicef = bottomSheetView.findViewById(R.id.radioUnicef);
        RadioButton radioSaveTheChildren = bottomSheetView.findViewById(R.id.radioSaveTheChildren);
        RadioButton radioPlanInternational = bottomSheetView.findViewById(R.id.radioPlanInternational);
        RadioButton radioGoodNeighbors = bottomSheetView.findViewById(R.id.radioGoodNeighbors);
        RadioButton radioPurnuFoundation = bottomSheetView.findViewById(R.id.radioPurnuFoundation);
        RadioButton radioChildFund = bottomSheetView.findViewById(R.id.radioChildFund);

        View.OnClickListener radioButtonClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearRadioButtons(bottomSheetView);
                ((RadioButton) view).setChecked(true);
            }
        };

        bottomSheetView.findViewById(R.id.add_goal_repeat_cencel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialog.dismiss();
            }
        });

        radioUnicef.setOnClickListener(radioButtonClickListener);
        radioSaveTheChildren.setOnClickListener(radioButtonClickListener);
        radioPlanInternational.setOnClickListener(radioButtonClickListener);
        radioGoodNeighbors.setOnClickListener(radioButtonClickListener);
        radioPurnuFoundation.setOnClickListener(radioButtonClickListener);
        radioChildFund.setOnClickListener(radioButtonClickListener);

        bottomSheetView.findViewById(R.id.add_goal_repeat_choose_complete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selectedOrganization = getSelectedOrganization(bottomSheetView);
                donationRecordTv.setText(selectedOrganization);
                bottomSheetDialog.dismiss();
            }
        });

        bottomSheetDialog.setContentView(bottomSheetView);
        bottomSheetDialog.show();
    }

    private void clearRadioButtons(View bottomSheetView) {
        RadioButton radioUnicef = bottomSheetView.findViewById(R.id.radioUnicef);
        RadioButton radioSaveTheChildren = bottomSheetView.findViewById(R.id.radioSaveTheChildren);
        RadioButton radioPlanInternational = bottomSheetView.findViewById(R.id.radioPlanInternational);
        RadioButton radioGoodNeighbors = bottomSheetView.findViewById(R.id.radioGoodNeighbors);
        RadioButton radioPurnuFoundation = bottomSheetView.findViewById(R.id.radioPurnuFoundation);
        RadioButton radioChildFund = bottomSheetView.findViewById(R.id.radioChildFund);

        radioUnicef.setChecked(false);
        radioSaveTheChildren.setChecked(false);
        radioPlanInternational.setChecked(false);
        radioGoodNeighbors.setChecked(false);
        radioPurnuFoundation.setChecked(false);
        radioChildFund.setChecked(false);
    }

    private String getSelectedOrganization(View bottomSheetView) {
        RadioButton radioUnicef = bottomSheetView.findViewById(R.id.radioUnicef);
        RadioButton radioSaveTheChildren = bottomSheetView.findViewById(R.id.radioSaveTheChildren);
        RadioButton radioPlanInternational = bottomSheetView.findViewById(R.id.radioPlanInternational);
        RadioButton radioGoodNeighbors = bottomSheetView.findViewById(R.id.radioGoodNeighbors);
        RadioButton radioPurnuFoundation = bottomSheetView.findViewById(R.id.radioPurnuFoundation);
        RadioButton radioChildFund = bottomSheetView.findViewById(R.id.radioChildFund);

        if (radioUnicef.isChecked()) return "UNICEF";
        if (radioSaveTheChildren.isChecked()) return "Save the Children";
        if (radioPlanInternational.isChecked()) return "Plan International";
        if (radioGoodNeighbors.isChecked()) return "Good Neighbors";
        if (radioPurnuFoundation.isChecked()) return "Purme Foundation";
        if (radioChildFund.isChecked()) return "Green Umbrella";
        return "";
    }

    private void personal() {
        imageCertificationP();
        Button personal_bt = findViewById(R.id.goal_add_team_personal);
        Button team_bt = findViewById(R.id.goal_add_team_team);
        Button certificationTeam_bt = findViewById(R.id.goal_add_team_certification_team);

        goalAddTeam.setVisibility(View.GONE);
        certificationTeam_bt.setVisibility(View.GONE);
        goalAddTeamTimeText.setVisibility(View.GONE);
        goalAddTeamTimeCalender.setVisibility(View.GONE);

        goalAddDateText.setVisibility(View.VISIBLE);
        goalAddDateCalender.setVisibility(View.VISIBLE);

        personal_bt.setBackgroundResource(R.drawable.circle_choose_btn);
        personal_bt.setTextColor(Color.parseColor("#FFFFFF"));

        team_bt.setBackgroundResource(R.drawable.circle_btn);
        team_bt.setTextColor(Color.parseColor("#0E9AFF"));
    }

    private void setTimeAttack() {
        goalAddDateText.setVisibility(View.GONE);
        goalAddDateCalender.setVisibility(View.GONE);

        goalAddTeamTimeText.setVisibility(View.VISIBLE);
        goalAddTeamTimeCalender.setVisibility(View.VISIBLE);

        Button timeAttack_bt = findViewById(R.id.goal_add_team_TimeAttack);
        Button goalSuccess_bt = findViewById(R.id.goal_add_team_GoalSuccess);

        timeAttack_bt.setBackgroundResource(R.drawable.circle_choose_btn);
        timeAttack_bt.setTextColor(Color.parseColor("#FFFFFF"));

        goalSuccess_bt.setBackgroundResource(R.drawable.circle_btn);
        goalSuccess_bt.setTextColor(Color.parseColor("#0E9AFF"));
    }

    private void setGoalSuccess() {
        goalAddDateText.setVisibility(View.VISIBLE);
        goalAddDateCalender.setVisibility(View.VISIBLE);

        goalAddTeamTimeText.setVisibility(View.GONE);
        goalAddTeamTimeCalender.setVisibility(View.GONE);

        Button timeAttack_bt = findViewById(R.id.goal_add_team_TimeAttack);
        Button goalSuccess_bt = findViewById(R.id.goal_add_team_GoalSuccess);

        timeAttack_bt.setBackgroundResource(R.drawable.circle_btn);
        timeAttack_bt.setTextColor(Color.parseColor("#0E9AFF"));

        goalSuccess_bt.setBackgroundResource(R.drawable.circle_choose_btn);
        goalSuccess_bt.setTextColor(Color.parseColor("#FFFFFF"));
    }

    private void imageCertificationP() {
        Button certificationImage_Pbt = findViewById(R.id.goal_add_certification_camera);
        Button certificationLocation_Pbt = findViewById(R.id.goal_add_certification_location);
        Button certificationTeam_bt = findViewById(R.id.goal_add_team_certification_team);

        certificationImage_Pbt.setBackgroundResource(R.drawable.circle_certification_choose);
        certificationLocation_Pbt.setBackgroundResource(R.drawable.recode_btn);
        certificationTeam_bt.setBackgroundResource(R.drawable.recode_btn);
        target_lacation_L.setVisibility(View.GONE);
    }

    private void locationCertificationP() {
        Button certificationImage_Pbt = findViewById(R.id.goal_add_certification_camera);
        Button certificationLocation_Pbt = findViewById(R.id.goal_add_certification_location);
        Button certificationTeam_bt = findViewById(R.id.goal_add_team_certification_team);

        certificationImage_Pbt.setBackgroundResource(R.drawable.recode_btn);
        certificationLocation_Pbt.setBackgroundResource(R.drawable.circle_certification_choose);
        certificationTeam_bt.setBackgroundResource(R.drawable.recode_btn);
        target_lacation_L.setVisibility(View.VISIBLE);
    }

    private void teamCertification() {
        Button certificationImage_bt = findViewById(R.id.goal_add_certification_camera);
        Button certificationLocation_bt = findViewById(R.id.goal_add_certification_location);
        Button certificationTeam_bt = findViewById(R.id.goal_add_team_certification_team);

        certificationImage_bt.setBackgroundResource(R.drawable.recode_btn);
        certificationLocation_bt.setBackgroundResource(R.drawable.recode_btn);
        certificationTeam_bt.setBackgroundResource(R.drawable.circle_certification_choose);
        target_lacation_L.setVisibility(View.GONE);
    }

    public void initializeListener() {
        startDateCallbackMethod = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int yyy, int mmm, int ddd) {
                startDate_Ptv.setText(yyy + "-" + (mmm + 1) + "-" + ddd);
                startDate.set(yyy, mmm, ddd);
            }
        };

        endDateCallbackMethod = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int yyy, int mmm, int ddd) {
                endDate_Ptv.setText(yyy + "-" + (mmm + 1) + "-" + ddd);
            }
        };
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == START_DATE_REQUEST && resultCode == RESULT_OK && data != null) {
            int year = data.getIntExtra("mYear", Calendar.getInstance().get(Calendar.YEAR));
            int month = data.getIntExtra("mMonth", Calendar.getInstance().get(Calendar.MONTH));
            int day = data.getIntExtra("mDay", Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
            startDate_Ptv.setText(year + "-" + (month + 1) + "-" + day);
            startDate.set(year, month, day);
        } else if (requestCode == END_DATE_REQUEST && resultCode == RESULT_OK && data != null) {
            int year = data.getIntExtra("mYear", Calendar.getInstance().get(Calendar.YEAR));
            int month = data.getIntExtra("mMonth", Calendar.getInstance().get(Calendar.MONTH));
            int day = data.getIntExtra("mDay", Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
            endDate_Ptv.setText(year + "-" + (month + 1) + "-" + day);
        } else if (requestCode == START_TIME_REQUEST && resultCode == RESULT_OK && data != null) {
            String selectedStartTime = data.getStringExtra("selectedStartTime");
            startTimeTextView.setText(selectedStartTime);
        } else if (requestCode == END_TIME_REQUEST && resultCode == RESULT_OK && data != null) {
            String selectedEndTime = data.getStringExtra("selectedEndTime");
            endTimeTextView.setText(selectedEndTime);
        }
    }


    private void showRepeatBottomSheet() {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        View bottomSheetView = LayoutInflater.from(this).inflate(R.layout.add_goal_repeat, null);

        Button dayButton = bottomSheetView.findViewById(R.id.add_goal_repeat_day_BT);
        Button weekButton = bottomSheetView.findViewById(R.id.add_goal_repeat_week_BT);
        Button monthButton = bottomSheetView.findViewById(R.id.add_goal_repeat_month_BT);

        LinearLayout addGoalRepeatDay = bottomSheetView.findViewById(R.id.add_goal_repeat_day);
        LinearLayout addGoalRepeatWeek = bottomSheetView.findViewById(R.id.add_goal_repeat_week);
        LinearLayout addGoalRepeatMonth = bottomSheetView.findViewById(R.id.add_goal_repeat_month);

        // 초기화
        String[] repeatType = {""};
        String[] selectedDays = {""};
        int[] dailyInterval = {1};
        int[] selectedDayOfMonth = {1};

        dayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                repeatType[0] = "매일";
                addGoalRepeatMonth.setVisibility(View.GONE);
                addGoalRepeatWeek.setVisibility(View.GONE);
                addGoalRepeatDay.setVisibility(View.VISIBLE);

                setButtonStyles(bottomSheetView, R.id.add_goal_repeat_day_BT, R.id.add_goal_repeat_week_BT, R.id.add_goal_repeat_month_BT);
            }
        });

        ToggleButton[] weekButtons = new ToggleButton[]{
                bottomSheetView.findViewById(R.id.add_goal_repeat_button_sun),
                bottomSheetView.findViewById(R.id.add_goal_repeat_button_mon),
                bottomSheetView.findViewById(R.id.add_goal_repeat_button_tue),
                bottomSheetView.findViewById(R.id.add_goal_repeat_button_wed),
                bottomSheetView.findViewById(R.id.add_goal_repeat_button_thu),
                bottomSheetView.findViewById(R.id.add_goal_repeat_button_fri),
                bottomSheetView.findViewById(R.id.add_goal_repeat_button_sat)
        };

        String[] dayNames = {"일", "월", "화", "수", "목", "금", "토"};

        weekButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                repeatType[0] = "매주";
                addGoalRepeatMonth.setVisibility(View.GONE);
                addGoalRepeatDay.setVisibility(View.GONE);
                addGoalRepeatWeek.setVisibility(View.VISIBLE);

                setButtonStyles(bottomSheetView, R.id.add_goal_repeat_week_BT, R.id.add_goal_repeat_day_BT, R.id.add_goal_repeat_month_BT);
            }
        });

        monthButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                repeatType[0] = "매월";
                addGoalRepeatDay.setVisibility(View.GONE);
                addGoalRepeatWeek.setVisibility(View.GONE);
                addGoalRepeatMonth.setVisibility(View.VISIBLE);

                setButtonStyles(bottomSheetView, R.id.add_goal_repeat_month_BT, R.id.add_goal_repeat_day_BT, R.id.add_goal_repeat_week_BT);
            }
        });

        // Spinner 설정
        Spinner daySpinner = bottomSheetView.findViewById(R.id.add_goal_repeat_text_spinner_days);
        Integer[] days = new Integer[31];
        for (int i = 0; i < 31; i++) {
            days[i] = i + 1; // 1부터 31까지의 값
        }
        ArrayAdapter<Integer> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, days);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        daySpinner.setAdapter(adapter);

        // Increase/Decrease 버튼과 TextView 초기화 및 연결
        Button increaseButton = bottomSheetView.findViewById(R.id.add_goal_repeat_button_increase);
        Button decreaseButton = bottomSheetView.findViewById(R.id.add_goal_repeat_button_decrease);
        TextView quantityTextView = bottomSheetView.findViewById(R.id.add_goal_repeat_text_quantity);

        // 수량 초기값
        int[] quantity = {1};

        // Increase 버튼 클릭 시
        increaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quantity[0]++;
                quantityTextView.setText(String.valueOf(quantity[0]));
            }
        });

        // Decrease 버튼 클릭 시
        decreaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (quantity[0] > 1) {
                    quantity[0]--;
                    quantityTextView.setText(String.valueOf(quantity[0]));
                }
            }
        });

        Button repeat_completeButton = bottomSheetView.findViewById(R.id.add_goal_repeat_choose_complete);
        repeat_completeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedDayOfMonth[0] = (Integer) daySpinner.getSelectedItem();

                if (repeatType[0].equals("매주")) {
                    StringBuilder selectedWeekDays = new StringBuilder();
                    for (int i = 0; i < weekButtons.length; i++) {
                        if (weekButtons[i].isChecked()) {
                            if (selectedWeekDays.length() > 0) {
                                selectedWeekDays.append(", ");
                            }
                            selectedWeekDays.append(dayNames[i]);
                        }
                    }
                    repeatRecordTv.setText("매주 " + selectedWeekDays.toString());
                } else if (repeatType[0].equals("매일")) {
                    repeatRecordTv.setText(quantity[0] + "일 마다");
                } else if (repeatType[0].equals("매월")) {
                    repeatRecordTv.setText(repeatType[0] + " " + selectedDayOfMonth[0] + "일");
                }
                bottomSheetDialog.dismiss();
            }
        });

        Button cancelButton = bottomSheetView.findViewById(R.id.add_goal_repeat_cencel);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialog.dismiss();
            }
        });

        dayButton.performClick();

        bottomSheetDialog.setContentView(bottomSheetView);
        bottomSheetDialog.show();
    }



    private String getSelectedDays(View bottomSheetView) {
        StringBuilder days = new StringBuilder();

        ToggleButton[] buttons = {
                bottomSheetView.findViewById(R.id.add_goal_repeat_button_sun),
                bottomSheetView.findViewById(R.id.add_goal_repeat_button_mon),
                bottomSheetView.findViewById(R.id.add_goal_repeat_button_tue),
                bottomSheetView.findViewById(R.id.add_goal_repeat_button_wed),
                bottomSheetView.findViewById(R.id.add_goal_repeat_button_thu),
                bottomSheetView.findViewById(R.id.add_goal_repeat_button_fri),
                bottomSheetView.findViewById(R.id.add_goal_repeat_button_sat)
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

    private void setButtonStyles(View bottomSheetView, int selectedButtonId, int... otherButtonIds) {
        Button selectedButton = bottomSheetView.findViewById(selectedButtonId);
        selectedButton.setBackgroundResource(R.drawable.add_goal_repeat_plus);
        selectedButton.setTextColor(Color.parseColor("#0E9AFF"));

        for (int buttonId : otherButtonIds) {
            Button button = bottomSheetView.findViewById(buttonId);
            button.setBackgroundResource(R.drawable.add_goal_repeat_sub);
            button.setTextColor(Color.BLACK);
        }
    }
}
