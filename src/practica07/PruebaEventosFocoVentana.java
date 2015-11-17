package practica07;

import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

import javax.swing.JFrame;

public class PruebaEventosFocoVentana
{
	public static void main(String[] args)
	{
		Frame mi = new Frame();
		mi.setBounds(100, 100, 300, 300);
		Frame mi2 = new Frame();
		utils.UtilsFrames.centrar(mi2);
	}
}

class Frame extends JFrame
{
	Frame()
	{
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		addWindowFocusListener(new windowListener());
	}

	class windowListener implements WindowFocusListener
	{
		@Override
		public void windowGainedFocus(WindowEvent e)
		{
			((JFrame) e.getSource()).setTitle("Tengo el foco");
		}

		@Override
		public void windowLostFocus(WindowEvent e)
		{
			((JFrame) e.getSource()).setTitle("");
		}
	}
}
