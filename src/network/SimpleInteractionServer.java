package network;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class SimpleInteractionServer {

    /**
     * The TCP port for the object time service.
     */
    public static final short TIME_PORT = 12000;

    public static void main(String[] argv) {
        ServerSocket sock;
        Socket clientSock;
        try {
            sock = new ServerSocket(TIME_PORT);
            while ((clientSock = sock.accept()) != null) {

                BufferedReader is = new BufferedReader(new InputStreamReader(
                        clientSock.getInputStream()));

                ObjectOutputStream st = new ObjectOutputStream(
                        clientSock.getOutputStream());
                PrintWriter os = new PrintWriter(st);

                for (int i = 0; i < 3; i++) {

                    char info = (is.readLine()).charAt(0);
                    System.out.println("Accept from " +
                            clientSock.getInetAddress() + " with info: " + is.readLine());

                    if (info < 39 && info >= 30) os.print(info + 1);
                    else os.print(info + " from " + InetAddress.getLocalHost());
                }
                // Construct and write the Object

                is.close();
                os.close();
                sock.close();
            }

        } catch (IOException e) {
            System.err.println(e);
        }


    }
}
