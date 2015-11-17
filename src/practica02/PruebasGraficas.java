package practica02;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class PruebasGraficas
{
	public static void main(String[] args)
	{
		VentanaGrafica miVentanaGrafica = new VentanaGrafica();

		LaminaGrafica miLaminaGrafica = new LaminaGrafica();
		miVentanaGrafica.add(miLaminaGrafica);
	}
}

class VentanaGrafica extends JFrame
{
	public VentanaGrafica()
	{
		setTitle("David López González Mis Gráficos");
		setBounds(100, 100, 800, 500);
		setVisible(true);
		// setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}

class LaminaGrafica extends JPanel
{
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		// Opciones de la fuente y su respectiva escritura
		Font miFuente = new Font("Arial", Font.ITALIC, 20);
		g2.setFont(miFuente);
		g2.setPaint(Color.RED);
		g2.drawString("Dibujando gráficos con Swing", 250, 100);
		// g2.drawRect(120,120, 400, 200);
		// Preparamos para pintar un rectangulo azul
		g2.setPaint(Color.BLUE);
		System.out.println("tamaño"+this.getWidth());
		g2.fillRect(195, 120, 400, 200);
		// Preparamos para pintar un circulo verde
		g2.setPaint(Color.GREEN);
		g2.fillOval(345, 170, 100, 100);
		// Preparamos para las lieas amarillas
		g2.setPaint(Color.YELLOW);
		g2.drawLine(195, 120, 595, 320);
		g2.drawLine(195, 320, 595, 120);

		// Enlazamos la imagen con el fichero
		File miImagen = new File("src/imagenes/java.png");

		// Intentamos leer la imagen
		Image imagen = null;
		try
		{
			imagen = ImageIO.read(miImagen);
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Pintamos la imagen
		g2.drawImage(imagen, 2, 2, null);
	}
}

class LaminaGrafica2 extends JPanel
{
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Font mifuente = new Font("Arial", Font.ITALIC, 20);
		g.setFont(mifuente);
		g.drawString("Dibujando gráficos con Swing", 100, 100);
	}
}
