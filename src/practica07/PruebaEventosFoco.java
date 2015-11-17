package practica07;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PruebaEventosFoco
{
	public static void main(String[] args)
	{
		MarcoEventosFoco miFrame = new MarcoEventosFoco();
		LaminaEventosFoco lamina = new LaminaEventosFoco();
		miFrame.add(lamina);

		miFrame.validate();
	}
}

class MarcoEventosFoco extends JFrame
{

	MarcoEventosFoco()
	{
		setTitle("Capturando eventos del foco");
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		utils.UtilsFrames.centrar(this,400);
	}

}

class LaminaEventosFoco extends JPanel
{
	private JLabel	lNombre, lApellidos;
	private JTextField	txtNombre, txtApellidos;

	LaminaEventosFoco()
	{
		lNombre = new JLabel("nombre");
		lApellidos = new JLabel("apellidos");
		txtNombre = new JTextField();
		txtApellidos = new JTextField();
		txtNombre.setColumns(6);
		txtApellidos.setColumns(6);
		add(lNombre);
		add(txtNombre);
		add(lApellidos);
		add(txtApellidos);
		// Al JTextField
		txtNombre.addFocusListener(new FocoMayusculas());
		txtApellidos.addFocusListener(new FocoMayusculas());
	}

	class FocoMayusculas implements FocusListener
	{
		@Override
		public void focusGained(FocusEvent e)
		{

		}
		// Cuando pierde el foco, el texto lo ponemos en mayusculas
		@Override
		public void focusLost(FocusEvent e)
		{
			((JTextField) e.getSource()).setText(((JTextField) e.getSource()).getText().toUpperCase());
		}
	}
}