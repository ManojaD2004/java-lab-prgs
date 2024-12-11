import java.io.*;
import java.net.*;
import java.util.*;

public class udps {
    public static void main(String[] args) {
        DatagramSocket skt = null;
        Scanner sc = new Scanner(System.in);
        try {
            skt = new DatagramSocket();
            byte[] buffer = new byte[1000];
            while (true) {
                DatagramPacket request = new DatagramPacket(buffer, buffer.length);
                skt.receive(request);
                String message = sc.nextLine();
                byte[] sendMsg = message.getBytes();
                DatagramPacket reply = new DatagramPacket(sendMsg, sendMsg.length, request.getAddress(),
                        request.getPort());
                skt.send(reply);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        sc.close();
    }
}
