package com.lifesolutions.bd.Api;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetCourses {

    @SerializedName("data")
    List<Data> dataList;


    public List<Data> getDataList() {
        return dataList;
    }

    public void setDataList(List<Data> dataList) {
        this.dataList = dataList;
    }
}
