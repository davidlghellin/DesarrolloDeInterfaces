package practica12;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class EditorFuente
{
	public static void main(String[] args)
	{
		MarcoEditor me = new MarcoEditor();
		LaminaEditor le = new LaminaEditor();

		me.setLayout(new BorderLayout());
		me.add(le, BorderLayout.CENTER);

		Toolkit miPantalla = Toolkit.getDefaultToolkit();
		Dimension resolucion = miPantalla.getScreenSize();
		int altura = resolucion.height;
		int anchura = resolucion.width;
		utils.UtilsFrames.centrar(me, anchura, altura);
	}
}

class MarcoEditor extends JFrame
{
	MarcoEditor()
	{
		setTitle("Mi editor de fuentes");
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}

class LaminaEditor extends JPanel
{
	JTextArea		jtaTexto;
	JScrollPane		scroll;
	OpcionesEditor	panelOpciones;

	public LaminaEditor()
	{
		jtaTexto = new JTextArea();
		setLayout(new BorderLayout());
		add(jtaTexto, BorderLayout.CENTER);
		scroll = new JScrollPane(jtaTexto);
		add(scroll);

		panelOpciones = new OpcionesEditor();
		add(panelOpciones, BorderLayout.NORTH);
	}

	class OpcionesEditor extends JPanel implements ActionListener
	{
		JCheckBox			chNegrita, chCursiva;

		JRadioButton		rd10pt;
		JRadioButton		rd14pt;
		JRadioButton		rd18pt;
		JRadioButton		rd22pt;
		ButtonGroup			btgPixel;

		JComboBox<String>	comboFuentes;

		// nuevo 12
		JSlider				sliRojo, sliVerde, sliAzul;

