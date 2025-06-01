package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class AddCourseUIController {
	private Person pLogged;
	private ArrayList<Course> courses;
	private PAreaUI_teachController c;

    @FXML
    private TextField desc;

    @FXML
    private TextField max;

    @FXML
    private ChoiceBox<String> sem;

    @FXML
    void doAddCourse(ActionEvent event) throws Exception{
    	if(!(desc.getText().isEmpty()&&max.getText().isEmpty()&&sem.getValue()==null)) {
    		boolean isSem=false;
        	if(sem.getValue().equals("Semestrale")) isSem=true;
        	if(addCourse(new Course(desc.getText(), pLogged.getFullName(), Integer.parseInt(max.getText()), isSem))) {
        		BufferedReader br=new BufferedReader(new FileReader("data/courses.csv"));
            	String s=br.readLine(),s1="";
            	if(s!=null)s1=s+"\n";
            	while(s!=null) {
            		s=br.readLine();
            		if(s!=null)s1+=s+"\n";
            	}
            	s1+=courses.get(courses.size()-1).saveToFile();
            	PrintWriter wri=new PrintWriter(new FileWriter("data/courses.csv"));
            	wri.println(s1);
            	wri.close();
            	br.close();
            	c.load();
            	saveToLog(pLogged.getFullName()+" ha creato il corso di:"+courses.get(courses.size()-1).getDesc());
            	sem.getScene().getWindow().hide();
        	}
    	}else ;

    }
    public void load(Person pLogged, ArrayList<Course> courses, PAreaUI_teachController c) {
    	this.pLogged=pLogged;
    	this.courses=courses;
    	this.c=c;
    	sem.getItems().add("Semestrale");
    	sem.getItems().add("Annuale");
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
    private boolean addCourse(Course c) {
    	for(int i=0;i<courses.size();i++) {
    		if(courses.get(i).getDesc().equalsIgnoreCase(c.getDesc()))return false;
    	}
    	courses.add(c);
    	return true;
    }

}
