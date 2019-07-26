/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package networks;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Date;

/**
 *
 * @author Kondos
 */
public class Http10 {

    static void get(String Path, PrintWriter out, OutputStream dataOut) throws IOException {
        File tempFile = new File("C:/htdocs" + Path);
        
        boolean exists = tempFile.exists();
       // System.out.println(Path);
        if (!exists) //return 404 notfound
        { 
            System.out.println("Sent not found");

            File file = new File("C:/htdocs/" + "notfound.html");
            int fileLength = (int) file.length();
            byte[] fileData = new byte[fileLength];
            FileInputStream FileRead = new FileInputStream(file);
            FileRead.read(fileData);
            FileRead.close();
            out.println("HTTP/1.0 404 File Not Found");
		out.println("Welcome in 7oda and dawdaw server : 1.0");
		out.println("Date: " + new Date());
		out.println("Content-type: text/html");
		out.println("Content-length: " + fileLength);
		out.println();
		out.flush(); 
		
		dataOut.write(fileData, 0, fileLength);
		dataOut.flush();
            
            
        }
        else
        {
            
            int fileLength = (int) tempFile.length();
            byte[] fileData = new byte[fileLength];
            FileInputStream FileRead = new FileInputStream(tempFile);
            FileRead.read(fileData);
            FileRead.close();
            
          
            
        }

    }

    
    
}
