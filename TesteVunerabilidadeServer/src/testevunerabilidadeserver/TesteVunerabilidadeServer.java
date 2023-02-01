/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package testevunerabilidadeserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author patri
 */
public class TesteVunerabilidadeServer extends Thread{

    private Socket clientConnection = null;
    
    public TesteVunerabilidadeServer(Socket clientConnection)
    {
        this.clientConnection = clientConnection;
       
    }
    
    public static void main(String[] args) throws IOException, InterruptedException  {
        
            //BufferedReader clientReceiver = new BufferedReader(new InputStreamReader(clientConnection.getInputStream()));
            //PrintWriter clientSender = new PrintWriter(clientConnection.getOutputStream(),true);
           
            try {
            //Criar um processo à escuta numa porta
             ServerSocket listener = new ServerSocket(5000);
            
            
            while(true){ 
            System.out.println("Esperando client se ligar...");
            Socket clientConnection = listener.accept();
            System.out.println("Client se ligou...");
            //Criar um Thread para atender o cliente que se ligou
            Thread t = new TesteVunerabilidadeServer(clientConnection); 
            //colocar a Thread em funcionamento
            t.start(); 
            }
            
        } catch (IOException ex) {
            Logger.getLogger(TesteVunerabilidadeServer.class.getName()).log(Level.SEVERE, null, ex);
        }
                
   
               
    } 
    @Override
    public void run()        
    {
    
        try {
            //criar canal de input
            BufferedReader clientReceiver = new BufferedReader(new InputStreamReader(clientConnection.getInputStream()));
            //Criar um canal de saida
            PrintStream clientSender = new PrintStream (clientConnection.getOutputStream());
        
            String ipTargetReceived = null;
            ipTargetReceived = clientReceiver.readLine();
            
            try {
                portScan(ipTargetReceived, clientSender);
            } catch (InterruptedException ex) {
                Logger.getLogger(TesteVunerabilidadeServer.class.getName()).log(Level.SEVERE, null, ex);
            }
            
             clientReceiver.close();
             clientSender.close();
             clientConnection.close();
        } 
             
        catch (IOException ex) {
            Logger.getLogger(TesteVunerabilidadeServer.class.getName()).log(Level.SEVERE, null, ex);
        } 
       }
    
    
    
    
    public void portScan(String ipClientTarget, PrintStream sendToClient) throws InterruptedException{
            for(int x=100; x<=137; x++){        
                try {
                    TimeUnit.SECONDS.sleep(1/1000);
                    Socket targetSocket = new Socket(ipClientTarget, x);
                    
                    sendToClient.println("Porta: " + x + " está aberta");
                   
                    targetSocket.close();
                } catch (IOException ex) {
                
                 sendToClient.println("Porta: " + x + " está fechada");     
                }
    
    }
    }
    
    
}

    
    
   
    

