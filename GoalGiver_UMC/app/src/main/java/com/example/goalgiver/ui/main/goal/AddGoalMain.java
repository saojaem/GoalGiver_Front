package com.example.goalgiver.ui.main.goal;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputFilter;
import android.util.Log;
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
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.goalgiver.R;
import com.example.goalgiver.ui.main.people.FriendItem;
import com.example.goalgiver.ui.main.people.PeopleFragment;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class AddGoalMain extends AppCompatActivity {
    private static final int TEAM_CHOOSE_REQUEST = 7;


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
    private static int timeattackCheck = 0;
    private int certificateCheck = 0;
    private int personTeam = 0;

    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.goal_add);

        Button teamButton, certificationImage_Pbt, certificationLocation_Pbt, certificationTeam_bt, personal_bt, repeatChoose_btn, timeAttack_bt, goalSuccess_bt, donation_choose_btn;
        Button addGoalButton = findViewById(R.id.goal_add_decide_btn);

        EditText add_goal_choose_emotion = findViewById(R.id.add_goal_choose_emotion);
        add_goal_choose_emotion.setFilters(new InputFilter[]{new CustomInputFilter()});

        personal_bt = findViewById(R.id.goal_add_team_personal);
        teamButton = findViewById(R.id.goal_add_team_team);

        timeAttack_bt = findViewById(R.id.goal_add_team_TimeAttack);
        goalSuccess_bt = findViewById(R.id.goal_add_team_GoalSuccess);
        donation_choose_btn = findViewById(R.id.goal_add_donation_btn);

        Button add_goal_team_choose_bt = (Button) findViewById(R.id.goal_add_team_choose_BT);

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
        startDate_Ptv.setText(cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH)+1) + "-" + cal.get(Calendar.DATE));
        endDate_Ptv.setText(cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH)+1) + "-" + cal.get(Calendar.DATE));

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
                timeattackCheck=0;
            }
        });

        teamButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setGoalSuccess();
                personTeam = 2;
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
                showCalendarBottomSheet();
            }
        });

        endDate_Calender_Pbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCalendarBottomSheet_end();
            }
        });

        startTimePickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showStartTimePickerBottomSheet();
            }
        });

        endTimePickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showEndTimePickerBottomSheet();
            }
        });

        timeAttack_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timeattackCheck = 1;
                setTimeAttack();
                startDate = Calendar.getInstance();
                startDate_Ptv.setText(cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH)) + "-" + cal.get(Calendar.DATE));
                endDate_Ptv.setText(cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH)) + "-" + cal.get(Calendar.DATE));
            }
        });

        goalSuccess_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timeattackCheck = 0;
                setGoalSuccess();
                String amPm = (cal.get(Calendar.AM_PM) == Calendar.AM) ? "오전" : "오후";
                startTimeTextView.setText(String.format("%s %02d:%02d", amPm, cal.get(Calendar.HOUR), cal.get(Calendar.MINUTE)));
                endTimeTextView.setText(String.format("%s %02d:%02d", amPm, cal.get(Calendar.HOUR), cal.get(Calendar.MINUTE)));
            }
        });

        add_goal_team_choose_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),AddGoalTeamChoose.class);
                startActivityForResult(intent, TEAM_CHOOSE_REQUEST);
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

        addGoalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 데이터 수집
                String goalTitle = ((EditText) findViewById(R.id.set_goal_tv)).getText().toString();
                String emotion = ((EditText) findViewById(R.id.add_goal_choose_emotion)).getText().toString();

                Log.d("AddGoalMain", "goalTitle=" + goalTitle + ", emotion=" + emotion);

                String startDate = startDate_Ptv.getText().toString();
                String endDate = endDate_Ptv.getText().toString();
                String startTime = startTimeTextView.getText().toString();
                String endTime = endTimeTextView.getText().toString();
                String repeat = repeatRecordTv.getText().toString();
                String donation = donationRecordTv.getText().toString();
                String donationAmount = ((EditText) findViewById(R.id.goal_add_donationP_tv2)).getText().toString();

                GoalSetItem goalItem;

                if(timeattackCheck==1){
                    goalItem = new GoalSetItem(emotion, goalTitle, "⏰"+formatTimeRemaining(calculateTimeRemaining(endTime)), donationAmount, "Progress 0%", 0,startDate,endDate,repeat,certificateCheck, personTeam, donation, 0);
                } else if (calculateDaysRemaining(startDate)>0) {
                    goalItem = new GoalSetItem(emotion, goalTitle, "시작전", donationAmount, "Progress 0%", 0,startDate,endDate,repeat,certificateCheck, personTeam, donation, 0);
                } else{
                    goalItem = new GoalSetItem(emotion, goalTitle, "D-"+calculateDaysRemaining(endDate), donationAmount, "Progress 0%", 0,startDate,endDate,repeat,certificateCheck, personTeam, donation, 0);
                }
                // 데이터를 전달할 Intent 생성
                Intent resultIntent = new Intent();
                if(emotion == null){
                    emotion = "🎯";
                }
                else{
                    resultIntent.putExtra("emotion", emotion);
                }

                resultIntent.putExtra("goalTitle", goalTitle);
                resultIntent.putExtra("startDate", startDate);
                resultIntent.putExtra("endDate", endDate);
                resultIntent.putExtra("startTime", startTime);
                resultIntent.putExtra("endTime", endTime);
                resultIntent.putExtra("repeat", repeat);
                resultIntent.putExtra("donation", donation);
                resultIntent.putExtra("donationAmount", donationAmount);
                resultIntent.putExtra("goalItem", goalItem);

                Log.d("AddGoalMain", "Sending Data: goalTitle=" + goalTitle + ", emotion=" + emotion);
                // 결과 설정 및 액티비티 종료
                setResult(Activity.RESULT_OK, resultIntent);

                finish();
            }
        });


    }
    //오늘부터 종료날짜 계산하는 코드
    private int calculateDaysRemaining(String endDateStr) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            Date endDate = sdf.parse(endDateStr);

            Calendar today = Calendar.getInstance();
            today.set(Calendar.HOUR_OF_DAY, 0);
            today.set(Calendar.MINUTE, 0);
            today.set(Calendar.SECOND, 0);
            today.set(Calendar.MILLISECOND, 0);

            long diffInMillis = endDate.getTime() - today.getTimeInMillis();
            return (int) (diffInMillis / (1000 * 60 * 60 * 24));
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    private String formatTimeRemaining(long diffInMillis) {
        long hours = diffInMillis / (1000 * 60 * 60)% 24;
        long minutes = (diffInMillis / (1000 * 60)) % 60;
        long seconds = (diffInMillis / 1000) % 60;

        return String.format(Locale.getDefault(), "%02d:%02d:%02d", hours, minutes, seconds);
    }

    // endTimeTextView에서 설정한 시간부터 현재 시간까지 경과된 시간을 계산하는 코드
    private long calculateTimeRemaining(String endTimeStr) {
        try {
            // 현재 시간
            Calendar now = Calendar.getInstance();

            // 설정된 종료 시간을 Calendar 객체로 변환
            SimpleDateFormat timeFormat = new SimpleDateFormat("a hh:mm", Locale.KOREAN);
            Date endTime = timeFormat.parse(endTimeStr);

            Calendar endTimeCal = Calendar.getInstance();
            endTimeCal.setTime(endTime);
            endTimeCal.set(Calendar.SECOND, 0);
            endTimeCal.set(Calendar.MILLISECOND, 0);

            Log.d("TimeCalculation", "endTimeCal: " + endTimeCal.getTimeInMillis());
            Log.d("TimeCalculation", "now: " + now.getTimeInMillis());
            Log.d("TimeCalculation", "Parsed endTime: " + endTime);

            // 종료 시간이 현재 시간보다 이전일 경우, 다음 날로 간주
            if (endTimeCal.before(now)) {
                endTimeCal.add(Calendar.DAY_OF_MONTH, 1);
            }

            // 차이 계산
            long diffInMillis = now.getTimeInMillis()-endTimeCal.getTimeInMillis()  ;
            Log.d("TimeCalculation", "diffInMillis: " + diffInMillis);
            return Math.max(0, diffInMillis); // 밀리초 단위 경과 시간 반환
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }

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
        personTeam = 1;
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
        certificateCheck = 11;
        Button certificationImage_Pbt = findViewById(R.id.goal_add_certification_camera);
        Button certificationLocation_Pbt = findViewById(R.id.goal_add_certification_location);
        Button certificationTeam_bt = findViewById(R.id.goal_add_team_certification_team);

        certificationImage_Pbt.setBackgroundResource(R.drawable.circle_certification_choose);
        certificationLocation_Pbt.setBackgroundResource(R.drawable.recode_btn);
        certificationTeam_bt.setBackgroundResource(R.drawable.recode_btn);
        target_lacation_L.setVisibility(View.GONE);
    }

    private void locationCertificationP() {
        certificateCheck = 12;
        Button certificationImage_Pbt = findViewById(R.id.goal_add_certification_camera);
        Button certificationLocation_Pbt = findViewById(R.id.goal_add_certification_location);
        Button certificationTeam_bt = findViewById(R.id.goal_add_team_certification_team);

        certificationImage_Pbt.setBackgroundResource(R.drawable.recode_btn);
        certificationLocation_Pbt.setBackgroundResource(R.drawable.circle_certification_choose);
        certificationTeam_bt.setBackgroundResource(R.drawable.recode_btn);
        target_lacation_L.setVisibility(View.VISIBLE);
    }

    private void teamCertification() {
        certificateCheck = 13;
        Button certificationImage_bt = findViewById(R.id.goal_add_certification_camera);
        Button certificationLocation_bt = findViewById(R.id.goal_add_certification_location);
        Button certificationTeam_bt = findViewById(R.id.goal_add_team_certification_team);

        certificationImage_bt.setBackgroundResource(R.drawable.recode_btn);
        certificationLocation_bt.setBackgroundResource(R.drawable.recode_btn);
        certificationTeam_bt.setBackgroundResource(R.drawable.circle_certification_choose);
        target_lacation_L.setVisibility(View.GONE);
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == TEAM_CHOOSE_REQUEST && resultCode == RESULT_OK && data != null) {
            ArrayList<AddGoalTeamList> selectedUsers = data.getParcelableArrayListExtra("selectedUsers");
            updateTeamSelection(selectedUsers);
        }
    }

    private void updateTeamSelection(ArrayList<AddGoalTeamList> selectedUsers) {
        // 선택된 사용자들을 화면에 추가하는 로직 구현
        LinearLayout teamContainer = findViewById(R.id.add_goal_team_container);
        teamContainer.removeAllViews();

        for (AddGoalTeamList user : selectedUsers) {
            View userView = LayoutInflater.from(this).inflate(R.layout.add_goal_team_check_true, teamContainer, false);

            ImageView imageView = userView.findViewById(R.id.add_goal_check_true_imageView);
            TextView textView = userView.findViewById(R.id.add_goal_check_true_name);

            imageView.setImageResource(user.getImageResId());
            textView.setText(user.getName());

            teamContainer.addView(userView);
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

    private void showCalendarBottomSheet_end() {
        // BottomSheetDialog 생성
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);

        // add_goal_calendar.xml 레이아웃 인플레이트
        View bottomSheetView = LayoutInflater.from(this).inflate(R.layout.add_goal_calendar, null);

        // MaterialCalendarView와 버튼 가져오기
        MaterialCalendarView calendarView = bottomSheetView.findViewById(R.id.add_Goal_calendar_view);
        Button cancelBtn = bottomSheetView.findViewById(R.id.add_goal_calendar_cancle_day);
        Button chooseBtn = bottomSheetView.findViewById(R.id.add_goal_calendar_choose_day);



        CalendarDay minDate = CalendarDay.from(startDate.get(Calendar.YEAR), startDate.get(Calendar.MONTH), startDate.get(Calendar.DAY_OF_MONTH));
        calendarView.addDecorator(new PastDateDecorator(minDate));


        calendarView.state().edit().setMinimumDate(minDate).commit();



        // 추가적인 로직 설정, 예를 들어 날짜 선택이나 버튼 클릭 처리
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialog.dismiss(); // 취소 버튼 클릭 시 BottomSheet 닫기
            }
        });

        chooseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 날짜 선택 처리 및 startDate_Ptv에 설정
                CalendarDay selectedDate = calendarView.getSelectedDate();
                if (selectedDate != null) {
                    if (!selectedDate.isBefore(minDate)) {
                        endDate_Ptv.setText(selectedDate.getYear() + "-" + (selectedDate.getMonth()) + "-" + selectedDate.getDay());
                    }
                }
                bottomSheetDialog.dismiss(); // 날짜 선택 후 BottomSheet 닫기
            }
        });

        // 인플레이트된 뷰를 BottomSheet의 내용으로 설정
        bottomSheetDialog.setContentView(bottomSheetView);
        bottomSheetDialog.show();
    }


    private void showCalendarBottomSheet() {
        // BottomSheetDialog 생성
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);

        // add_goal_calendar.xml 레이아웃 인플레이트
        View bottomSheetView = LayoutInflater.from(this).inflate(R.layout.add_goal_calendar, null);

        // MaterialCalendarView와 버튼 가져오기
        MaterialCalendarView calendarView = bottomSheetView.findViewById(R.id.add_Goal_calendar_view);
        Button cancelBtn = bottomSheetView.findViewById(R.id.add_goal_calendar_cancle_day);
        Button chooseBtn = bottomSheetView.findViewById(R.id.add_goal_calendar_choose_day);

        // 추가적인 로직 설정, 예를 들어 날짜 선택이나 버튼 클릭 처리
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialog.dismiss(); // 취소 버튼 클릭 시 BottomSheet 닫기
            }
        });

        chooseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 날짜 선택 처리 및 startDate_Ptv에 설정
                CalendarDay selectedDate = calendarView.getSelectedDate();
                if (selectedDate != null) {
                    startDate_Ptv.setText(selectedDate.getYear() + "-" + (selectedDate.getMonth()) + "-" + selectedDate.getDay());
                    startDate.set(selectedDate.getYear(), selectedDate.getMonth(), selectedDate.getDay());
                }
                bottomSheetDialog.dismiss(); // 날짜 선택 후 BottomSheet 닫기
            }
        });

        // 인플레이트된 뷰를 BottomSheet의 내용으로 설정
        bottomSheetDialog.setContentView(bottomSheetView);
        bottomSheetDialog.show();
    }

    private void showEndTimePickerBottomSheet() {
        // BottomSheetDialog 생성
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);

        // add_goal_finish_time_spinner.xml 레이아웃 인플레이트
        View bottomSheetView = LayoutInflater.from(this).inflate(R.layout.add_goal_finish_time_spinner, null);

        // TimePicker와 버튼 가져오기
        TimePicker timePicker = bottomSheetView.findViewById(R.id.finish_timePicker);
        Button cancelBtn = bottomSheetView.findViewById(R.id.add_goal_finish_cencel);
        Button chooseBtn = bottomSheetView.findViewById(R.id.add_goal_finish_choose_complete);

        // 시간 선택 모드 설정 (12시간 또는 24시간 모드 선택)
        timePicker.setIs24HourView(false);

        // 취소 버튼 클릭 리스너 설정
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialog.dismiss(); // 취소 버튼 클릭 시 BottomSheet 닫기
            }
        });

        // 선택 완료 버튼 클릭 리스너 설정
        chooseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int hour = timePicker.getCurrentHour();
                int minute = timePicker.getCurrentMinute();
                String amPm = (hour >= 12) ? "오후" : "오전";
                if (hour > 12) {
                    hour -= 12;
                }
                String selectedTime = String.format("%s %02d:%02d", amPm, hour, minute);
                endTimeTextView.setText(selectedTime); // 선택된 시간을 TextView에 표시
                bottomSheetDialog.dismiss(); // 시간 선택 후 BottomSheet 닫기
            }
        });

        // 인플레이트된 뷰를 BottomSheet의 내용으로 설정
        bottomSheetDialog.setContentView(bottomSheetView);
        bottomSheetDialog.show();
    }

    private void showStartTimePickerBottomSheet() {
        // BottomSheetDialog 생성
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);

        // add_goal_finish_time_spinner.xml 레이아웃 인플레이트
        View bottomSheetView = LayoutInflater.from(this).inflate(R.layout.add_goal_start_time_spinner, null);

        // TimePicker와 버튼 가져오기
        TimePicker timePicker = bottomSheetView.findViewById(R.id.start_timePicker);
        Button cancelBtn = bottomSheetView.findViewById(R.id.add_goal_start_cencel);
        Button chooseBtn = bottomSheetView.findViewById(R.id.add_goal_start_choose_complete);

        // 시간 선택 모드 설정 (12시간 또는 24시간 모드 선택)
        timePicker.setIs24HourView(false);

        // 취소 버튼 클릭 리스너 설정
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialog.dismiss(); // 취소 버튼 클릭 시 BottomSheet 닫기
            }
        });

        // 선택 완료 버튼 클릭 리스너 설정
        chooseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int hour = timePicker.getCurrentHour();
                int minute = timePicker.getCurrentMinute();
                String amPm = (hour >= 12) ? "오후" : "오전";
                if (hour > 12) {
                    hour -= 12;
                }
                String selectedTime = String.format("%s %02d:%02d", amPm, hour, minute);
                startTimeTextView.setText(selectedTime); // 선택된 시간을 TextView에 표시
                bottomSheetDialog.dismiss(); // 시간 선택 후 BottomSheet 닫기
            }
        });

        // 인플레이트된 뷰를 BottomSheet의 내용으로 설정
        bottomSheetDialog.setContentView(bottomSheetView);
        bottomSheetDialog.show();
    }


}
