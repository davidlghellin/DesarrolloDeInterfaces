package practica15;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.beans.FeatureDescriptor;
import java.util.ArrayList;

import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
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
import javax.swing.JTextPane;
import javax.swing.SpinnerListModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.basic.BasicSliderUI.ActionScroller;
import javax.swing.text.StyledEditorKit;
import javax.swing.text.StyledEditorKit.FontFamilyAction;
import javax.swing.text.StyledEditorKit.ForegroundAction;

public class EditorFuenteSeleccion
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

	JTextPane			jtaTexto;
	JScrollPane			scroll;
	OpcionesEditor		panelOpciones;

	JCheckBox			chNegrita, chCursiva;
	JButton 			botonSubrayado;

	JComboBox<Integer>  comboTamanyo;
	JComboBox<String>	comboFuentes;
	String fuentesPC[] ;
	JLabel lblTamanyo;

	JSlider				sliRojo, sliVerde, sliAzul;
	int					colorRojo	= 0, colorVerde = 0, colorAzul = 0;
	JCheckBox			botonCambiarColor;
	
	// Menus
	MenuEditorBarra		menu;
	JMenu				jmFuente, jmEstilo, jmTamanyo;

	JMenuItem			jmArial, jmVerdana, jmCourier, jmImpact;
	JMenuItem			jmNegrita, jmCursiva;
	JMenuItem			jm10pt, jm14pt, jm18pt, jm22pt;

	// Forma del profesor, usar las variables globales dando los valores y actualizando
	String tipoFuente;
	int estiloFuente;
	int tamanyoFuente;
	////
	JLabel labelPruebas;
	public LaminaEditor(MarcoEditor frame)
	{
		this.frame = frame;

		jtaTexto = new JTextPane();
		setLayout(new BorderLayout());
		add(jtaTexto, BorderLayout.CENTER);
		scroll = new JScrollPane(jtaTexto);
		add(scroll);

		panelOpciones = new OpcionesEditor();
		add(panelOpciones, BorderLayout.NORTH);

		menu = new MenuEditorBarra();
		frame.setJMenuBar(menu);
	}

	class OpcionesEditor extends JPanel
	{

		OpcionesEditor()
		{
			fuentesPC = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
			
			// comboBox Tamaño
			// añadimos los tamaños de fuentes al comboBox
			 lblTamanyo = new JLabel("Tamaño");
			add(lblTamanyo);
			comboTamanyo= new JComboBox<Integer>();
			for (int i = 1; i < 31; i++)
			{
				comboTamanyo.addItem(i*2);
			}
			
			comboTamanyo.setSelectedIndex(5);
		
			comboTamanyo.addActionListener(new ActionListener()
			{
				
				@Override
				public void actionPerformed(ActionEvent e)
				{
					int tamanyo=(int)((JComboBox)e.getSource()).getSelectedItem();
					// Establecemos la acción que queremos que haga cuando se cambie
					Action accion = new StyledEditorKit.FontSizeAction("Tamaño",tamanyo);
					// Hacemos que la accion ejecute el actionPerformand 
					accion.actionPerformed(e);
				}
			});
			add(comboTamanyo);

			// comboBox Fuentes
			// añadimos las tipos de fuentes al comboBox
			comboFuentes = new JComboBox<String>();
			for (String f : fuentesPC)
			{
				comboFuentes.addItem(f);
			}
			
			// TODO
			comboFuentes.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e)
				{
					/*System.out.println(e.getActionCommand());
					String tipoLetra=((JComboBox)e.getSource()).getSelectedItem().toString();
					((JComboBox)e.getSource()).addActionListener(new StyledEditorKit.FontFamilyAction("TipoLtra",tipoLetra));
					(comboFuentes).addActionListener(new StyledEditorKit.FontFamilyAction("TipoLtra",tipoLetra));
					System.out.println(tipoLetra);*/

					String tipoLetra=((JComboBox)e.getSource()).getSelectedItem().toString();					
					Action accion= new StyledEditorKit.FontFamilyAction("TipoLtra",tipoLetra);
					accion.actionPerformed(e);
					
				}
			});
			comboFuentes.addMouseListener(new MouseListener()
			{
				
				@Override
				public void mouseReleased(MouseEvent e)
				{
					String tipoLetra=((JComboBox)e.getSource()).getSelectedItem().toString();
					// TODO Auto-generated method stub
					labelPruebas.setText(tipoLetra);
				}
				
				@Override
				public void mousePressed(MouseEvent e)
				{
					String tipoLetra=((JComboBox)e.getSource()).getSelectedItem().toString();
					// TODO Auto-generated method stub
					labelPruebas.setText(tipoLetra);
				}
				
				@Override
				public void mouseExited(MouseEvent e)
				{
					String tipoLetra=((JComboBox)e.getSource()).getSelectedItem().toString();
					// TODO Auto-generated method stub
					labelPruebas.setText(tipoLetra);
				}
				
				@Override
				public void mouseEntered(MouseEvent e)
				{
					String tipoLetra=((JComboBox)e.getSource()).getSelectedItem().toString();
					// TODO Auto-generated method stub
					labelPruebas.setText(tipoLetra);
				}
				
				@Override
				public void mouseClicked(MouseEvent e)
				{
					String tipoLetra=((JComboBox)e.getSource()).getSelectedItem().toString();
					// TODO Auto-generated method stub
					labelPruebas.setText(tipoLetra);
				}
			});
			add(comboFuentes);
			labelPruebas=new JLabel("prueba de formatod de funete");
			add(labelPruebas);
						
			// CheckboxNegrita			
			chNegrita = new JCheckBox("Negrita");
			chNegrita.addActionListener(new StyledEditorKit.BoldAction());
			// FIN CheckboxNegrita
			
			// CheckboxCursiva
			chCursiva = new JCheckBox("Cursiva");
			chCursiva.addActionListener(new StyledEditorKit.ItalicAction());
			// FIN CheckboxCursiva
			
			add(chNegrita);
			add(chCursiva);
			
			// Boton para subrayado
			botonSubrayado=new JButton("Subrayar");
			botonSubrayado.addActionListener(new StyledEditorKit.UnderlineAction());
			add(botonSubrayado);
			
			setBackground(Color.GRAY);

			// Colores de la fuente
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
	
				//TODO
				s.addChangeListener(clColoresFuente);
			}
			//TODO jboton para cambiar el color, no funciona
//			botonCambiarColor = new JCheckBox("botonCambiarColor");
//			botonCambiarColor.addActionListener(
//					new StyledEditorKit.ForegroundAction("Color", new Color(colorRojo, colorVerde,colorAzul)));
//			add(botonCambiarColor);
		}

		

	}


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
			//TODO
//			jmArial.addActionListener(alTipoLetra);
//			jmVerdana.addActionListener(alTipoLetra);
//			jmCourier.addActionListener(alTipoLetra);
//			jmImpact.addActionListener(alTipoLetra);
			
			// Estilos
			jmEstilo.add(jmNegrita = new JMenuItem("Negrita"));
			jmEstilo.add(jmCursiva = new JMenuItem("Cursiva"));
			// Eventos
			jmNegrita.addActionListener(new StyledEditorKit.BoldAction());
			jmCursiva.addActionListener(new StyledEditorKit.ItalicAction());

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
			comboTamanyo.setSelectedItem(n);
			
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
	
	
}
