package ExportStatistics;

import ImportExpenses.ImportExpenses;
import ImportExpenses.Expenses;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MaxCategory {
    private String category;
    private long sum;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public long getSum() {
        return sum;
    }

    public void setSum(long sum) {
        this.sum = sum;
    }

    public JsonObject max(ImportExpenses importExpenses, String date) {
        if (date.equals("all time")) {
            maxOfMapCategories(importExpenses.getExpensesMap());
        } else {
            maxForDate(importExpenses, date);
        }
        JsonObject maxCategory = new JsonObject();
        maxCategory.addProperty("category", category);
        maxCategory.addProperty("sum", sum);
        return maxCategory;
    }

    public void maxOfMapCategories(HashMap<String, Long> map) {
        for (String title : map.keySet()) {
            if (sum == 0) {
                setCategory(title);
                setSum(map.get(title));
            } else if (sum < map.get(title)) {
                setCategory(title);
                setSum(map.get(title));
            }
        }
    }

    public void maxForDate(ImportExpenses importExpenses, String date) {
        String other = importExpenses.getOther();
        HashMap<String, Long> expensesMap = new HashMap<>();
        List<Expenses> expensesOfDay = new ArrayList<>();
        for (Expenses expenses : importExpenses.getSOE().getExpensesList()) {
            if (expenses.getDate().matches(date + ".*")) expensesOfDay.add(expenses);
        }
        for (Expenses expenses : expensesOfDay) {
            importExpenses.addCategoryToMap(
                    importExpenses.getCategories(),
                    expenses,
                    other,
                    expensesMap);
        }
        maxOfMapCategories(expensesMap);
    }
}

