package application;

public class Exam {
	private String desc, course, prof;
	private int voto;
	private boolean passed;
	
	public Exam(String desc,String course, String prof) {
		this.desc=desc;
		this.course=course;
		this.prof=prof;
		passed=false;
	}
	public Exam(String desc,String course, int voto, String prof) {
		this.desc=desc;
		this.course=course;
		this.voto=voto;
		this.prof=prof;
		if(voto>=18)passed=true;
		else passed=false;
	}
	public int getVotoF() {
		return voto;
	}
	public void setVotoF(int voto) {
		this.voto = voto;
	}
	public String getDesc() {
		return desc;
	}
	public boolean isPassed() {
		return passed;
	}
	public String getProf() {
		return prof;
	}
	public String getCourse() {
		return course;
	}
	public String toString() {
		return "Esame:"+desc+", voto:"+voto+", corso:"+course+", docente:"+prof;
	}
	public String saveToFile() {
		return desc+";"+course+";"+voto+";"+prof+";";
	}

}
