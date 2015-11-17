package practica08;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PruebaDisposiciónLibre
{
	public static void main(String[] args)
	{
		MiFrameDisposicionLibre frame = new MiFrameDisposicionLibre();
		MiLaminaDisposicionLibre lamina = new MiLaminaDisposicionLibre();
		utils.UtilsFrames.centrar(frame, 400,250);
		frame.add(lamina);
	}
}

class MiFrameDisposicionLibre extends JFrame
{
	public MiFrameDisposicionLibre()
	{
		setVisible(true);
		setTitle("Registro");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}

class MiLaminaDisposicionLibre extends JPanel
{
	JLabel	l1, l2, l3;
	JTextField	t1, t2, t3;

	MiLaminaDisposicionLibre()
	{
		l1 = new JLabel("uno");
		l2 = new JLabel("dos");
		l3 = new JLabel("tres");
		t1 = new JTextField(20);
		t2 = new JTextField(20);
		t3 = new JTextField(20);

		DisposicionEnColumnas layoutCol = new DisposicionEnColumnas();
		setLayout(layoutCol);

		add(l1);
		add(t1);
		add(l2);
		add(t2);
		add(l3);
		add(t3);

	}
}

class DisposicionEnColumnas implements LayoutManager
{

	@Override
	public void addLayoutComponent(String name, Component comp)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void layoutContainer(Container parent)
	{
		int pixel = parent.getWidth();
		int n = parent.getComponentCount();
		int y = 0;
		
		for (int i = 0; i < n; i++)
		{
			if (i % 2 == 0)
				parent.getComponent(i).setBounds((pixel - 150) / 2, y, 150, 20);
			else
			{
				parent.getComponent(i).setBounds((pixel ) / 2, y, 150, 20);
				y+=20;
			}
		}

	}

	@Override
	public Dimension minimumLayoutSize(Container parent)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Dimension preferredLayoutSize(Container parent)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeLayoutComponent(Component comp)
	{
		// TODO Auto-generated method stub

	}

}