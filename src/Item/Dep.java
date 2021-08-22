package Item;

public class Dep {
    int id;
    String name;

    public Dep(){

    }
    public Dep(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Dep{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
