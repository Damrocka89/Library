package app;

public class Category {

    private int cathegoryId;
    private String cathegoryName;
    private int cathegoryPriorityOfDisplay;

     Category(int cathegoryId, String cathegoryName, int cathegoryPriorityOfDisplay) {
        this.cathegoryId = cathegoryId;
        this.cathegoryName = cathegoryName;
        this.cathegoryPriorityOfDisplay = cathegoryPriorityOfDisplay;
    }

    public int getCategoryId() {
        return cathegoryId;
    }

    @Override
    public String toString() {
        return cathegoryId + " - " + cathegoryName;
    }
}
