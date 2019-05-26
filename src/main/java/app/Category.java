package app;

import java.util.Objects;

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

    @Override
    public String toString() {
        return cathegoryId + " - " + cathegoryName;
    }

    public String categoryToCsv() {
        return cathegoryId + ";" + cathegoryName + ";" + cathegoryPriorityOfDisplay;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return cathegoryId == category.cathegoryId &&
                cathegoryPriorityOfDisplay == category.cathegoryPriorityOfDisplay &&
                Objects.equals(cathegoryName, category.cathegoryName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cathegoryId, cathegoryName, cathegoryPriorityOfDisplay);
    }
}
