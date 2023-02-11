package ImportExpenses;

import java.util.ArrayList;
import java.util.List;

public class StatisticOfExpenses {

    private List<Expenses> expensesList = new ArrayList<>();

    public List<Expenses> getExpensesList() {
        return expensesList;
    }

    public void setExpensesList(List<Expenses> expensesList) {
        this.expensesList = expensesList;
    }
    public void printExpensesList(){
        for (Expenses expenses : expensesList){
            System.out.println(expenses);
        }
    }
}
