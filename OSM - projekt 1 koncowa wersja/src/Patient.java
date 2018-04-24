/*
 * Klasa pacjenta
 */
public class Patient {

	private String firstName;
	private String lastName;
	private String gender;
	private String peselNumber;
	private String insurance;
	private Boolean examined;
	private Examination examination;
	
	Patient()
	{
		this.firstName = null;
		this.lastName = null;
		this.peselNumber = null;
		this.gender = null;
		this.insurance = null;
	}
	
	public Patient(String firstName, String lastName, String peselNumber, String gender, String insurance)
	{
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.peselNumber = peselNumber;
		this.insurance = insurance;
		this.examined = false;
	}
	/*
	 * konstruktor kopiujacy
	 */
	public Patient(Patient patient)
	{
		this.firstName = patient.firstName;
		this.lastName = patient.lastName;
		this.gender = patient.gender;
		this.peselNumber = patient.peselNumber;
		this.insurance = patient.insurance;
		this.examined = patient.examined;
		this.examination = patient.examination;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPeselNumber() {
		return peselNumber;
	}

	public void setPeselNumber(String peselNumber) {
		this.peselNumber = peselNumber;
	}

	public String getInsurance() {
		return insurance;
	}

	public void setInsurance(String insurance) {
		this.insurance = insurance;
	}
	/*
	 * sprawdza czy zosta³o przeprowadzone badanie na danym pacjencie
	 */
	public Boolean isExamined() {
		return examined;
	}

	public void setExamined(Boolean examined) {
		this.examined = examined;
	}

	public Examination getExamination() {
		return examination;
	}

	public void setExamination(Examination examination) {
		this.examination = examination;
		this.examined = true;
	}
	

	
	
	


	
}

