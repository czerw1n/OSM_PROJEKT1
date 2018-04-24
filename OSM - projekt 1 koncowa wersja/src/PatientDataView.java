import java.awt.*;
import javax.swing.*;
/*
 * Panel formularza danych pacjenta. Rozdzielony na dwa panele: dla labeli i pól tekstowych oraz dla przycisków.
 *
 */
public class PatientDataView extends JPanel
{

	private static final long serialVersionUID = -4855202081479802741L;
	//panel labeli i pól tekstowych
	private JPanel formPane;
	//panel przycisków
	private JPanel buttonPane;
	private JLabel name;
	private JLabel surname;
	private JLabel pesel;
	private JLabel gender;
	private JLabel insurance;
	private JTextField nameField;
	private JTextField surnameField;
	private JTextField peselField;
	private JRadioButton womanRadio;
	private JRadioButton manRadio;
	private JComboBox<String> insuranceBox;
	private JButton saveButton;
	private JButton cancelButton;
	private ButtonGroup genderButtonGroup;
	
	public PatientDataView()
	{
		this.setBorder(BorderFactory.createTitledBorder("Dane pacjenta"));
		this.setPreferredSize(new Dimension(400,220));
		
		setComponents();
	}
	
	public JTextField getNameField()
	{
		return this.nameField;
	}
	
	public JTextField getSurnameField()
	{
		return this.surnameField;
	}
	
	public JTextField getPeselField()
	{
		return this.peselField;
	}
	
	public JRadioButton getWomanRadio()
	{
		return this.womanRadio;
	}
	
	public JRadioButton getManRadio()
	{
		return this.manRadio;
	}
	
	public JComboBox<String> getInsuranceBox()
	{
		return this.insuranceBox;
	}
	
	public JPanel getButtonPane()
	{
		return this.buttonPane;
	}
	
	public JPanel getFormPane()
	{
		return this.formPane;
	}
	
	public JButton getSaveButton()
	{
		return this.saveButton;
	}
	
	public JButton getCancelButton()
	{
		return this.cancelButton;
	}
	
	public ButtonGroup getGenderButtonGroup()
	{
		return this.genderButtonGroup;
	}
	/*
	 * Uk³ada panele jeden pod drugim, tworzy elementy i dodaje je do paneli.
	 */
	private void setComponents()
	{
		formPane = new JPanel();
		formPane.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		name = new JLabel("Imiê:");
		c.insets= new Insets(5,5,5,5);
		c.weightx = 0.5;
		c.weighty = 0.5;
		c.ipadx = 50;
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor=GridBagConstraints.LINE_START;
		formPane.add(name, c);
		
		surname = new JLabel("Nazwisko:");
		c.gridx=0;
		c.gridy=1;
		formPane.add(surname, c);
		
		pesel = new JLabel("PESEL:");
		c.gridx=0;
		c.gridy=2;
		formPane.add(pesel, c);
		
		gender = new JLabel("P³eæ:");
		c.gridx=0;
		c.gridy=3;
		formPane.add(gender, c);
		
		insurance = new JLabel("Ubezpieczenie:");
		c.gridx=0;
		c.gridy=4;
		formPane.add(insurance, c);
		
		nameField = new JTextField();
		nameField.setMaximumSize(new Dimension(200,50));
		c.ipadx = 0;
		c.gridx=2;
		c.gridy=0;
		c.gridwidth=2;
		formPane.add(nameField, c);
		
		surnameField = new JTextField();
		c.gridx=2;
		c.gridy=1;
		formPane.add(surnameField, c);
		
		peselField = new JTextField();
		c.gridx=2;
		c.gridy=2;
		formPane.add(peselField, c);
		
		womanRadio = new JRadioButton("Kobieta");
		c.gridx=2;
		c.gridy=3;
		c.gridwidth=1;
		formPane.add(womanRadio, c);
		
		manRadio = new JRadioButton("Mê¿czyzna");
		c.gridx=3;
		c.gridy=3;
		formPane.add(manRadio, c);
		
		genderButtonGroup = new ButtonGroup();
		genderButtonGroup.add(manRadio);
		genderButtonGroup.add(womanRadio);
		
		String[] insuranceType = { "NFZ" , "Prywatne" , "Brak" };
		insuranceBox = new JComboBox<String>(insuranceType);
		insuranceBox.setSelectedIndex(-1);
		c.gridx=2;
		c.gridy=4;
		c.gridwidth=2;
		formPane.add(insuranceBox, c);	
		
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
