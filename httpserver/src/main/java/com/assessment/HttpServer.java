package com.assessment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class HttpServer implements Runnable{
    private ServerSocket ss;
    private String command;
    private Socket socket;
    private int portNumber;
    static final String defaultFile = "index.html";
    private String dir = "static";
    private HttpClientConnection clientConnection;

    public HttpServer(){
        
    }


    // if user did not enter
    public int getHttp() {
        this.portNumber = 3000;
        System.out.println("./" + dir);
        return this.portNumber;
    }
    
    // if user enter only one command
    // eg. --docRoot
    public int getHttp(String command) {
        this.portNumber = 3000;
        if (command.contains("docRoot")) {
            System.out.println("./static:/opt/tmp/www");
        }
        return this.portNumber;
        // user entered --docRoot
    }

    public int getHttp(String command, String command2) {
        this.command = command;
        this.portNumber = Integer.parseInt(command2);
        System.out.println("./" + dir);
        return this.portNumber;
    }

    public int getHttp(String command, String command2, String command3) {
        this.command = command;
        this.portNumber = Integer.parseInt(command2);
        this.dir = command3;
        if (command3.contains("docRoot")) {
            System.out.println("./static:/opt/tmp/www");
        }
        return this.portNumber;
    }

    // user select directory from docRoot
    public String goDir(String dir) {
        Path path = Paths.get(dir);
        String selectedFile = "";

        if (!Files.exists(path) && !Files.isDirectory(path) && !Files.isReadable(path)) {
            System.out.println("Fail condition(s)");
            System.out.println("Exiting Program");
            System.exit(1);
        } else {
            System.out.println("Passed all 3 conditions");
            File Dir = Paths.get(dir).toFile();
            int i = 1;
            for(File f: Dir.listFiles()){
                String[] fileNames = f.getName().split("\\.");
                System.out.println(i + ". "+ fileNames[0]);
                i++;
            }
            System.out.println("Select file");
            Scanner scan1 = new Scanner(System.in);
            selectedFile = dir + "/" + scan1.nextLine() + ".html";
            
        }
        return selectedFile;
    }

    private byte[] readContent(File file, int length) throws IOException{
        FileInputStream fileIn = null;
        byte[] content = new byte[length];

        try{
            fileIn = new FileInputStream(file);
            fileIn.read(content);
        } finally{
            if(fileIn != null){
                fileIn.close();
            }
            return content;
        }
    }

    @Override
    public void run(){
        String request = "";
        ServerSocket ss;
        try {
            ss = new ServerSocket(portNumber);
            socket = ss.accept();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        try{
            clientConnection = new HttpClientConnection(socket);
            String method = clientConnection.getMethod();
            if(method.equals("GET")){
                request = clientConnection.getRequest();

                if(request.endsWith("/")){
                    request += defaultFile;
                }

                File f = new File(".", request);
                int fLength = (int) f.length();
                String content;
                if(request.endsWith(".htm") || request.endsWith("html")){
                    content = "text/html";
                } else {
                    content = "text/plain";
                }
                byte[] data = readContent(f, fLength);
                clientConnection.display("HTTP/1.1 200 OK", content, fLength, data);
                System.out.println("File " + request + ". Type: " + content);
            } else {
                String title = "HTTP/1.1 404 Not Found";
                String error = request + " not found";
                clientConnection.displayError(title, error);
                System.out.println(method + " not supported");
                clientConnection.close();
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return;
            }
        } catch (FileNotFoundException fnfe){
                String title = "HTTP/1.1 404 Not Found";
                String error = request + " not found";
                clientConnection.displayError(title, error);
                System.out.println("File " + request + " not found");
                clientConnection.close();
                try{
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return;
            
        } catch (IOException ioe){
            System.err.println("Server erro: " + ioe);
        }finally {
            try {
                clientConnection.close();
                socket.close();
            } catch (Exception e){
                e.printStackTrace();
            }
            System.out.println("Connection closed.");
        }
    }
}
