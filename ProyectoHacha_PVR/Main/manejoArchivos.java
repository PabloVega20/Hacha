package Main;
/*
 * Pablo Vega Roca 
 * clase manejo de archivos
 * metodos incluidos:
 * 	darArchivo
 * 	escribirArchivo
 * 	leerArchivo
 * 	leerStringArchivo
 * 	getFileChecksum
 */

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.swing.JOptionPane;
import javax.xml.parsers.*;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

public class manejoArchivos {
	
	//Devuelve una ruta en forma de archivo
	public static File darArchivo(String ruta) {
		File arch = new File(ruta);
		return arch;
	}
	
	//Escribe texto en un archivo
	public static void escribirArchivo(File arch,String s) {
		try {
			FileWriter archW = new FileWriter(arch);
			archW.write(s);
			archW.close();
		}catch(IOException e) {
			System.out.print(e);
		}
	}
	//Lee flujo de datos de un archivo
	public static int leerArchivo(File arch) {
		int r = 0;
		try {
			BufferedInputStream archR = new BufferedInputStream(new FileInputStream (arch.getPath()));
			
			int valor=archR.read();
			while(valor!=-1) {
				r+=valor;
				valor=archR.read();
			}
			archR.close();
		}catch(IOException e) {
			
		}
		return r;
	}
	
	//Lee texto de un archivo
	public static String leerStringArchivo(File arch) {
		String r = "";
		try {
			BufferedInputStream archR = new BufferedInputStream(new FileInputStream (arch.getPath()));
			
			int valor=archR.read();
			while(valor!=-1) {
				r+=(char)valor;
				valor=archR.read();
			}
			archR.close();
		}catch(IOException e) {
			
		}
		return r;
	}
		

	//Devuelve el hashcode de un archivo
	static String getFileChecksum(MessageDigest digest, File file) throws IOException{
	    FileInputStream fis = new FileInputStream(file);

	    byte[] byteArray = new byte[1024];
	    int bytesCount = 0; 

	    while ((bytesCount = fis.read(byteArray)) != -1) {
	        digest.update(byteArray, 0, bytesCount);
	    };
	     
	    fis.close();
	     
	    byte[] bytes = digest.digest();
	     
	    StringBuilder sb = new StringBuilder();
	    for(int i=0; i< bytes.length ;i++)
	    {
	        sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
	    }
	   return sb.toString();
	}
}
