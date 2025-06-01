package application;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;

public class SearchCUIController {
	private ArrayList<Course> courses;

    @FXML
    private ChoiceBox<String> courseList;

    @FXML
    private TextArea output;

    @FXML
    void doShowCourse(ActionEvent event) {
    	String s=courseList.getValue();
    	String txt="Scegliere il corso";
    	if(!(s==null||s.isEmpty())){
    		Course c=searchCourse(s);
    		txt="Docente:"+c.getProf()+"\nLista degli studenti:\n"+c.getStuList();
    	}
    	output.setText(txt);

    }
    public void load(ArrayList<Course> courses) {
    	output.setEditable(false);
    	this.courses=courses;
    	for(int i=0;i<courses.size();i++) {
    		courseList.getItems().add(courses.get(i).getDesc());
    	}
    }
    private Course searchCourse(String desc) {
    	for(int i=0;i<courses.size();i++) {
    		Course c=courses.get(i);
    		if(c.getDesc().equals(desc))return c;
    	}
    	return null;
    }

}
