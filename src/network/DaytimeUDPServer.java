package network;

import javax.imageio.plugins.tiff.TIFFDirectory;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Date;

public class DaytimeUDPServer {

    private static final int TIME_PORT = 12000;
    private static final int PACKET_SIZE = 1024;

    public static void main(String[] args) throws IOException{
        DatagramSocket socket = new DatagramSocket(TIME_PORT);

        System.out.println("the TIME server is running and waiting~");

        while (true){
            byte[] buff = new byte[PACKET_SIZE];
            DatagramPacket request = new DatagramPacket(buff, PACKET_SIZE);

            // when UDP packet arrives, socket put it to the request packet
            socket.receive(request);
            if (request.getLength() == 0)   continue;

            String dateString = (new Date()).toString();
            byte[] date = dateString.getBytes();

            System.out.printf("receive packet from %s / %s \n",
                    request.getAddress(), request.getPort());

            DatagramPacket response = new DatagramPacket(date, date.length,
                    request.getAddress(), request.getPort());
            socket.send(response);


        }
    }


}
