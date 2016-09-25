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

/**
 *
 * @author hironori
 */
public class EchoClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int port = 10007;//port
        String host = "localhost";

        INetConnector inc = new NomalNetConnector();

        try (Socket socket = inc.getSocket(host, port)) {
            try (ClientEchoWorker woker = new ClientEchoWorker(socket)) {
                woker.process();
            }
        } catch (IOException ex) {
            System.out.println(ex);
        }

    }

}
