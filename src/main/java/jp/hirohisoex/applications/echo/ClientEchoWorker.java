/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.hirohisoex.applications.echo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hironori
 */
public class ClientEchoWorker implements AutoCloseable {

    private final BufferedReader networkIn;
    private final PrintWriter networkOut;
    private final BufferedReader consoleIn;

    public ClientEchoWorker(Socket s) throws IOException {
        this.networkIn = new BufferedReader(new InputStreamReader(s.getInputStream()));
        this.networkOut = new PrintWriter(s.getOutputStream());
        this.consoleIn = new BufferedReader(new InputStreamReader(System.in));
    }

    public void process() throws IOException {
        while (true) {
            System.out.println("Input Strung.");
            String request = this.consoleIn.readLine();
            if (request.equals("Exit")) {
                break;
            }
            this.networkOut.println(request);
            this.networkOut.flush();
            String responce = this.networkIn.readLine();
            if (responce != null) {
                System.out.println(responce);
            }
        }
    }

    @Override
    public void close() {
        try {
            if (this.networkIn != null) {
                this.networkIn.close();
            }
            if (this.networkOut != null) {
                this.networkOut.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(ClientEchoWorker.class.getName()).log(Level.SEVERE, null, ex);
        }
        try{
            if(this.consoleIn != null){
                this.consoleIn.close();
            }
        }catch(IOException ex){
            Logger.getLogger(ClientEchoWorker.class.getName()).log(Level.SEVERE, null, ex);           
        }
    }

}
