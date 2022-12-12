package Jpo;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Server {
    private DatagramSocket datagramSocket;
    private byte[] buffer = new byte[256];

    public Server (DatagramSocket datagramSocket) {
    this.datagramSocket = datagramSocket;
    }

    public void receiveThenSend() {
        while (true) {
            try {

                DatagramPacket datagramPacket = new DatagramPacket(buffer, buffer.length);
                datagramSocket.receive(datagramPacket);
                InetAddress InetAddress = datagramPacket.getAddress();
                int port = datagramPacket.getPort(); 
                String messageFromClient = new String(datagramPacket.getData() , 0 , datagramPacket.getLength());

                String messageToSend = String.valueOf(Jpo.jogadausuario);
                buffer = messageToSend.getBytes();
                datagramPacket = new DatagramPacket(buffer , buffer.length , InetAddress , port);
                datagramSocket.send(datagramPacket);
                String messageFromServer = new String(datagramPacket.getData() , 0 , datagramPacket.getLength());
                Jpo.jogadausuario = Integer.parseInt(messageFromServer);
                Jpo.jogadabot = Integer.parseInt(messageFromClient);
                

                
            } catch(IOException e){
                e.printStackTrace();
                break;
                
            }
        }
    }
    public static void main(String[] args)throws SocketException{
       
        DatagramSocket datagramSocket = new DatagramSocket(1111);
        Server server = new Server(datagramSocket);
        server.receiveThenSend();
    
    }

}
