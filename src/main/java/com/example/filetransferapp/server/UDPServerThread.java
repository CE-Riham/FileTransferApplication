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

    private ArrayList<Byte> incomingData ;
    private boolean readingData ;
    private static boolean running;
    UDPServerThread() throws SocketException {
        incomingData = new ArrayList<Byte>() ;
        readingData = false ;
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
            while(running){
                try {
                    socket.receive(packet);
                } catch (IOException e) {
                    System.out.println("invalid Socket");
                }
                int t=0;
                String input = (new String(packet.getData(), 0, packet.getLength()));
                if(input.equals("##start##")){
                    readingData = true ;
                }
                if(input.equals("##end##")){
                    readingData = false ;
                    byte []dataArr = getBytes(incomingData);
                    incomingData.clear();


                    BufferedImage image = null;
                    try {
                        image = ImageIO.re;
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                    // create the object of ByteArrayOutputStream class
                    ByteArrayOutputStream outStreamObj = new ByteArrayOutputStream();

                    // write the image into the object of ByteArrayOutputStream class
                    try {
                        ImageIO.write(image, "jpg", outStreamObj);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                    // create the byte array from image
                    byte [] byteArray = outStreamObj.toByteArray();

                    // create the object of ByteArrayInputStream class
                    // and initialized it with the byte array.
                    ByteArrayInputStream inStreambj = new ByteArrayInputStream(byteArray);

                    // read image from byte array
                    BufferedImage newImage = null;
                    try {
                        newImage = ImageIO.read(inStreambj);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                    // write output image
                    try {
                        ImageIO.write(newImage, "jpg", new File("outputImage.jpg"));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }


                    System.out.println((new String(dataArr, 0, dataArr.length)));
                }
                if(readingData){
                    for(int i =0 ; i < packet.getData().length ; i ++ ){
                        incomingData.add(packet.getData()[i]);
                    }
                }

            }
            socket.close();
        }
    }

}
