package Schedulers;


import java.util.TreeSet;

public class WeakDependencyContainer extends BaseDependencyContainer {
    TreeSet<String> tasks; // TreeSet keeps them in alphabetical order

    public WeakDependencyContainer(String filePath) {
        this.dependencies = this.readDependencies(filePath);
        this.tasks = this.getTasks(this.dependencies);
    }


    @Override
    public Iterator getIterator() {
        return new WeakTaskIterator();
    }

    private class WeakTaskIterator implements Iterator {
        @Override
        public boolean hasNext() {
            return tasks.size() > 0;
        }

        @Override
        public String next() {
            if (hasNext()) {

                String taskToBeDeleted = getAvailableTasks().first(); // get next note to execute
                //marks the dependencies where the task is parent as done
                markDependenciesDone(taskToBeDeleted);
                tasks.remove(taskToBeDeleted);

                return taskToBeDeleted;

            }
            return null;

        }

        private TreeSet<String> getAvailableTasks() {
            // returns a set order alphabetically with available tasks for execution
            TreeSet<String> availableTasks = new TreeSet<String>();

            for (String task : tasks) {
                boolean hasDependencyDone = false;
                boolean hasAnyDependency = false;

                for (Dependency dep : dependencies) {
                    if (task.equals(dep.getChild())) {
                        hasAnyDependency = true;

                        if (dep.isDone()) {
                            hasDependencyDone = true;
                        }
                    }
                }

                if (hasDependencyDone || !hasAnyDependency)
                    availableTasks.add(task);
            }
            return availableTasks;
        }

        private void markDependenciesDone(String task) {
            for (Dependency dep : dependencies) {
                if (dep.getParent().equals(task)) {
                    dep.setDone(true);
                }
            }

        }


    }
}
