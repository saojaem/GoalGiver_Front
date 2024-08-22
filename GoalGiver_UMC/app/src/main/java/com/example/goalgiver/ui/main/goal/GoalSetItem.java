package com.example.goalgiver.ui.main.goal;

import android.os.Parcel;
import android.os.Parcelable;

public class GoalSetItem implements Parcelable {

    private String goalIcon;
    private String goalTitle;
    public String goalDDay;
    private String goalPoints;
    private String goalProgressText;
    private int goalProgress;
    private String goalEndDate;
    private String goalStartDate;
    private String goalrepeat_Tv;
    private int goalCertificationCheck;
    private int goalPersonCheck;
    private long remainingTime;  // 남은 시간 필드 추가

    // 생성자
    public GoalSetItem(String goalIcon, String goalTitle, String goalDDay, String goalPoints, String goalProgressText, int goalProgress, String goalStartDate, String goalEndDate, String goalrepeat_Tv, int goalCertificationCheck, int goalPersonCheck, long remainingTime) {
        this.goalIcon = goalIcon;
        this.goalTitle = goalTitle;
        this.goalDDay = goalDDay;
        this.goalPoints = goalPoints;
        this.goalProgressText = goalProgressText;
        this.goalProgress = goalProgress;
        this.goalEndDate = goalEndDate;
        this.goalStartDate = goalStartDate;
        this.goalrepeat_Tv = goalrepeat_Tv;
        this.goalCertificationCheck = goalCertificationCheck;
        this.goalPersonCheck = goalPersonCheck;
        this.remainingTime = remainingTime;  // 초기화
    }

    protected GoalSetItem(Parcel in) {
        goalIcon = in.readString();
        goalTitle = in.readString();
        goalDDay = in.readString();
        goalPoints = in.readString();
        goalProgressText = in.readString();
        goalProgress = in.readInt();
        goalEndDate = in.readString();
        goalStartDate = in.readString();
        goalrepeat_Tv = in.readString();
        goalCertificationCheck = in.readInt();
        goalPersonCheck = in.readInt();
        remainingTime = in.readLong();  // 추가된 필드
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(goalIcon);
        dest.writeString(goalTitle);
        dest.writeString(goalDDay);
        dest.writeString(goalPoints);
        dest.writeString(goalProgressText);
        dest.writeInt(goalProgress);
        dest.writeString(goalEndDate);
        dest.writeString(goalStartDate);
        dest.writeString(goalrepeat_Tv);
        dest.writeInt(goalCertificationCheck);
        dest.writeInt(goalPersonCheck);
        dest.writeLong(remainingTime);  // 추가된 필드
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<GoalSetItem> CREATOR = new Creator<GoalSetItem>() {
        @Override
        public GoalSetItem createFromParcel(Parcel in) {
            return new GoalSetItem(in);
        }

        @Override
        public GoalSetItem[] newArray(int size) {
            return new GoalSetItem[size];
        }
    };

    // Getter와 Setter 메서드 추가
    public long getRemainingTime() {
        return remainingTime;
    }

    public void setRemainingTime(long remainingTime) {
        this.remainingTime = remainingTime;
    }

    public String getGoalIcon() {
        return goalIcon;
    }

    public String getGoalTitle() {
        return goalTitle;
    }

    public String getGoalDDay() {
        return goalDDay;
    }
    public String getGoalEndDate() { return goalEndDate;}
    public String getGoalStartDate() { return goalStartDate;}
    public String getGoalPoints() {
        return goalPoints;
    }

    public String getGoalProgressText() {
        return goalProgressText;
    }

    public int getGoalProgress() {
        return goalProgress;
    }
    public int getPersonTeam(){return goalPersonCheck;}
}
