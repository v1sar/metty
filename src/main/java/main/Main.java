package main;

import additional.*;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ForkJoinPool;

/**
 * Created by Дмитрий on 11.09.2016.
 */

public class Main {

    private static int PORT = 80;
    private static final int QUEUE = 100;
    private static int POOL_SIZE = 4;

    public static void main(String[] args) throws IOException{
        CmdLine parser = new CmdLine(args);
        String ROOTDIR = parser.getValue("r");
        try {
            POOL_SIZE = Integer.parseInt(parser.getValue("c"));
        }
        catch (Exception e) {}
        try {
            PORT = Integer.parseInt(parser.getValue("p"));
        }
        catch (Exception e) {}
        FileSystem.DOCUMENT_ROOT = ROOTDIR;
        ServerSocket serverSocket = new ServerSocket(PORT,QUEUE);
        ForkJoinPool pool = new ForkJoinPool(POOL_SIZE*2 + 1);
        System.out.println((new StringBuilder())
                .append("Server started at port: "+PORT+'\n')
                .append("CPU count is: "+POOL_SIZE+'\n')
                .append("ROOTDIR is: "+ROOTDIR+'\n')
                .toString());
        while(true){
            Socket socket = serverSocket.accept();
            pool.execute(new Task(socket));
        }

    }
}
