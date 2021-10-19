package Main;
/*
 * Pablo Vega Roca
 * Este programa tiene un defecto que es si la extension es mayor de 3 letras no trabaja de forma correcta
 */
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JTextArea;

public class interfaz {

	private JFrame frame;
	static JTextField txtRuta;
	private JTextArea txtArea;
	private JButton bEscribir,bNoE,bCopias,bContenido,bEliminar,bUnir,bSiE,bLeer;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					interfaz window = new interfaz();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public interfaz() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setLayout(null);
		frame.setBounds(100, 100, 508, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton bEliminar = new JButton("Eliminar");
		JButton bSiE_1 = new JButton("Guardar");
		JButton bNoE_1 = new JButton("Descartar");
		JButton bCopias_1 = new JButton("Seguridad");
		JButton bUnir_1 = new JButton("Unir partes");
		JButton bContenido_1 = new JButton("Archivo");
		JButton bLeer = new JButton("Leer");
		
		JButton bHash = new JButton("Hash");
		bHash.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					MessageDigest md5Digest = MessageDigest.getInstance("MD5");
					String checksum = manejoArchivos.getFileChecksum(md5Digest,manejoArchivos.darArchivo(txtRuta.getText()));
					JOptionPane.showMessageDialog(null,"El hash del archivo seleccionado es "+checksum);
				} catch (NoSuchAlgorithmException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		bHash.setBounds(335, 75, 135, 23);
		frame.getContentPane().add(bHash);
		
		bContenido_1.setBounds(335, 143, 135, 23);
		frame.getContentPane().add(bContenido_1);
		bCopias_1.setBounds(335, 178, 135, 23);
		frame.getContentPane().add(bCopias_1);
		bUnir_1.setBounds(335, 109, 135, 23);
		frame.getContentPane().add(bUnir_1);
		frame.getContentPane().add(bSiE_1);

		bSiE_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manejoArchivos.escribirArchivo(manejoArchivos.darArchivo(txtRuta.getText()), txtArea.getText());				
				bCopias_1.setVisible(true);
				bContenido_1.setVisible(true);
				bUnir_1.setVisible(true);
				bHash.setVisible(true);
				
				bSiE_1.setVisible(false);
				bNoE_1.setVisible(false);
			}
		});
		bSiE_1.setBounds(335, 109, 135, 23);
		bSiE_1.setVisible(false);
						
		bUnir_1.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		//Uso un filechooser con filtro para elegir el xml del que obtener la informacion para unir las partes
			JFileChooser chooser = new JFileChooser();
			chooser.setAcceptAllFileFilterUsed(false);
			FileNameExtensionFilter filter = new FileNameExtensionFilter("xml","XML");
			chooser.addChoosableFileFilter(filter);

			int returnVal = chooser.showOpenDialog(null);
			if(returnVal == JFileChooser.APPROVE_OPTION) {
			//Obtengo los datos necesarios del xml seleccionado
			  	int p =Integer.parseInt(metodosXML.partesXML(manejoArchivos.darArchivo(chooser.getSelectedFile().getPath())));
					    	String n = metodosXML.nombreXML(manejoArchivos.darArchivo(chooser.getSelectedFile().getPath()));
					    	String x = metodosXML.extensionXML(manejoArchivos.darArchivo(chooser.getSelectedFile().getPath()));
					    	String check= metodosXML.checksumXML(manejoArchivos.darArchivo(chooser.getSelectedFile().getPath()));
					    	String ruta= metodosXML.rutaXML(manejoArchivos.darArchivo(chooser.getSelectedFile().getPath()));
					    	Long t = Long.parseLong(metodosXML.tamañoXML(manejoArchivos.darArchivo(chooser.getSelectedFile().getPath())));
					       	seguridadArchivos.unirPartes(p,n,x,ruta,t);			       	
					       	String checksum=null;
							//Comprobacion del archivo unido con la informacion guardada en el xml del original
					       	try {
								MessageDigest md5Digest = MessageDigest.getInstance("MD5");
								checksum = manejoArchivos.getFileChecksum(md5Digest,manejoArchivos.darArchivo(chooser.getSelectedFile().getParent()+"\\recuperado"+n+x));
								if(checksum==check) {
						       		JOptionPane.showMessageDialog(null,"iguales");
						       	}
							} catch (NoSuchAlgorithmException e2) {
								// TODO Auto-generated catch block
								e2.printStackTrace();
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							if(checksum.equals(check)) {
					       		JOptionPane.showMessageDialog(null,"Los archivos son iguales");
					       	}
					    }			      
					}
				});
				
				
				bCopias_1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						jDialog_copias dialog = new jDialog_copias();
						dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
						dialog.setVisible(true);
						dialog.getContentPane().setLayout(null);
					}
				});
				
				
				bContenido_1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						bEscribir.setVisible(true);
						bEliminar.setVisible(true);
						bLeer.setVisible(true);
						
						bContenido_1.setVisible(false);
						bUnir_1.setVisible(false);
						bCopias_1.setVisible(false);
						bHash.setVisible(false);
					}
				});
		
		
		
		
		
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		
		bLeer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtArea.setText(manejoArchivos.leerStringArchivo(manejoArchivos.darArchivo(txtRuta.getText())));
				bEscribir.setVisible(false);
				bEliminar.setVisible(false);
				bLeer.setVisible(false);
				
				bContenido_1.setVisible(true);
				bUnir_1.setVisible(true);
				bCopias_1.setVisible(true);
				bHash.setVisible(true);
			}
		});
		bLeer.setBounds(335, 109, 135, 23);
		frame.getContentPane().add(bLeer);
		
		JButton bFileChooser = new JButton("...");
		bFileChooser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				
				int returnVal = chooser.showOpenDialog(null);
			    if(returnVal == JFileChooser.APPROVE_OPTION) {
			       System.out.println("Has elegido este archivo: " +
			        chooser.getSelectedFile().getName());
			       	txtRuta.setText(chooser.getSelectedFile().getPath());
			    }			      
			}
		});
		bFileChooser.setBounds(335, 24, 135, 23);
		frame.getContentPane().add(bFileChooser);
		
		txtRuta = new JTextField();
		txtRuta.setBounds(33, 25, 292, 20);
		frame.getContentPane().add(txtRuta);
		txtRuta.setColumns(10);
		
		JLabel lbl1 = new JLabel("Ruta del archivo");
		lbl1.setBounds(51, 11, 200, 14);
		frame.getContentPane().add(lbl1);
		bNoE_1.setBounds(335, 178, 135, 23);
		bNoE_1.setVisible(false);
		frame.getContentPane().add(bNoE_1);
		
		
		bNoE_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bCopias_1.setVisible(true);
				bContenido_1.setVisible(true);
				bUnir_1.setVisible(true);
				bHash.setVisible(true);
				
				bSiE_1.setVisible(false);
				bNoE_1.setVisible(false);
				
				txtArea.setText(manejoArchivos.leerStringArchivo(manejoArchivos.darArchivo(txtRuta.getText())));
			}
		});
		
		
		bEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i=JOptionPane.showConfirmDialog(null, "Esta seguro de eliminar el archivo");
				if(i==0) {
					manejoArchivos.darArchivo(txtRuta.getText()).delete();
					JOptionPane.showMessageDialog(null,"Se ha borrado el archivo");
				}else {
					JOptionPane.showMessageDialog(null,"No se ha borrado el archivo");
				}
				txtRuta.setText("");
				txtArea.setText("");				
				bEscribir.setVisible(false);
				bEliminar.setVisible(false);
				bLeer.setVisible(false);
				
				bContenido_1.setVisible(true);
				bUnir_1.setVisible(true);
				bCopias_1.setVisible(true);
				bHash.setVisible(true);
			}
		});
		bEliminar.setBounds(335, 144, 135, 23);
		frame.getContentPane().add(bEliminar);
		bEliminar.setVisible(false);
		
		bEscribir = new JButton("Editar texto");
		bEscribir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bEscribir.setVisible(false);
				bEliminar.setVisible(false);
				bLeer.setVisible(false);
				
				bSiE_1.setVisible(true);
				bNoE_1.setVisible(true);
				txtArea.setText(manejoArchivos.leerStringArchivo(manejoArchivos.darArchivo(txtRuta.getText())));				
			}
		});
		bEscribir.setBounds(335, 178, 135, 23);
		frame.getContentPane().add(bEscribir);
		
		
		txtArea= new JTextArea();
		txtArea.setBounds(33, 72, 292, 178);
		frame.getContentPane().add(txtArea);
		
				
	}
}
