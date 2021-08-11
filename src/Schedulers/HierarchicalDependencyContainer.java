package Schedulers;

import java.util.*;

public class HierarchicalDependencyContainer extends BaseDependencyContainer {
    HashMap<String, Integer> tasks = new HashMap<>();

    public HierarchicalDependencyContainer(String filePath) {
        this.dependencies = this.readDependencies(filePath);
        for (String task : this.getTasks(this.dependencies))
            tasks.put(task, null);
    }

    @Override
    public Iterator getIterator() {
        return new HierarchicalTaskIterator();
    }

    private class HierarchicalTaskIterator implements Iterator {
        public HierarchicalTaskIterator() {
            while (areNodesUnparsed()) {
                parseTasks();
            }
        }

        @Override
        public boolean hasNext() {
            return !tasks.isEmpty();
        }

        @Override
        public String next() {
//            will return the smallest level found in the tasks
            Integer current_level = Collections.min(tasks.values());

            for (HashMap.Entry<String, Integer> entry : tasks.entrySet())
                if (entry.getValue().equals(current_level)) {
                    String key = entry.getKey();
                    tasks.remove(key);
                    return key;

                }

            return null;
        }

        private void parseTasks() {
            for (HashMap.Entry<String, Integer> entry : tasks.entrySet())
            // iterates over the hashmap entries to find the level of each task
            {
                String task = entry.getKey();
                Integer rank = entry.getValue();

                if (rank == null) {
                    Integer parentRank = null;
                    boolean isRoot = true;

                    for (Dependency dep : dependencies) {
                        if (dep.getChild().equals(task)) {
                            isRoot = false;
                            if (tasks.get(dep.getParent()) != null) {
                                parentRank = tasks.get(dep.getParent());
                            }
                        }
                    }

                    if (isRoot) {
                        tasks.replace(task, 1);
                    }
                    if (parentRank != null) {
                        tasks.replace(task, parentRank + 1);
                    }
                }
            }
        }

        private Boolean areNodesUnparsed() {
            for (HashMap.Entry<String, Integer> entry : tasks.entrySet())
                if (entry.getValue() == null)
                    return true;

            return false;
        }


    }

}
