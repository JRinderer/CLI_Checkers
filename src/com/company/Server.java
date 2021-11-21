package com.company;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private ServerSocket server_socket = null;
    private Socket client_socket = null;

    private ArrayList<ClientProcessing> players = new ArrayList<>();
    private ExecutorService pool = Executors.newFixedThreadPool(2);

    public Server(int port){
        Game2ElectricBoogaLoo thisGame = new Game2ElectricBoogaLoo();
        thisGame.Start("R","W");
        try{
            server_socket = new ServerSocket(port);
            while(true){
                client_socket = server_socket.accept();
                System.out.println("Player joined!");
                ClientProcessing playerThread = new ClientProcessing(client_socket, thisGame);
                players.add(playerThread);
                pool.execute(playerThread);
                //always show board last
                //for(ClientProcessing p : players){
                  //  p.sendBack();
                //}
                //client_socket.close();
            }
        }catch (Exception ex){
            System.out.println(ex);
        }
    }


    public static void main(String args[]){
        Server server = new Server(4269);
    }
}

