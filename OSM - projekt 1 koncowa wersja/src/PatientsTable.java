import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/*
 * Tabela zawieraj¹ca dane pacjentów
 */
public class PatientsTable extends JPanel
{

	private static final long serialVersionUID = -1757359108409977035L;
	
	private JTable table;
	private JScrollPane scrollPane;
	private DefaultTableModel defaultModel;
	private PatientList patientList;

	public PatientsTable(PatientList patientList)
	{
		this.patientList = patientList;
		this.configureModel();
		
		table = new JTable(defaultModel);
		table.setFillsViewportHeight(true);
		table.getColumnModel().getColumn(1).setMaxWidth(40);
		table.getColumnModel().getColumn(4).setMaxWidth(60);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		scrollPane = new JScrollPane(table);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		this.setLayout(new BorderLayout());
		this.add(scrollPane, BorderLayout.CENTER);
		
	}
	
	/*
	 * Konfiguracja modelu DefaultTableModel 
	 * 
	 * Override:
	 * 	-isCellEditable uniemo¿liwia edycje komórek Tabeli klikaj¹c bezpoœrednio w nie.
	 * 	-getColumnClass powodujê wyœwietlenie siê pola do zaznaczenia w kolumnie Badanie (bez tego wyœwietlane by³oby true/false)
	 */
	public void configureModel()
	{
		defaultModel = new DefaultTableModel(){

			private static final long serialVersionUID = 8731799746740707591L;
			
			@Override
		    public boolean isCellEditable(int row, int column) {
		       return false;
		    }
			
			@Override
			public Class<?> getColumnClass(int columnIndex) {
			    if (columnIndex == 4)
			        return Boolean.class;
			    return super.getColumnClass(columnIndex);
			}
		};
		defaultModel.addColumn("Imiê i nazwisko");
		defaultModel.addColumn("P³eæ");
		defaultModel.addColumn("PESEL");
		defaultModel.addColumn("Ubezpieczenie");
		defaultModel.addColumn("Badanie");
	}
	/*
	 * Dodanie ca³ej listy do JTable - dzia³a na zasadzie odœwie¿ania. Najpierw wszystko usuwa aby nastêpnie dodaæ na nowo zmodyfikowane dane.
	 */
	public void addPatientListToTable(PatientList patientList)
	{
		defaultModel.getDataVector().removeAllElements();
		defaultModel.fireTableDataChanged();
		for(int i = 0; i < patientList.getListOfPatients().size();i++)
		{
			defaultModel.addRow(new Object[]{patientList.getListOfPatients().get(i).getFirstName() + " " + patientList.getListOfPatients().get(i).getLastName(),
					patientList.getListOfPatients().get(i).getGender(), patientList.getListOfPatients().get(i).getPeselNumber(),
					patientList.getListOfPatients().get(i).getInsurance(),
					patientList.getListOfPatients().get(i).isExamined()});
		}
		
	}
	
	public JTable getTable()
	{
		return this.table;
	}
	
	public DefaultTableModel getDefaultModel()
	{
		return this.defaultModel;
	}
	
	public PatientList getPatientList()
	{
		return this.patientList;
	}
}
