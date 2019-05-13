package App;

public class Category {

    private int cathegoryId;
    private String cathegoryName;
    private int cathegoryPriorityOfDisplay;

    public Category(int cathegoryId, String cathegoryName, int cathegoryPriorityOfDisplay) {
        this.cathegoryId = cathegoryId;
        this.cathegoryName = cathegoryName;
        this.cathegoryPriorityOfDisplay = cathegoryPriorityOfDisplay;
    }

    public int getCategoryId() {
        return cathegoryId;
    }


    public String toCsv() {
        return cathegoryId + ";" + cathegoryName + ";" + cathegoryPriorityOfDisplay;
    }

    @Override
    public String toString() {
        return cathegoryId + " - " + cathegoryName;
    }
}
