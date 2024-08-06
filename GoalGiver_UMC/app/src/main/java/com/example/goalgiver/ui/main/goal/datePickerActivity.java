package com.example.goalgiver.ui.main.goal;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;

import com.example.goalgiver.R;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class datePickerActivity extends AppCompatActivity{
    private int mYear =0, mMonth=0, mDay=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.add_goal_date_picker);
        Calendar calendar = Calendar.getInstance();
        Button EnterDate;

        // 현재 년, 월, 일 가져옴
        mYear = calendar.get(Calendar.YEAR);
        mMonth = calendar.get(Calendar.MONTH);
        mDay = calendar.get(Calendar.DAY_OF_MONTH);

        DatePicker datePicker = findViewById(R.id.vDatePicker);
        EnterDate = findViewById(R.id.vDateEnter);
        datePicker.init(mYear, mMonth, mDay,mOnDateChangedListener);

        EnterDate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                mOnClick(view);
            }
        });
    }
    public void mOnClick(View v){
        Intent intent = new Intent();

        intent.putExtra("mYear",mYear);
        intent.putExtra("mMonth", mMonth);
        intent.putExtra("mDay", mDay);

        setResult(RESULT_OK, intent);
        finish();
    }
    DatePicker.OnDateChangedListener mOnDateChangedListener = new DatePicker.OnDateChangedListener(){
        @Override
        public void onDateChanged(DatePicker datePicker, int yy, int mm, int dd) {

            mYear = yy;
            mMonth = mm;
            mDay = dd;
        }
    };




}
