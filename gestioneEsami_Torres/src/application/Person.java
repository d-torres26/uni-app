package application;

public class Person {
	protected String fName;
	protected String lName;
	protected Account acc;

	public Person() {}
	public Person(String fName, String lName, String uName, String pWord) {
		char c=' ';
		this.fName=fName;
		this.lName=lName;
		if(this instanceof Student)c='s';
		else if(this instanceof Teacher) c='t';
		acc=new Account(uName, pWord, c);
	}

	public String getfName() {
		return fName;
	}
	public String getlName() {
		return lName;
	}
	public Account getAcc() {
		return acc;
	}
	public void setfName(String fName) {
		this.fName = fName;
	}
	public void setlName(String lName) {
		this.lName = lName;
	}
	public void setuName(String uName) {
		acc.setuName(uName);
	}
	public void setpWord(String pWord) {
		acc.setpWord(pWord);
	}
	public boolean logIn(String uName, String pWord) {
		return acc.logIn(uName, pWord);
	}
	public String getFullName() {
		return fName+" "+lName;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((acc == null) ? 0 : acc.hashCode());
		result = prime * result + ((fName == null) ? 0 : fName.hashCode());
		result = prime * result + ((lName == null) ? 0 : lName.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (acc == null) {
			if (other.acc != null)
				return false;
		} else if (!acc.equals(other.acc))
			return false;
		if (fName == null) {
			if (other.fName != null)
				return false;
		} else if (!fName.equals(other.fName))
			return false;
		if (lName == null) {
			if (other.lName != null)
				return false;
		} else if (!lName.equals(other.lName))
			return false;
		return true;
	}
	public String saveToFile() {
		String s=fName+";"+lName+";"+acc.saveToFile();
		return s;
	}

}
