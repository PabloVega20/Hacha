package Main;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SwingConstants;

public class jDialog_copias extends JDialog {
	JSpinner spinner;
	public jDialog_copias() {
		setBounds(100, 100, 427, 263);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JButton bCopiaSeguridad = new JButton("BackUp");
		bCopiaSeguridad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				seguridadArchivos.copiarArchivo(manejoArchivos.darArchivo(interfaz.txtRuta.getText()));
				
			}
		});
		bCopiaSeguridad.setBounds(128, 64, 154, 23);
		panel.add(bCopiaSeguridad);
		
		JButton bCambiarExt = new JButton("Cambiar Formato");
		bCambiarExt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Recojo texto del input y si tiene punto lo considero une extension
				String r=JOptionPane.showInputDialog("Escriba la extension");
				if(r.contains(".")) {
					File n = new File(extensionesArchivos.cambiarExtension(r,manejoArchivos.darArchivo(interfaz.txtRuta.getText())));
					if(manejoArchivos.darArchivo(interfaz.txtRuta.getText()).renameTo(n)) {
						System.out.print("cambiada");
						interfaz.txtRuta.setText(n.getPath());
					}
				}else {
				}
			}
		});
		bCambiarExt.setBounds(234, 114, 154, 23);
		panel.add(bCambiarExt);
		
		JButton bPartir = new JButton("Fragmentar");
		bPartir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				metodosXML.crearXML(manejoArchivos.darArchivo(interfaz.txtRuta.getText()), String.valueOf(spinner.getValue()));
				seguridadArchivos.crearPartesArchivo(manejoArchivos.darArchivo(interfaz.txtRuta.getText()), (int)spinner.getValue());				
			}
		});
		bPartir.setBounds(10, 114, 154, 23);
		panel.add(bPartir);
		
		JLabel lblNombre = new JLabel(manejoArchivos.darArchivo(interfaz.txtRuta.getText()).getName());
		lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombre.setBounds(64, 11, 283, 14);
		panel.add(lblNombre);
		
		spinner = new JSpinner();
		spinner.setBounds(163, 115, 30, 20);
		panel.add(spinner);
		{
			JButton btnNewButton = new JButton("New button");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});
			
		}
	}
}
