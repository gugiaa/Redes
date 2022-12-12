package Jpo;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Client {

    private DatagramSocket datagramSocket;
    private InetAddress inetAddress;
    private byte[] buffer;
    public Client(DatagramSocket datagramSocket, InetAddress inetAddress){
        this.datagramSocket = datagramSocket;
        this.inetAddress = inetAddress;


    }
public void sendThenReceive(){
    while(true){
        try{
            String messageToSend = String.valueOf(Jpo.jogadausuario);
            buffer = messageToSend.getBytes();
            DatagramPacket datagramPacket = new DatagramPacket(buffer , buffer.length , inetAddress , 1111);
            String messageFromClient = new String(datagramPacket.getData(), 0 , datagramPacket.getLength());

            datagramSocket.send(datagramPacket);
            datagramSocket.receive(datagramPacket);

            String messageFromServer = new String(datagramPacket.getData(), 0 , datagramPacket.getLength());
            Jpo.jogadausuario = Integer.parseInt(messageFromServer);
            Jpo.jogadabot = Integer.parseInt(messageFromClient);

        } catch(IOException e){
            e.printStackTrace();
            break;

        }
    }
}
    public static void main(String[] args)throws SocketException, UnknownHostException{

        DatagramSocket datagramSocket = new DatagramSocket();
        InetAddress inetAddress = InetAddress.getByName("localhost");
        Client client = new Client(datagramSocket, inetAddress);
        client.sendThenReceive();

    }
    
}
