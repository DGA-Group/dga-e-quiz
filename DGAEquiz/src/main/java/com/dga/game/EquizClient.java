package com.dga.game;

import com.dga.equiz.utils.ApplicationData;

import java.io.IOException;
import java.net.Socket;

public class EquizClient implements Runnable {
    Socket socket = null;

    @Override
    public void run() {
        try {
            socket = new Socket("127.0.0.1", 54321);
            System.out.println("Success connect to equiz server at port 6789...");
        } catch (IOException e) {
            System.out.println("Unable to connect to server! Please try again later!");
            return;
        }

        ApplicationData.getInstance().socket = socket;

        /*try {
            String id = ApplicationData.getInstance().profile.getID() + "";
            ConnectClientRequest request = new ConnectClientRequest(id);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(request);
        } catch (Exception e) {
            System.out.println("Unable to establish new connect!");
            close();
            return;
        }*/

        // new ReadThread(socket, this).start();
        new ClientListener(socket).start();
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
