package com.example.filetransferapp.client;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;


public class UDPClient {

    public static void main(String[] args)throws Exception {
        // TODO Auto-generated method stub
        Scanner sc=new Scanner(System.in);
        DatagramSocket s=new DatagramSocket();
        InetAddress ia=InetAddress.getLocalHost();
        File f1=new File("C:\\Users\\Asus\\Desktop");
        File[] files=f1.listFiles();

        StringBuilder sb=new StringBuilder("\n");
        int x=0;
        for(int i=0;i<files.length;i++)
        {
            if(files[i].canRead())
            {
                sb.append(files[i].getName()+" ,size"+files[i].length()+"bytes\n");
                x++;
            }
        }
        System.out.println(x+" Files found");
        System.out.println(sb);
        System.out.println(" Enter filename for download ");
        String fname = sc.nextLine();
        System.out.println(fname);

        boolean flag = false;
        int id= 0;
        for(int i=0;i<files.length;i++) {
            if(files[i].getName().toString().equalsIgnoreCase(fname)) {
                flag = true;
                id = i;
                break;
            }
        }

        if(!flag) {
            System.out.println(fname + " does not exist!");
            return;
        }

        File filetocopy=new File(files[id].getAbsolutePath());
        FileReader fileReader=new FileReader(filetocopy);
        BufferedReader br=new BufferedReader(fileReader);
        StringBuilder sb1=new StringBuilder();

        String line;
        while((line=br.readLine())!=null)
        {
            sb1.append(line);
            sb1.append("\n");
        }
        System.out.println(sb1.toString());

        byte[] sentname=files[id].getName().getBytes();
        DatagramPacket p1=new DatagramPacket(sentname,sentname.length,ia,65535);
        s.send(p1);

        byte[] senttoserver=sb1.toString().getBytes();
        DatagramPacket p2=new DatagramPacket(senttoserver,senttoserver.length,ia,65535);
        s.send(p2);
        s.close();

    }

}
