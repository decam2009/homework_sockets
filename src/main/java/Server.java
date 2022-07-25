import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class Server {

    private static final int port = 8080;

    private static void Server() {
        System.out.println("Server started...");
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                System.out.println("New connection accepted");
                out.println("What is your name?");

                final String name = in.readLine();

                out.println("What season do you like most? (print - s for Summer or w - for Winter ?)");

                String season = in.readLine();
                if ("s".equals(season)) {
                    season = "Summer";
                } else {
                    season = "Winter";
                }

                out.println("How much money do you want to spend on vacation? (from 0 to 200000 RUB)");
                final int summa = Integer.parseInt(in.readLine());
                List<String> countries;
                if (summa > 0 && summa <= 100000) {
                    countries = List.of("Stay home", "Your city suburbs", "Dacha");
                } else {
                    countries = List.of("Ibiza", "Mexico", "UAE", "Monaco", "Kurorty Krasnodarskogo kraya");
                }
                out.println(String.format("Hi %s, your mostly liked season is %s and you may travel to %s", name, season, countries));
                out.println("end");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        Server();
    }
}
