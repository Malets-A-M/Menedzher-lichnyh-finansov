import ExportStatistics.ExportStatistics;
import ImportExpenses.ImportExpenses;
import SavingLoading.FileSavingLoading;
import SavingLoading.Load;
import SavingLoading.Save;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

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
                        System.out.println("Работа завершена");
                        exportStatistics.maxCategory(importExpenses);
                        break;
                    }
                }
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            System.out.println("Не могу стартовать сервер");
            e.printStackTrace();
        }
    }
}
