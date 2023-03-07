package DAO;

import Model.Task;

public interface TaskDao {

    public int addTask(Task task);

    public void deleteTask(int id);

    public void viewTasks();
}
