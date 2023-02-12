package SavingLoading;

import ImportExpenses.ImportExpenses;
import ImportExpenses.Expenses;
import ImportExpenses.StatisticOfExpenses;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Load {
    private FileSavingLoading file = new FileSavingLoading();

    public void loading(ImportExpenses importExpenses) throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(file.getSavingLoading());
        ObjectInputStream ois = new ObjectInputStream(fis);
        StatisticOfExpenses obj= (StatisticOfExpenses) ois.readObject();
        importExpenses.getSOE().setExpensesList(obj.getExpensesList());
        System.out.println("Загрузка прошла успешно");
        importExpenses.getSOE().printExpensesList();
        for (Expenses expenses : importExpenses.getSOE().getExpensesList()) {
            importExpenses.addCategoryToMap(
                    importExpenses.getCategories(),
                    expenses,
                    importExpenses.getOther(),
                    importExpenses.getExpensesMap());
        }

    }
}
