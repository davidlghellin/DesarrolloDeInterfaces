package practica03;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class PruebaEventos
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

class LaminaEventos extends JPanel implements ActionListener
{
	// Creamos las variables para poder tener acceso desde todos los métodos
	JButton	btnVerde;
	JButton	btnAzul;
	JButton	btnRojo;

	public LaminaEventos()
	{
		// Añadimos los 3 botones
		add(btnRojo = new JButton("Rojo"));
		add(btnVerde = new JButton("Verde"));
		add(btnAzul = new JButton("Azul"));

		// A los botones le asignamos el evento a la fuente (Botones)
		// El oyente es el que realizará la acción. En este caso la lámina
		btnAzul.addActionListener(this);
		btnRojo.addActionListener(this);
		btnVerde.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		Object btnPulsado = e.getSource();
		if (btnPulsado == btnAzul)
			this.setBackground(Color.BLUE);
		else if (btnPulsado == btnVerde)
			this.setBackground(Color.GREEN);
		else if (btnPulsado == btnRojo)
			this.setBackground(Color.RED);
	}
}