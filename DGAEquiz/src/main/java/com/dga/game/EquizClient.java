package com.dga.game;

import com.dga.game.EquizPacket.Client.ConnectClientRequest;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class EquizClient implements Runnable {
    Socket socket = null;
    Scanner sc = new Scanner(System.in);

    @Override
    public void run() {
        try {
            socket = new Socket("127.0.0.1", 54321);
            System.out.println("Success connect to equiz server at port 6789...");
        } catch (IOException e) {
            System.out.println("Unable to connect to server! Please try again later!");
            return;
        }

        try {
            ConnectClientRequest request = new ConnectClientRequest("username");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(request);
        }catch (Exception e){
            System.out.println("Unable to establish new connect!");
            close();
            return;
        }

        // new ReadThread(socket, this).start();
        new Receiver(socket).start();
    }

    private void close() {
        if (socket != null) {
            try {
                socket.close();
            } catch (IOException e) {
                System.out.println("Unable to close socket!");
            }
        }
    }
}
