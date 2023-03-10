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
                PortScan scan = new PortScan();
                try {
                    scan.portScan(ipTarget, clientSender);
                } catch (InterruptedException ex) {
                    Logger.getLogger(TesteVunerabilidadeServer.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
            else if(testType.equalsIgnoreCase("<DS>"))
            {
                AttackDos attack = new AttackDos();
                String ipTarget = dataFromClient.substring(dataFromClient.indexOf(">")+1,dataFromClient.indexOf("|"));
                String portTarget = dataFromClient.substring(dataFromClient.indexOf("|")+1,dataFromClient.length());
                System.out.println("portTarget"+portTarget);
                attack.dosAttack(ipTarget, portTarget, clientSender);
            }
            
            
    
            clientReceiver.close();
            clientSender.close();
            clientConnection.close(); 
        } 
             
        catch (IOException ex) {
            System.out.println("Teste de vulnerabilidade não correu bem!");
        } 
       }
    

}
    
    


    
    
   
    

