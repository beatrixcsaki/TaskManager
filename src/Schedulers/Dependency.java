package Schedulers;

public class Dependency {
    String parent;
    String child;
    Boolean isDone;

    public Dependency(String parent, String child) {
        this.parent = parent;
        this.child = child;
        this.isDone = false;
    }

    public String getChild() {
        return child;
    }

    public void setChild(String child) {
        this.child = child;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public Boolean isDone() {
        return isDone;
    }

    public void setDone(Boolean done) {
        isDone = done;
    }

    @Override
    public java.lang.String toString() {
        return child + " depends on " + parent + " completed: " + isDone;

    }
}
