package practica03b;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class PruebaEventos2
{
	public static void main(String[] args)
	{
		VentanaEventos miFrame = new VentanaEventos();
		LaminaEventos miLamina = new LaminaEventos();
		miFrame.add(miLamina);
	}
}

class VentanaEventos extends JFrame
{
	public VentanaEventos()
	{
		setTitle("Botones y eventos + David López González");
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
	}
}

class LaminaEventos extends JPanel
{
	// Creamos las variables para poder tener acceso desde todos los métodos
	private JButton	btnVerde;
	private JButton	btnAzul;
	private JButton	btnRojo;

	public LaminaEventos()
	{
		// Añadimos los 3 botones
		add(btnRojo = new JButton("Rojo 2"));
		add(btnVerde = new JButton("Verde 2"));
		add(btnAzul = new JButton("Azul 2"));


		ColorFondo verde=new ColorFondo(Color.GREEN);
		ColorFondo azul=new ColorFondo(Color.BLUE);
		ColorFondo rojo=new ColorFondo(Color.RED);
		
		
		btnAzul.addActionListener(azul);
		btnRojo.addActionListener(rojo);
		btnVerde.addActionListener(verde);
		// Prueba mia
		/*
		btnAzul.addActionListener(new ActionListener()
		{			
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				((JButton)arg0.getSource()).setBackground(Color.black);
				
			}
		});*/
	}

	/**
	 * Clase interna en la que implementaremos el actionPerformand
	 * 
	 * @author david
	 *
	 */
	private class ColorFondo implements ActionListener
	{
		private Color	colorFondo;

		public ColorFondo(Color c)
		{
			colorFondo = c;
		}

		@Override
		public void actionPerformed(ActionEvent e)
		{
			setBackground(colorFondo);
		}
	}

}