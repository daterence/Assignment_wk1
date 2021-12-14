package com.assessment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        HttpServer httpServer = new HttpServer();

        String command, command1, command2 = "";
        int port = 3000;
        
        if(args != null && args.length >= 1){
            if(args.length == 1){
                //--docRoot
                command = args[0];
                httpServer.getHttp(command);
            } else if (args.length == 2){
                //--port 8080
                command = args[0];
                command1 = args[1];
                httpServer.getHttp(command, command1);
            } else if (args.length == 3){
                command = args[0];
                command1 = args[1];
                command2 = args[2];
                httpServer.getHttp(command, command1, command2);
            }
            
        }else {
            httpServer.getHttp();
        }
    }
}
