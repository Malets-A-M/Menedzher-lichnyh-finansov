package ImportExpenses;

import java.io.Serializable;

public class Expenses implements Serializable {
    private String title;
    private String date;
    private int sum;

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public int getSum() {
        return sum;
    }

    @Override
    public String toString() {
        return date + " " + title + " " + sum;
    }
}
