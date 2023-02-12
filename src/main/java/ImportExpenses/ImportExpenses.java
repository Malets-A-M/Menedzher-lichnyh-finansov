package ImportExpenses;

import ImportCategories.Categories;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class ImportExpenses {
    private String title;
    private String date;
    private int sum;
    private File file = new File("import.json");
    private String other = "другое";
    private Expenses expenses;
    private Gson gson = new Gson();
    private HashMap<String, Long> expensesMap = new HashMap<>();
    private Categories categories = Categories.getInstance();
    private StatisticOfExpenses SOE = new StatisticOfExpenses();

    public ImportExpenses() throws IOException {
    }

    public void addExExpense() throws IOException {
        JsonReader reader = new JsonReader(new FileReader(file));
        expenses = gson.fromJson(reader, Expenses.class);
        title = expenses.getTitle();
        date = expenses.getDate();
        sum = expenses.getSum();
        addCategoryToMap(categories, expenses, other, expensesMap);
        System.out.println(date + " Новые расходы учтены: " + title + " " + sum + " руб.");
        reader.close();
        SOE.getExpensesList().add(expenses);
    }
    public void addCategoryToMap(Categories categories, Expenses expenses, String other, HashMap<String, Long> expensesMap){
        if (categories.getCategories().containsKey(expenses.getTitle())) {
            if (expensesMap.containsKey(categories.getCategories().get(title))) {
                long updateSum = expensesMap.remove(categories.getCategories().get(expenses.getTitle())) + expenses.getSum();
                expensesMap.put(categories.getCategories().get(expenses.getTitle()), updateSum);
            } else {
                expensesMap.put(categories.getCategories().get(expenses.getTitle()), (long) expenses.getSum());
            }
        } else if (expensesMap.containsKey(other)){
            long updateSum = expensesMap.remove(other) + expenses.getSum();
            expensesMap.put(other, updateSum);
        } else {
            expensesMap.put(other, (long) expenses.getSum());
        }
    }

        @Override
    public String toString() {
        return "Expenses{" +
                "title='" + title + '\'' +
                ", date='" + date + '\'' +
                ", sum=" + sum +
                '}';
    }

    public void printExpensesMap(){
        for (String title : expensesMap.keySet()){
            System.out.println(title + " " + expensesMap.get(title) + " руб.");
        }
    }
    public HashMap<String, Long> getExpensesMap(){
        return expensesMap;
    }
    public StatisticOfExpenses getSOE(){
        return SOE;
    }
    public Categories getCategories(){
        return categories;
    }
    public String getOther(){
        return other;
    }
}
