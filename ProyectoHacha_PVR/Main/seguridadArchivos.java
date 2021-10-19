package Main;
/*
 * Pablo Vega Roca
 * clase de seguridad de archivos
 * metodos incluidos:
 * 	copiarArchivo
 * 	crearPartesArchivo
 * 	unirPartes
 */
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JOptionPane;

public class seguridadArchivos {
	//Hace una copia exacta del archivo
	public static void copiarArchivo(File arch) {
		try {
			File directorio = new File(arch.getParent()+"\\"+extensionesArchivos.quitarExtension(arch.getName())+"copias");
			
			if(!directorio.exists()) {
				directorio.mkdir();
			}

			BufferedInputStream archR = new BufferedInputStream(new FileInputStream (arch.getPath()));
			BufferedOutputStream archW = new BufferedOutputStream(new FileOutputStream(directorio.getPath()+"\\copiaseguridad"+extensionesArchivos.cogerExtension(arch)));
	
			int valor=archR.read();
			while(valor!=-1) {
				archW.write(valor);
				valor=archR.read();			
			}
			archW.close();
			archR.close();
			JOptionPane.showMessageDialog(null,"Se ha copiado el archivo correctamente");
		}catch(IOException e) {
			System.out.print(e);
		}
	}
	
	
	//Crea las partes de un archivo segun el numero de partes dadas
	public static void crearPartesArchivo(File arch,int nPartes){
		try {
			File directorio = new File(arch.getParent()+"\\"+extensionesArchivos.quitarExtension(arch.getName())+"copias");
			directorio.mkdir();
			
			int a = manejoArchivos.leerStringArchivo(arch).length();
			int nChar = a/nPartes;
			int j=1;
			BufferedInputStream archR = new BufferedInputStream (new FileInputStream(arch.getPath()));			
			for(int i=1;i<nPartes+1;i++) {
				BufferedOutputStream archW = new BufferedOutputStream(new FileOutputStream(directorio.getPath()+"\\"+extensionesArchivos.quitarExtension(arch.getName())+String.valueOf(i)+".txt"));				
				int valor=archR.read();				
				while(valor!=-1) {
					while(j<nChar*i) {
						archW.write(valor);
						valor=archR.read();
						j++;
					}
					if(valor!=-1) {
						archW.write(valor);
					}
					valor=-1;
				}				
				archW.close();
			}
			JOptionPane.showMessageDialog(null,"Se ha fragmentado el archivo correctamente");
		}catch(IOException e) {
			
		}
	}
	
	//Une los fragmentos del archivo según los datos dados 
	public static void unirPartes(int nPartes,String nombre,String extension,String ruta,long tamaño) {
		try {	
			long tamañoV=0;
			File directorio = new File(ruta+"\\"+nombre+"copias");
			int z=0;
			for(int j=1;j<nPartes;j++) {
				File a = new File(directorio.getPath()+"\\"+nombre+String.valueOf(j)+".txt");
				if(!a.exists()) {
					z=-1;					
				}
			}
			if(z!=-1) {
			BufferedOutputStream archW = new BufferedOutputStream(new FileOutputStream(directorio.getPath()+"\\recuperado"+nombre+extension));			
				for(int i=1;i<nPartes+1;i++) {
					File a = new File(directorio.getPath()+"\\"+nombre+String.valueOf(i)+".txt");
					
					tamañoV+=a.length();
					BufferedInputStream archR = new BufferedInputStream (new FileInputStream(directorio.getPath()+"\\"+nombre+String.valueOf(i)+".txt"));
					int valor=archR.read();				
					while(valor!=-1) {
						archW.write(valor);
						valor=archR.read();			
					}
					archR.close();
				}
				archW.close();
				if(tamañoV<tamaño-tamaño/(nPartes-1) || tamañoV>tamaño+(tamaño/nPartes)) {
					File recu = new File(directorio.getPath()+"\\recuperado"+nombre+extension);
					recu.delete();
					JOptionPane.showMessageDialog(null,"El archivo recuperado no es el mismo al original");
				}else {
					JOptionPane.showMessageDialog(null,"Se ha unido el archivo correctamente");
				}
			}else {
				JOptionPane.showMessageDialog(null,"Las partes del archivo no son correctas");	
			}
			}catch(IOException e) {
			}
		}
	}
