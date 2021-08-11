package Tests;

import org.junit.jupiter.api.Test;
import Schedulers.BaseDependencyContainer;
import Schedulers.Dependency;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BaseDependencyContainerTest {

    @Test
    void readDependencies() {
        BaseDependencyContainer baseContainer = new BaseDependencyContainer();
        ArrayList<Dependency> dependencies = baseContainer.readDependencies("src/tasks.txt");

        assert (dependencies.size() == 11);
    }

    @Test
    void getTasks() {
        BaseDependencyContainer baseContainer = new BaseDependencyContainer();
        ArrayList<Dependency> dependencies = new ArrayList<Dependency>();

        assert (baseContainer.getTasks(dependencies).size() == 0);

        dependencies.add(new Dependency("A", "B"));
        dependencies.add(new Dependency("C", "B"));

        assert (baseContainer.getTasks(dependencies).size() == 3);
        assert (baseContainer.getTasks(dependencies).first()) == "A";
        assert (baseContainer.getTasks(dependencies).toArray()[1]) == "B";
        assert (baseContainer.getTasks(dependencies).last()) == "C";
    }
}