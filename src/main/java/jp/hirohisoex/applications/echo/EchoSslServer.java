/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.hirohisoex.applications.echo;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLServerSocketFactory;
import jp.hirohisoex.applications.echo.ServerPort;

/**
 *
 * @author hironori
 */
public class EchoSslServer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int port = 10008;//port

        //サーバ通信ポートを開く
        try (ServerSocket server = getServerSocket(port)) {
            //クライアントからの接続要求をうける
            try (Socket clientSock = server.accept()) {
                //クライアントからの入出力を得る
                try (BufferedReader input = new BufferedReader(new InputStreamReader(clientSock.getInputStream()));
                        PrintWriter output = new PrintWriter(clientSock.getOutputStream())) {
                    //クライアントの要求を応答として返す
                    String request;
                    System.out.println("ここまでできた2");
                    while ((request = input.readLine()) != null) {
                        System.out.println("ここまでできた3");
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

    private static ServerSocket getServerSocket(int port) {
        ServerSocket sc = null;
        try {
            final String keyStorePass = "F:\\program\\EchoSSL\\serverSSL.jks";
            KeyStore keyStore = KeyStore.getInstance("JKS");
            char[] password = "hogehoge".toCharArray();
            keyStore.load(new FileInputStream(keyStorePass), password);
            KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
            kmf.init(keyStore, password);
            SSLContext sslCon = SSLContext.getInstance("TLS");
            sslCon.init(kmf.getKeyManagers(), null, null);
            SSLServerSocketFactory sssf = sslCon.getServerSocketFactory();
            sc = sssf.createServerSocket(port);
            System.out.println("ここまでできた");
        } catch (KeyStoreException | IOException | NoSuchAlgorithmException | CertificateException | UnrecoverableKeyException | KeyManagementException ex) {
            Logger.getLogger(EchoSslServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sc;
    }
}
