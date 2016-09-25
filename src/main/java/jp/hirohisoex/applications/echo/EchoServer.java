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
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author hironori
 */
public class EchoServer {

    public static void main(String[] args) {
        int port = 10007;//port

        //サーバ通信ポートを開く
        try (ServerPort server = new ServerPort(port)) {
            //クライアントからの接続要求をうける
            try (Socket clientSock = server.lisenClientConnection()) {
                //クライアントからの入出力を得る
                try (BufferedReader input = new BufferedReader(new InputStreamReader(clientSock.getInputStream()));
                        PrintWriter output = new PrintWriter(clientSock.getOutputStream())) {
                    //クライアントの要求を応答として返す
                    String request;
                    while ((request = input.readLine()) != null) {
                        System.out.println(request);
                        output.println(request);
                        output.flush();
                    }

                }
            }
        } catch (IOException ex) {
            System.out.println(ex);
        }

    }

}
