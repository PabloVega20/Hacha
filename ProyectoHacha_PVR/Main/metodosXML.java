package Main;
/*
 * Pablo Vega Roca
 * Clase de metodos XML
 * metodos incluidos:
 * 	crearXML
 * 	partesXML
 * 	tamañoXML
 * 	rutaXML
 * 	extensionXML
 * 	nombeXML
 * 	checksumXML 
 */
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class metodosXML {
	//Crea un XML con los datos recogidos del arhivo elegido
	public static void crearXML(File arch,String numPartes) {
		File directorio = new File(arch.getParent()+"\\"+extensionesArchivos.quitarExtension(arch.getName())+"copias");
		if(!directorio.exists()) {
			directorio.mkdir();			
		}
		String f= directorio.getPath()+"\\"+extensionesArchivos.quitarExtension(arch.getName())+"xml.xml";
		Path path = Paths.get(arch.getPath());
		long byt=0;
		try {
			byt = Files.size(path);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			MessageDigest md5Digest = MessageDigest.getInstance("MD5");
			String checksum = manejoArchivos.getFileChecksum(md5Digest, arch);
			
			DocumentBuilderFactory docFactory =  DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			
			//ELEMENTO RAIZ
			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement(extensionesArchivos.quitarExtension(arch.getName()));
			doc.appendChild(rootElement);
			
			//PARTES
			Element nPartes = doc.createElement("Partes");
			nPartes.setTextContent(numPartes);
			rootElement.appendChild(nPartes);
			
		
			//HASHCODE
			Element hashcode = doc.createElement("hashcode");
			hashcode.setTextContent(checksum);
			rootElement.appendChild(hashcode);
			
			//NOMBRE
			Element nombre = doc.createElement("nombre");
			nombre.setTextContent(extensionesArchivos.quitarExtension(arch.getName()));
			rootElement.appendChild(nombre);
			
			//EXTENSION
			Element extension = doc.createElement("extension");
			extension.setTextContent(extensionesArchivos.cogerExtension(arch));
			rootElement.appendChild(extension);
			
			//RUTA
			Element ruta = doc.createElement("ruta");
			ruta.setTextContent(arch.getParent());
			rootElement.appendChild(ruta);
			
			//TAMAÑO
			Element tamaño = doc.createElement("tamaño");
			tamaño.setTextContent(String.valueOf(byt));
			rootElement.appendChild(tamaño);
			
			TransformerFactory transFactory = TransformerFactory.newInstance();
			Transformer trans = transFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(f));
			
			trans.transform(source, result);
			
		}catch(ParserConfigurationException pce) {
			pce.printStackTrace();
		}catch (TransformerException tfe) {
		    tfe.printStackTrace();
	    } catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//Devuelve las partes en las que se fragmento el archivo
	public static String partesXML(File arch) {
		String b="";
		String c= arch.getPath();
		try {
			
			File file = new File(c);
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document document = db.parse(file);
			document.getDocumentElement().normalize();
			
			NodeList nList = document.getElementsByTagName(extensionesArchivos.quitarXML(extensionesArchivos.quitarExtension(arch.getName())));
                Node nNode = nList.item(0);
       
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    b = eElement.getElementsByTagName("Partes").item(0).getTextContent();
                }
		}catch(IOException | ParserConfigurationException | SAXException e) {
			System.out.print(e);
		}
		return b;
	}
	
	//Devuelve el tamaño  del archivo
	public static String tamañoXML(File arch) {
		String b="";
		String c= arch.getPath();
		try {
			
			File file = new File(c);
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document document = db.parse(file);
			document.getDocumentElement().normalize();
			
			NodeList nList = document.getElementsByTagName(extensionesArchivos.quitarXML(extensionesArchivos.quitarExtension(arch.getName())));
				Node nNode = nList.item(0);
	      
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
	                b = eElement.getElementsByTagName("tamaño").item(0).getTextContent();
	            }
		}catch(IOException | ParserConfigurationException | SAXException e) {
			System.out.print(e);
		}
		return b;
	}
		
	//Devuelve la ruta del archivo
	public static String rutaXML(File arch) {
		String b="";
		String c= arch.getPath();
		try {
			
			File file = new File(c);
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document document = db.parse(file);
			document.getDocumentElement().normalize();
			
			NodeList nList = document.getElementsByTagName(extensionesArchivos.quitarXML(extensionesArchivos.quitarExtension(arch.getName())));
                Node nNode = nList.item(0);
       
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    b = eElement.getElementsByTagName("ruta").item(0).getTextContent();
                }
		}catch(IOException | ParserConfigurationException | SAXException e) {
			System.out.print(e);
		}
		return b;
	}
	
	//Devuelve la extension del archivo
	public static String extensionXML(File arch) {
		String b="";
		String c= arch.getPath();
		try {
			
			File file = new File(c);
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document document = db.parse(file);
			document.getDocumentElement().normalize();
			
			NodeList nList = document.getElementsByTagName(extensionesArchivos.quitarXML(extensionesArchivos.quitarExtension(arch.getName())));
                Node nNode = nList.item(0);
       
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    b = eElement.getElementsByTagName("extension").item(0).getTextContent();
                }
		}catch(IOException | ParserConfigurationException | SAXException e) {
			System.out.print(e);
		}
		return b;
	}
	
	//Devuelve el nombre del archivo
	public static String nombreXML(File arch) {
		String b="";
		String c= arch.getPath();
		try {
			
			File file = new File(c);
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document document = db.parse(file);
			document.getDocumentElement().normalize();
			
			NodeList nList = document.getElementsByTagName(extensionesArchivos.quitarXML(extensionesArchivos.quitarExtension(arch.getName())));
                Node nNode = nList.item(0);
       
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    b = eElement.getElementsByTagName("nombre").item(0).getTextContent();
                }
		}catch(IOException | ParserConfigurationException | SAXException e) {
			System.out.print(e);
		}
		return b;
	}
	
	//Devuelve el hashcodeMD5 del archivo
	public static String checksumXML(File arch) {
		String b="";
		String c= arch.getPath();
		try {
			
			File file = new File(c);
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document document = db.parse(file);
			document.getDocumentElement().normalize();
			
			NodeList nList = document.getElementsByTagName(extensionesArchivos.quitarXML(extensionesArchivos.quitarExtension(arch.getName())));
                Node nNode = nList.item(0);
       
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    b = eElement.getElementsByTagName("hashcode").item(0).getTextContent();
                }
		}catch(IOException | ParserConfigurationException | SAXException e) {
			System.out.print(e);
		}
		return b;
	}

}
