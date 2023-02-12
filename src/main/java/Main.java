import ExportStatistics.ExportStatistics;
import ExportStatistics.MaxCategory;
import ImportExpenses.ImportExpenses;
import SavingLoading.FileSavingLoading;
import SavingLoading.Load;
import SavingLoading.Save;
import ImportExpenses.Expenses;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Set;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) throws IOException {
        ExportStatistics exportStatistics = new ExportStatistics();
        File file = new File("import.json");
        FileSavingLoading fileSavingLoading = new FileSavingLoading();
        ImportExpenses importExpenses = new ImportExpenses();
        String input;
        try (ServerSocket serverSocket = new ServerSocket(8989)) {
            System.out.println("Сервер работает");
            try (Socket clientSocket = serverSocket.accept();
                 PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                 BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

                if (fileSavingLoading.getSavingLoading().exists()){
                    System.out.println("Найден файл для загрузки");
                    Load load = new Load();
                    load.loading(importExpenses.getSOE());
                } else System.out.println("Файл для загрузки отсутствует");

                while (true) {
                    while (file.exists()) file.delete();
                    if (!file.exists()) file.createNewFile();
                    FileWriter writer = new FileWriter(file);
                    input = in.readLine();
                    if (!input.equals("end")) {
                        System.out.println(input);
                        writer.write(input);
                        writer.flush();
                        writer.close();
                        importExpenses.addExExpense();
                        importExpenses.printExpensesMap();

                        Save save = new Save();
                        save.saving(importExpenses.getSOE());

                        while (file.exists()) file.delete();
                        System.out.println("|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||");
                    } else {
                        System.out.println("Учет финансов завершен\n" +
                                "Желаете получить статистику по финансам?   (y / n)");
                        input = in.readLine();
                        if (input == "n") break;
                        else {
                            while (true) {
                                System.out.println("Чтобы получить статистику за весь период, введите \"all time\"\n" +
                                        "Чтобы получить статистику за год, введите дату в формате \"ГГГГ\"\n" +
                                        "Чтобы получить статистику за месяц, введите дату в формате \"ГГГГ.ММ\"\n" +
                                        "Чтобы получить статистику за день, введите дату в формате \"ГГГГ.ММ.ДД\"\n" +
                                        "Чтобы показать, какие даты задействованы в статистике, введите \"date\"\n" +
                                        "Чтобы завершить работу, введите \"end\"\n" +
                                        "|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||");
                                input = in.readLine();
                                if (input.equals("end")) {
                                    exportStatistics.writer();
                                    break;
                                } else if (input.equals("all time")) {
                                    exportStatistics.addJSON(new MaxCategory(), importExpenses, "all time", "maxCategory");
                                } else if (input.equals("date")) {
                                    Set<String> date = new TreeSet<>();
                                    for (Expenses expenses : importExpenses.getSOE().getExpensesList()){
                                        date.add(expenses.getDate());
                                    }
                                    for (String d : date){
                                        System.out.println(d);
                                    }
                                } else {
                                    int length = input.length();
                                    switch (length) {
                                        case 4 :
                                            exportStatistics.addJSON(new MaxCategory(), importExpenses, input, "maxYearCategory");
                                            continue;
                                        case 7 :
                                            exportStatistics.addJSON(new MaxCategory(), importExpenses, input, "maxMonthCategory");
                                            continue;
                                        case 10 :
                                            exportStatistics.addJSON(new MaxCategory(), importExpenses, input, "maxDayCategory");
                                            continue;
                                    }
                                }
                            }
                        }
                        break;
                    }
                }
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            System.out.println("Не могу стартовать сервер");
            e.printStackTrace();
        }
    }
}
