package com.example.filetransferapp.client;


import java.io.IOException;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class UDPFileSender{
    private static DatagramSocket socket;

    private static byte[] getBytes(ArrayList<Byte> arr){
        byte[] byteArray = new byte[arr.size()];
        for (int i = 0; i < arr.size(); i++) {
            byteArray[i] = arr.get(i); // Unboxing and copying to the byte array
        }
        return byteArray ;
    }
    public static void sendData(byte[] data ,String serverAddress ,int serverPort ) throws IOException{
        socket = new DatagramSocket();
        DatagramPacket sendPacket1 = new DatagramPacket((new String("##start##")).getBytes(), 9, InetAddress.getByName(serverAddress), serverPort);
        socket.send(sendPacket1);
        socket.close();
        ArrayList<Byte> splitData = new ArrayList<Byte>();
        for(int i =0 ; i < data.length; i++){
            if(i%1024==0 ){
                socket = new DatagramSocket();
                DatagramPacket sendPacket2 = new DatagramPacket(getBytes(splitData), splitData.size(), InetAddress.getByName(serverAddress), serverPort);
                socket.send(sendPacket2);
                socket.close();
                splitData.clear();
            }
            splitData.add(data[i]);
        }
        if(splitData.size()!=0){
            socket = new DatagramSocket();
            DatagramPacket sendPacket3 = new DatagramPacket(getBytes(splitData), splitData.size(), InetAddress.getByName(serverAddress), serverPort);
            socket.send(sendPacket3);
            socket.close();
        }

        socket = new DatagramSocket();
        DatagramPacket sendPacket4 = new DatagramPacket((new String("##end##")).getBytes(), 7, InetAddress.getByName(serverAddress), serverPort);
        socket.send(sendPacket4);
        socket.close();
    }


}
