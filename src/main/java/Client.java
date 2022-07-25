import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private static final String host = "netology.homework";
    private static final int port = 8080;

    private static int questionsCount = 0;

    private static void dialogQuestions(String question) {
        System.out.println(question);
        questionsCount++;
    }

    public static void Client() {
        try (Socket client = new Socket(host, port)) {
            PrintWriter out = new PrintWriter(client.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            Scanner clientInput = new Scanner(System.in);
            while (questionsCount < 3) {
                dialogQuestions(in.readLine());
                String answer = clientInput.next();
                out.println(answer);
            }
            String resp = in.readLine();
            System.out.println(resp);


        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        Client();
    }
}
