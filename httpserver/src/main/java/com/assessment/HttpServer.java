package com.assessment;

public class HttpServer {
    private String command;
    private int portNumber;
    private String dir = "/src/static";
    HttpClientConnection clientConnection = new HttpClientConnection();

    //if user did not enter
    public void getHttp(){
        this.portNumber = 3000;
        System.out.println("." + dir);
        // System.out.println(this.portNumber);
    }

    //if user enter only one command
    //eg. --docRoot
    public void getHttp(String command){
        this.portNumber = 3000;
        if (command.contains("docRoot")){
            System.out.println("./src/static:/opt/tmp/www");
        }
        //user entered --docRoot
        // System.out.println(this.portNumber);
        // System.out.println(this.command);
    }

    public void getHttp(String command, String command2){
        this.command = command;
        this.portNumber = Integer.parseInt(command2);
        System.out.println("." + dir);
        // System.out.println(this.command);
        // System.out.println(this.portNumber);
    }

    public void getHttp(String command, String command2, String command3){
        this.command = command;
        this.portNumber = Integer.parseInt(command2);
        this.dir = command3; 
        if(command3.contains("docRoot")){
            System.out.println("./src/static:/opt/tmp/www");
        }
        // System.out.println(this.command);
        // System.out.println(this.portNumber);
        // System.out.println(this.docRoot);
    }

    //constructor
    public HttpServer() {
        this.command = command;
        this.portNumber = portNumber;
        this.dir = dir;
    }

    //getters and setters
    public String getCommand() {
        return this.command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public int getPortNumber() {
        return this.portNumber;
    }

    public void setPortNumber(int portNumber) {
        this.portNumber = portNumber;
    }

    public String getdir() {
        return this.dir;
    }

    public void setdir(String dir) {
        this.dir = dir;
    }
    
}
