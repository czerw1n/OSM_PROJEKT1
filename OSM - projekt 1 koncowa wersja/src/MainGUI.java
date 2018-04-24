import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
/*
 * Klasa tworz¹ca widok aplikacji, definiuje rozmieszczenie paneli trzech g³ównych paneli oraz paska menu
 *
 */
public class MainGUI extends JFrame implements ActionListener
{
 static final long serialVersionUID = -5778856184731491899L;

	public MainGUI()
	{
		PatientList patientList = new PatientList();
		PatientDataView patientDataView = new PatientDataView();
		ExaminationView examinationView = new ExaminationView();
		PatientListView patientListView = new PatientListView(patientList);
		
		@SuppressWarnings("unused")
		Controller controller = new Controller(examinationView, patientDataView, patientListView, patientList);
		
		
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		c.weightx = 0.5;
		c.weighty = 0.5;
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.BOTH;
		this.getContentPane().add(patientDataView, c);
		
		c.gridx = 0;
		c.gridy = 1;
		this.getContentPane().add(examinationView, c);
		
		c.gridx = 1;
		c.gridy = 0;
		c.gridheight = 2;
		this.getContentPane().add(patientListView, c);
		
		this.setMenu();
		
		this.setTitle("Rejestracja wyników");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		
		// umieszczenie okna na œrodku ekranu
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - this.getSize().width) / 2);
        int y = (int) ((dimension.getHeight() - this.getSize().height) / 2);
		this.setLocation(x,y);
	}

	public static void main(String[] args) 
	{
		Runnable thread = new Runnable()
		{
			@Override
			public void run()
			{
				MainGUI app = new MainGUI();
				app.setVisible(true);
		
			}
		};
		SwingUtilities.invokeLater(thread);
	}
	/**
	 * Tworzy pasek menu
	 */
	private void setMenu()
	{
		JMenuBar menuBar = new JMenuBar();
		this.setJMenuBar(menuBar);
		menuBar.setVisible(true);
		
		JMenu menu = new JMenu("Aplikacja");
		menuBar.add(menu);
		
		JMenuItem menuItem = new JMenuItem("Zamknij");
		menuItem.addActionListener(this);
		menu.add(menuItem);
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if (e.getActionCommand().equals("Zamknij"))
		{
			this.dispose();
		}
		
	}
}
