package com.company;

public class Task {



    int roomLocation;



    int workLeft;
    int skillUsed;
    enum TaskTypes {
        move,build,need,use,trade,fix
    }

    public TaskTypes getTaskTypes() {
        return taskTypes;
    }

    public void setTaskTypes(TaskTypes taskTypes) {
        this.taskTypes = taskTypes;
    }
    public int getRoomLocation() {
        return roomLocation;
    }

    public void setRoomLocation(int roomLocation) {
        this.roomLocation = roomLocation;
    }
    public int getWorkLeft() {
        return workLeft;
    }

    public void setWorkLeft(int workLeft) {
        this.workLeft = workLeft;
    }

    public int getSkillUsed() {
        return skillUsed;
    }

    public void setSkillUsed(int skillUsed) {
        this.skillUsed = skillUsed;
    }

    TaskTypes taskTypes;

}
