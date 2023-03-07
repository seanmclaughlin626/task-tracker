package DAO;

import Model.Task;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class JdbcTaskDao implements TaskDao{
    private JdbcTemplate jdbcTemplate;
    private final String API_BASE_URL = "http://localhost:5432/";

    public JdbcTaskDao(DataSource dataSource){this.jdbcTemplate = new JdbcTemplate(dataSource);}

    @Override
    public int addTask(Task task){
        String taskInsertSql = "INSERT INTO task (task_name, task_category) VALUES (?,?) RETURNING task_id";
        int taskId = jdbcTemplate.queryForObject(taskInsertSql, int.class, task.getTaskName(), task.getCategory());
//        String dayInsertSql = "INSERT INTO day (day_id, day_name) VALUES (?, ?)";
//        String dayName = getDayName(task.getDay());
//        jdbcTemplate.queryForObject(dayInsertSql, task.getDay(), dayName);
        return taskId;
    }

    @Override
    public void deleteTask(int id){
        String taskDaySql = "DELETE FROM task_day WHERE task_id = ?";
        String taskSql = "DELETE FROM task WHERE task_id = ?";
        jdbcTemplate.update(taskDaySql);
        jdbcTemplate.update(taskSql);
    }

    @Override
    public void viewTasks(){
        List<String> dayCodes = getDayCodeList();
        for(String day : dayCodes){
            printForDay(day);
        }
    }

    private List<Task> printForDay(String dayCode){
        List<Task> dailyTaskList = new ArrayList<>();
        String dayName = getDayName(dayCode);
        String tasksSql = "SELECT t.task_id, task_name, task_category, day_name FROM task AS t JOIN task_day AS td ON t.task_id = "
                + "td.task_id JOIN day AS d ON td.day_id = day.day_id WHERE d.day_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(tasksSql, dayCode);

        System.out.println(dayName + ":");
        while (results.next()){
            Task taskAtHand = mapRowToTask(results);
            System.out.println(taskAtHand.toString());
            dailyTaskList.add(taskAtHand);
        }
        return dailyTaskList;
    }

    private String getDayName(String dayCode){
        String dayNameSql = "SELECT day_name FROM day WHERE day_id = ?";
        SqlRowSet dayNameRow = jdbcTemplate.queryForRowSet(dayNameSql, dayCode);
        return dayNameRow.getString("day_name");
    }

    private Task mapRowToTask(SqlRowSet results){
        int taskId = results.getInt("task_id");
        String taskName = results.getString("task_name");
        String category = results.getString("task_category");
        String day = results.getString("day_name");

        return new Task(taskId, taskName, category, day);
    }

    private List<String> getDayCodeList(){
        List<String> dayCodes = new ArrayList<>();
        dayCodes.add("mo");
        dayCodes.add("tu");
        dayCodes.add("we");
        dayCodes.add("th");
        dayCodes.add("fr");
        dayCodes.add("sa");
        dayCodes.add("su");
        return dayCodes;
    }
}