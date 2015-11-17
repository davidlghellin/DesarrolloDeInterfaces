package utils;

import java.awt.Event;
import java.awt.Font;

import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.event.ChangeEvent;

/**
 * Clase auxiliar para ayudarnos en la parte que corresponde cambiar la fuente
 * 
 * @author david
 * 
 */
public class UtilsFuentes
{
	public static Font cambiarFuente(int tamanyo,JTextArea jTextArea,ChangeEvent e)
	{
		Font miFuente = jTextArea.getFont();

		Font fuenteActual = jTextArea.getFont();

		miFuente = new Font(fuenteActual.getFontName(),
				fuenteActual.getStyle(), Integer.parseInt(((JSpinner) e.getSource())
						.getValue().toString()));
		
		return miFuente;
		
		
	}
}
