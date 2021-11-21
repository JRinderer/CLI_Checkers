package com.company;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class ClientProcessing implements Runnable {

    private Socket client;
    private String client_message;
    private DataInputStream data_in = null;
    private DataOutputStream data_out = null;
    //static data type to track 1 game.
    private static Game2ElectricBoogaLoo game = new Game2ElectricBoogaLoo();
    private String color = null;

    public ClientProcessing(Socket clientSocket, Game2ElectricBoogaLoo gameSnt) {
        game = gameSnt;
        try {
            this.client = clientSocket;
            data_in = new DataInputStream(new BufferedInputStream(client.getInputStream()));
            data_out = new DataOutputStream(client.getOutputStream());
        } catch (Exception ex) {
            System.out.println("Error in client processing");
        }

    }

    public void sendBack() {
        String dataBack = "";
        dataBack = game.serializeGameBoard();
        try {
            this.data_out.writeUTF(dataBack);
        } catch (Exception ex) {
            System.out.println("error");
        }
    }

    private String parseClientMessage(String key){
        Map<String, String> map = new HashMap<>();
        String[] pairs= this.client_message.split(",");
        for(int i =0; i<pairs.length;i++){
            String pair = pairs[i];
            String[] keyVal = pair.split(":");
            map.put(keyVal[0],keyVal[1]);
        }
        return map.get(key);
    }

    public void run() {

        String dataBack = "";
        try {
            //original String input = this.data_in.readUTF();
            this.client_message = this.data_in.readUTF();

            //split in the input
            String inputArr[] = input.split(",");
            //determine what action to take in the game.
            if (inputArr[0].equals("color set")) {
                if (game.isColorAvailable(inputArr[1])) {
                    this.color = inputArr[1];
                    dataBack = "color:" + inputArr[1];
                    dataBack = game.serializeGameBoard() + "," + dataBack + "";
                    this.data_out.writeUTF(dataBack);
                } else {
                    dataBack = "The color " + inputArr[1] + " is unavailable";
                    this.data_out.writeUTF(dataBack);
                }
            } else {


                dataBack = game.processPlayerInput(input);
                this.data_out.writeUTF(dataBack);
            }
        } catch (Exception ex) {
            System.out.println("Error in Run");
        }
    }

}
