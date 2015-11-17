package practica04b;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

public class PruebaEventosVentana
{
	public static void main(String[] args)
	{
		MarcoVentana mi = new MarcoVentana();
		MarcoVentana mi2 = new MarcoVentana();
		// La ventana 2 será la que podamos ver el mensaje de cerrar
		// para ello usamos el DISPOSE
		mi.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mi2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		mi2.setBounds(200, 400, 200, 200);
		mi2.setTitle("Segunda ventana");
	}
}

class MarcoVentana extends JFrame
{
	MarcoVentana()
	{
		setTitle("Primer ventana");
		setBounds(100, 100, 200, 200);
		setVisible(true);
		
		addWindowListener(new adapterVentana());
	}

	// Clase interna que hereda del Adaptador de la ventana
	// por lo que solamente debemos implementar los métodos que necesitemos
	class adapterVentana extends WindowAdapter
	{
		@Override
		public void windowIconified(WindowEvent e)
		{
			System.out.println("Ventana minimizada: \t"+((MarcoVentana)e.getSource()).getTitle());
		}

		@Override
		public void windowOpened(WindowEvent e)
		{
			System.out.println("Ventana abierta: \t"+((MarcoVentana)e.getSource()).getTitle());
		}
	}
}