import DAO.JdbcTaskDao;
import DAO.TaskDao;
import Model.Task;
import Services.ConsoleService;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;

public class App {
    private final ConsoleService consoleService = new ConsoleService();

    public App(){

    }

    public static void main(String[] arg){
        App app = new App();
        app.run();
    }

    public void run(){
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:postgresql://localhost:5432/TaskTracker");
        dataSource.setUsername("postgres");
        dataSource.setPassword("postgres1");
        TaskDao taskDao = new JdbcTaskDao(dataSource);

        System.out.println("Welcome back, Sean");
        System.out.println("What would you like to do today?");
        System.out.println();
        int menuSelection = -1;
        while (menuSelection != 4){
            menuSelection = consoleService.mainMenuPrompt();
            switch (menuSelection){
                case 1:
                    taskDao.viewTasks();
                    break;
                case 2:
                    Task taskToAdd = consoleService.addTaskMenu();
                    taskDao.addTask(taskToAdd);
                    break;
                case 3:
                    taskDao.viewTasks();
                    taskDao.deleteTask(consoleService.deleteTaskMenu());
                    break;
                case 4:
                    System.out.println("Goodbye! Drink water!");
                    break;
                default:
                    System.out.println("Invalid selection. Try again.");
            }
        }

    }

    public void dataSourceSetup() {

    }
}
