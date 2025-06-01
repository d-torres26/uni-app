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
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class PAreaUI_stuController {
	private Person pLogged;
	private ArrayList<Course> courses;
	private ArrayList<Person> peeps;
	private boolean shown;
	private SampleController c;

    @FXML
    private TextArea info;

    @FXML
    private AnchorPane sub;

    @FXML
    private ChoiceBox<String> courseList;

    @FXML
    private Button sub_btn;

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
    void doShowCourseSub(ActionEvent event) {
    	shown=!shown;
    	sub.setVisible(shown);
    	if(shown)sub_btn.setTranslateY(-148);
    	else sub_btn.setTranslateY(0);

    }

    @FXML
    void doSubscribe(ActionEvent event) throws Exception{
    	String course=courseList.getValue();
    	for(int i=0;i<courses.size();i++) {
    		if(courses.get(i).getDesc().equalsIgnoreCase(course)) {
    			courses.get(i).addStu(pLogged.getFullName());
    			saveToLog(pLogged.getFullName()+" si è iscritto al corso:"+courses.get(i).getDesc()+"(modifica del corso)");
    			PrintWriter wri=new PrintWriter(new FileWriter("data/courses.csv"));
    			for(int j=0;j<courses.size();j++) {
        	    	wri.println(courses.get(j).saveToFile());
        		}
    	    	wri.close();
    			load();
    			return;
    		}
    	}

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
    	sub_btn.setTranslateY(0);
    	sub.setVisible(false);
    	Student temp=(Student)pLogged;
    	String s="";
    	for(int i=0;i<courses.size();i++) {
    		if(courses.get(i).searchStu(pLogged.getFullName()))s+=courses.get(i).getDesc()+"\n";
    		else courseList.getItems().add(courses.get(i).getDesc());
    	}
    	info.setText("Nome:"+temp.fName+"\nCognome:"+temp.lName+"\nUsername:"+temp.getAcc().getuName()+"\nMatricola:"+temp.getsNum()+"\nCorsi:\n"+s);
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
