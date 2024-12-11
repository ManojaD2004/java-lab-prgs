import java.io.*;
import java.net.*;
import java.util.Random;

public class SlidingWindowServer {
    private static final int PORT = 12345;
    private static final int WINDOW_SIZE = 4;
    private static final double PACKET_LOSS_RATE = 0.1; // 10% packet loss

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server is running and waiting for connections...");
            try (Socket clientSocket = serverSocket.accept();
                    BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {
                System.out.println("Client connected.");
                String line;
                int base = 0;
                while ((line = in.readLine()) != null) {
                    if (simulatePacketLoss()) {
                        System.out.println("Packet " + line + " lost during transmission.");
                    } else {
                        System.out.println("Received packet: " + line);
                        // Simulate acknowledgment
                        if (base < WINDOW_SIZE) {
                            out.println("ACK: " + base);
                            base++;
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean simulatePacketLoss() {
        Random random = new Random();
        return random.nextDouble() < PACKET_LOSS_RATE;
    }
}
