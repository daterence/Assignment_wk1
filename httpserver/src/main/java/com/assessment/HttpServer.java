package com.assessment;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class HttpServer {
    private String command;
    private int portNumber;
    private String dir = "static";
    HttpClientConnection clientConnection = new HttpClientConnection();

    // if user did not enter
    public int getHttp() {
        this.portNumber = 3000;
        System.out.println("./" + dir);
        return this.portNumber;
        // System.out.println(this.portNumber);
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
        // System.out.println(this.portNumber);
        // System.out.println(this.command);
    }

    public int getHttp(String command, String command2) {
        this.command = command;
        this.portNumber = Integer.parseInt(command2);
        System.out.println("./" + dir);
        return this.portNumber;
        // System.out.println(this.command);
        // System.out.println(this.portNumber);
    }

    public int getHttp(String command, String command2, String command3) {
        this.command = command;
        this.portNumber = Integer.parseInt(command2);
        this.dir = command3;
        if (command3.contains("docRoot")) {
            System.out.println("./static:/opt/tmp/www");
        }
        return this.portNumber;
        // System.out.println(this.command);
        // System.out.println(this.portNumber);
        // System.out.println(this.docRoot);
    }

    // user select directory from docRoot
    public String goDir(String dir) {
        Path path = Paths.get(dir);
        String selectedFile = "";
        System.out.println("Path exist? " + Files.exists(path));
        System.out.println("Is path a directory? " + Files.isDirectory(path));
        System.out.println("Is path readable? " + Files.isReadable(path));

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
            selectedFile = scan1.nextLine() + ".html";
            
        }
        return selectedFile;
    }
    
    // constructor
    // public HttpServer() {
    //     this.command = command;
    //     this.portNumber = portNumber;
    //     this.dir = dir;
    // }

    // // getters and setters
    // public String getCommand() {
    //     return this.command;
    // }

    // public void setCommand(String command) {
    //     this.command = command;
    // }

    // public int getPortNumber() {
    //     return this.portNumber;
    // }

    // public void setPortNumber(int portNumber) {
    //     this.portNumber = portNumber;
    // }

    // public String getdir() {
    //     return this.dir;
    // }

    // public void setdir(String dir) {
    //     this.dir = dir;
    // }

}
