package practica08;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Spring;
import javax.swing.SpringLayout;

public class PruebaDisposiciónMuelle
{
	public static void main(String[] args)
	{
		MarcoDisposicionMuelle frame = new MarcoDisposicionMuelle();
		LaminaDisposicionMuelle lamina = new LaminaDisposicionMuelle();
		frame.add(lamina);
		utils.UtilsFrames.centrar(frame, 300);
	}
}

class MarcoDisposicionMuelle extends JFrame
{
	public MarcoDisposicionMuelle()
	{
		setVisible(true);
		setTitle("Botones");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}

class LaminaDisposicionMuelle extends JPanel
{
	JButton	b1, b2, b3;
	Spring	muelle;

	public LaminaDisposicionMuelle()
	{
		b1 = new JButton("Boton1");
		b2 = new JButton("Boton2");
		b3 = new JButton("Boton3");
		muelle = Spring.constant(0, 10, 100);

		SpringLayout milayout = new SpringLayout();
		setLayout(milayout);

		add(b1);
		add(b2);
		add(b3);

		//derecha a izq
		// (donde_empieza_Elementoderecha,elemento_empieza,tipo_muelle,donde_acaba,elemento_acaba)
		milayout.putConstraint(SpringLayout.WEST, b1, muelle,SpringLayout.WEST, this);
		milayout.putConstraint(SpringLayout.WEST, b2, muelle,SpringLayout.WEST, b1);
		milayout.putConstraint(SpringLayout.WEST, b3, muelle,SpringLayout.WEST, b2);
		milayout.putConstraint(SpringLayout.EAST, this, muelle,SpringLayout.EAST, b3);
	}
}