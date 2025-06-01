package application;

public class Account {
	private String uName;
	private String pWord;
	private char aType;
	
	public Account(String uName, String pWord, char aType) {
		this.uName=uName;
		this.pWord=pWord;
		this.aType=aType;
	}

	public String getuName() {
		return uName;
	}
		public char getaType() {
		return aType;
	}
	public void setuName(String uName) {
			this.uName = uName;
	}

	public void setpWord(String pWord) {
		this.pWord = pWord;
	}

	public boolean logIn(String uName, String pWord) {
		if(this.uName.equals(uName) && this.pWord.equals(pWord))return true;
		return false;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + aType;
		result = prime * result + ((pWord == null) ? 0 : pWord.hashCode());
		result = prime * result + ((uName == null) ? 0 : uName.hashCode());
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
		Account other = (Account) obj;
		if (aType != other.aType)
			return false;
		if (pWord == null) {
			if (other.pWord != null)
				return false;
		} else if (!pWord.equals(other.pWord))
			return false;
		if (uName == null) {
			if (other.uName != null)
				return false;
		} else if (!uName.equals(other.uName))
			return false;
		return true;
	}
	public String saveToFile() {
		return uName+";"+pWord;
	}

}
