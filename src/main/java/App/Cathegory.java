package App;

public class Cathegory {

    private int cathegoryId;
    private String cathegoryName;
    private int cathegoryPriorityOfDisplay;

    public Cathegory(int cathegoryId, String cathegoryName, int cathegoryPriorityOfDisplay) {
        this.cathegoryId = cathegoryId;
        this.cathegoryName = cathegoryName;
        this.cathegoryPriorityOfDisplay = cathegoryPriorityOfDisplay;
    }

    public int getCathegoryId() {
        return cathegoryId;
    }


    public String toCsv() {
        return cathegoryId+";"+cathegoryName+";"+cathegoryPriorityOfDisplay;
    }
}
