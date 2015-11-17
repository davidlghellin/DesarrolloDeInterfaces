package practica04;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class PruebaEventosVentana
{
	public static void main(String[] args)
	{
		MarcoVentana mi = new MarcoVentana();
		MarcoVentana mi2 = new MarcoVentana();
		// La ventana 2 será la que podamos ver el mensaje de cerrar
		// para ello usamos el DISPOSE
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
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		addWindowListener(new listenerVentana());
	}

	// Clase inerna que implementa todas las opciones de la ventana
	// Los eventos están explicados en la salida extandar
	class listenerVentana implements WindowListener
	{
		@Override
		public void windowActivated(WindowEvent e)
		{
			System.out.println("Ventana activada: \t"+((MarcoVentana)e.getSource()).getTitle());
		}

		@Override
		public void windowClosed(WindowEvent e)
		{
			System.out.println("La ventana se ha cerrado: \t"+((MarcoVentana)e.getSource()).getTitle());
		}

		@Override
		public void windowClosing(WindowEvent e)
		{
			System.out.println("Cerrando ventana: \t"+((MarcoVentana)e.getSource()).getTitle());
		}

		@Override
		public void windowDeactivated(WindowEvent e)
		{
			System.out.println("Pierde foco: \t\t"+((MarcoVentana)e.getSource()).getTitle());
		}

		@Override
		public void windowDeiconified(WindowEvent e)
		{
			System.out.println("Ventana restaurada: \t"+((MarcoVentana)e.getSource()).getTitle());
		}

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