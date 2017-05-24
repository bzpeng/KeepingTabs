package com.imp.beezy.keepingtabs;

import java.util.Map;

/**
 * Created by bpeng on 2017-05-09.
 */

public class Goal {

    private String goalName;
    private String startDate;
    private String endDate;
    private String Description;

    private boolean access;
    private Map<String, Boolean> typeMap;


    public Goal (String goal, String startDate, String endDate, String Description, boolean access, Map<String,Boolean> map) {
        goal = goalName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.Description = Description;
        this.access = access;
        this.typeMap = map;
    }



   // public static Goal newInstance(String name)
}

