package com.olebas.criminalintent;

import java.util.Date;
import java.util.UUID;

public class Crime {

    private UUID mId;
    private String mTitle;
    private Date mDate;
    private Boolean mSolved;
    private String mSuspect;
    private long mSuspectId;

    public Crime() {
        this(UUID.randomUUID());
//        mSolved = false;
    }

    public Crime(UUID id) {
        mId = id;
        mDate = new Date();
        mSolved = false;
    }

    public UUID getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public Boolean isSolved() {
        return mSolved;
    }

    public void setSolved(Boolean solved) {
        mSolved = solved;
    }

    public String getSuspect() {
        return mSuspect;
    }

    public void setSuspect(String suspect) {
        mSuspect = suspect;
    }

    public long getSuspectId() {
        return mSuspectId;
    }

    public void setSuspectId(long suspectId) {
        mSuspectId = suspectId;
    }
}
