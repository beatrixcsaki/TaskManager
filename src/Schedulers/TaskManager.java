package Schedulers;

import java.util.ArrayList;

public class TaskManager {

    public ArrayList<String> getTasksInOrder(String filePath, String orderType) {
//      Returns an ArrayList with the tasks in order.
//      orderType can be: "strong", "weak", "hierarchical"

        Container container;
        ArrayList<String> tasksInOrder = new ArrayList<>();
        if (orderType.equals("strong")) {
            container = new StrongDependencyContainer(filePath);
        } else if (orderType.equals("weak")) {
            container = new WeakDependencyContainer(filePath);
        } else if (orderType.equals("hierarchical")) {
            container = new HierarchicalDependencyContainer(filePath);
        } else {
            System.out.println("Invalid ordering type of tasks! " + orderType + " not found!");
            return null;
        }

        Iterator i = container.getIterator();

        while (i.hasNext())
            tasksInOrder.add((String) i.next());

        return tasksInOrder;
    }

}
