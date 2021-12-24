package Business;

public class Trial {
    protected String name;

    public Trial(String name) {
        this.name = name;
    }

    public void printInformation() {
        System.out.println("Trial: "+ name);
    }
}
