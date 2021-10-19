package Main;
/*
 * Pablo Vega Roca
 * clase extensionesArchivos
 * metodos incluidos:
 * 	cambiarExtension
 * 	quitarExtension
 * 	quitarXML
 * 	cogerExtension
 */
import java.io.File;

public class extensionesArchivos {
	//Quita la extension de 3 char de longitud de un archivo y pone la escrita
	public static String cambiarExtension(String extension,File copia){
		int l=copia.getPath().length();
		StringBuilder cB = new StringBuilder(copia.getPath());
		cB = cB.deleteCharAt(l-1);
		cB = cB.deleteCharAt(l-2);
		cB = cB.deleteCharAt(l-3);
		cB = cB.deleteCharAt(l-4);
		String c = String.valueOf(cB);
		if(extension!="") {
			c+=extension;
		}	
		return c;
	}
	
	//Elimina la extension de un archivo
	public static String quitarExtension(String nombre){
		int l=nombre.length();
		StringBuilder cB = new StringBuilder(nombre);
		cB = cB.deleteCharAt(l-1);
		cB = cB.deleteCharAt(l-2);
		cB = cB.deleteCharAt(l-3);
		cB = cB.deleteCharAt(l-4);
		String c = String.valueOf(cB);	
		return c;
	}
	
	//Quita la parte xml del nombre de archivo para generar el archivo recuperado
	public static String quitarXML(String nombre){
		int l=nombre.length();
		StringBuilder cB = new StringBuilder(nombre);
		cB = cB.deleteCharAt(l-1);
		cB = cB.deleteCharAt(l-2);
		cB = cB.deleteCharAt(l-3);
		String c = String.valueOf(cB);	
		return c;
	}
	
	//Guarda la extension del archivo
	public static String cogerExtension(File arch) {
		int l=arch.getPath().length();
		String c = Character.toString(arch.getPath().charAt(l-4));
		c+=Character.toString(arch.getPath().charAt(l-3));
		c+=Character.toString(arch.getPath().charAt(l-2));
		c+=Character.toString(arch.getPath().charAt(l-1));
		
		return c;
	}
}
