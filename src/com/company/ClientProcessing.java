package com.company;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/*client message will look like:
type:color_set,color:R
or
type:move,piece_name:some_name,x_cord:3,y_cord:y,color:R
[1,2,3,4,5]
*/

public class ClientProcessing implements Runnable {

    private Socket client;
    private String client_message;
    private String client_message_type;
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
        String piece_name="";
        String x_cord = "";
        String y_cord = "";

        try {
            //original String input = this.data_in.readUTF();
            this.client_message = this.data_in.readUTF();
            //how do we want to process the users input?
            this.client_message_type = parseClientMessage("type");
            //String inputArr[] = input.split(",");
            //determine what action to take in the game.
            if (client_message_type.equals("color_set")) {
                if (game.isColorAvailable(parseClientMessage("color"))) {
                    this.color = parseClientMessage("color");
                    dataBack = "color:" + this.color;
                    dataBack = game.serializeGameBoard() + "," + dataBack + "";
                    this.data_out.writeUTF(dataBack);
                } else {
                    dataBack = "The color " + parseClientMessage("color") + " is unavailable";
                    this.data_out.writeUTF(dataBack);
                }
            } else if(client_message_type.equals("move")) {
                this.color = parseClientMessage("color");
                piece_name = parseClientMessage("piece_name");
                x_cord = parseClientMessage("x_cord");
                y_cord = parseClientMessage("y_cord");
                dataBack = game.processPlayerInput(piece_name,x_cord,y_cord,this.color,"");
                this.data_out.writeUTF(dataBack);
            } else if(client_message_type.equals("get_turn")){
                dataBack = game.serializeGameBoard();
                dataBack = dataBack + "," + game.processPlayerInput(piece_name,x_cord,y_cord,this.color,"get_turn");
                this.data_out.writeUTF(dataBack);
            } else if(client_message_type.equals("return_board")){
                dataBack= game.serializeGameBoard();
                this.data_out.writeUTF(dataBack);
            }
        } catch (Exception ex) {
            System.out.println("Error in Run");
        }
        try {
            this.client.close();
        }catch (Exception ex){
            System.out.println("error");
        }
    }


}
