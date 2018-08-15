package GPAMS;

public class Student {
	private String FName,LName;
    private String SID;
	public Student(String fName, String lName, String sID) {
		super();
		FName = fName;
		LName = lName;
		SID = sID;
	}
	public String getFName() {
		return FName;
	}
	public String getLName() {
		return LName;
	}
	public String getSID() {
		return SID;
	}
	
	
}
