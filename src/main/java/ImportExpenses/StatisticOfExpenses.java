package ImportExpenses;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class StatisticOfExpenses implements Serializable {

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
