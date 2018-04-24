import java.awt.*;
import javax.swing.*;
/*
 * Panel listy pacjentów. Rozdzielony na dwa panele: dla listy oraz dla przycisków.
 */
public class PatientListView extends JPanel
{
	private static final long serialVersionUID = -8340201359408569953L;
	
	//panel listy
	private JPanel tablePane;
	//panel przycisków
	private JPanel buttonPane;
	private PatientsTable patientsTable;
	private JButton addButton;
	private JButton deleteButton;
	private PatientList patientList;
	
	public PatientListView(PatientList patientList)
	{
		this.setBorder(BorderFactory.createTitledBorder("Lista pacjentów"));
		this.setPreferredSize(new Dimension(500,500));
		this.patientList = patientList;
		setComponents();
		
	}
	
	public JPanel getTablePane()
	{
		return this.tablePane;
	}
	
	public PatientsTable getPatientsTable()
	{
		return this.patientsTable;
	}
	
	public JPanel getButtonPane()
	{
		return this.buttonPane;
	}
	
	public JButton getAddButton()
	{
		return this.addButton;
	}
	
	public JButton getDeleteButton()
	{
		return this.deleteButton;
	}
	/*
	 * 
	 * Uk³ada panele jeden pod drugim, tworzy elementy i dodaje je do paneli.
	 */
	private void setComponents()
	{			 
		tablePane = new JPanel();
		tablePane.setLayout(new BorderLayout());
		patientsTable = new PatientsTable(patientList);
		tablePane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		tablePane.add(patientsTable, BorderLayout.CENTER);
		
		buttonPane = new JPanel();
		buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));
		addButton = new JButton("Dodaj");
		deleteButton = new JButton("Usuñ");
		
		buttonPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		buttonPane.add(addButton);
		buttonPane.add(Box.createRigidArea(new Dimension(10,0)));
		buttonPane.add(deleteButton);
		
		this.setLayout(new BorderLayout());
		this.add(tablePane, BorderLayout.CENTER);	
		this.add(buttonPane, BorderLayout.PAGE_END);

		
	}
}
