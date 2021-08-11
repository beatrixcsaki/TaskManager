package Tests;

import Schedulers.HierarchicalDependencyContainer;
import Schedulers.Iterator;
import Schedulers.StrongDependencyContainer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StrongDependencyContainerTest {

    @Test
    void getIterator() {
        StrongDependencyContainer container = new StrongDependencyContainer("src/tasks.txt");
        Iterator iteratorTest = container.getIterator();

        assert (iteratorTest.hasNext() == true);

        assert (iteratorTest.next().equals("C"));
        assert (iteratorTest.next().equals("A"));
        assert (iteratorTest.next().equals("B"));
        assert (iteratorTest.next().equals("D"));
        assert (iteratorTest.next().equals("G"));
        assert (iteratorTest.next().equals("F"));
        assert (iteratorTest.next().equals("E"));
        assert (iteratorTest.next().equals("H"));
        assert (iteratorTest.next().equals("J"));

        assert (iteratorTest.hasNext() == false);

    }
}