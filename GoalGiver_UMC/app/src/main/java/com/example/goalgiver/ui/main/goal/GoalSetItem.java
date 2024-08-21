package com.example.goalgiver.ui.main.goal;

import android.os.Parcel;
import android.os.Parcelable;

public class GoalSetItem implements Parcelable {

    private String goalIcon;
    private String goalTitle;
    private String goalDDay;
    private String goalPoints;
    private String goalProgressText;
    private int goalProgress;
    private String goalEndDate;
    private String goalStartDate;
    private String goalrepeat_Tv;
    private int goalCertificationCheck;
    private int goalPersonCheck;
    private String goalDonation;


    // 생성자
    public GoalSetItem(String goalIcon, String goalTitle, String goalDDay, String goalPoints, String goalProgressText, int goalProgress, String goalStartDate, String goalEndDate, String goalrepeat_Tv, int goalCertificationCheck, int goalPersonCheck, String goalDonation) {
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
        this.goalDonation = goalDonation;
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
        goalDonation = in.readString();
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
        dest.writeString(goalDonation);
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

    // Getter와 Setter 메서드
    public String getGoalIcon() {
        return goalIcon;
    }

    public void setGoalIcon(String goalIcon) {
        this.goalIcon = goalIcon;
    }

    public String getGoalTitle() {
        return goalTitle;
    }

    public void setGoalTitle(String goalTitle) {
        this.goalTitle = goalTitle;
    }

    public String getGoalDDay() {
        return goalDDay;
    }

    public void setGoalDDay(String goalDDay) {
        this.goalDDay = goalDDay;
    }

    public String getGoalPoints() {
        return goalPoints;
    }

    public void setGoalPoints(String goalPoints) {
        this.goalPoints = goalPoints;
    }

    public String getGoalProgressText() {
        return goalProgressText;
    }

    public void setGoalProgressText(String goalProgressText) {
        this.goalProgressText = goalProgressText;
    }

    public int getGoalProgress() {
        return goalProgress;
    }

    public void setGoalProgress(int goalProgress) {
        this.goalProgress = goalProgress;
    }

    public String getGoalEndDate() { return goalEndDate; }

    public void setGoalEndDate(String goalEndDate) { this.goalEndDate = goalEndDate; }

    public String getGoalStartDate() { return goalStartDate; }

    public void setGoalStartDate(String goalStartDate) { this.goalStartDate = goalStartDate; }

    public String getGoalrepeat_Tv() { return goalrepeat_Tv; }

    public void setGoalrepeat_Tv(String goalrepeat_Tv) { this.goalrepeat_Tv = goalrepeat_Tv; }

    public int getGoalCertificationCheck() { return goalCertificationCheck; }

    public void setGoalCertificationCheck(int goalCertificationCheck) { this.goalCertificationCheck = goalCertificationCheck; }

    public int getGoalPersonCheck() { return goalPersonCheck; }

    public String getGoalDonation() { return goalDonation; }

    public void setGoalDonation(String goalDonation) { this.goalDonation = goalDonation; }
}
