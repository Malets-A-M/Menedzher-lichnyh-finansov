package ImportExpenses;

public class Expenses {
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
