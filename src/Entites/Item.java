package Entites;

public class Item {
    private String name;
    private String description;

    public Item(String name, String desc) {
        this.name = name;
        this.description = desc;
    }

    public String getName() {return name;}
    public String getDescription() {return description;}

    // temp to check if item behaves right.
    public void displayItem() {
        System.out.println("--" + name + "--\nDescription: " + description + "\n");
    }
}
