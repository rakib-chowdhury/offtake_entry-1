package com.nuveq.data_entry.model.history;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DatePost {

@SerializedName("date")
@Expose
private String date;

public String getDate() {
return date;
}

public void setDate(String date) {
this.date = date;
}

}