package practica14;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.SpinnerListModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class EditorFuenteMenu
{
	public static void main(String[] args)
	{
		MarcoEditor me = new MarcoEditor();
		LaminaEditor le = new LaminaEditor(me);

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
	JFrame				frame;

	JTextArea			jtaTexto;
	JScrollPane			scroll;
	OpcionesEditor		panelOpciones;

	JCheckBox			chNegrita, chCursiva;

	
	JComboBox<String>	comboFuentes;

	JSlider				sliRojo, sliVerde, sliAzul;

	// práctica 13
	JSpinner			spinnerFuente, spinnerTamanyo;

	// Práctica 14
	MenuEditorBarra		menu;
	JMenu				jmFuente, jmEstilo, jmTamanyo;

	JMenuItem			jmArial, jmVerdana, jmCourier, jmImpact;
	JMenuItem			jmNegrita, jmCursiva;
	JMenuItem			jm10pt, jm14pt, jm18pt, jm22pt;

	// Forma del profesor, usar las variables globales dando los valores y actualizando
	String tipoFuente;
	int estiloFuente;
	int tamanyoFuente;
	public LaminaEditor(MarcoEditor frame)
	{
		this.frame = frame;

		jtaTexto = new JTextArea();
		setLayout(new BorderLayout());
		add(jtaTexto, BorderLayout.CENTER);
		scroll = new JScrollPane(jtaTexto);
		add(scroll);

		panelOpciones = new OpcionesEditor();
		add(panelOpciones, BorderLayout.NORTH);

		menu = new MenuEditorBarra();
		frame.setJMenuBar(menu);
	}

	class OpcionesEditor extends JPanel implements ActionListener
	{

		OpcionesEditor()
		{
			// práctica 13
			String fuentesPC[] = GraphicsEnvironment
					.getLocalGraphicsEnvironment()
					.getAvailableFontFamilyNames();
			spinnerFuente = new JSpinner(new SpinnerListModel(fuentesPC));
			
			//TODO
			//spinnerFuente.addChangeListener();
			//add(spinnerFuente);
			
			JLabel lblTamanyo = new JLabel("Tamaño");
			add(lblTamanyo);
			SpinnerNumberModel num = new SpinnerNumberModel();
			num.setMinimum(1);
			spinnerTamanyo = new JSpinner(num);

			spinnerTamanyo.setValue(14);
			spinnerTamanyo.addChangeListener(clTamanyoFuente);
			add(spinnerTamanyo);

			// comboBox
			// añadimos las tipos de fuentes al comboBox
			comboFuentes = new JComboBox<String>();
			comboFuentes.addItem("Arial");
			comboFuentes.addItem("Courier");
			comboFuentes.addItem("Georgia");
			comboFuentes.addItem("Impact");
			comboFuentes.addItem("Verdana");
			// implementamos el actionListener con clases anónimas
			comboFuentes.addActionListener(alTipoLetra);
			add(comboFuentes);

			chNegrita = new JCheckBox("Negrita");
			chCursiva = new JCheckBox("Cursiva");

			add(chNegrita);
			add(chCursiva);

			
			setBackground(Color.GRAY);

			// nuevo 12
			sliRojo = new JSlider(JSlider.HORIZONTAL, 0, 255, 0);
			sliVerde = new JSlider(JSlider.HORIZONTAL, 0, 255, 0);
			sliAzul = new JSlider(JSlider.HORIZONTAL, 0, 255, 0);
			sliRojo.setForeground(Color.RED);
			sliVerde.setForeground(Color.GREEN);
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
				
				//TODO poner las anónimas en internas
				s.addChangeListener(clColoresFuente);
			}

			// Establecemos los action a los check
			// CheckboxNegrita
			chNegrita.addActionListener(alPonerNegrita);
			// FIN CheckboxNegrita
			// CheckboxCursiva
			chCursiva.addActionListener(alPonerCursiva);
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

	// Práctica 14
	class MenuEditorBarra extends JMenuBar
	{

		public MenuEditorBarra()
		{
			jmFuente = new JMenu("Fuente");
			jmEstilo = new JMenu("Estilo");
			jmTamanyo = new JMenu("Tamaño");

			this.add(jmFuente);
			this.add(jmEstilo);
			this.add(jmTamanyo);

			// Fuentes
			jmFuente.add(jmArial = new JMenuItem("Arial"));
			jmFuente.add(jmVerdana = new JMenuItem("Verdana"));
			jmFuente.add(jmCourier = new JMenuItem("Courier"));
			jmFuente.add(jmImpact = new JMenuItem("Impact"));
			jmArial.addActionListener(alTipoLetra);
			jmVerdana.addActionListener(alTipoLetra);
			jmCourier.addActionListener(alTipoLetra);
			jmImpact.addActionListener(alTipoLetra);
			
			// Estilos
			jmEstilo.add(jmNegrita = new JMenuItem("Negrita"));
			jmEstilo.add(jmCursiva = new JMenuItem("Cursiva"));
			// Eventos
			jmNegrita.addActionListener(alPonerNegrita);
			jmCursiva.addActionListener(alPonerCursiva);

			// Tamaño
			jmTamanyo.add(jm10pt = new JMenuItem("10"));
			jmTamanyo.add(jm14pt = new JMenuItem("14"));
			jmTamanyo.add(jm18pt = new JMenuItem("18"));
			jmTamanyo.add(jm22pt = new JMenuItem("22"));
			// Establecemos los eventos del tamaño
			jm10pt.addActionListener(apCambiarTamaño);
			jm14pt.addActionListener(apCambiarTamaño);
			jm18pt.addActionListener(apCambiarTamaño);
			jm22pt.addActionListener(apCambiarTamaño);
		}
	}
	// Negrita
	ActionListener alPonerNegrita=new ActionListener()
	{
		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			Font fuenteActual=jtaTexto.getFont();
			Font miFuente = null;
			// si pulsamos el menu de negrita, lo encerramos en un try para evitar la excepción
			try
			{
				if ((JMenuItem) arg0.getSource() == jmNegrita)
					chNegrita.setSelected(true);
			} catch (Exception e)
			{

			}
			// Comprobación de 
			if (chNegrita.isSelected())
			{	
				miFuente = new Font(fuenteActual.getFontName(),fuenteActual.getStyle() + Font.BOLD,
							fuenteActual.getSize());
				
				System.out.println(fuenteActual);
			} else if (!chNegrita.isSelected())
			{
				fuenteActual = jtaTexto.getFont();
				miFuente = new Font(fuenteActual.getFontName(),fuenteActual.getStyle() - Font.BOLD,
							fuenteActual.getSize());

				System.out.println(fuenteActual);
			}
			jtaTexto.setFont(miFuente);
		}
	};
	// FIN Negrita
	// Cursiva
	ActionListener alPonerCursiva=new ActionListener()
	{
		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			//TODO
			String textoDelObjeto=arg0.getActionCommand();
			System.out.println(textoDelObjeto);
			
			Font fuenteActual=jtaTexto.getFont();
			Font miFuente = null;
			// si pulsamos el menu de negrita, lo encerramos en un try para evitar la excepción
			try
			{
				if ((JMenuItem) arg0.getSource() == jmCursiva)
					chCursiva.setSelected(true);
			} catch (Exception e)
			{

			}
			if (chCursiva.isSelected())
			{
				fuenteActual = jtaTexto.getFont();
				miFuente = new Font(fuenteActual.getFontName(),fuenteActual.getStyle() + Font.ITALIC, fuenteActual.getSize());
				
				System.out.println(fuenteActual);
			} else if (!chCursiva.isSelected())
			{
				fuenteActual = jtaTexto.getFont();
				 miFuente = new Font(fuenteActual.getFontName(),fuenteActual.getStyle() - Font.ITALIC, fuenteActual.getSize());
				
				System.out.println(fuenteActual);
			}
			jtaTexto.setFont(miFuente);
		}
	};
	// FIN Cursiva
	
	// Cambiar tamaño de la fuente con change
	ChangeListener clTamanyoFuente=new ChangeListener()
	{
		@Override
		public void stateChanged(ChangeEvent e)
		{
			// TODO
			Font miFuente = null ;//= jtaTexto.getFont();
			Font fuenteActual = jtaTexto.getFont();
			
			miFuente = new Font(fuenteActual.getFontName(),fuenteActual.getStyle(), 
								Integer.parseInt(((JSpinner) e.getSource()).getValue().toString()));
			
			jtaTexto.setFont(miFuente);
		}
	};
	// Cambiar tamaño con action perfonmand
		ActionListener apCambiarTamaño=new ActionListener()
		{
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				Font miFuente = null ;//= jtaTexto.getFont();
				Font fuenteActual = jtaTexto.getFont();
				int n=Integer.parseInt(((JMenuItem)e.getSource()).getText());
				miFuente = new Font(fuenteActual.getFontName(),fuenteActual.getStyle(),n);
				
				// Para tener coherencia con el programa
				spinnerTamanyo.setValue(n);
				
				jtaTexto.setFont(miFuente);
			}
		};
	// FIN Cambiar tamaño de la fuente
	
	// Cambiar color de la fuente
	ChangeListener clColoresFuente=new ChangeListener()
	{
	
		@Override
		public void stateChanged(ChangeEvent arg0)
		{
			int colorRojo = 0;
			int colorVerde = 0;
			int colorAzul = 0;
			
			// Controlamos el evento de slider
			if(((JSlider)arg0.getSource())==sliAzul || 
				((JSlider)arg0.getSource())==sliVerde|| 
				((JSlider)arg0.getSource())==sliRojo)
			{
				// System.out.println("Movemos slider");
				 colorRojo = sliRojo.getValue();
				 colorVerde = sliVerde.getValue();
				 colorAzul = sliAzul.getValue();
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
				
			}
			jtaTexto.setForeground(new Color(colorRojo, colorVerde,colorAzul));
		}
	};
	// FIN Cambiar color de la fuente
	
	// Cambiar tipo de letra change Listener
	ChangeListener clTipoLetra=new ChangeListener()
	{

		@Override
		public void stateChanged(ChangeEvent e)
		{
			String tipoFuente = (((JSpinner) e.getSource()).getValue()).toString();
			Font miFuente = jtaTexto.getFont();

			Font fuenteActual = jtaTexto.getFont();

			miFuente = new Font(tipoFuente, fuenteActual.getStyle(),fuenteActual.getSize());
			jtaTexto.setFont(miFuente);
		}
	};
	ActionListener alTipoLetra= new ActionListener()
	{
		
		@Override
		public void actionPerformed(ActionEvent e)
		{
			Font fuenteActual = jtaTexto.getFont();
			Font miFuente ;
			String tipoLetra=null;
			// Comprobamos si se a pulsado por menú item
			try
			{
				if (((JMenuItem) e.getSource()) == jmCourier|| 
					((JMenuItem) e.getSource()) == jmArial	||
					((JMenuItem) e.getSource()) == jmVerdana|| 
					((JMenuItem) e.getSource()) == jmImpact)
					{
					System.out.println(((JMenuItem)e.getSource()).getText());
					comboFuentes.setSelectedItem(((JMenuItem)e.getSource()).getText());
					tipoLetra=((JMenuItem)e.getSource()).getText();
				}
				else
					 tipoLetra=((JComboBox) e.getSource()).getSelectedItem().toString();
			}catch (Exception ex){}
			
			// Comprobamos si se a pulsado por combobox
			try
			{
				if (((JComboBox) e.getSource()) == comboFuentes)
					tipoLetra = ((JComboBox) e.getSource()).getSelectedItem().toString();
			} catch (Exception ex){}
			
			miFuente= new Font(tipoLetra, fuenteActual.getStyle(), fuenteActual.getSize());
			jtaTexto.setFont(miFuente);
			
		}
	};
}
