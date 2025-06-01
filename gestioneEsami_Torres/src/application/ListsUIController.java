package application;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class ListsUIController {
	private ArrayList<Person> peeps;
	private ArrayList<Course> courses;

    @FXML
    private ChoiceBox<String> lists;

    @FXML
    private TextArea output;

    @FXML
    void doSaveAs(ActionEvent event) throws Exception{
    	FileChooser fc= new FileChooser();
    	fc.getExtensionFilters().add(new ExtensionFilter("All types","*.*"));
    	fc.getExtensionFilters().add(new ExtensionFilter("Text file","*.txt"));
    	fc.getExtensionFilters().add(new ExtensionFilter("Text file","*.csv"));
    	File f=fc.showSaveDialog(null);
    	if(f!=null) {
    		FileWriter fw=new FileWriter(f);
    		PrintWriter wri=new PrintWriter(fw);
    		wri.println(output.getText());
    		wri.close();
    	}

    }

    @FXML
    void doShowList(ActionEvent event) {
    	String s="";
    	switch(lists.getValue().charAt(0)) {
    		case 'C':
    			s="Corsi:\n"+getCourses();
    			break;
    		case 'S':
    			s="Studenti:\n"+getStus();
    			break;
    		case 'I':
    			s="Insegnanti:\n"+getTeachs();
    			break;
    	}
    	output.setText(s);

    }
    public void load(ArrayList<Person> peeps, ArrayList<Course> courses) {
    	this.peeps=peeps;
    	this.courses=courses;
    	output.setEditable(false);
    	lists.getItems().add("Corsi");
    	lists.getItems().add("Studenti");
    	lists.getItems().add("Insegnanti");
    }
    private String getCourses(){
    	String s="";
    	for(int i=0;i<courses.size();i++) {
    		s+=courses.get(i).getDesc()+"\n";
    	}
    	return s;
    }
    private String getStus(){
    	String s="";
    	for(int i=0;i<peeps.size();i++) {
    		Person p=peeps.get(i);
    		if(p instanceof Student) {
    			s+=p.getFullName()+"\n";
    		}
    	}
    	return s;
    }
    private String getTeachs(){
    	String s="";
    	for(int i=0;i<peeps.size();i++) {
    		Person p=peeps.get(i);
    		if(p instanceof Teacher) {
    			s+=p.getFullName()+"\n";
    		}
    	}
    	return s;
    }

}
