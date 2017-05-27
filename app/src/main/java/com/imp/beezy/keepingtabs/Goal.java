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


    public String getGoalName() {
        return goalName;
    }

    public void setGoalName(String goalName) {
        this.goalName = goalName;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public boolean isAccess() {
        return access;
    }

    public void setAccess(boolean access) {
        this.access = access;
    }

    public Map<String, Boolean> getTypeMap() {
        return typeMap;
    }

    public void setTypeMap(Map<String, Boolean> typeMap) {
        this.typeMap = typeMap;
    }

    public Goal () {}

    public Goal (String goal, String startDate, String endDate, String Description, boolean access, Map<String,Boolean> map) {
        this.goalName = goal;
        this.startDate = startDate;
        this.endDate = endDate;
        this.Description = Description;
        this.access = access;
        this.typeMap = map;
    }



   // public static Goal newInstance(String name)
}

