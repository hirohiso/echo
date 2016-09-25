/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.hirohisoex.applications.echo;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author hironori
 */
public class ServerPort implements AutoCloseable{
    private ServerSocket socket = null;
            
    public ServerPort(int port) throws IOException{
            socket= new ServerSocket(port);
            
    }

    public Socket lisenClientConnection() throws IOException{
        return socket.accept();
    }
    
    @Override
    public void close() throws IOException{
       if(socket != null){
           socket.close();
       }
    }
        
    
}
