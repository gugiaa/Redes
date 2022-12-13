import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Cliente {

    private DatagramSocket datagramSocket;
    private InetAddress inetAddress;
    private byte[] buffer;
    public Cliente(DatagramSocket datagramSocket, InetAddress inetAddress){
        this.datagramSocket = datagramSocket;
        this.inetAddress = inetAddress;


    }
public void sendThenReceive(){
    while(true){
        try{
            String messageToSend = String.valueOf(Jokenpo.jogadausuario);
            buffer = messageToSend.getBytes();
            DatagramPacket datagramPacket = new DatagramPacket(buffer , buffer.length , inetAddress , 1111);
            String messageFromClient = new String(datagramPacket.getData(), 0 , datagramPacket.getLength());

            datagramSocket.send(datagramPacket);
            datagramSocket.receive(datagramPacket);

            String messageFromServer = new String(datagramPacket.getData(), 0 , datagramPacket.getLength());
            Jokenpo.jogadausuario = Integer.parseInt(messageFromServer);
            Jokenpo.getInstance().setJogadainimigo(Integer.parseInt(messageFromClient));;

        } catch(IOException e){
            e.printStackTrace();
            break;

        }
    }
}
    public static void main(String[] args)throws SocketException, UnknownHostException{
      Jokenpo.titulo();
      Jokenpo.menu();
        DatagramSocket datagramSocket = new DatagramSocket();
        InetAddress inetAddress = InetAddress.getByName("localhost");
        Cliente client = new Cliente(datagramSocket, inetAddress);
        client.sendThenReceive();

    }
    
}
