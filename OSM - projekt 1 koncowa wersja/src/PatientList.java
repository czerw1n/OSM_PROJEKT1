import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
/*
 * Klasa reprezentuj¹ca liste pacjentów
 */
public class PatientList {
	
	private List<Patient> listOfPatients;

	public PatientList() {
		listOfPatients = new ArrayList<Patient>();
	}
	
	public List<Patient> getListOfPatients()
	{
		return this.listOfPatients;
	}
	/*
	 * Dodaje pacjenta do listy
	 */
	public void addPatientToList(String firstName, String lastName, String peselNumber, String gender, String insurance)
	{
		Patient buffer = new Patient(firstName, lastName, peselNumber, gender, insurance);
		this.listOfPatients.add(buffer);
	}

	/*
	 * Wyszukuje pacjenta w liscie po jego numerze PESEL
	 * Jeœli pacjent o danym numerze PESEL istnieje - zwraca go.
	 */
	public Patient searchForPatient(String SearchingPesel)
	{
		Iterator<Patient> iterator = listOfPatients.iterator();
		
		System.out.println("pesel do wyszukania");
		System.out.println(SearchingPesel);
		Patient SearchedPatient = null;
		
		while(iterator.hasNext())
		{
			SearchedPatient = iterator.next(); //SearchedPatient - wyszukiwany pacjent
			if(SearchedPatient.getPeselNumber().equals(SearchingPesel))
			{
				return SearchedPatient;
			}
		}
		return null;
	}
	

}