		OpcionesEditor()
		{
			// comboBox
			// añadimos las tipos de fuentes al comboBox
			comboFuentes = new JComboBox<String>();
			comboFuentes.addItem("Arial");
			comboFuentes.addItem("Courier");
			comboFuentes.addItem("Georgia");
			comboFuentes.addItem("Impact");
			comboFuentes.addItem("Verdana");
			// implementamos el actionListener con clases anónimas
			comboFuentes.addActionListener(new ActionListener()
			{

				@Override
				public void actionPerformed(ActionEvent arg0)
				{
					Font fuenteActual = jtaTexto.getFont();
					Font miFuente = new Font(((JComboBox) arg0.getSource())
							.getSelectedItem().toString(), fuenteActual
							.getStyle() + fuenteActual.getStyle(), fuenteActual
							.getSize());
					jtaTexto.setFont(miFuente);
				}
			});
			add(comboFuentes);

			chNegrita = new JCheckBox("Negrita");
			chCursiva = new JCheckBox("Cursiva");

			add(chNegrita);
			add(chCursiva);

			// RadioButtons
			rd10pt = new JRadioButton("10pt");
			rd14pt = new JRadioButton("14pt");
			rd18pt = new JRadioButton("18pt");
			rd22pt = new JRadioButton("22pt");

			rd10pt.addActionListener(this);
			rd14pt.addActionListener(this);
			rd18pt.addActionListener(this);
			rd22pt.addActionListener(this);

			btgPixel = new ButtonGroup();
			// Añadimos al grupo
			btgPixel.add(rd10pt);
			btgPixel.add(rd14pt);
			btgPixel.add(rd18pt);
			btgPixel.add(rd22pt);
			// Añadimos los radios al panel
			add(rd10pt);
			add(rd14pt);
			add(rd18pt);
			add(rd22pt);
			setBackground(Color.GRAY);

			// nuevo 12
			sliRojo = new JSlider(JSlider.HORIZONTAL, 0, 255, 125);
			sliRojo.setForeground(Color.RED);
			sliVerde = new JSlider(JSlider.HORIZONTAL, 0, 255, 125);
			sliVerde.setForeground(Color.GREEN);
			sliAzul = new JSlider(JSlider.HORIZONTAL, 0, 255, 125);
			sliAzul.setForeground(Color.BLUE);
			// ponemos nombre a los slider para diferenciarlos
			sliRojo.setName("rojo");
			sliVerde.setName("verde");
			sliAzul.setName("azul");

			// añadimos los slider
			add(sliRojo);
			add(sliVerde);
			add(sliAzul);
			ArrayList<JSlider> arraySlider = new ArrayList<JSlider>();
			arraySlider.add(sliRojo);
			arraySlider.add(sliVerde);
			arraySlider.add(sliAzul);
			for (JSlider s : arraySlider)
			{
				s.setPaintTicks(true);
				s.setMajorTickSpacing(50);
				s.setPaintLabels(true);
				s.addChangeListener(new ChangeListener()
				{

					@Override
					public void stateChanged(ChangeEvent arg0)
					{
						int colorRojo = sliRojo.getValue();
						int colorVerde = sliVerde.getValue();
						int colorAzul = sliAzul.getValue();
						// como hemos puesto nombre podemos saber que slider se
						// ha cambiado
						// System.out.println(((JSlider)arg0.getSource()).getName());
						if (((JSlider) arg0.getSource()).getName().equals("rojo"))
							colorRojo = ((JSlider) arg0.getSource()).getValue();
						else if (((JSlider) arg0.getSource()).getName().equals("verde"))
							colorVerde = ((JSlider) arg0.getSource()).getValue();
						else if (((JSlider) arg0.getSource()).getName().equals("azul"))
							colorAzul = ((JSlider) arg0.getSource()).getValue();

						// ponemos el color especificado
						jtaTexto.setForeground(new Color(colorRojo,colorVerde,colorAzul));
					}
				});
			}

			// Establecemos los action a los check
			// CheckboxNegrita
			chNegrita.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent arg0)
				{
					Font fuenteActual;
					if (chNegrita.isSelected())
					{
						fuenteActual = jtaTexto.getFont();
						Font miFuente = new Font(fuenteActual.getFontName(),
								fuenteActual.getStyle() + Font.BOLD,
								fuenteActual.getSize());
						jtaTexto.setFont(miFuente);
						System.out.println(fuenteActual);
					} else if (!chNegrita.isSelected())
					{
						fuenteActual = jtaTexto.getFont();
						Font miFuente = new Font(fuenteActual.getFontName(),
								fuenteActual.getStyle() - Font.BOLD,
								fuenteActual.getSize());
						jtaTexto.setFont(miFuente);
						System.out.println(fuenteActual);
					}

				}
			});
			// FIN CheckboxNegrita
			// CheckboxCursiva
			chCursiva.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent arg0)
				{
					Font fuenteActual;
					if (chCursiva.isSelected())
					{
						fuenteActual = jtaTexto.getFont();
						Font miFuente = new Font(fuenteActual.getFontName(),
								fuenteActual.getStyle() + Font.ITALIC,
								fuenteActual.getSize());
						jtaTexto.setFont(miFuente);
						System.out.println(fuenteActual);
					} else if (!chCursiva.isSelected())
					{
						fuenteActual = jtaTexto.getFont();
						Font miFuente = new Font(fuenteActual.getFontName(),
								fuenteActual.getStyle() - Font.ITALIC,
								fuenteActual.getSize());
						jtaTexto.setFont(miFuente);
						System.out.println(fuenteActual);
					}
				}
			});
			// FIN CheckboxCursiva
		}

		@Override
		public void actionPerformed(ActionEvent e)
		{
			JRadioButton radioSelected = ((JRadioButton) e.getSource());
			Font miFuente = jtaTexto.getFont();
			System.out.println(radioSelected);
			Font fuenteActual = jtaTexto.getFont();
			if (radioSelected.getText().equals("10pt"))
				miFuente = new Font(fuenteActual.getFontName(),
						fuenteActual.getStyle(), 10);
			else if (radioSelected.getText().equals("14pt"))
				miFuente = new Font(fuenteActual.getFontName(),
						fuenteActual.getStyle(), 14);
			else if (radioSelected.getText().equals("18pt"))
				miFuente = new Font(fuenteActual.getFontName(),
						fuenteActual.getStyle(), 18);
			else if (radioSelected.getText().equals("22pt"))
				miFuente = new Font(fuenteActual.getFontName(),
						fuenteActual.getStyle(), 22);
			jtaTexto.setFont(miFuente);
		}

	}
}
