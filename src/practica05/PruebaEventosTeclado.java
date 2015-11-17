package practica05;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

public class PruebaEventosTeclado
{
	public static void main(String[] args)
	{
		MarcoVentanaEventosTeclado mi = new MarcoVentanaEventosTeclado();
	}
}

class MarcoVentanaEventosTeclado extends JFrame
{
	Toolkit		miPantalla;
	Dimension	resolucion;
	int			altura;
	int			anchura;
	int			tamano;

	public MarcoVentanaEventosTeclado()
	{
		miPantalla = Toolkit.getDefaultToolkit();
		resolucion = miPantalla.getScreenSize();
		setTitle("Capturando eventos del teclado");
		// setBounds(100, 100, 400, 400);
		// Centraremos la ventana
		altura = resolucion.height;
		anchura = resolucion.width;
		tamano = 400;
		setBounds((anchura - tamano) / 2, ((altura - tamano) / 2), tamano,
				tamano);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		addKeyListener(new adapterVentana());
	}

	class adapterVentana extends KeyAdapter
	{
		@Override
		public void keyPressed(KeyEvent e)
		{
			// Capturamos el
			char caracterPulsado = e.getKeyChar();
			if (caracterPulsado == '+')
				((JFrame) e.getSource()).setBounds(
						((JFrame) e.getSource()).getX() - 50,
						((JFrame) e.getSource()).getY() - 50,
						((JFrame) e.getSource()).getWidth() + 100,
						((JFrame) e.getSource()).getHeight() + 100);
			else if (caracterPulsado == '-')
				((JFrame) e.getSource()).setBounds(
						((JFrame) e.getSource()).getX() + 50,
						((JFrame) e.getSource()).getY() + 50,
						((JFrame) e.getSource()).getWidth() - 100,
						((JFrame) e.getSource()).getHeight() - 100);
			else if (caracterPulsado == 'r')
				setBounds((anchura - tamano) / 2, ((altura - tamano) / 2),
						tamano, tamano);
		}
	}
}