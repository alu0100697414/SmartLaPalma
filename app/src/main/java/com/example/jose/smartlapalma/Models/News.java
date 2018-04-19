package com.example.jose.smartlapalma.Models;

import java.util.ArrayList;
import java.util.List;

public class News {

    private List<New> mNewList;

    public News(){
        mNewList = new ArrayList<New>();
    }

    public void setmNewList(List<New> mNewList) {
        this.mNewList = mNewList;
    }

    public List<New> getmNewList() {
        return mNewList;
    }
}
