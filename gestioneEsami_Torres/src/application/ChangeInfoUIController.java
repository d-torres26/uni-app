package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class ChangeInfoUIController {
	private Person pLogged;
	private ArrayList<Person> peeps;
	private ArrayList<Course> courses;
	private PAreaUI_stuController c;
	private PAreaUI_teachController c1;

    @FXML
    private TextField fName;

    @FXML
    private TextField lName;

    @FXML
    private TextField uName;

    @FXML
    private TextField pWord;

    @FXML
    void doChange(ActionEvent event) throws Exception{
    	int pos=peeps.indexOf(pLogged);
    	String fN=fName.getText(), lN=lName.getText(), uN=uName.getText(), pW=pWord.getText(), oldName=pLogged.getFullName();
    	if(!fN.isEmpty()) pLogged.setfName(fN);
    	if(!lN.isEmpty()) pLogged.setlName(lN);
    	if(!uN.isEmpty()) pLogged.setuName(uN);
    	if(!pW.isEmpty()) pLogged.setpWord(pW);
    	peeps.set(pos, pLogged);
    	if(!(fN.isEmpty()&&lN.isEmpty()&&uN.isEmpty()&&pW.isEmpty())) {
    		saveToLog(pLogged.getFullName()+" ha modificato delle informazioni personali");
    		PrintWriter wriS=new PrintWriter(new FileWriter("data/students.csv"));
    		PrintWriter wriT=new PrintWriter(new FileWriter("data/teachers.csv"));
        	for(int i=0;i<peeps.size();i++) {
    	    	if(peeps.get(i) instanceof Student) wriS.println(((Student)peeps.get(i)).saveToFile());
    	    	else wriT.println(peeps.get(i).saveToFile());
    		}
        	wriS.close();
        	wriT.close();
        	for(int i=0;i<courses.size();i++) {
        		courses.get(i).changeStuName(oldName, pLogged.getFullName());
        		courses.get(i).changeProfName(oldName, pLogged.getFullName());
        	}
        	PrintWriter wri=new PrintWriter(new FileWriter("data/courses.csv"));
			for(int j=0;j<courses.size();j++) {
    	    	wri.println(courses.get(j).saveToFile());
    		}
	    	wri.close();
    	}
    	pWord.getScene().getWindow().hide();
    	try {
    		c.load();
    	}catch(Exception e) {
        	c1.load();
    	}

    }
    public void load(Person pLogged, ArrayList<Person> peeps,ArrayList<Course> courses, PAreaUI_stuController c) {
    	this.pLogged=pLogged;
    	this.peeps=peeps;
    	this.courses=courses;
    	this.c=c;
    }
    public void load(Person pLogged, ArrayList<Person> peeps,ArrayList<Course> courses, PAreaUI_teachController c) {
    	this.pLogged=pLogged;
    	this.peeps=peeps;
    	this.courses=courses;
    	this.c1=c;
    }
    private void saveToLog(String txt) throws Exception{
    	BufferedReader br=new BufferedReader(new FileReader("data/log.csv"));
    	String s=br.readLine(),s1="";
    	if(s!=null)s1=s+"\n";
    	while(s!=null) {
    		s=br.readLine();
    		if(s!=null)s1+=s+"\n";
    	}
    	s1+=txt;
    	PrintWriter wri=new PrintWriter(new FileWriter("data/log.csv"));
    	wri.println(s1);
    	wri.close();
    	br.close();
    }

}
