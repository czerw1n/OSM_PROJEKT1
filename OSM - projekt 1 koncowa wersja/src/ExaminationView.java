import javax.swing.*;
import com.toedter.calendar.*;
import java.awt.*;

/*
 * Panel formularza badania. Rozdzielony na dwa panele: dla labeli i pól tekstowych oraz dla przycisków.
 *
 */
public class ExaminationView extends JPanel 
{
	private static final long serialVersionUID = 3088520475104112398L;
	//panel formularza
	private JPanel formPane;
	//panel przycisków
	private JPanel buttonPane;
	private JLabel date;
	private JLabel ggt;
	private JLabel aiat;
	private JLabel aspat;
	private JDateChooser calendar;
	private JTextField ggtField;
	private JTextField aiatField;
	private JTextField aspatField;
	private JButton saveButton;
	private JButton cancelButton;
	
	public ExaminationView()
	{
		this.setBorder(BorderFactory.createTitledBorder("Badanie"));
		this.setPreferredSize(new Dimension(400,180));
		
		setComponents();
	}
	
	public JDateChooser getCalendar()
	{
		return this.calendar;
	}
	
	public JTextField getGgtField()
	{
		return this.ggtField;
	}
	
	public JTextField getAiatField()
	{
		return this.aiatField;
	}
	
	public JTextField getAspatField()
	{
		return this.aspatField;
	}
	
	public JPanel getFormPane()
	{
		return this.formPane;
	}
	
	public JPanel getButtonPane()
	{
		return this.buttonPane;
	}
	
	public JButton getSaveButton()
	{
		return this.saveButton;
	}
	
	public JButton getCancelButton()
	{
		return this.cancelButton;
	}
	
	/*
	 * Uk³ada panele jeden pod drugim, tworzy elementy i dodaje je do paneli.
	 */
	private void setComponents()
	{
		formPane = new JPanel();
		formPane.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		date = new JLabel("Data:");
		c.insets= new Insets(5,5,5,5);
		c.weightx = 0.5;
		c.weighty = 0.5;
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor=GridBagConstraints.LINE_START;
		formPane.add(date, c);
		
		ggt = new JLabel("GGT:");
		c.gridx = 0;
		c.gridy = 1;
		formPane.add(ggt, c);
		
		aiat = new JLabel("AlAT:");
		c.gridx = 0;
		c.gridy = 2;
		formPane.add(aiat, c);
		
		aspat = new JLabel("AspAT:");
		c.gridx = 0;
		c.gridy = 3;
		formPane.add(aspat, c);
		
		calendar = new JDateChooser();
		c.anchor = GridBagConstraints.LINE_END;
		c.gridx = 1;
		c.gridy = 0;
		calendar.setDateFormatString("dd/MM/yyyy");
		formPane.add(calendar, c);
		
		ggtField = new JTextField();
		c.gridx = 1;
		c.gridy = 1;
		formPane.add(ggtField, c);
		
		aiatField = new JTextField();
		c.gridx = 1;
		c.gridy = 2;
		formPane.add(aiatField, c);
		
		aspatField = new JTextField();
		c.gridx = 1;
		c.gridy = 3;
		formPane.add(aspatField, c);
		
		buttonPane = new JPanel();
		buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));
		
		saveButton = new JButton("Zapisz");
		cancelButton = new JButton("Anuluj");
		buttonPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		buttonPane.add(saveButton);
		buttonPane.add(Box.createRigidArea(new Dimension(10,0)));
		buttonPane.add(cancelButton);
		
		this.setLayout(new BorderLayout());
		this.add(formPane, BorderLayout.CENTER);	
		this.add(buttonPane, BorderLayout.PAGE_END);


	}
}
