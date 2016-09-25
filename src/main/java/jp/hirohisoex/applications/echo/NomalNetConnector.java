/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.hirohisoex.applications.echo;

import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author hironori
 */
public class NomalNetConnector implements INetConnector{

    @Override
    public Socket getSocket(String host, int port) throws IOException {
        return new Socket(host, port);
    }
    
}
