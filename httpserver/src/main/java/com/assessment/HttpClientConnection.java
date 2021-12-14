package com.assessment;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.StringTokenizer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HttpClientConnection{
    private ServerSocket ss;
    private Socket socket;
    private String file;
    private BufferedReader in = null;
    private PrintWriter out = null;
    private BufferedOutputStream msgOut = null;

    private String method;
    private String request;

    public String getMethod(){
        return this.method;
    }
    public String getRequest(){
        return this.request;
    }

    public HttpClientConnection(Socket socket) throws IOException{
    
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream());
        msgOut = new BufferedOutputStream(socket.getOutputStream());

        String input = in.readLine();
        StringTokenizer parse = new StringTokenizer(input);
        method = parse.nextToken().toUpperCase();
        request = parse.nextToken().toLowerCase();
    }

    public void displayError(String title, String error){
        out.println(title);
        out.println();
        out.println(error);
        out.println();
        out.flush();
    }

    public void display(String title, String content, int fileLength, byte[] fileData) throws IOException{
        out.println(title);
        out.println("Server: HTTP Server");
        out.println("Content-type: " + content);
        out.println("Content-length: " + fileLength);
        out.println();
        out.flush();

        msgOut.write(fileData, 0, fileLength);
        msgOut.flush();
    }

    public void close(){
        try {
            in.close();
            out.close();
            msgOut.close();;
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
    

}
