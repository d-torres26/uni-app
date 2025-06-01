package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class PAreaUI_teachController {
	private Person pLogged;
	private ArrayList<Course> courses;
	private ArrayList<Person> peeps;
	private boolean shown;
	private SampleController c;

    @FXML
    private TextArea info;

    @FXML
    private AnchorPane deleteCourse;

    @FXML
    private ChoiceBox<String> courseList;

    @FXML
    void doAddCourse(ActionEvent event) throws Exception{
    	FXMLLoader loader=new FXMLLoader(getClass().getResource("AddCourseUI.fxml"));
    	Parent AddCourseUI=(Parent)loader.load();
    	Scene nuovaScena=new Scene(AddCourseUI);
    	AddCourseUIController controller=loader.getController();
    	Stage palco=new Stage();
    	palco.setTitle("Crea corso");
    	palco.setScene(nuovaScena);
    	palco.show();
    	palco.setMaxWidth(820);
    	palco.setMaxHeight(650);
    	palco.centerOnScreen();
    	palco.setResizable(false);
    	controller.load(pLogged,courses, this);

    }

    @FXML
    void doChangeInfo(ActionEvent event) throws Exception{
    	FXMLLoader loader=new FXMLLoader(getClass().getResource("ChangeInfoUI.fxml"));
    	Parent ChangeInfoUI=(Parent)loader.load();
    	Scene nuovaScena=new Scene(ChangeInfoUI);
    	ChangeInfoUIController controller=loader.getController();
    	Stage palco=new Stage();
    	palco.setTitle("Finestra per modificare i dati");
    	palco.setScene(nuovaScena);
    	palco.show();
    	palco.setMaxWidth(820);
    	palco.setMaxHeight(650);
    	palco.centerOnScreen();
    	palco.setResizable(false);
    	controller.load(pLogged,  peeps, courses, this);

    }

    @FXML
    void doDelete(ActionEvent event) throws Exception{
    	String s=courseList.getValue();
    	for(int i=0;i<courses.size();i++) {
    		if(courses.get(i).getDesc().equalsIgnoreCase(s))courses.remove(i);
    	}
    	PrintWriter wri=new PrintWriter(new FileWriter("data/courses.csv"));
		for(int i=0;i<courses.size();i++) {
	    	wri.println(courses.get(i).saveToFile());
		}
    	wri.close();
    	saveToLog(pLogged.getFullName()+"ha eliminato il corso di:"+s);
    	load();

    }

    @FXML
    void doDeleteAcc(ActionEvent event) throws Exception{
    	saveToLog(pLogged.getFullName()+" ha eliminato l'account");
    	peeps.remove(pLogged);
		PrintWriter wriS=new PrintWriter(new FileWriter("data/students.csv"));
		PrintWriter wriT=new PrintWriter(new FileWriter("data/teachers.csv"));
    	for(int i=0;i<peeps.size();i++) {
	    	if(peeps.get(i) instanceof Student) wriS.println(((Student)peeps.get(i)).saveToFile());
	    	else wriT.println(peeps.get(i).saveToFile());
		}
    	wriS.close();
    	wriT.close();
    	info.getScene().getWindow().hide();
    	c.logout();
    	c.load();

    }

    @FXML
    void doGiveGrades(ActionEvent event) throws Exception{
    	FXMLLoader loader=new FXMLLoader(getClass().getResource("GiveGradesUI.fxml"));
    	Parent GiveGradesUI=(Parent)loader.load();
    	Scene nuovaScena=new Scene(GiveGradesUI);
    	GiveGradesUIController controller=loader.getController();
    	Stage palco=new Stage();
    	palco.setTitle("Creazione esame");
    	palco.setScene(nuovaScena);
    	palco.show();
    	palco.setMaxWidth(820);
    	palco.setMaxHeight(650);
    	palco.centerOnScreen();
    	palco.setResizable(false);
    	controller.load(pLogged,peeps, courses);

    }

    @FXML
    void doShowCourseList(ActionEvent event) {
    	shown=!shown;
    	deleteCourse.setVisible(shown);

    }

    public void load(Person pLogged, ArrayList<Course> courses, ArrayList<Person> peeps, SampleController c) {
    	this.pLogged=pLogged;
    	this.courses=courses;
    	this.peeps=peeps;
    	this.c=c;
    	load();
    }
    public void load() {
    	info.setEditable(false);
    	shown=false;
    	deleteCourse.setVisible(false);
    	String s="";
    	for(int i=0;i<courses.size();i++) {
    		if(courses.get(i).getProf().equalsIgnoreCase(pLogged.getFullName())) {
    			courseList.getItems().add(courses.get(i).getDesc());
    			s+=courses.get(i).getDesc()+"\n";
    		}
    	}
    	info.setText("Nome:"+pLogged.fName+"\nCognome:"+pLogged.lName+"\nUsername:"+pLogged.getAcc().getuName()+"\nCorsi che tiene:\n"+s);
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
