package practica09;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Curriculum
{
	public static void main(String[] args)
	{
		MarcoCurriculum mc = new MarcoCurriculum();
		LaminaCurriculum lc = new LaminaCurriculum();
		mc.add(lc);
		utils.UtilsFrames.centrar(mc);
	}
}

class MarcoCurriculum extends JFrame
{
	public MarcoCurriculum()
	{
		setTitle("Curriculum");
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}

class LaminaCurriculum extends JPanel
{
	JTextArea	jtxCurriculum;
	JButton		btnSaltos;
	JScrollPane	scroll;

	public LaminaCurriculum()
	{
		setLayout(new BorderLayout());
		jtxCurriculum = new JTextArea(30,30);
		
		// Creamos el scroll pasandole como parámetro el elemento que le queremos añadir dicho scroll
		scroll = new JScrollPane(jtxCurriculum);
		add(scroll, BorderLayout.CENTER);
		
		btnSaltos = new JButton("Añadir saltos");
		add(btnSaltos, BorderLayout.SOUTH);

		btnSaltos.addActionListener(new ActionListener()
		{

			/**
			 * Activar o desactivar el LineWrap
			 */
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				if (jtxCurriculum.getLineWrap())
					jtxCurriculum.setLineWrap(false);
				else
					jtxCurriculum.setLineWrap(true);
			}
		});
	}

}