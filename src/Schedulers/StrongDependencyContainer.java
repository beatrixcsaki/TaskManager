package Schedulers;

import java.util.*;


public class StrongDependencyContainer extends BaseDependencyContainer {
    TreeSet<String> tasks; // TreeSet keeps them in alphabetical order

    public StrongDependencyContainer(String filePath) {
        this.dependencies = this.readDependencies(filePath);
        this.tasks = this.getTasks(this.dependencies);
    }

    public Iterator getIterator() {
        return new StrongTaskIterator();
    }

    private class StrongTaskIterator implements Iterator {
        @Override
        public boolean hasNext() {
            return dependencies.size() > 0 || tasks.size() > 0;
        }

        @Override
        public String next() {
            if (hasNext()) {
                String taskToBeDeleted;
                if (dependencies.size() > 0) {
                    taskToBeDeleted = (String) getAvailableTasks().first();

                    // removes dependencies where the task is parent
                    dependencies.removeIf(dep -> dep.getParent().equals(taskToBeDeleted));

                } else {

                    // if no dependencies are left only the end nodes are present
                    taskToBeDeleted = tasks.first();
                }

                tasks.remove(taskToBeDeleted);

                return taskToBeDeleted;

            }

            return null;
        }

        private TreeSet<String> getAvailableTasks() {
            // returns a set order alphabetically with available tasks for execution
            TreeSet<String> availableTasks;
            availableTasks = (TreeSet) tasks.clone();

            for (Dependency dep : dependencies) {
                availableTasks.remove(dep.getChild());
            }

            return availableTasks;
        }
    }


}
