package utils;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

/**
 * Clase auxiliar para ayudarnos en la parte que corresponde a centrar la imagen
 * Son métodos estáticos para poder hacer la llamada sin construir ninguna objeto 
 * y así facilitar el código.
 * 
 * @author david
 *
 */
public class UtilsFrames
{
	/**
	 * Método que sirve para centrar el frame, siendo tanto de ancho como de
	 * alto de 400
	 * 
	 * @param jframe
	 */
	public static void centrar(JFrame jframe)
	{
		Toolkit miPantalla = Toolkit.getDefaultToolkit();
		Dimension resolucion = miPantalla.getScreenSize();
		int altura = resolucion.height;
		int anchura = resolucion.width;
		int tamano = 400;
		jframe.setBounds((anchura - tamano) / 2, ((altura - tamano) / 2),
				tamano, tamano);
	}

	/**
	 * Método que sirve para centrar el frame, pasando el tamaño
	 * 
	 * @param jframe
	 * @param tamano = ancho y alto
	 */
	public static void centrar(JFrame jframe, int tamano)
	{
		Toolkit miPantalla = Toolkit.getDefaultToolkit();
		Dimension resolucion = miPantalla.getScreenSize();
		int altura = resolucion.height;
		int anchura = resolucion.width;
		jframe.setBounds((anchura - tamano) / 2, ((altura - tamano) / 2), tamano, tamano);
	}

	/**
	 * Método que sirve para centrar el frame, pasando el ancho y el alto del frame 
	 * 
	 * @param jframe
	 * @param anchoFrame
	 * @param altoFrame
	 */
	public static void centrar(JFrame jframe, int anchoFrame, int altoFrame)
	{
		Toolkit miPantalla = Toolkit.getDefaultToolkit();
		Dimension resolucion = miPantalla.getScreenSize();
		int altura = resolucion.height;
		int anchura = resolucion.width;
		jframe.setBounds((anchura - anchoFrame) / 2,
				((altura - altoFrame) / 2), anchoFrame, altoFrame);
	}
}
