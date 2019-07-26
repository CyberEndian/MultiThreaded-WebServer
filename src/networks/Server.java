package networks;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Kondos
 */
   
public class Server {
     private ServerSocket serverSocket;
     private Socket clientSocket;
     private InputStream in ;
     private PrintWriter out ;
     private BufferedOutputStream dataOut;
     private int port;
     private String ver;
     
     public void start(int port) throws IOException {
     int c;
serverSocket = new ServerSocket(port);
System.out.println("Server is listening at " + port + " waiting for connections!");
clientSocket = serverSocket.accept();
System.out.println("New Connection established!"); 

in = clientSocket.getInputStream();

while (in.available()  == 0 ) //wait for data lol
{
    int a=0;
}
        StringBuilder str 
            = new StringBuilder(); 

 while (in.available()  != 0 )
 {
              str.append((char) in.read());   
 }
 			out = new PrintWriter(clientSocket.getOutputStream());
			// get binary output stream to client (for requested data)
			dataOut = new BufferedOutputStream(clientSocket.getOutputStream());

 
 
 
 
 //if get 1.0
String[] toppings = parseRequest(str.toString());
       //  System.out.println( toppings[1]);
 if(toppings[0].equals("GET") && toppings[2].equals("HTTP/1.0") )
    Http10.get(toppings[1],  out , dataOut);  //(String Path, PrintWriter out, OutputStream dataOut)
 
 
 
 System.out.print(str.toString());
       //  System.out.println("dasd");

}
 
public void stop() throws IOException {

clientSocket.close();
serverSocket.close();
}

public Server(int port) throws IOException {
        this.port = port;
        start(port);
    }

public InputStream getIn() {
        return in;
    }


public String[]  parseRequest(String request)

    {
String[] toppings = new String[3];
        Pattern patternType = Pattern.compile("(?:GET|POST|PUT|DELETE)");
        Matcher matcherType = patternType.matcher(request);
        matcherType.find();
       toppings[0] = matcherType.group(0);
        Pattern pattern = Pattern.compile("\\s+([^?\\s]+)((?:[?&][^&\\s]+)*)\\s+(HTTP.*)");
        Matcher matcher = pattern.matcher(request);
        if (matcher.find())
        {
         toppings[1] =  matcher.group(1);
         toppings[2] = matcher.group(3);
    }
         return toppings;
    }

    
}
