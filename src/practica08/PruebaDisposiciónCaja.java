package practica08;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class PruebaDisposiciónCaja
{
	public static void main(String[] args)
	{
		MarcoDisposicionCaja frame = new MarcoDisposicionCaja();
		LaminaDisposicionCaja panel = new LaminaDisposicionCaja();
		utils.UtilsFrames.centrar(frame, 400,250);
		frame.add(panel);
	}
}

class MarcoDisposicionCaja extends JFrame
{
	public MarcoDisposicionCaja()
	{
		setVisible(true);
		setTitle("login");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}

class LaminaDisposicionCaja extends JPanel
{
	JLabel			jlbUsu, jlbpass;
	JTextField		jtfUsu;
	JPasswordField	jpasPas;
	JButton			jbtLimpiar, jbtAcceder;

	public LaminaDisposicionCaja()
	{
		jlbUsu = new JLabel("Usuario:");
		jtfUsu = new JTextField(20);
		
		jlbpass = new JLabel("Contaseña:");
		jpasPas = new JPasswordField();
		jbtLimpiar = new JButton("Limpiar");
		jbtAcceder = new JButton("Acceder");

		Box box = Box.createVerticalBox();
		Box boxH1 = Box.createHorizontalBox();
		Box boxH2 = Box.createHorizontalBox();
		Box boxH3 = Box.createHorizontalBox();

		add(box);

		// horizontal 1
		// añadimos elementos al boxH1 y luego lo añadimos al box
		boxH1.add(jlbUsu);
		// Separación entre los elementos jlbUsu y jtfUsu de 30
		boxH1.add(Box.createHorizontalStrut(30));
		boxH1.add(jtfUsu);
		box.add(boxH1);

		// horizontal 2
		// añadimos elementos al boxH2 y luego lo añadimos al box
		boxH2.add(jlbpass);
		// Separación entre los elementos jlbpass y jpasPas de 30
		boxH2.add(Box.createHorizontalStrut(30));
		boxH2.add(jpasPas);
		box.add(boxH2);

		// horizontal 3
		boxH3.add(jbtLimpiar);
		// El pegamento adhiere el elemento al extremo
		boxH3.add(Box.createGlue());
		boxH3.add(jbtAcceder);
		box.add(boxH3);

	}
}