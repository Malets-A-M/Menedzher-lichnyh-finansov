package SavingLoading;

import ImportExpenses.StatisticOfExpenses;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Load {
    private FileSavingLoading file = new FileSavingLoading();

    public void loading(StatisticOfExpenses SOE) throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(file.getSavingLoading());
        ObjectInputStream ois = new ObjectInputStream(fis);
        StatisticOfExpenses obj= (StatisticOfExpenses) ois.readObject();
        SOE.setExpensesList(obj.getExpensesList());
        System.out.println("Загрузка прошла успешно");
        SOE.printExpensesList();
    }
}
