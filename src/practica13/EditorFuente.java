package practica13;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JTextPane;
import javax.swing.SpinnerListModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.StyledEditorKit;

public class EditorFuente
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
	JTextPane	styleTexto;
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
	String				tipoFuente;
	int					estiloFuente;
	int					tamanyoFuente;

	public LaminaEditor(MarcoEditor frame)
	{
		styleTexto = new JTextPane();
		setLayout(new BorderLayout());

		scroll = new JScrollPane(styleTexto);
		add(scroll);
		
		panelOpciones = new OpcionesEditor();
		add(panelOpciones, BorderLayout.NORTH);
		
		add(styleTexto, BorderLayout.CENTER);

		menu = new MenuEditorBarra();
		frame.setJMenuBar(menu);
	}
	class OpcionesEditor extends JPanel 
	{

		OpcionesEditor()
		{
			// práctica 13
			String fuentesPC[] = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
			spinnerFuente = new JSpinner(new SpinnerListModel(fuentesPC));
			
			//TODO
			//spinnerFuente.addChangeListener();
			add(spinnerFuente);
			
			JLabel lblTamanyo = new JLabel("Tamaño");
			add(lblTamanyo);
			SpinnerNumberModel num = new SpinnerNumberModel();
			num.setMinimum(1);
			spinnerTamanyo = new JSpinner(num);

			spinnerTamanyo.setValue(14);
			//spinnerTamanyo.addChangeListener(clTamanyoFuente);
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
			//comboFuentes.addActionListener(alTipoLetra);
			//TODO
			add(comboFuentes);

			chNegrita = new JCheckBox("Negrita");
			chNegrita.addActionListener(new StyledEditorKit.BoldAction());
			
			chCursiva = new JCheckBox("Cursiva");
			chCursiva.addActionListener(new StyledEditorKit.ItalicAction());

			add(chNegrita);
			add(chCursiva);

			
			setBackground(Color.GRAY);

			// nuevo 12
			sliRojo = new JSlider(JSlider.HORIZONTAL, 0, 255, 0);
			sliVerde = new JSlider(JSlider.HORIZONTAL, 0, 255, 0);
			sliAzul = new JSlider(JSlider.HORIZONTAL, 0, 255, 0);
			//Texto de los slider
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
				
				//TODO 
				s.addChangeListener(clColoresFuente);
			}

			// Establecemos los action a los check
			// CheckboxNegrita
			//chNegrita.addActionListener(alPonerNegrita);
			// FIN CheckboxNegrita
			// CheckboxCursiva
			//chCursiva.addActionListener(alPonerCursiva);
			// FIN CheckboxCursiva

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
		
			
			// Estilos
			jmEstilo.add(jmNegrita = new JMenuItem("Negrita"));
			jmEstilo.add(jmCursiva = new JMenuItem("Cursiva"));
			// Eventos

			// Tamaño
			jmTamanyo.add(jm10pt = new JMenuItem("10"));
			jmTamanyo.add(jm14pt = new JMenuItem("14"));
			jmTamanyo.add(jm18pt = new JMenuItem("18"));
			jmTamanyo.add(jm22pt = new JMenuItem("22"));
			// Establecemos los eventos del tamaño
		}
	}
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
				styleTexto.setForeground(new Color(colorRojo, colorVerde,colorAzul));
				
			}
		};
		// FIN Cambiar color de la fuente
}
