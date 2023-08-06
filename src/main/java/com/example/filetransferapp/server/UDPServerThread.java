package com.example.filetransferapp.server;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Arrays;

public class UDPServerThread implements Runnable{
    private  DatagramSocket socket;

    private  DatagramPacket packet, sendPacket;
    private  byte[] buffer;



    private static boolean running;
    UDPServerThread() throws SocketException {


        running = true;
        buffer = new byte[1024];
        packet = new DatagramPacket(buffer, buffer.length);
        socket = new DatagramSocket(1234);
    }

    private static byte[] getBytes(ArrayList<Byte> arr){
        byte[] byteArray = new byte[arr.size()];
        for (int i = 0; i < arr.size(); i++) {
            byteArray[i] = arr.get(i); // Unboxing and copying to the byte array
        }
        return byteArray ;
    }
    @Override
    public void run(){
        synchronized(this){
            boolean readingData =false  ;
            ArrayList<Byte> incomingData = new ArrayList<Byte>() ; ;
            while(running){
                try {
                    socket.receive(packet);
                } catch (IOException e) {
                    System.out.println("invalid Socket");
                }
                int t=0;
                String input = (new String(packet.getData(), 0, packet.getLength()));
                if(input.equals("##end##")){
                    readingData = false ;

                    byte []dataArr = getBytes(incomingData);
                    incomingData.clear();
                    System.out.println((new String(dataArr, 0, dataArr.length)));
                }

                if(readingData){
                    for(int i =0 ; i < packet.getData().length ; i ++ ){
                        incomingData.add(packet.getData()[i]);
                    }
                }
                if(input.startsWith("##start##")){
                    readingData = true ;
                }


            }
            socket.close();
        }
    }

}
