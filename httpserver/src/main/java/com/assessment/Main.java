package com.assessment;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) throws IOException {
        HttpServer httpServer = new HttpServer();

        String command, command1, command2, file = "";
        int port = 3000;

        if (args != null && args.length >= 1) {
            if (args.length == 1) {
                // --docRoot
                command = args[0];
                port = httpServer.getHttp(command);
            } else if (args.length == 2) {
                // --port 8080
                command = args[0];
                command1 = args[1];
                port = httpServer.getHttp(command, command1);
            } else if (args.length == 3) {
                command = args[0];
                command1 = args[1];
                command2 = args[2];
                port = httpServer.getHttp(command, command1, command2);
            }

        } else {
            port = httpServer.getHttp();
        }
        System.out.println("Selected port: " + port);
        Scanner scan = new Scanner(System.in);
        String inputDir = scan.nextLine();

        file = httpServer.goDir(inputDir);
        System.out.println(file);
        ExecutorService poolThread = Executors.newFixedThreadPool(3);

        System.out.println("Server waiting for connection @ port: " + port);

        try (ServerSocket ss = new ServerSocket(port)) {
            while (true) {
                Socket socket = ss.accept();
                System.out.println("Client Connected");
                HttpServer server = new HttpServer();
                poolThread.execute(server);
                ss.close();
            }

        }

    }
}