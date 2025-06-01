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

public class GiveGradesUIController {
	private Person pLogged;
	private ArrayList<Person> peeps;

    @FXML
    private TextField desc;

    @FXML
    private TextField grade;

    @FXML
    private ChoiceBox<String> stu;

    @FXML
    private ChoiceBox<String> courseList;

    @FXML
    void doAddExam(ActionEvent event) throws Exception{
    	if(!(desc.getText().isEmpty()&&desc.getText().isEmpty()&&stu.getValue().isEmpty()&&courseList.getValue()==null)) {
    		for(int i=0;i<peeps.size();i++) {
    			if(peeps.get(i).getFullName().equalsIgnoreCase(stu.getValue())) {
    				((Student)peeps.get(i)).addExam(new Exam(desc.getText(), courseList.getValue(), Integer.parseInt(grade.getText()), pLogged.getFullName()));
    		    	PrintWriter wriS=new PrintWriter(new FileWriter("data/students.csv"));
    				PrintWriter wriT=new PrintWriter(new FileWriter("data/teachers.csv"));
    		    	for(int j=0;j<peeps.size();j++) {
    			    	if(peeps.get(j) instanceof Student) wriS.println(((Student)peeps.get(j)).saveToFile());
    			    	else wriT.println(peeps.get(j).saveToFile());
    				}
    		    	wriS.close();
    		    	wriT.close();
    		    	saveToLog(pLogged.getFullName()+" ha creato un esame e messo il voto");
    		    	desc.getScene().getWindow().hide();
    			}
    		}
    	}

    }
    public void load(Person pLogged, ArrayList<Person> peeps, ArrayList<Course> courses) {
    	this.pLogged=pLogged;
    	this.peeps=peeps;
    	courseList.getItems().clear();
    	stu.getItems().clear();
    	for(int i=0;i<courses.size();i++) {
    		if(courses.get(i).getProf().equalsIgnoreCase(pLogged.getFullName())) {
    			courseList.getItems().add(courses.get(i).getDesc());
    		}
    	}
    	for(int i=0;i<courses.size();i++) {
    		ArrayList<String> stus=courses.get(i).getStus();
    		for(int j=0;j<stus.size();j++) {
    			for(int k=0;k<peeps.size();k++) {
    				if(peeps.get(k) instanceof Student) {
    					if(stus.get(j).equals(peeps.get(k).getFullName())) {
    						stu.getItems().add(stus.get(j));
    					}
    				}
    			}
    		}
    	}//fare in base al corso
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
