import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.util.Random;

public class SlidingWindowClient {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int PORT = 12345;
    private static final int WINDOW_SIZE = 4;
    private static final double PACKET_LOSS_RATE = 0.1; // 10% packet loss

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try (Socket socket = new Socket(SERVER_ADDRESS, PORT);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            System.out.println("Connected to server.");
            System.out.println("Enter packets (type 'exit' to quit):");
            int base = 0;
            while (true) {
                String packet = scanner.nextLine();
                if (packet.equalsIgnoreCase("exit")) {
                    break;
                }
                if (simulatePacketLoss()) {
                    System.out.println("Packet " + packet + " lost during transmission.");
                } else {
                    System.out.println("Sending packet: " + packet);
                    out.println(packet);
                }
                if (base < WINDOW_SIZE) {
                    // Wait for acknowledgment
                    String ack = in.readLine();
                    if (ack != null) {
                        System.out.println("Received " + ack);
                        base++;
                    }
                }
            }
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean simulatePacketLoss() {
        Random random = new Random();
        return random.nextDouble() < PACKET_LOSS_RATE;
    }
}