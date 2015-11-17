package editortextosSinInternacionalizar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ResourceBundle;

import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.FileChooserUI;
import javax.swing.text.DefaultEditorKit;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.swing.text.StyledEditorKit;

public class MiEditorPersonalSinInternacionalizar
{
	public static void main(String[] args)
	{
		MarcoEditor me = new MarcoEditor();
		LaminaEditor le = new LaminaEditor(me);

		me.setLayout(new BorderLayout());
		me.add(le, BorderLayout.CENTER);
		
		//mejora panel inferior
		MiPanelInferior panelInfereior= new MiPanelInferior(le);
		panelInfereior.setBackground(Color.YELLOW);
		me.add(panelInfereior,BorderLayout.SOUTH);

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
// Mejora para poder serializar
class MiJTextPane extends JTextPane implements Serializable
{
	
}

// Mejora panel inferior
class MiPanelInferior extends JPanel
{
	LaminaEditor	le;
	JLabel			info;
	MiJTextPane		jtaTexto;
	MiPanelInferior(LaminaEditor le)
	{
		this.le = le;
		info = new JLabel();
		add(info);
		jtaTexto = le.jtaTexto;
		jtaTexto.addMouseListener(new MouseListener()
		{
			
			@Override
			public void mouseReleased(MouseEvent arg0)
			{}
			
			@Override
			public void mousePressed(MouseEvent arg0)
			{
				actualizarLabel();
			}
			
			@Override
			public void mouseExited(MouseEvent arg0)
			{}
			
			@Override
			public void mouseEntered(MouseEvent arg0)
			{}
			
			@Override
			public void mouseClicked(MouseEvent arg0)
			{}
		});
		jtaTexto.addKeyListener(new KeyListener()
		{
			
			@Override
			public void keyTyped(KeyEvent e)
			{
				 actualizarLabel();
			}
			
			@Override
			public void keyReleased(KeyEvent e)
			{}
			
			@Override
			public void keyPressed(KeyEvent e)
			{}
		});
	}
	private void actualizarLabel()
	{
		info.setText("Número de caracteres: "+(jtaTexto.getText().length()+1)
				+"\n Cursor en: "+(jtaTexto.getCaret().getDot()+1));
	}
}


class LaminaEditor extends JPanel 
{
	JFrame				frame;

	MiJTextPane			jtaTexto;
	JScrollPane			scroll;
	OpcionesEditor		panelOpciones;

	JButton				botonNegrita, botonCursiva;
	JButton				botonSubrayado;

	JComboBox<Integer>	comboTamanyo;
	JComboBox<String>	comboFuentes;
	String				fuentesPC[];
	JLabel				lblTamanyo;

	int					colorRojo	= 0, colorVerde = 0, colorAzul = 0;
	JCheckBox			botonCambiarColor;

	// Menus
	MenuEditorBarra		menu;
	JMenu				jmFuente, jmEstilo, jmTamanyo;
	JMenu				jmParrafo;

	JMenuItem			jmArial, jmVerdana, jmCourier, jmImpact;
	JCheckBoxMenuItem	jcbmNegrita, jcbmCursiva, jcbmSubrayado;
	JRadioButtonMenuItem	jrdm10pt, jrdm14pt, jrdm18pt, jrdm22pt;
	ButtonGroup			grupoTamanyo;
	JMenuItem			jmIzquierda, jmCentrado, jmDerecha, jmJustificado;

	// Menu 21
	JMenu				jmArchivo;
	JMenuItem			jmAbrir, jmGuardar, jmSalir;
	JButton				botonAbrir, botonGuardar, botonSalir;
	JMenu				jmInfor;
	JMenuItem			jmNombre, jmVersion;

	JButton				botonCentrar, botonDerecha, botonIzquierda,botonJustificado;
	JButton				botonColorFuente;

	// 18
	JPopupMenu			jpmMenuPop;

	// 22
	JButton				botonEspaña, botonInglaterra;

	// mejora de panel infereior
	JLabel				panelInfereior;
	// mejora portapapeles
	JButton				botonCopiar, botonPegar;
	Clipboard 			portapapeles;
	Transferable 		datosPortapapeles;
	String 				textoSelecionado;
	// mejora de insertar imágen
	JButton				botonInsertarImagen;
	
	// Forma del profesor, usar las variables globales dando los valores y actualizando
	String				tipoFuente;
	int					estiloFuente;
	int					tamanyoFuente;
	// //
	public LaminaEditor(MarcoEditor frame)
	{
		this.frame = frame;

		jtaTexto = new MiJTextPane();
		setLayout(new BorderLayout());
		add(jtaTexto, BorderLayout.CENTER);
		scroll = new JScrollPane(jtaTexto);
		add(scroll);

		panelOpciones = new OpcionesEditor();
		add(panelOpciones, BorderLayout.NORTH);

		menu = new MenuEditorBarra();
		frame.setJMenuBar(menu);
		
		//TODO 18
		jpmMenuPop= new JPopupMenu();
		//si hago eso digamos quito el menu de la barra;
		//jpmMenuPop.add(botonCursiva);
		JButton botonNegrita2 = new JButton(new ImageIcon("src/iconos/bold-2x.png"));			
		JButton botonCursiva2=new JButton(new ImageIcon("src/iconos/italic-2x.png"));		
		JButton	botonSubrayado2=new JButton(new ImageIcon("src/iconos/underline-2x.png"));		
		JButton	botonCentrar2=new JButton(new ImageIcon("src/iconos/align-center-2x.png"));
		JButton	botonDerecha2=new JButton(new ImageIcon("src/iconos/align-right-2x.png"));
		JButton	botonIzquierda2=new JButton(new ImageIcon("src/iconos/align-left-2x.png"));
		JButton	botonJustificado2=new JButton(new ImageIcon("src/iconos/justify-center-2x.png"));
		
		botonNegrita2.addActionListener(new StyledEditorKit.BoldAction());
		botonCursiva2.addActionListener(new StyledEditorKit.ItalicAction());
		botonSubrayado2.addActionListener(new StyledEditorKit.UnderlineAction());
		botonCentrar2.addActionListener(new StyledEditorKit.AlignmentAction("Centrar",StyleConstants.ALIGN_CENTER));
		botonDerecha2.addActionListener(new StyledEditorKit.AlignmentAction("Derecha",StyleConstants.ALIGN_RIGHT));
		botonIzquierda2.addActionListener(new StyledEditorKit.AlignmentAction("Izquierda",StyleConstants.ALIGN_LEFT));
		botonJustificado2.addActionListener(new StyledEditorKit.AlignmentAction("Justificado",StyleConstants.ALIGN_JUSTIFIED));
				
		jpmMenuPop.add(botonNegrita2);
		jpmMenuPop.add(botonCursiva2);
		jpmMenuPop.add(botonSubrayado2);
		jpmMenuPop.add(botonIzquierda2);
		jpmMenuPop.add(botonCentrar2);
		jpmMenuPop.add(botonDerecha2);
		jpmMenuPop.add(botonJustificado2);
		
		jtaTexto.setComponentPopupMenu(jpmMenuPop);
	}

	// Práctica 20, en esta practica solo se cambia de donde heredamos 
	class OpcionesEditor extends JToolBar
	{

		OpcionesEditor()
		{
			fuentesPC = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
			// Botones 21
			botonAbrir= new JButton(ResourceBundle.getBundle("internacionalizacion.Etiquetas").getString("etiqueta_boton_abrir"),new ImageIcon("src/iconos/open.png"));
			botonGuardar= new JButton("Guardar",new ImageIcon("src/iconos/save.png"));
			botonSalir= new JButton("Salir",new ImageIcon("src/iconos/account-logout-2x.png"));
			botonSalir.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent arg0)
				{
					int op= JOptionPane.showConfirmDialog(null, "¿Desea salir?");
					if(op==JOptionPane.OK_OPTION)
						System.exit(0);
				}
			});
			botonAbrir.addActionListener(controlFicheros);
			botonGuardar.addActionListener(controlFicheros);
			add(botonAbrir);add(botonGuardar);add(botonSalir);
			add(new JToolBar.Separator());
			
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
			comboTamanyo.setMaximumSize(new Dimension(1,40));
		
			comboTamanyo.addActionListener(apCambiarTamaño);
			add(comboTamanyo);

			// comboBox Fuentes
			// añadimos las tipos de fuentes al comboBox
			add(new JLabel("Fuentes"));
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
					String tipoLetra=((JComboBox)e.getSource()).getSelectedItem().toString();					
					Action accion= new StyledEditorKit.FontFamilyAction("TipoLtra",tipoLetra);
					accion.actionPerformed(e);	
				}
			});
			
			add(comboFuentes);
			comboFuentes.setPreferredSize(new Dimension(1,70));
			
						
			// Negrita			
			botonNegrita = new JButton(new ImageIcon("src/iconos/bold-2x.png"));
			botonNegrita.addActionListener(new StyledEditorKit.BoldAction());
	
			// Cursiva
			botonCursiva = new JButton(new ImageIcon("src/iconos/italic-2x.png"));
			botonCursiva.addActionListener(new StyledEditorKit.ItalicAction());
			
			// Boton para subrayado
			botonSubrayado=new JButton(new ImageIcon("src/iconos/underline-2x.png"));
			botonSubrayado.addActionListener(new StyledEditorKit.UnderlineAction());
			
			add(botonNegrita);
			add(botonCursiva);
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
			botonCentrar     = 	new JButton(new ImageIcon("src/iconos/align-center-2x.png"));
			botonDerecha     = 	new JButton(new ImageIcon("src/iconos/align-right-2x.png"));
			botonIzquierda   = 	new JButton(new ImageIcon("src/iconos/align-left-2x.png"));
			botonJustificado = 	new JButton(new ImageIcon("src/iconos/justify-center-2x.png"));

			botonCentrar.addActionListener(new StyledEditorKit.AlignmentAction("Centrar",StyleConstants.ALIGN_CENTER ));
			botonDerecha.addActionListener(new StyledEditorKit.AlignmentAction("Derecha",StyleConstants.ALIGN_RIGHT ));
			botonIzquierda.addActionListener(new StyledEditorKit.AlignmentAction("Izquierda",StyleConstants.ALIGN_LEFT ));
			botonJustificado.addActionListener(new StyledEditorKit.AlignmentAction("Justificado",StyleConstants.ALIGN_JUSTIFIED ));
			
			
			
			add(botonCentrar);add(botonDerecha);add(botonIzquierda);add(botonJustificado);
			//21
			botonColorFuente = new JButton("ColorFuente");
			botonColorFuente.addActionListener(new ActionListener()
			{
				
				@Override
				public void actionPerformed(ActionEvent arg0)
				{
					JColorChooser jc= new JColorChooser();
					Color color=jc.showDialog(null, "Selecione un color", Color.BLACK);
					jtaTexto.setForeground(color);
					//new StyledEditorKit.ForegroundAction("color", color);
					new StyledEditorKit.ForegroundAction ("set-foreground-blue", Color.blue);
				}
			});
			add(botonColorFuente);
			
			//22	//http://javiergarbedo.es/15-apuntes-java/ficheros/359-internacionalizacion-con-bundles
			botonEspaña = new JButton(new ImageIcon("src/iconos/españa.png"));
			add(botonEspaña);
			botonEspaña.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent arg0)
				{
					ResourceBundle bundle = ResourceBundle.getBundle("internacionalizacion.Etiquetas_es_ES");
					establecerTexto(bundle);
				}
			});
			botonInglaterra = new JButton(new ImageIcon("src/iconos/england.png"));
			add(botonInglaterra);
			botonInglaterra.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent arg0)
				{
					ResourceBundle bundle = ResourceBundle.getBundle("internacionalizacion.Etiquetas_en");
					establecerTexto(bundle);
				}
			});
			setBackground(Color.GRAY);
			
			// TODO mejora portapapeles
			botonCopiar= new JButton("Copiar");
			botonCopiar.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e)
				{
					copiarAlPortapapeles();
				}
			});
			add(botonCopiar);
			
			botonPegar= new JButton("Pegar");
			botonPegar.addActionListener(new ActionListener()
			{
				
				@Override
				public void actionPerformed(ActionEvent e)
				{
					// TODO Auto-generated method stub
					pegarDelPortapapeles();
				}
			});
			add(botonPegar);
			//TODO mejora insertar imagen
			botonInsertarImagen=new JButton("Insertar imagen");
			botonInsertarImagen.addActionListener(new ActionListener()
			{				
				@Override
				public void actionPerformed(ActionEvent e)
				{
				    JFileChooser file = new JFileChooser();
				    if(file.showOpenDialog(null)==JFileChooser.APPROVE_OPTION)
					{
				    	String ruta = file.getSelectedFile().getPath();
				    	ImageIcon im=new ImageIcon(ruta);
				    	jtaTexto.insertIcon(im);
					}
				}
			});
			add(botonInsertarImagen);
			
		}
		//TODO
		//jtaTexto.insertIcon(new ImageIcon("src/iconos/españa.png"));
		//portapapeles
		public void pegarDelPortapapeles()
		{
			portapapeles = Toolkit.getDefaultToolkit().getSystemClipboard();
			datosPortapapeles = portapapeles.getContents(null);
			  if(datosPortapapeles.isDataFlavorSupported(DataFlavor.stringFlavor))
				try
				{	
					System.out.println(""+datosPortapapeles.getTransferData(DataFlavor.stringFlavor));
					int n= jtaTexto.getCaretPosition();
					String textoJta=jtaTexto.getText();
					//String 
					String sub1=(String) textoJta.subSequence(0, n);
					String sub2=(String) textoJta.subSequence(n, textoJta.length());
					jtaTexto.setText(sub1+""+datosPortapapeles.getTransferData(DataFlavor.stringFlavor)+""+sub2);				
				} catch (UnsupportedFlavorException e)
				{
					e.printStackTrace();
				} catch (IOException e)
				{
					e.printStackTrace();
				}
		}

		public void copiarAlPortapapeles()
		{
			portapapeles = Toolkit.getDefaultToolkit().getSystemClipboard();
			StringSelection texto = new StringSelection( jtaTexto.getSelectedText());
			portapapeles.setContents(texto, texto);
		}

		// método que establecera los textos
		public void establecerTexto(ResourceBundle bundle)
		{
			//TODO
			/*
			 * //titulo
			{ "etiqueta_titulo", "Mi editor de fuentes" },
			//salir
			{ "etiqueta_salir", "¿Desea salir?" },
			//barra
			{ "etiqueta_boton_abrir", "Abrir" },
			{ "etiqueta_boton_cerrar", "Cerrar" },
			{ "etiqueta_tamaño", "Tamaño" } ,
			{ "etiqueta_fuentes", "Fuentes" } ,
			{ "etiqueta_boton_color", "ColorFuente" },
			// menu
			{ "etiqueta_boton_abrir_serializado", "Abrir serializado" } ,
			{ "etiqueta_boton_guardar_serializado", "Guardar serializado" } ,
			{ "etiqueta_menu_archivo", "Archivo" } ,
			{ "etiqueta_boton_guardar", "Guardar" } ,
			{ "etiqueta_menu_fuente", "Fuente" } ,
			{ "etiqueta_menu_estilo", "Estilo" } ,
			{ "etiqueta_menu_parrafo", "Parrafo" } ,
			{ "etiqueta_menu_parrafo_izquierda", "Izquierda" } ,
			{ "etiqueta_menu_parrafo_derecha", "Derecha" } ,
			{ "etiqueta_menu_parrafo_justificado", "Justificado" } ,
			{ "etiqueta_menu_parrafo_centrar", "Centrar" } ,
			{ "etiqueta_menu_tamaño", "Tamaño" } ,
			{ "etiqueta_menu_informacion", "Información" } ,
			{ "etiqueta_menu_informacion_nombre", "Nombre" } ,
			{ "etiqueta_menu_informacion_version", "Versión" } 
			 */
			botonSalir.setText(bundle.getString("etiqueta_boton_salir"));
			botonAbrir.setText(bundle.getString("etiqueta_boton_abrir"));
			botonGuardar.setText(bundle.getString("etiqueta_boton_guardar"));
			jmSalir.setText(bundle.getString("etiqueta_boton_salir"));
		}
	}
	//TODO serializable guardar
	public static void guardarTXT(MiJTextPane mjt) throws IOException
	{
		JFileChooser file= new JFileChooser();
		if(file.showSaveDialog(null)==JFileChooser.APPROVE_OPTION)
		{
			String ruta = file.getSelectedFile().getPath();
			FileOutputStream fos = new FileOutputStream(ruta);
			ObjectOutputStream out = new ObjectOutputStream(fos);
			out.writeObject(mjt);
			out.flush();
			out.close();
			fos.close();
		}
	}
	//TODO serializable abrir
	public static MiJTextPane abrirTXT( ) throws IOException, ClassNotFoundException
	{
		MiJTextPane jtp= new MiJTextPane();
		JFileChooser file= new JFileChooser();
		if(file.showOpenDialog(null)==JFileChooser.APPROVE_OPTION)
		{
			String ruta = file.getSelectedFile().getPath();
			FileInputStream fos = new FileInputStream(ruta);
			ObjectInputStream oin = new ObjectInputStream(fos);
			
			jtp=(MiJTextPane)oin.readObject();
			
		}
		return jtp;
	}
	
	class MenuEditorBarra extends JMenuBar
	{
		public MenuEditorBarra()
		{
			JButton abrirSeria=new JButton("Abrir Serializado");
			add(abrirSeria);
			abrirSeria.addActionListener(new ActionListener()
			{
				
				@Override
				public void actionPerformed(ActionEvent arg0)
				{
					try
					{
						jtaTexto.setStyledDocument((StyledDocument)abrirTXT().getStyledDocument());
					} catch (ClassNotFoundException | IOException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			});
			JButton guarda=new JButton("Guardar Serializado");
			add(guarda);
			guarda.addActionListener(new ActionListener()
			{
				
				@Override
				public void actionPerformed(ActionEvent arg0)
				{
					JButton b=(JButton)arg0.getSource();
					try
					{
						guardarTXT((MiJTextPane)jtaTexto);
					} catch (IOException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			});
			//21
			jmArchivo=new JMenu("Archivo");
			jmAbrir= new JMenuItem("Abrir",new ImageIcon("src/iconos/open.png"));
			jmGuardar= new JMenuItem("Guardar",new ImageIcon("src/iconos/save.png"));
			jmSalir= new JMenuItem("Salir",new ImageIcon("src/iconos/account-logout-2x.png"));
			this.add(jmArchivo);
			jmArchivo.add(jmAbrir);
			jmArchivo.add(jmGuardar);
			jmArchivo.add(jmSalir);
			jmAbrir.addActionListener(controlFicheros);
			jmGuardar.addActionListener(controlFicheros);
			jmSalir.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent arg0)
				{
					int op= JOptionPane.showConfirmDialog(null, "¿Desea salir?");
					if(op==JOptionPane.OK_OPTION)
					System.exit(0);		
				}
			});
			
			
			
			//Anterior
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
			
			// acceso directo de teclas practica 19
			jcbmNegrita.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,InputEvent.CTRL_DOWN_MASK));
			jcbmCursiva.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_K,InputEvent.CTRL_DOWN_MASK));
			jcbmSubrayado.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,InputEvent.CTRL_DOWN_MASK));

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
			
			//21 informacion
			jmInfor= new JMenu("Información");
			add(jmInfor);
			// información de usuario
			jmNombre=new JMenuItem("Nombre");
			jmNombre.addActionListener(new ActionListener()
			{
				
				@Override
				public void actionPerformed(ActionEvent arg0)
				{
					JOptionPane.showMessageDialog(null, "David López\nDiseño de interfaces");
					
				}
			});
			jmInfor.add(jmNombre);
			// información de versión
			jmVersion=new JMenuItem("Version");
			jmVersion.addActionListener(new ActionListener()
			{
				
				@Override
				public void actionPerformed(ActionEvent arg0)
				{
					JOptionPane.showMessageDialog(null, "Alpha");
				}
			});
			jmInfor.add(jmVersion);
			
			
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
			//TODO colores
			jtaTexto.setForeground(new Color(colorRojo, colorVerde,colorAzul));
			//jtaTexto.setSelectedTextColor(new Color(colorRojo, colorVerde,colorAzul));
			//jtaTexto.setCaretColor(new Color(colorRojo, colorVerde,colorAzul));
			//jtaTexto.setSelectionColor(new Color(colorRojo, colorVerde,colorAzul));
			//Action accion=new StyledEditorKit.ForegroundAction("",new Color(colorRojo, colorVerde,colorAzul) );
			
		}
	};
	// FIN Cambiar color de la fuente
	ActionListener controlFicheros= new ActionListener()
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			JFileChooser file = new JFileChooser();
			try{
				// pulsamos guardar
				if (e.getActionCommand()=="Guardar")
				{
					if(file.showSaveDialog(null)==JFileChooser.APPROVE_OPTION)
					{
						String ruta = file.getSelectedFile().getPath();
						try
						{
							PrintWriter pw= new PrintWriter(ruta);
							pw.print(jtaTexto.getText());
							pw.close();
						} catch (Exception e2)
						{
							e2.printStackTrace();
						}
						
					}
				}
				//pulsamos abrir
				else if (e.getActionCommand()=="Abrir")
				{
					if (file.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
					{
						String ruta = file.getSelectedFile().getPath();
						BufferedReader bf=null;
						StringBuilder sb = null;
						try
						{
							bf = new BufferedReader(new FileReader(ruta));
							sb=new StringBuilder();
							String linea=null;
							do{
								linea = bf.readLine();
								sb.append(linea);
							}while(linea != null);
							bf.close();
						} catch (Exception e1)
						{
							e1.printStackTrace();
						}
						
						jtaTexto.setText(sb.toString());
					}
				}}catch(Exception ex){}			
		}
	};
}

