import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public Client() {
    }

    /** ПЕРЕДАЧА В КОНСОЛЬ ФАЙЛОВ ТИПА JSON
{"title": "булка", "date": "2022.02.08", "sum": 200}
{"title": "колбаса", "date": "2022.02.08", "sum": 1000}
{"title": "сухарики", "date": "2022.02.08", "sum": 100}
{"title": "курица", "date": "2022.02.08", "sum": 250}
{"title": "тапки", "date": "2022.02.08", "sum": 500}
{"title": "шапка", "date": "2022.02.08", "sum": 450}
{"title": "мыло", "date": "2022.02.08", "sum": 50}
{"title": "акции", "date": "2022.02.08", "sum": 5000}
{"title": "салфетки", "date": "2022.02.08", "sum": 150}
{"title": "булка", "date": "2023.01.03", "sum": 200}
{"title": "колбаса", "date": "2023.01.03", "sum": 1000}
{"title": "сухарики", "date": "2023.01.03", "sum": 100}
{"title": "курица", "date": "2023.01.05", "sum": 250}
{"title": "тапки", "date": "2023.01.05", "sum": 500}
{"title": "шапка", "date": "2023.02.08", "sum": 450}
{"title": "мыло", "date": "2023.02.08", "sum": 50}
{"title": "акции", "date": "2023.02.10", "sum": 5000}
{"title": "салфетки", "date": "2023.03.02", "sum": 150}
end
     */

    public static void main(String[] args) {
        String input;
        Scanner scanner = new Scanner(System.in);
        try (Socket socket = new Socket("127.0.0.1", 8989);
             PrintWriter out = new PrintWriter(socket .getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket .getInputStream())))
        {
            while (true) {
                out.println(scanner.nextLine());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}