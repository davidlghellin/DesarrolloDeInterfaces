package practica05;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

public class PruebaEventosTeclado2
{
	public static void main(String[] args)
	{
		MarcoVentanaEventosTeclado2 mi = new MarcoVentanaEventosTeclado2();
		utils.UtilsFrames.centrar(mi);
	}
}

class MarcoVentanaEventosTeclado2 extends JFrame
{
	Toolkit		miPantalla;
	Dimension	resolucion;
	int			altura;
	int			anchura;
	int			tamano;

	public MarcoVentanaEventosTeclado2()
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
			// Capturamos el evento
			char caracterPulsado = e.getKeyChar();
			// Si se pulsa el '+' aumentará, usamos el método estático para que centre automáticamente
			if (caracterPulsado == '+')
				utils.UtilsFrames.centrar(((MarcoVentanaEventosTeclado2) e.getSource()),
						((JFrame) e.getSource()).getWidth() + 100,
						((JFrame) e.getSource()).getHeight() + 100);
			// Disminuimos si pulsa '-'
			// En el operador ternarío se usa para forzar a que centre si el tamaño fuese menor que cero, 
			// ya que lo desplazaba un poco descentrandolo.
			else if (caracterPulsado == '-')
				utils.UtilsFrames.centrar(((MarcoVentanaEventosTeclado2) e.getSource()),
								((((JFrame) e.getSource()).getWidth() - 100 > 0) 
										?((JFrame) e.getSource()).getWidth() - 100 : 0),
								((((JFrame) e.getSource()).getWidth() - 100 > 0) 
										?((JFrame) e.getSource()).getWidth() - 100 : 0));
			// Opción para restaurar los valores de la ventana por defecto
			else if (caracterPulsado == 'r')
				utils.UtilsFrames.centrar(((MarcoVentanaEventosTeclado2) e.getSource()),400);
		}
	}
}