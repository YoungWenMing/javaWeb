package network;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class SimpleInteractionClient {

    private static final int PORT = 12000;

    public static void main(String[] args) throws IOException {
        if (args.length == 0)
            throw new IllegalArgumentException("tell me a hostname of ip address, Please!");

        // construct socket
        Socket socket = new Socket(args[0], PORT);

        // construct reader and writer
        BufferedReader is = new BufferedReader(new InputStreamReader(
                        socket.getInputStream()));
        PrintWriter os = new PrintWriter(new OutputStreamWriter(
                        socket.getOutputStream()));

        for (int i = 0; i < 3; i ++){
            //os.write();
            System.out.printf("from %s sent %s \r\n", InetAddress.getLocalHost(), Integer.toString(i));

            os.print(i);
            os.flush();
            String echo =  is.readLine();
            System.out.printf("From %s we got %s \n", args[0], echo);
        }

        os.close();
        is.close();
        socket.close();
    }

}
