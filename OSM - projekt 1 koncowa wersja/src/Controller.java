import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;


import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/*
 * Kontroler odpowiadaj¹cy za funkcjonalnoœæ programu
 */
public class Controller implements ActionListener{

	private ExaminationView examinationView;
	private PatientDataView patientDataView;
	private PatientListView patientListView;
	private PatientList patientList;
	private Patient selected;
	private Patient searched;
	private int selectedRow;
	
	public Controller(ExaminationView examinationView, PatientDataView patientDataView, PatientListView patientListView, PatientList patientList)
	{
		this.examinationView = examinationView;
		this.patientDataView = patientDataView;
		this.patientListView = patientListView;
		this.patientList = patientList;
		
		patientListView.getAddButton().requestFocus();
		patientListView.getAddButton().addActionListener(this);
		patientListView.getDeleteButton().addActionListener(this);
		patientDataView.getSaveButton().addActionListener(this);
		patientDataView.getCancelButton().addActionListener(this);
		examinationView.getSaveButton().addActionListener(this);
		examinationView.getCancelButton().addActionListener(this);
		
		this.disableInputMode();
		
		
		/*
		 * Listener do Tabeli
		 * Sprawdza czy jakiœ wiersz tabeli zosta³ zaznaczony
		 * Jeœli tak to w zmiennej selected przechowuje pacjenta z listy pacjentów, w zmiennej selectedRow przechowuje numer zaznaczonego wiersza.
		 * (indeksy w liœcie pacjentów odpowiadaj¹ numerom wiersza tabeli)
		 * Dodaje do formularzy dane zaznaczonego pacjenta.
		 * 
		 */
		patientListView.getPatientsTable().getTable().getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent event) {
				if (patientListView.getPatientsTable().getTable().getSelectedRow() != -1)
				{
				selectedRow = patientListView.getPatientsTable().getTable().getSelectedRow();
				selected = patientList.getListOfPatients().get(selectedRow);
				insertPatientToForm(selected);	
				insertExaminationToForm(selected);
				}
			}
		});
	}
	
	/*
	 * Wypisuje dane pacjenta do formularza.
	 * Najpierw umozliwia edycje w formularzu, czyœci formularz, zczytujê dane zaznaczonego pacjenta i wypisuje je do odpowiednich pól tekstowych.
	 */
	public void insertPatientToForm(Patient selected)
	{
		this.enableInputMode();
		this.clearPatientForm();
		patientDataView.getNameField().setText(selected.getFirstName());
		patientDataView.getSurnameField().setText(selected.getLastName());
		patientDataView.getPeselField().setText(selected.getPeselNumber());
		if (selected.getGender().toString() == "K")
		{
			patientDataView.getWomanRadio().setSelected(true);
		}
		else
		{
			patientDataView.getManRadio().setSelected(true);
		}
		
        patientDataView.getInsuranceBox().setSelectedItem(selected.getInsurance());
	}
	/*
	 * Wypisuje dane badania do formularza.
	 * Najpierw umozliwia edycje w formularzu, czyœci formularz, sprawdza czy dany pacjent ma wpisane badanie
	 * jeœli tak to zczytujê dane zaznaczonego pacjenta i wypisuje je do odpowiednich pól tekstowych.
	 */
	public void insertExaminationToForm(Patient selected)
	{
		this.clearExaminationForm();
		this.enableInputMode();
		
		
		if(selected.isExamined())
		{
			Examination examination = selected.getExamination();
			examinationView.getCalendar().setDate(examination.getExaminationDate());
			examinationView.getGgtField().setText(examination.getGgt().toString());
			examinationView.getAspatField().setText(examination.getAspat().toString());
			examinationView.getAiatField().setText(examination.getAiat().toString());
		}
	}
	
	/*
	 * Uniemozliwia wpisywanie czegokolwiek do formularzy.
	 */
	public void disableInputMode()
	{
		patientDataView.getNameField().setEditable(false);
		patientDataView.getSurnameField().setEditable(false);
		patientDataView.getPeselField().setEditable(false);
		patientDataView.getManRadio().setEnabled(false);
		patientDataView.getWomanRadio().setEnabled(false);
		patientDataView.getInsuranceBox().setEnabled(false);
		patientDataView.getSaveButton().setEnabled(false);
		patientDataView.getCancelButton().setEnabled(false);
		
		examinationView.getAiatField().setEditable(false);
		examinationView.getCalendar().setEnabled(false);
		examinationView.getAspatField().setEditable(false);
		examinationView.getGgtField().setEditable(false);
		examinationView.getSaveButton().setEnabled(false);
		examinationView.getCancelButton().setEnabled(false);
	}
	
	/*
	 * Umo¿liwia wpisywanie do formularza pacjenta
	 * Gdy jakis pacjent jest zaznaczony umo¿liwia równie¿ wpisywanie do formularza badania
	 */
	public void enableInputMode()
	{
		patientDataView.getNameField().setEditable(true);
		patientDataView.getSurnameField().setEditable(true);
		patientDataView.getPeselField().setEditable(true);
		patientDataView.getManRadio().setEnabled(true);
		patientDataView.getWomanRadio().setEnabled(true);
		patientDataView.getInsuranceBox().setEnabled(true);
		patientDataView.getSaveButton().setEnabled(true);
		patientDataView.getCancelButton().setEnabled(true);
		
		if(selected !=  null)
		{
			examinationView.getAiatField().setEditable(true);
			examinationView.getCalendar().setEnabled(true);
			examinationView.getAspatField().setEditable(true);
			examinationView.getGgtField().setEditable(true);
			examinationView.getSaveButton().setEnabled(true);
			examinationView.getCancelButton().setEnabled(true);
		}
	}
	
	/*
	 * Czysci formularz pacjenta
	 */
	public void clearPatientForm()
	{
		patientDataView.getNameField().setText("");
		patientDataView.getSurnameField().setText("");
		patientDataView.getPeselField().setText("");
		patientDataView.getGenderButtonGroup().clearSelection();
		patientDataView.getInsuranceBox().setSelectedIndex(-1);
	}
	/*
	 * Czysci formularz badania
	 */
	public void clearExaminationForm()
	{
		examinationView.getCalendar().setCalendar(null);
		examinationView.getAiatField().setText("");
		examinationView.getAspatField().setText("");
		examinationView.getGgtField().setText("");
	}
	
	/*
	 * Dodaje pacjenta
	 * - jeœli jest to nowy pacjent - tworzy nowego pacjenta, dodaje do listy pacjentów
	 *  -jeœli ju¿ istniej¹cy pacjent - dane z formularza umieszcza do zaznaczonego pacjenta i go aktualizuje
	 */
	public void addPatient()
	{
		if(validPatientData() == true)
		{
			Patient patient = null;
			String tempName = patientDataView.getNameField().getText().toString();
			String tempSurname = patientDataView.getSurnameField().getText().toString();
			String tempPeselNumber = patientDataView.getPeselField().getText().toString();
			String tempGender;
			if (patientDataView.getWomanRadio().isSelected()) {
					tempGender = "K"; }
			else {
					tempGender = "M"; }
			String tempInsurance = patientDataView.getInsuranceBox().getSelectedItem().toString();
			if(selected == null)
			{
				patient = new Patient(tempName, tempSurname, tempPeselNumber, tempGender, tempInsurance);
				patientList.getListOfPatients().add(patient);
			}
			else
			{
				selected.setFirstName(tempName);
				selected.setLastName(tempSurname);
				selected.setGender(tempGender);
				selected.setInsurance(tempInsurance);
				selected.setPeselNumber(tempPeselNumber);
			
			}
			patientListView.getPatientsTable().addPatientListToTable(patientList);
			this.clearRowSelection();
			this.clearPatientForm();
			this.clearExaminationForm();
			this.disableInputMode();
			
		int lastRow = patientListView.getPatientsTable().getTable().getRowCount() - 1;
		patientListView.getPatientsTable().getTable().setRowSelectionInterval(lastRow, lastRow);
		}

	}
	/*
	 * Usuwa pacjenta z listy i odœwie¿a tabele
	 */
	public void deletePatient(int selectedRow)
	{
		if(selected != null)
		{
			patientList.getListOfPatients().remove(selectedRow);
			patientListView.getPatientsTable().addPatientListToTable(patientList);
		}
	}
	/*
	 * Dodaje badanie do pacjenta, sprawdza czy miesz¹ siê w normie
	 *  GGT norma 8-61 U/l
 	 *  ALAT norma <=52 U/l
 	 *	AspAT norma <31U U/l
	 * 
	 */
	public void addExamination()
	{
		if(validExaminationData() == true)
		{
			Double tempGgt = Double.parseDouble(examinationView.getGgtField().getText().toString());
			Integer tempAspat = Integer.parseInt(examinationView.getAspatField().getText().toString());
			Integer tempAiat = Integer.parseInt(examinationView.getAiatField().getText().toString());
			Date tempDate = examinationView.getCalendar().getDate();
			
			Examination examination = new Examination(tempDate, tempGgt, tempAspat, tempAiat);
			
			selected.setExamination(examination);
			patientListView.getPatientsTable().addPatientListToTable(patientList);
			
			if (tempGgt < 8)
				JOptionPane.showMessageDialog(null,
					    "Zani¿ona wartoœæ GGT",
					    "Uwaga",
					    JOptionPane.WARNING_MESSAGE);
			else if(tempGgt > 61)
				JOptionPane.showMessageDialog(null,
					    "Zawy¿ona wartoœæ GGT",
					    "Uwaga",
					    JOptionPane.WARNING_MESSAGE);
			if (tempAiat >= 52)
				JOptionPane.showMessageDialog(null,
					    "Zawy¿ona wartoœæ ALAT",
					    "Uwaga",
					    JOptionPane.WARNING_MESSAGE);
			if (tempAspat >=31)
				JOptionPane.showMessageDialog(null,
					    "Zawy¿ona wartoœæ AspAT",
					    "Uwaga",
					    JOptionPane.WARNING_MESSAGE);
			
			this.clearRowSelection();
			this.clearPatientForm();
			this.clearExaminationForm();
			this.disableInputMode();
		}
	}
	/*
	 * Usuwa zaznaczenie w tabeli
	 */
	public void clearRowSelection()
	{
		patientListView.getPatientsTable().getTable().clearSelection();
		selected = null;
	}
	
	/*
	 * Sprawdza poprawnoœæ wpisanych danych pacjenta
	 */
	public boolean validPatientData()
	{
		
		boolean validName = true; 
		boolean validSurname = true;
		boolean validPesel = true;
		boolean validSex = true;
		boolean validInsurance = true;
		boolean validPatient = true; //informujê, ¿e przynajmniej jedna dana jest wpisania niepoprawnie, u¿ywana przy dodawaniu pacjenta.
		
		String pesel; // potrzebny do sprawzania unikalnoœci peselu
		
		/*
		 * Kolory obwódek pól tekstowych.
		 */
		Border redline = BorderFactory.createLineBorder(Color.red);
		Border defaultBorder = UIManager.getBorder("TextField.border");
		Color defaultColor = UIManager.getColor("RadioButton.foreground");
		
		
		//testowanie imienia
		if(patientDataView.getNameField().getText().equals("") || !patientDataView.getNameField().getText().matches("^[a-zA-Z]+$"))
		{
			patientDataView.getNameField().setBorder(redline);
			validName = false;
		}
		else
		{
			patientDataView.getNameField().setBorder(defaultBorder);
		}
			
		//testowanie nazwiska
		if(patientDataView.getSurnameField().getText().equals("")|| !patientDataView.getSurnameField().getText().matches("^[a-zA-Z]+$"))
		{
			patientDataView.getSurnameField().setBorder(redline);
			validSurname = false;
		}
		else
		{
			patientDataView.getSurnameField().setBorder(defaultBorder);
		}
			
		//testowanie peselu - sprawdzanie intergalnoœci danych peselu(czy 11 znaków i czy same cyfry)
		if(patientDataView.getPeselField().getText().length() != 11)
		{
			patientDataView.getPeselField().setBorder(redline);
			validPesel = false;
		}
		else 
		{
			try
			{
				Long.parseLong(patientDataView.getPeselField().getText().toString());
				patientDataView.getPeselField().setBorder(defaultBorder);
			}
			catch(NumberFormatException NEx)
			{
				patientDataView.getPeselField().setBorder(redline);
				validPesel = false;
			}
		}
			
		//testowanie numeru PESEL - sprawdzanie, czy numer PESEL jest inny dla ka¿dego pacjenta
		pesel = patientDataView.getPeselField().getText().toString();
		searched = patientList.searchForPatient(pesel);
		if(searched!=null && searched!=selected )
		{
			patientDataView.getPeselField().setBorder(redline);
			validPesel = false;
			searched = null;
		}
		
		//testowanie p³ci
		if(!patientDataView.getWomanRadio().isSelected() && !patientDataView.getManRadio().isSelected())
		{
			patientDataView.getWomanRadio().setForeground(Color.RED);
			patientDataView.getManRadio().setForeground(Color.RED);
			validSex = false;
		}
		else
		{
			patientDataView.getWomanRadio().setForeground(defaultColor);
			patientDataView.getManRadio().setForeground(defaultColor);
		}
		
		
		//testowanie ubezpieczenia
		if(patientDataView.getInsuranceBox().getSelectedIndex() == -1)
		{
			patientDataView.getInsuranceBox().setBorder(redline);
			validInsurance = false;
		}
		else
		{
			patientDataView.getInsuranceBox().setBorder(defaultBorder);
		}
		
		// blok wypisu b³êdów na okno
		if(validName == false)
		{
			JOptionPane.showMessageDialog(null,
				    "Nie wprowadzono imienia",
				    "B³¹d danych wejœciowych",
				    JOptionPane.ERROR_MESSAGE);
			validPatient = false;
		}
		else if(validSurname == false)
		{
			JOptionPane.showMessageDialog(null,
				    "Nie wprowadzono nazwiska",
				    "B³¹d danych wejœciowych",
				    JOptionPane.ERROR_MESSAGE);
			validPatient = false;
		}
		else if(validPesel == false)
		{
			JOptionPane.showMessageDialog(null,
				    "Wprowadzono b³êdny pesel b¹dz pesel nale¿y ju¿ do innego pacjenta",
				    "B³¹d danych wejœciowych",
				    JOptionPane.ERROR_MESSAGE);
			validPatient = false;
		}
		else if(validSex == false)
		{
			JOptionPane.showMessageDialog(null,
				    "Wprowadzono b³êdnie p³eæ",
				    "B³¹d danych wejœciowych",
				    JOptionPane.ERROR_MESSAGE);
			validPatient = false;
		}
		else if(validInsurance == false)
		{
			JOptionPane.showMessageDialog(null,
				    "Wprowadzono b³êdnie ubezpieczenie",
				    "B³¹d danych wejœciowych",
				    JOptionPane.ERROR_MESSAGE);
			validPatient = false;
		}
		else
		{
			JOptionPane.showMessageDialog(null,
				    "Wprowadzono dane pacjenta i do³¹czono do bazy",
				    "Wprowadzono zmiany",
				    JOptionPane.INFORMATION_MESSAGE);
		}
		return validPatient;
	}
	
	/*
	 * Sprawdzanie poprawnoœci danych w formularzu badania.
	 */
	public boolean validExaminationData()
	{		
		
		boolean validGgt = true; 
		boolean validAiat = true;
		boolean validAspat = true;
		boolean validDate = true;
		
		boolean validExamination = true; // informuje, ¿e przynajmniej jedna dana badania jest wpisana niepoprawnie, u¿ywana przy dodawaniu badania.
		
		Border redline = BorderFactory.createLineBorder(Color.red);
		Border defaultBorder = UIManager.getBorder("TextField.border");
		
		//testowanie Ggt
		if(examinationView.getGgtField().getText().equals(""))
		{
			examinationView.getGgtField().setBorder(redline);
			validGgt = false;
		}
		else //gdy pole niepuste
		{
			try
			{
				Double.parseDouble(examinationView.getGgtField().getText().toString());
				examinationView.getGgtField().setBorder(defaultBorder);
			}
			catch(NumberFormatException NEx)
			{
				examinationView.getGgtField().setBorder(redline);
				validGgt = false;
			}
		}
		
		//testowanie Alat
		if(examinationView.getAiatField().getText().equals(""))
		{
			examinationView.getAiatField().setBorder(redline);
			validAiat = false;
		}
		else //gdy pole niepuste
		{
			try
			{
				Integer.parseInt(examinationView.getAiatField().getText().toString());
				examinationView.getAiatField().setBorder(defaultBorder);
			}
			catch(NumberFormatException NEx)
			{
				examinationView.getAiatField().setBorder(redline);
				validAiat = false;
			}
		}
		
		//testowanie Aspat
		if(examinationView.getAspatField().getText().equals(""))
		{
			examinationView.getAspatField().setBorder(redline);
			validAspat = false;
		}
		else //gdy pole niepuste
		{
			try
			{
				Integer.parseInt(examinationView.getAspatField().getText().toString());
				examinationView.getAspatField().setBorder(defaultBorder);
			}
			catch(NumberFormatException NEx)
			{
				examinationView.getAspatField().setBorder(redline);
				validAspat = false;
			}
		}
		

		// warunek do sprawdzania wa¿noœci daty 
		Date tempDate = examinationView.getCalendar().getDate();
		if(tempDate == null)
		{
			examinationView.getCalendar().setBorder(redline);
			validDate = false;
		}
		else
		{
			examinationView.getCalendar().setBorder(defaultBorder);
			validDate = true;
		}
		
		// blok wypisu b³êdów na okno
		if(validDate == false)
		{
			JOptionPane.showMessageDialog(null,
				    "Data niezgodna z za³o¿onym formatem!",
				    "B³¹d danych wejœciowych",
				    JOptionPane.ERROR_MESSAGE);
			validExamination = false;
		}
		else if(validGgt == false)
		{
			JOptionPane.showMessageDialog(null,
				    "Brak Ggt lub niew³aœciwy typ danych dla GGT",
				    "B³¹d danych wejœciowych",
				    JOptionPane.ERROR_MESSAGE);
			validExamination = false;
		}
		else if(validAiat == false)
		{
			JOptionPane.showMessageDialog(null,
				    "Brak Aiat lub niewlaœciwy typ danych dla AiAT",
				    "B³¹d danych wejœciowych",
				    JOptionPane.ERROR_MESSAGE);
			validExamination = false;
		}
		else if(validAspat == false)
		{
			JOptionPane.showMessageDialog(null,
				    "Brak AspAT lub niew³aœciwy typ danych dla AspAT",
				    "B³¹d danych wejœciowych",
				    JOptionPane.ERROR_MESSAGE);
			validExamination = false;
		} 
		else
		{
			JOptionPane.showMessageDialog(null,
				    "Wprowadzono dane badania",
				    "i do³¹czono badanie do pacjenta",
				    JOptionPane.INFORMATION_MESSAGE);
			validExamination = true;
		}
		return validExamination;
	}
	
	/*
	 * Ustawianie domyœlnych obwódek pól tekstowych.
	 */
	public void setDefaultBorder()
	{
		Border defaultBorder = UIManager.getBorder("TextField.border");
		patientDataView.getNameField().setBorder(defaultBorder);
		patientDataView.getSurnameField().setBorder(defaultBorder);
		patientDataView.getPeselField().setBorder(defaultBorder);
		patientDataView.getInsuranceBox().setBorder(defaultBorder);
	}

	/*
	 * Obsluga przycisków 
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// przycisk "Dodaj" w paneli listy pacjentów
		if (e.getSource() == patientListView.getAddButton())
		{
			this.clearRowSelection();
			this.disableInputMode();
			this.clearPatientForm();
			this.clearExaminationForm();
			this.enableInputMode();
		}
		// przycisk "Zapisz" w panelu formularza danych pacjenta
		else if(e.getSource() == patientDataView.getSaveButton())
		{
			this.addPatient();
		} 
		// przycisk "Usuñ" w panelu listy pacjentów
		else if(e.getSource() == patientListView.getDeleteButton())
		{
			this.deletePatient(selectedRow);
			this.clearRowSelection();
			this.clearPatientForm();
			this.clearExaminationForm();
			this.setDefaultBorder();
			this.disableInputMode();
		}
		// przycisk "Zapisz" w panelu badania
		else if(e.getSource() == examinationView.getSaveButton())
		{
			this.addExamination();
		}
		// oba przyciski "Anuluj"
		else if(e.getSource() == patientDataView.getCancelButton() || e.getSource() == examinationView.getCancelButton())
		{
			this.clearRowSelection();
			this.clearPatientForm();
			this.clearExaminationForm();
			this.setDefaultBorder();
			this.disableInputMode();
		}
		
	}
}

	