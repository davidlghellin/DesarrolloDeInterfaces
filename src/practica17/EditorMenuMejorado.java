package practica17;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledEditorKit;

public class EditorMenuMejorado
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

	JButton		botonNegrita, botonCursiva;
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
	JMenu 				jmParrafo;

	JMenuItem			jmArial, jmVerdana, jmCourier, jmImpact;
	JCheckBoxMenuItem	jcbmNegrita, jcbmCursiva, jcbmSubrayado;
	JRadioButtonMenuItem	jrdm10pt, jrdm14pt, jrdm18pt, jrdm22pt;
	ButtonGroup			grupoTamanyo;
	JMenuItem			jmIzquierda,jmCentrado,jmDerecha,jmJustificado;
	
	// 16
	JButton				botonCentrar, botonDerecha, botonIzquierda,botonJustificado;
	
	// Forma del profesor, usar las variables globales dando los valores y
	// actualizando
	String				tipoFuente;
	int					estiloFuente;
	int 				tamanyoFuente;
	////
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
		
			comboTamanyo.addActionListener(apCambiarTamaño);
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
			
			add(comboFuentes);
			
						
			// Negrita			
			botonNegrita = new JButton(new ImageIcon("src/iconos/bold-2x.png"));
			botonNegrita.addActionListener(new StyledEditorKit.BoldAction());
			// FIN Negrita
			
			// Cursiva
			botonCursiva = new JButton(new ImageIcon("src/iconos/italic-2x.png"));
			botonCursiva.addActionListener(new StyledEditorKit.ItalicAction());
			// FIN Cursiva
			add(botonNegrita);
			add(botonCursiva);
			// Boton para subrayado
			botonSubrayado=new JButton(new ImageIcon("src/iconos/underline-2x.png"));
			botonSubrayado.addActionListener(new StyledEditorKit.UnderlineAction());
			add(botonSubrayado);
			
			botonNegrita.addActionListener(new ActionListener()
			{
				
				@Override
				public void actionPerformed(ActionEvent e)
				{
					if (jcbmNegrita.isSelected())
						jcbmNegrita.setSelected(false);
					else
						jcbmNegrita.setSelected(true);

				}
			});
			botonCursiva.addActionListener(new ActionListener()
			{
				
				@Override
				public void actionPerformed(ActionEvent e)
				{
					if (jcbmCursiva.isSelected())
						jcbmCursiva.setSelected(false);
					else
						jcbmCursiva.setSelected(true);

				}
			});
			botonSubrayado.addActionListener(new ActionListener()
			{
				
				@Override
				public void actionPerformed(ActionEvent e)
				{
					if (jcbmSubrayado.isSelected())
						jcbmSubrayado.setSelected(false);
					else
						jcbmSubrayado.setSelected(true);

				}
			});
	

			
			// Alineado de texto (pract.16)
			botonCentrar=new JButton(new ImageIcon("src/iconos/align-center-2x.png"));
			botonDerecha=new JButton(new ImageIcon("src/iconos/align-right-2x.png"));
			botonIzquierda=new JButton(new ImageIcon("src/iconos/align-left-2x.png"));
			botonJustificado=new JButton(new ImageIcon("src/iconos/justify-center-2x.png"));
			
			botonCentrar.addActionListener(new StyledEditorKit.AlignmentAction("Centrar",StyleConstants.ALIGN_CENTER ));
			botonDerecha.addActionListener(new StyledEditorKit.AlignmentAction("Derecha",StyleConstants.ALIGN_RIGHT ));
			botonIzquierda.addActionListener(new StyledEditorKit.AlignmentAction("Izquierda",StyleConstants.ALIGN_LEFT ));
			botonJustificado.addActionListener(new StyledEditorKit.AlignmentAction("Justificado",StyleConstants.ALIGN_JUSTIFIED ));
			
			
			
			add(botonCentrar);add(botonDerecha);add(botonIzquierda);add(botonJustificado);
			
			
			setBackground(Color.GRAY);

			// Colores de la fuente
			sliRojo = new JSlider(JSlider.HORIZONTAL, 0, 255, 0);
			sliVerde = new JSlider(JSlider.HORIZONTAL, 0, 255, 0);
			sliAzul = new JSlider(JSlider.HORIZONTAL, 0, 255, 0);
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
			jmParrafo= new JMenu("Parrafo");

			this.add(jmFuente);
			this.add(jmEstilo);
			this.add(jmParrafo);
			this.add(jmTamanyo);

			// Fuentes
			jmFuente.add(jmArial = new JMenuItem("Arial"));
			jmFuente.add(jmVerdana = new JMenuItem("Verdana"));
			jmFuente.add(jmCourier = new JMenuItem("Courier"));
			jmFuente.add(jmImpact = new JMenuItem("Impact"));
			jmArial.addActionListener(new StyledEditorKit.FontFamilyAction("TipoLetra","Arial"));
			jmVerdana.addActionListener(new StyledEditorKit.FontFamilyAction("TipoLetra","Verdana"));
			jmCourier.addActionListener(new StyledEditorKit.FontFamilyAction("TipoLetra","Courier"));
			jmImpact.addActionListener(new StyledEditorKit.FontFamilyAction("TipoLetra","Impact"));
			
			// Estilos
			jmEstilo.add(jcbmNegrita = new JCheckBoxMenuItem(new ImageIcon("src/iconos/bold-2x.png")));
			jmEstilo.add(jcbmCursiva= new JCheckBoxMenuItem(new ImageIcon("src/iconos/italic-2x.png")));
			jmEstilo.add(jcbmSubrayado = new JCheckBoxMenuItem(new ImageIcon("src/iconos/underline-2x.png")));
			jcbmNegrita.addActionListener(new StyledEditorKit.BoldAction());
			jcbmCursiva.addActionListener(new StyledEditorKit.ItalicAction());
			jcbmSubrayado.addActionListener(new StyledEditorKit.UnderlineAction());

			// Parrafo
			jmParrafo.add(jmIzquierda= new JMenuItem("Izquierda", new ImageIcon("src/iconos/align-left-2x.png")));
			jmParrafo.add(jmCentrado= new JMenuItem("Centrado",new ImageIcon("src/iconos/align-center-2x.png")));
			jmParrafo.add(jmDerecha= new JMenuItem("Derecha",new ImageIcon("src/iconos/align-right-2x.png")));
			jmParrafo.add(jmJustificado= new JMenuItem("Justificado",new ImageIcon("src/iconos/justify-center-2x.png")));
			
			jmCentrado.addActionListener(new StyledEditorKit.AlignmentAction("Centrar",StyleConstants.ALIGN_CENTER ));
			jmDerecha.addActionListener(new StyledEditorKit.AlignmentAction("Derecha",StyleConstants.ALIGN_RIGHT ));
			jmIzquierda.addActionListener(new StyledEditorKit.AlignmentAction("Izquierda",StyleConstants.ALIGN_LEFT ));
			jmJustificado.addActionListener(new StyledEditorKit.AlignmentAction("Justificado",StyleConstants.ALIGN_JUSTIFIED ));
			
			
			
			// Tamaño
			jmTamanyo.add(jrdm10pt = new JRadioButtonMenuItem("10"));
			jmTamanyo.add(jrdm14pt = new JRadioButtonMenuItem("14"));
			jmTamanyo.add(jrdm18pt = new JRadioButtonMenuItem("18"));
			jmTamanyo.add(jrdm22pt = new JRadioButtonMenuItem("22"));
			
			grupoTamanyo=new ButtonGroup();
			grupoTamanyo.add(jrdm10pt);
			grupoTamanyo.add(jrdm14pt);
			grupoTamanyo.add(jrdm18pt);
			grupoTamanyo.add(jrdm22pt);
			
			// Establecemos los eventos del tamaño
			jrdm10pt.addActionListener(apCambiarTamaño);
			jrdm14pt.addActionListener(apCambiarTamaño);
			jrdm18pt.addActionListener(apCambiarTamaño);
			jrdm22pt.addActionListener(apCambiarTamaño);
		}
	}
	
	
	// Cambiar tamaño con action perfonmand
	ActionListener apCambiarTamaño=new ActionListener()
	{
		
		@Override
		public void actionPerformed(ActionEvent e)
		{
			int tamanyo=14;
			try{
				if((JComboBox)(e.getSource())==comboTamanyo)
				{
					 tamanyo=(int)((JComboBox)e.getSource()).getSelectedItem();	
					 if(tamanyo==10) jrdm10pt.setSelected(true);
					 else if(tamanyo==14) jrdm14pt.setSelected(true);
					 else if(tamanyo==18) jrdm18pt.setSelected(true);
					 else if(tamanyo==22) jrdm22pt.setSelected(true);
				}
			}catch(Exception ex){}
			try{ 
				if((JRadioButtonMenuItem)(e.getSource())==jrdm10pt ||(JRadioButtonMenuItem)(e.getSource())==jrdm14pt ||
					(JRadioButtonMenuItem)(e.getSource())==jrdm18pt ||(JRadioButtonMenuItem)(e.getSource())==jrdm22pt )
				{
					 tamanyo = Integer.parseInt(((JRadioButtonMenuItem)e.getSource()).getText());
					 comboTamanyo.setSelectedItem(tamanyo);
				}
			}catch(Exception ex){}
			// Establecemos la acción que queremos que haga cuando se cambie
			Action accion = new StyledEditorKit.FontSizeAction("Tamaño",tamanyo);
			// Hacemos que la accion ejecute el actionPerformand 
			accion.actionPerformed(e);
			
			//como lo hacia antes
			/*
			
			Font miFuente = null ;//= jtaTexto.getFont();
			Font fuenteActual = jtaTexto.getFont();
			int n=Integer.parseInt(((JMenuItem)e.getSource()).getText());
			miFuente = new Font(fuenteActual.getFontName(),fuenteActual.getStyle(),n);
			
			// Para tener coherencia con el programa
			comboTamanyo.setSelectedItem(n);
			
			jtaTexto.setFont(miFuente);*/
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
