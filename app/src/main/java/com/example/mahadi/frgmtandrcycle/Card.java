package com.example.mahadi.frgmtandrcycle;

/**
 * Created by Mahadi on 3/11/2018.
 */

public class Card {

    private String name;
    private String phn;
    private int photo;
    private String endTime;

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Card() {

    }

    public Card(String name, String phn, int photo, String endTime) {
        this.name = name;
        this.phn = phn;
        this.photo = photo;
        this.endTime = endTime;
    }

    public String getName() {
        return name;
    }

    public String getPhn() {
        return phn;
    }

    public int getPhoto() {
        return photo;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhn(String phn) {
        this.phn = phn;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }
}
