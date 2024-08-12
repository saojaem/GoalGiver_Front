package com.example.goalgiver.ui.main.goal;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.goalgiver.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.Calendar;

public class AddGoalMain extends AppCompatActivity {
    private static final int REPEAT_REQUEST = 1;  // 요청 코드 상수 정의
    private static final int DONATION_REQUEST = 2;  // 추가된 요청 코드
    private static final int START_TIME_REQUEST = 3; // 시작 시간 요청 코드
    private static final int END_TIME_REQUEST = 4; // 종료 시간 요청 코드
    private static final int START_DATE_REQUEST = 5; // 시작 날짜 요청 코드
    private static final int END_DATE_REQUEST = 6; // 종료 날짜 요청 코드

    private TextView startDate_Ptv;
    private TextView endDate_Ptv;
    private TextView repeatRecordTv;
    private TextView donationRecordTv;  // 기부 단체 텍스트 뷰 추가
    private TextView startTimeTextView; // 시작 시간 텍스트 뷰 추가
    private TextView endTimeTextView; // 종료 시간 텍스트 뷰 추가
    private DatePickerDialog.OnDateSetListener startDateCallbackMethod;
    private DatePickerDialog.OnDateSetListener endDateCallbackMethod;
    private ImageButton startDate_Calender_Pbtn;
    private ImageButton endDate_Calender_Pbtn;
    private ImageButton startTimePickerButton; // 시작 시간 피커 버튼 추가
    private ImageButton endTimePickerButton; // 종료 시간 피커 버튼 추가
    private LinearLayout goalAddTeamTimeText;
    private LinearLayout goalAddTeamTimeCalender;
    private LinearLayout goalAddDateText;
    private LinearLayout goalAddDateCalender;
    private LinearLayout goalAddTeam;
    private LinearLayout target_lacation_L;
    private Calendar startDate; // 추가된 시작 날짜 저장 변수

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
        donationRecordTv = findViewById(R.id.goal_add_donation_tv2);  // 텍스트 뷰 초기화

        repeatChoose_btn = findViewById(R.id.add_goal_repeat_btn);
        repeatRecordTv = findViewById(R.id.add_goal_repeat_record);

        startTimePickerButton = findViewById(R.id.goal_add_team_Starttime_calender); // 시작 시간 피커 버튼 초기화
        startTimeTextView = findViewById(R.id.goal_add_team_Starttime_date); // 시작 시간 텍스트 뷰 초기화
        endTimePickerButton = findViewById(R.id.goal_add_team_Endtime_calender); // 종료 시간 피커 버튼 초기화
        endTimeTextView = findViewById(R.id.goal_add_team_endtime_date); // 종료 시간 텍스트 뷰 초기화

        Calendar cal = Calendar.getInstance();
        startDate = Calendar.getInstance(); // 시작 날짜 변수 초기화
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
                finish();  // 현재 액티비티를 종료하고 이전 화면으로 돌아갑니다.
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
                Intent intent = new Intent(getApplicationContext(), AddGoalRepeat.class);
                startActivityForResult(intent, REPEAT_REQUEST);
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
                startDate = Calendar.getInstance(); // 시작 날짜 변수 초기화
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

        certificationTeam_bt.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
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
            intent.putExtra("minDate", startDate.getTimeInMillis()); // 종료 날짜 선택 시 최소 날짜를 시작 날짜로 설정
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
                bottomSheetDialog.dismiss();  // BottomSheetDialog 닫기
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
                startDate.set(yyy, mmm, ddd); // 시작 날짜 변수 업데이트
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
        if (requestCode == REPEAT_REQUEST && resultCode == RESULT_OK && data != null) {
            String repeatType = data.getStringExtra("repeatType");
            String selectedDays = data.getStringExtra("selectedDays");
            int dailyInterval = data.getIntExtra("dailyInterval", 1);
            int selectedDayOfMonth = data.getIntExtra("selectedDayOfMonth", 1);

            if (repeatType.equals("매주") && !selectedDays.isEmpty()) {
                repeatRecordTv.setText(repeatType + " " + selectedDays);
            } else if (repeatType.equals("매일")) {
                repeatRecordTv.setText(repeatType + " " + dailyInterval + "일 마다");
            } else if (repeatType.equals("매월")) {
                repeatRecordTv.setText(repeatType + " " + selectedDayOfMonth + "일");
            }
        } else if (requestCode == DONATION_REQUEST && resultCode == RESULT_OK && data != null) {
            String selectedOrganization = data.getStringExtra("selectedOrganization");
            donationRecordTv.setText(selectedOrganization);
        } else if (requestCode == START_DATE_REQUEST && resultCode == RESULT_OK && data != null) {
            int year = data.getIntExtra("mYear", Calendar.getInstance().get(Calendar.YEAR));
            int month = data.getIntExtra("mMonth", Calendar.getInstance().get(Calendar.MONTH));
            int day = data.getIntExtra("mDay", Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
            startDate_Ptv.setText(year + "-" + (month + 1) + "-" + day);
            startDate.set(year, month, day); // 업데이트된 시작 날짜 저장
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
}
