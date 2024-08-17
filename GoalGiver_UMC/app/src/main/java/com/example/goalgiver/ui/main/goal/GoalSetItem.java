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

    // 생성자
    public GoalSetItem(String goalIcon, String goalTitle, String goalDDay, String goalPoints, String goalProgressText, int goalProgress) {
        this.goalIcon = goalIcon;
        this.goalTitle = goalTitle;
        this.goalDDay = goalDDay;
        this.goalPoints = goalPoints;
        this.goalProgressText = goalProgressText;
        this.goalProgress = goalProgress;
    }

    protected GoalSetItem(Parcel in) {
        goalIcon = in.readString();
        goalTitle = in.readString();
        goalDDay = in.readString();
        goalPoints = in.readString();
        goalProgressText = in.readString();
        goalProgress = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(goalIcon);
        dest.writeString(goalTitle);
        dest.writeString(goalDDay);
        dest.writeString(goalPoints);
        dest.writeString(goalProgressText);
        dest.writeInt(goalProgress);
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
}
