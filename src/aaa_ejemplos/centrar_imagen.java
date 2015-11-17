package aaa_ejemplos;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class centrar_imagen
{
	public static void main(String[] args)
	{
		Frame mi = new Frame();
		utils.UtilsFrames.centrar(mi);
	}

}

class Frame extends JFrame
{
	Toolkit		miPantalla;
	Dimension	resolucion;
	int			altura;
	int			anchura;
	int			tamano;

	Frame()
	{
		setTitle("Centrar ventana");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		// Centraremos la ventana
		/*
		 * miPantalla = Toolkit.getDefaultToolkit(); resolucion
		 * =miPantalla.getScreenSize(); altura = resolucion.height; anchura =
		 * resolucion.width; tamano = 400;
		 */
	}
}
