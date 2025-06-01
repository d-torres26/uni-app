package application;

public class Teacher extends Person{
	public Teacher(String fName, String lName, String uName, String pWord) {
		super(fName, lName, uName, pWord);
	}
	public String saveToFile(String pWord) {
		String s=fName+";"+lName+";"+acc.getuName()+";"+pWord;
		return s;
	}

}
