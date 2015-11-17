package practica09;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Registro
{
	public static void main(String[] args)
	{
		MarcoRegistro frame = new MarcoRegistro();
		LaminaRegistro lamina = new LaminaRegistro();
		utils.UtilsFrames.centrar(frame, 400);
		frame.add(lamina);
	}
}

class MarcoRegistro extends JFrame
{
	public MarcoRegistro()
	{
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}

class LaminaRegistro extends JPanel
{
	JTextField		txt1;
	JPasswordField	txtPass;
	JLabel			lblUser, lblPass, lblNotificacion;
	String			textoContraseña;

	public LaminaRegistro()
	{
		txt1 = new JTextField(20);
		txtPass = new JPasswordField();
		lblNotificacion = new JLabel("Nivel contraseña");
		lblUser = new JLabel("USUARIO");
		lblPass = new JLabel("CONTRASEÑA");

		Box box = Box.createVerticalBox();
		Box boxH1 = Box.createHorizontalBox();
		Box boxH2 = Box.createHorizontalBox();
		Box boxH3 = Box.createHorizontalBox();
		
		txtPass.addKeyListener(new AdapterBoton());
		add(box);

		// horizontal 1
		boxH1.add(lblUser);
		boxH1.add(Box.createHorizontalStrut(30));
		boxH1.add(txt1);
		box.add(boxH1);

		// horizontal 2
		boxH2.add(lblPass);
		boxH2.add(Box.createHorizontalStrut(30));
		boxH2.add(txtPass);
		box.add(boxH2);

		// horizontal 3
		boxH3.add(lblNotificacion);
		box.add(boxH3);
	}

	/**
	 * Adapter para controlar el número de letras puestas en el campo de contraseña
	 * @author david
	 * 
	 */
	class AdapterBoton extends KeyAdapter
	{
		@Override
		public void keyPressed(KeyEvent arg0)
		{
			//Establecemos una fuente que resalte más
			Font miFuente = new Font("Arial", Font.ITALIC, 20);
			lblNotificacion.setFont(miFuente);
			
			// Comprobamos el tamaño de la password introducida.
			 if (txtPass.getText().length() + 1 < 5 || 
					txtPass.getText().length() + 1 > 15)
			{				
				lblNotificacion.setForeground(Color.RED);
				txtPass.setForeground(Color.RED);
				lblNotificacion.setText("Contraseña no segura");
			} else if (txtPass.getText().length() + 1 <= 8)
			{
				lblNotificacion.setForeground(Color.ORANGE);
				txtPass.setForeground(Color.ORANGE);
				lblNotificacion.setText("Contraseña Débil");
			} else if (txtPass.getText().length() + 1 <= 12)
			{
				lblNotificacion.setForeground(Color.YELLOW);
				txtPass.setForeground(Color.YELLOW);
				lblNotificacion.setText("Contraseña Regular");
			} else if (txtPass.getText().length() + 1 <= 15)
			{
				lblNotificacion.setForeground(Color.GREEN);
				txtPass.setForeground(Color.GREEN);
				lblNotificacion.setText("Contraseña Segura");
			}
			
		}
	}
}