package practica06;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;


public class PruebaEventosRaton
{
	public static void main(String[] args)
	{
		MarcoEventosRaton mi = new MarcoEventosRaton();
	}
}

class MarcoEventosRaton extends JFrame
{
	MarcoEventosRaton()
	{
		setTitle("Capturando eventos del raton");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		// setBounds(100, 100, 400, 400);

		// Centraremos la ventana
		Toolkit miPantalla = Toolkit.getDefaultToolkit();
		Dimension resolucion = miPantalla.getScreenSize();
		int altura = resolucion.height;
		int anchura = resolucion.width;
		int tamano = 400;
		setBounds((anchura - tamano) / 2, ((altura - tamano) / 2), 
				tamano,	tamano);

		setVisible(true);
		addMouseListener(new adapterMouse());
	}

	class adapterMouse extends MouseAdapter
	{
		public void mouseEntered(MouseEvent ev)
		{
			//setBounds(100, 100, 600, 600);
			utils.UtilsFrames.centrar(((MarcoEventosRaton) ev.getSource()), 600);
		}

		public void mouseExited(MouseEvent ev)
		{
			//setBounds(100, 100, 400, 400);
			utils.UtilsFrames.centrar(((MarcoEventosRaton) ev.getSource()), 400);
		}
	}

}