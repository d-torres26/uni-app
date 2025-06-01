package application;

import java.util.ArrayList;

public class Course {
	private String desc;
	private String prof;
	private ArrayList<String> stus;
	private int maxCap;
	private boolean sem;
	
	public Course(String desc, String prof, int maxCap, boolean sem) {
		this.desc=desc;
		this.prof=prof;
		stus=new ArrayList<String>();
		this.maxCap=maxCap;
		this.sem=sem;
	}

	public String getDesc() {
		return desc;
	}
	public String getProf() {
		return prof;
	}
	public String getSem() {
		if(sem)return "Semestrale";
		return "Annuale";
	}
	public boolean isSem() {
		return sem;
	}
	public ArrayList<String> getStus(){
		return stus;
	}
	public String getStuList() {
		String s="";
		for(int i=0;i<stus.size();i++) {
			s+=stus.get(i)+"\n";
		}
		return s;
	}
	public boolean addStu(Student stu) {
		if(stus.size()<=maxCap) {
			stus.add(stu.lName+" "+stu.fName);
			return true;
		}
		return false;
	}
	public boolean addStu(String stuName) {
		if(stus.size()<=maxCap) {
			stus.add(stuName);
			return true;
		}
		return false;
	}
	public boolean searchStu(String fullName) {
		for(int i=0;i<stus.size();i++) {
			if(stus.get(i).equalsIgnoreCase(fullName))return true;
		}
		return false;
	}
	public void changeStuName(String oldName, String newName) {
		for(int i=0;i<stus.size();i++) {
			if(stus.get(i).equalsIgnoreCase(oldName))stus.set(i, newName);
		}
	}
	public void changeProfName(String oldName, String newName) {
		if(prof.equals(oldName))prof=newName;
	}
	public String saveToFile() {
		String s=desc+";"+prof+";"+maxCap+";"+sem+";";
		for(int i=0;i<stus.size();i++) {
			s+=stus.get(i)+";";
		}
		return s;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((desc == null) ? 0 : desc.hashCode());
		result = prime * result + maxCap;
		result = prime * result + ((prof == null) ? 0 : prof.hashCode());
		result = prime * result + (sem ? 1231 : 1237);
		result = prime * result + ((stus == null) ? 0 : stus.hashCode());
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
		Course other = (Course) obj;
		if (desc == null) {
			if (other.desc != null)
				return false;
		} else if (!desc.equals(other.desc))
			return false;
		if (maxCap != other.maxCap)
			return false;
		if (prof == null) {
			if (other.prof != null)
				return false;
		} else if (!prof.equals(other.prof))
			return false;
		if (sem != other.sem)
			return false;
		if (stus == null) {
			if (other.stus != null)
				return false;
		} else if (!stus.equals(other.stus))
			return false;
		return true;
	}

}
