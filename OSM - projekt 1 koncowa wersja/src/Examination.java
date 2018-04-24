import java.util.Date;

/*
 * Klasa badania
 */
public class Examination {
	
	private Date examinationDate;
	private Double ggt;
	private Integer aspat;
	private Integer aiat;
	
	public Examination(Date examinationDate, double ggt, int aspat, int aiat)
	{
		this.setExaminationDate(examinationDate);
		this.setGgt(new Double(ggt));
		this.setAspat(new Integer(aspat));
		this.setAiat(new Integer(aiat));
	}

	public Date getExaminationDate() {
		return examinationDate;
	}

	public void setExaminationDate(Date examinationDate) {
		this.examinationDate = examinationDate;
	}

	public Double getGgt() {
		return ggt;
	}

	public void setGgt(Double ggt) {
		this.ggt = ggt;
	}

	public Integer getAspat() {
		return aspat;
	}

	public void setAspat(Integer aspat) {
		this.aspat = aspat;
	}

	public Integer getAiat() {
		return aiat;
	}

	public void setAiat(Integer aiat) {
		this.aiat = aiat;
	}
}
