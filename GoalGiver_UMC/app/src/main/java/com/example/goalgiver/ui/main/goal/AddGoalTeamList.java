package com.example.goalgiver.ui.main.goal;

import android.os.Parcel;
import android.os.Parcelable;

public class AddGoalTeamList implements Parcelable {
    private String name;
    private int imageResId;
    private boolean isChecked;

    // Constructor
    public AddGoalTeamList(String name, int imageResId, boolean isChecked) {
        this.name = name;
        this.imageResId = imageResId;
        this.isChecked = isChecked;
    }

    // Parcelable implementation
    protected AddGoalTeamList(Parcel in) {
        name = in.readString();
        imageResId = in.readInt();
        isChecked = in.readByte() != 0;
    }

    public static final Creator<AddGoalTeamList> CREATOR = new Creator<AddGoalTeamList>() {
        @Override
        public AddGoalTeamList createFromParcel(Parcel in) {
            return new AddGoalTeamList(in);
        }

        @Override
        public AddGoalTeamList[] newArray(int size) {
            return new AddGoalTeamList[size];
        }
    };

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(imageResId);
        dest.writeByte((byte) (isChecked ? 1 : 0));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public int getImageResId() {
        return imageResId;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}
