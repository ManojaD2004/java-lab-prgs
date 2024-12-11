import java.io.*;
import java.util.*;

class CRC {
    public static void main(String a[]) throws IOException {
        Scanner sc = new Scanner(System.in);
        int[] message;
        int[] gen;
        int[] app_message;
        int[] rem;
        int[] trans_message;
        int message_bits, gen_bits, total_bits;
        System.out.println("Enter no bits in mwssage:");
        message_bits = sc.nextInt();
        message = new int[message_bits];
        System.out.println("\nEnter message bits:");
        for (int i = 0; i < message_bits; i++)
            message[i] = sc.nextInt();
        System.out.println("\nEnter number of bits in gen:");
        gen_bits = sc.nextInt();
        gen = new int[gen_bits];
        System.out.println("\nEnter gen bits:");
        for (int i = 0; i < gen_bits; i++)
            gen[i] = sc.nextInt();
        total_bits = message_bits + gen_bits - 1;
        app_message = new int[total_bits];
        rem = new int[total_bits];
        trans_message = new int[total_bits];
        for (int i = 0; i < message.length; i++)
            app_message[i] = message[i];
        System.out.println("\nMessage bits are:");
        for (int i = 0; i < message_bits; i++)
            System.out.print("\t" + message[i]);
        System.out.println("\nGenerators bits are:");
        for (int i = 0; i < gen_bits; i++)
            System.out.print("\t" + gen[i]);
        System.out.println("\nAppended message is:");
        for (int i = 0; i < app_message.length; i++)
            System.out.print("\t" + app_message[i]);
        for (int j = 0; j < app_message.length; j++)
            rem[j] = app_message[j];
        rem = computecrc(app_message, gen, rem);
        for (int i = 0; i < app_message.length; i++)
            trans_message[i] = (app_message[i] ^ rem[i]);
        System.out.println("\nTransmitted message from the transmitter is:");
        for (int i = 0; i < trans_message.length; i++)
            System.out.print("\t" + trans_message[i]);
        System.out.println("\nEnter received message of" + total_bits + "bits at receiver end:");
        for (int i = 0; i < trans_message.length; i++)
            trans_message[i] = sc.nextInt();
        System.out.println("\nReceived message is:");
        for (int i = 0; i < trans_message.length; i++)
            System.out.print("\t" + trans_message[i]);
        for (int j = 0; j < trans_message.length; j++)
            rem[j] = trans_message[j];
        rem = computecrc(trans_message, gen, rem);
        for (int i = 0; i < rem.length; i++) {
            if (rem[i] != 0) {
                System.out.println("\nThere is error in the received message");
                break;
            }
            if (i == rem.length - 1)
                System.out.println("\nThere is no erron in the received mesage!!");
        }
        sc.close();
    }

    static int[] computecrc(int app_message[], int gen[], int rem[]) {
        int current = 0;
        while (true) {
            for (int i = 0; i < gen.length; i++)
                rem[current + i] = (rem[current + i] ^ gen[i]);
            while (rem[current] == 0 && current != rem.length - 1)
                current++;
            if ((rem.length - current) < gen.length)
                break;
        }
        return rem;
    }
}