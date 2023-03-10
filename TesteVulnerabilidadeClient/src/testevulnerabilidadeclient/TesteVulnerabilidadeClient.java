/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package testevulnerabilidadeclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author patri
 */
public class TesteVulnerabilidadeClient extends Thread {

    private static boolean ligado = true;
    private BufferedReader  entrada = null;
    private PrintStream sendToServer = null;
    private static PortScanDos_Client clientInterface = null;

   
    public TesteVulnerabilidadeClient(BufferedReader entrada)
    {
        this.entrada = entrada; 
    }
    
    public TesteVulnerabilidadeClient(PortScanDos_Client clientInterface){
        this.clientInterface = clientInterface;
    }
    
    
    public boolean ConexaoServidor(){
        
        try {
            Socket s = new Socket("127.0.0.1",5000);
            
            BufferedReader receivedFromServer = new BufferedReader(new InputStreamReader(s.getInputStream()));
            sendToServer = new PrintStream(s.getOutputStream()); 
            
           //criar e arrancar a Thread
           Thread t = new TesteVulnerabilidadeClient(receivedFromServer);
           t.start();
            
            
        } catch (IOException ex) {
            Logger.getLogger(TesteVulnerabilidadeClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
     
    public void enviarDadosPortScan(String ipTarget){
        
        sendToServer.println("<PS>"+ipTarget);
    }
    public void enviarDadosDos(String ipTarget, String portTarget){
        
            sendToServer.println("<DS>"+ipTarget+"|"+portTarget);
    }
    
    @Override
    public void run()
    {
        while(ligado){
            try {
                clientInterface.recebeMensagemServidor(entrada.readLine());
            } catch (IOException ex) {
                Logger.getLogger(TesteVulnerabilidadeClient.class.getName()).log(Level.SEVERE, null, ex);
            }

    }try {
            entrada.close();
            
        } catch (IOException ex) {
            Logger.getLogger(TesteVulnerabilidadeClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        
}
}