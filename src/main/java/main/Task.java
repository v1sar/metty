package main;

//import http.Request;
//import http.Response;

import java.io.*;
import java.net.Socket;
import java.net.URLDecoder;
import java.util.concurrent.RecursiveAction;

/**
 * Created by qwerty on 26.09.16.
 */
public class Task extends RecursiveAction {
    Socket socket;
    InputStream in;
    OutputStream out;
    BufferedReader br;
    BufferedWriter writer;

    public Task(Socket socket) throws IOException{
        this.socket = socket;
        in = socket.getInputStream();
        out = socket.getOutputStream();
        br = new BufferedReader(new InputStreamReader(in));
        writer = new BufferedWriter(new OutputStreamWriter(out));
    }

    @Override
    protected void compute(){//CAS
        try {
            Request request = new Request();
            while (true) {
                String buf = br.readLine();
                if(buf == null || buf.trim().isEmpty()){
                    break;
                }
                //System.out.println(buf);
                request.newHeader(buf);
            }
            //System.out.println();
            Response response = new Response(request);
            response.writeResponse(out);
        }catch (IOException e){
            //e.printStackTrace();
        }finally {
            closeAll();
        }
    }
    public void closeAll(){
        try {
            in.close();
            out.close();
            br.close();
            writer.close();
            socket.close();
        }catch (IOException e){
            //e.printStackTrace();
        }
    }
}
