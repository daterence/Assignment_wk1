package com.assessment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HttpClientConnection {
    // int port = 3000;
    // ServerSocket serverSocket;
    // Socket socket;
    // String inputFile = "";

    // ExecutorService threadPool = Executors.newFixedThreadPool(3);
    // serverSocket = 

    public void connectBrowser(int port, String file) throws IOException{
        ServerSocket ss = new ServerSocket(port);
        
        System.out.println("Server is running on port: " + port);

        // while(true){
            Socket s = ss.accept();
            System.out.println("Client connected");
            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            String msg = in.readLine();

            // while(msg != null){
            //     System.out.println(s);
            //     if(msg.isEmpty()){
            //         break;
            //     }
            // }

            OutputStream browserOutput = s.getOutputStream();
            browserOutput.write("HTTP/1.1 200 OK\r\n".getBytes());
            browserOutput.write("\r\n".getBytes());
            browserOutput.write("<b>Welcome</b>".getBytes());
            browserOutput.flush();
            System.out.println("Client Connection close");

            in.close();
            browserOutput.close();
        // }
    }

}
