package Main;

import Schedulers.TaskManager;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        String orderType = "strong";
        TaskManager manager = new TaskManager();
        System.out.println(manager.getTasksInOrder("src/tasks.txt", orderType));
    }


}
