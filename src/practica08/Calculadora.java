package practica08;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ButtonModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Calculadora
{
	public static void main(String[] args)
	{
		MarcoCalculadora mi = new MarcoCalculadora();
		// mi.setBounds(100, 100, 300, 300);
		utils.UtilsFrames.centrar(mi);
		LaminaCalculadora miPanel = new LaminaCalculadora();
		mi.add(miPanel);

		mi.validate();
		mi.repaint();
	}
}

class MarcoCalculadora extends JFrame
{
	public MarcoCalculadora()
	{
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}

class LaminaCalculadora extends JPanel
{
	JTextField		txt;
	LaminaBotones	miPanel2;

	public LaminaCalculadora()
	{
		// JtextField
		txt = new JTextField("0");
		txt.setEditable(false);
		// txt.setBackground(Color.RED);
		txt.setHorizontalAlignment(JTextField.CENTER);

		setLayout(new BorderLayout());
		add(txt, BorderLayout.NORTH);

		// MiPanel2
		miPanel2 = new LaminaBotones(txt);
		add(miPanel2, BorderLayout.CENTER);
	}

}

class LaminaBotones extends JPanel implements ActionListener
{
	JButton[]		b			= new JButton[16];
	float			resultado;
	float			operando1;
	StringBuilder	sbNumeros	= new StringBuilder("");
	String			operador	= "";
	JTextField		txt;

	public LaminaBotones(final JTextField txt)
	{
		this.txt = txt;

		setLayout(new GridLayout(4, 4));
		setBackground(Color.BLUE);
		for (int i = 0; i < 16; i++)
		{
			b[i] = new JButton();
			add(b[i]);
		}

		// asignación de numeros a los botones
		b[0].setText("7");
		b[1].setText("8");
		b[2].setText("9");
		b[3].setText("/");
		b[4].setText("4");
		b[5].setText("5");
		b[6].setText("6");
		b[7].setText("*");
		b[8].setText("1");
		b[9].setText("2");
		b[10].setText("3");
		b[11].setText("-");
		b[12].setText("0");
		b[13].setText(".");
		b[14].setText("=");
		b[15].setText("+");

		// asignamos los adapter al teclado numeros
		for (int i = 0; i < 16; i++)
		{

			b[i].addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e)
				{
					JButton btnPulsado = (JButton) e.getSource();
					if (btnPulsado.getText().equals("="))
					{
						//al pulsar el "=" comprobaremos que operador teníamos marcado para realizar esa operación
						float operando2 = Float.parseFloat(sbNumeros.toString());
						if (operador.equals("+"))
						{
							resultado = operando1 + operando2;
							sbNumeros.delete(0, sbNumeros.length());
							System.out.println(operando1 + operando2);
							txt.setText(operando1 + operando2 + "");
						} else if (operador.equals("-"))
						{
							resultado = operando1 - operando2;
							sbNumeros.delete(0, sbNumeros.length());
							System.out.println(operando1 - operando2);
							txt.setText(operando1 - operando2 + "");
						} else if (operador.equals("*"))
						{
							resultado = operando1 * operando2;
							sbNumeros.delete(0, sbNumeros.length());
							System.out.println(operando1 * operando2);
							txt.setText(operando1 * operando2 + "");
						} else if (operador.equals("/"))
						{
							resultado = operando1 / operando2;
							sbNumeros.delete(0, sbNumeros.length());
							System.out.println(operando1 / operando2);
							txt.setText(operando1 / operando2 + "");
						}
						operando1 = resultado;
					} 
					// Si pulsamos el operaor "+" prepara el operador y actualiza los registros a usar, limpiando el operando 1
					else if (btnPulsado.getText().equals("+"))
					{
						if (operador.equals(""))
						{
							operando1 = Float.parseFloat(sbNumeros.toString());
							sbNumeros.delete(0, sbNumeros.length());
						}
						operador = "+";
						System.out.println("+");
					}
					// Si pulsamos el operaor "-" prepara el operador y actualiza los registros a usar, limpiando el operando 1
					else if (btnPulsado.getText().equals("-"))
					{
						if (operador.equals(""))
						{
							operando1 = Float.parseFloat(sbNumeros.toString());
							sbNumeros.delete(0, sbNumeros.length());
						}
						operador = "-";
						System.out.println("-");
					} 
					// Si pulsamos el operaor "/" prepara el operador y actualiza los registros a usar, limpiando el operando 1
					else if (btnPulsado.getText().equals("/"))
					{
						if (operador.equals(""))
						{
							operando1 = Float.parseFloat(sbNumeros.toString());
							sbNumeros.delete(0, sbNumeros.length());
						}
						operador = "/";
						System.out.println("/");
					} 
					// Si pulsamos el operaor "*" prepara el operador y actualiza los registros a usar, limpiando el operando 1
					else if (btnPulsado.getText().equals("*"))
					{
						if (operador.equals(""))
						{
							operando1 = Float.parseFloat(sbNumeros.toString());
							sbNumeros.delete(0, sbNumeros.length());
						}
						operador = "*";
						System.out.println("*");
					} else
					{
						sbNumeros.append(btnPulsado.getText());
						System.out.println(sbNumeros);
					}
				}
			});
		}
	}

	// ActionPErforman global
	@Override
	public void actionPerformed(ActionEvent e)
	{
		JButton btnPulsado = (JButton) e.getSource();
		sbNumeros.append(btnPulsado.getText());
		System.out.println(sbNumeros);
	}

}