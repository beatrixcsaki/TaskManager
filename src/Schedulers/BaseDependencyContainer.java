package Schedulers;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeSet;


public class BaseDependencyContainer implements Container {
    ArrayList<Dependency> dependencies;

    public ArrayList<Dependency> readDependencies(String filePath) {
        ArrayList<Dependency> dependencies = new ArrayList<>();
        // reads the dependencies from a text file and creates the container
        try {
            File file = new File(filePath);
            Scanner myReader = new Scanner(file);

            while (myReader.hasNextLine()) {
                String line = myReader.nextLine();

                String parent = line.split("->", 0)[0].strip();
                String child = line.split("->", 0)[1].strip();

                dependencies.add(new Dependency(parent, child));
            }

            myReader.close();

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return dependencies;
    }

    public TreeSet<String> getTasks(ArrayList<Dependency> dependencies) {
        TreeSet<String> tasks = new TreeSet<String>();
        for (Dependency dep : dependencies) {
            tasks.add(dep.getParent());
            tasks.add(dep.getChild());
        }
        return tasks;
    }

    @Override
    public Iterator getIterator() {
        return null;
    }
}
