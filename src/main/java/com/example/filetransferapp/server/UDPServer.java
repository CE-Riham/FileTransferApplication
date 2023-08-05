package com.example.filetransferapp.server;
import java.io.File;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.io.FileWriter;

public class UDPServer {

    public static void  main(String[] args) throws Exception{

        System.out.print("hello");
        DatagramSocket s=new DatagramSocket(65535);
        InetAddress ia=InetAddress.getLocalHost();
        byte[]filename=new byte[1000000000];
        byte[]b=new byte[1000000000];



        //receiving name

        DatagramPacket name=new DatagramPacket(filename,filename.length);
        s.receive(name);
        System.out.print("hello");
        System.out.println(new String(name.getData()));


        DatagramPacket p=new DatagramPacket(b,b.length);
        s.receive(p);
        System.out.print("hello");
        System.out.println(new String(p.getData()));


        String rec="Received your packet";
        byte[] b1=rec.getBytes();
        System.out.println(b1);
        DatagramPacket p1=new DatagramPacket(b1,b1.length,ia,p.getPort());
        s.send(p1);

        String fname=new String(name.getData()).trim();
        fname="//home//sanket//CN//"+fname;
        System.out.println(fname);

        String filedata=new String(p.getData()).trim();
        System.out.println(filedata);


        PrintWriter pw = new PrintWriter(fname);
        pw.println(filedata);
        pw.close();





    }
}