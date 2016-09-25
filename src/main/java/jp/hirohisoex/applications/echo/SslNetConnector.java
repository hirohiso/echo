/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.hirohisoex.applications.echo;

import java.io.IOException;
import java.net.Socket;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;

/**
 *
 * @author hironori
 */
public class SslNetConnector implements INetConnector{

    @Override
    public Socket getSocket(String host, int port) throws IOException {
        Socket sc = null;
        try {
            SSLContext sCon = SSLContext.getInstance("TLS");
            sCon.init(null, new X509TrustManager[]{new UnsecuredTrustManager()}, new SecureRandom());
            SSLSocketFactory ssf = sCon.getSocketFactory();
            sc = ssf.createSocket(host, port);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(EchoSslClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (KeyManagementException ex) {
            Logger.getLogger(EchoSslClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(EchoSslClient.class.getName()).log(Level.SEVERE, null, ex);
        }

        return sc; 
    }
    
}
