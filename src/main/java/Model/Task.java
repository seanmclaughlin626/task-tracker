package Model;

import java.util.Locale;

public class Task {
    private int taskId;
    private String taskName;
    private String category;
    private String day;

    public Task(String taskName, String category, String day){
        this.taskId = 0;
        this.taskName = taskName;
        this.category = category;
        this.day = day;
    }
    public Task(int taskId, String taskName, String category, String day){
        this.taskId = taskId;
        this.taskName = taskName;
        this.category = category;
        this.day = day;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String toString(){
        String result = null;
        if(taskId != 0) {
            result = category.toUpperCase() + ": " + this.taskName + "--Task ID " + this.taskId;
        }
        return result;
    }
}
