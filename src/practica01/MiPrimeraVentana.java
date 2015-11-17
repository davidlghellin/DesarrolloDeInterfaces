package practica01;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class MiPrimeraVentana
{
	public static void main(String[] args)
	{
		MiFrame mi = new MiFrame();
		/*
		 * El siguiente método estático, se usa para centrar. 
		 * Está sobrecargado para que se ajuste a nuestras necesidades
		 */
		///////////////////////////////
		// Segunda parte de la práctica
		///////////////////////////////
		utils.UtilsFrames.centrar(mi, 600, 400);
	}
}
/**
 * Frame que creamos para realizar la primera práctica, heredando de JFrame, en el que:
 * Establecemos título 
 * Visible
 * Que se cierre la ventana al pulsar la "X" de dicha ventana
 * No se pueda ridimensionar (en ocasiones trae problemas)
 * Establecemos el icóno de la aplicación
 * 
 * @author david
 *
 */
class MiFrame extends JFrame
{
	public MiFrame()
	{
		/* Con setBounds establecemos las dimensiones y cordenadas.*/
		///////////////////////////////
		// Primera parte de la práctica
		///////////////////////////////
		//setBounds(300, 100, 600, 400);

		/*/
		 * primera prueba para centrar el frame, con los 
		 */
		// Toolkit miPnatalla = Toolkit.getDefaultToolkit();
		// // propiedades relativas a mi equipo
		// Dimension resolucion = miPnatalla.getScreenSize();
		// // propiedades de la pantalla

		// int altura = resolucion.height;
		// int anchura = resolucion.width;
		// System.out.println(altura + " altura");
		// System.out.println(anchura + " anchura");
		// int alto = 600;
		// int ancho = 400;
		// // setBounds((anchura-this.getSize().width)/2,
		// // (altura-this.getSize().height)/2, alto, ancho);
		// setBounds((anchura - ancho) / 2, (altura - alto) / 2, alto, ancho);
		// System.out.println(alto + " alto");
		// System.out.println(ancho + " ancho");

		// setLocationRelativeTo(null);

		setTitle("David López González");
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);

		Image icon = new ImageIcon(getClass().getResource(
				"/imagenes/España.png")).getImage();
		setIconImage(icon);
	}
}