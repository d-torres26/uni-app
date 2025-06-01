package application;

import java.util.ArrayList;

public class Student extends Person{
	private String sNum;
	private ArrayList<Exam> exams;
	
	public Student(String fName, String lName, String uName, String pWord, String sNum) {
		super(fName, lName, uName, pWord);
		this.sNum=sNum;
		exams=new ArrayList<Exam>();
	}

	public String getsNum() {
		return sNum;
	}
	public double getMedia() {
		int sum=0;
		for(int i=0;i<exams.size();i++) {
			sum+=exams.get(i).getVotoF();
		}
		if(exams.size()!=0)return sum/exams.size();
		return 0;
	}
	public int getNumExams() {
		return exams.size();
	}
	public String getAllExams() {
		String s="";
		for(int i=0;i<exams.size();i++) {
			s+=exams.get(i).toString()+"\n";
		}
		return s;
	}
	public ArrayList<Exam> getExams(){
		return exams;
	}
	public String saveToFile(String pWord) {
		String s=fName+";"+lName+";"+acc.getuName()+";"+pWord+";"+sNum+";";
		for(int i=0;i<exams.size();i++) {
			s+=exams.get(i).saveToFile();
		}
		return s;
	}
	@Override
	public String saveToFile() {
		String s=fName+";"+lName+";"+acc.saveToFile()+";"+sNum+";";
		for(int i=0;i<exams.size();i++) {
			s+=exams.get(i).saveToFile();
		}
		return s;
	}
	public void addExam(Exam e) {
		exams.add(e);
	}

}
