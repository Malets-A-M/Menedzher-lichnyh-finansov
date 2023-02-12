package SavingLoading;

import ImportExpenses.StatisticOfExpenses;

import java.io.*;

public class Save {
    private FileSavingLoading file = new FileSavingLoading();
    public void saving(StatisticOfExpenses SOE) throws IOException {
        FileOutputStream fos = new FileOutputStream(file.getSavingLoading(), false);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(SOE);
        oos.close();
        System.out.println("Сохранение прошло успешно");
    }
}
