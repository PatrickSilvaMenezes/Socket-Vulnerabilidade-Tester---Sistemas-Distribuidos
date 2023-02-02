package testevunerabilidadeserver;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.concurrent.TimeUnit;


public class PortScan {
    
    
    public PortScan(){
    }
    
    public void portScan(String ipClientTarget, PrintStream sendToClient) throws InterruptedException{
        for(int x=0; x<=65535; x++){        
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
