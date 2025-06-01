package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class LoginUIController {
	private ArrayList<Person> peeps=new ArrayList<Person>();
	private boolean moved=false, isStu;
	private int x;
	private SampleController c;

    @FXML
    private AnchorPane UI;

    @FXML
    private AnchorPane login;

    @FXML
    private TextField uNameL;

    @FXML
    private PasswordField pWordL;

    @FXML
    private Label errorL;

    @FXML
    private AnchorPane signin;

    @FXML
    private AnchorPane makeAcc;

    @FXML
    private TextField uNameR;

    @FXML
    private TextField fName;

    @FXML
    private TextField lName;

    @FXML
    private PasswordField pWordR;

    @FXML
    private AnchorPane stu;

    @FXML
    private TextField sNum;

    @FXML
    private Label errorR;

    @FXML
    void doLogIn(ActionEvent event) {
    	String uName=uNameL.getText(), pWord=pWordL.getText(), error="";
    	if(!(uName.isEmpty()||pWord.isEmpty())) {
    		for(int i=0;i<peeps.size();i++) {
        		if(peeps.get(i).acc.getuName().equals(uName)) {
        			if(peeps.get(i).logIn(uName, pWord)) {
        				c.loadData(peeps.get(i));
        		    	UI.getScene().getWindow().hide();
        		    	c.update();
        		    	return;
        			}else{
        				error="Password sbagliata";
        				break;
        			}
        		}
        	}
        	if(error.isEmpty())error="Account inesistente";
    	}else error="Inserire credenziali";
    	errorL.setText(error);
    	errorL.setStyle("-fx-text-fill: red;");
    	//aggiungere sul file di log che qualcuno ha fatto il login

    }

    @FXML
    void doMove(ActionEvent event) {
    	moved=!moved;
    	if(moved) x=-800;
    	else x=0;
    	signin.setTranslateX(x);
    	makeAcc.setTranslateX(x);

    }

    @FXML
    void doSignIn(ActionEvent event) throws Exception{
    	String fN=fName.getText(), lN=lName.getText(), uName=uNameR.getText(), pWord=pWordR.getText();
    	
    	if(isStu) {
    		String mat=sNum.getText();
    		try {
        		Long.parseLong(mat);
        		System.out.print("v");
        		Student s=new Student(fN, lN, uName, pWord, mat);
        		String er=addStudent(s);
        		if(er==null) {
            		saveToFile(s.saveToFile(pWord),"data/students.csv");
            		c.loadData(s);
            		UI.getScene().getWindow().hide();
        	    	c.update();
        		}
        		else {
        			errorR.setText(er);
            		errorR.setStyle("-fx-text-fill:red");
            		return;
        		}
        	}catch(Exception e) {
        		//stampa errore
        		errorR.setText("La matricola deve essere un numero");
        		errorR.setStyle("-fx-text-fill:red");
        		return;
        	}
    	}else {
    		Teacher t=new Teacher(fN, lN, uName, pWord);
    		String er=addTeacher(t);
    		if(er==null) {
        		saveToFile(t.saveToFile(pWord),"data/teachers.csv");
        		c.loadData(t);
        		UI.getScene().getWindow().hide();
    	    	c.update();
    		}
    		else {
    			errorR.setText(er);
        		errorR.setStyle("-fx-text-fill:red");
        		return;
    		}
    	}
    	saveToFile(peeps.get(peeps.size()-1).getFullName()+" si è iscritto(inserimento di una persona)", "data/log.csv");
    	// aggiungi pLogged sul file di log che qualcuno si è iscritto

    }
    @FXML
    void isStudent(ActionEvent event) {
    	isStu=true;
    	makeAcc.setVisible(true);
    	stu.setVisible(true);

    }

    @FXML
    void isTeacher(ActionEvent event) {
    	isStu=false;
    	makeAcc.setVisible(true);

    }

    public void load(ArrayList<Person> peeps,  SampleController c) {
    	this.peeps=peeps;
    	this.c=c;
    	makeAcc.setVisible(false);
    	stu.setVisible(false);
    }
    private void saveToFile(String txt, String file) throws Exception{
    	BufferedReader br=new BufferedReader(new FileReader(file));
    	String s=br.readLine(),s1="";
    	if(s!=null)s1=s+"\n";
    	while(s!=null) {
    		s=br.readLine();
    		if(s!=null)s1+=s+"\n";
    	}
    	s1+=txt;
    	PrintWriter wri=new PrintWriter(new FileWriter(file));
    	wri.println(s1);
    	wri.close();
    	br.close();
    }
    private String addStudent(Student s) {
    	for(int i=0;i<peeps.size();i++) {
    		Person p=peeps.get(i);
    		if(p.acc.getuName().equals(s.acc.getuName()))return "Esiste già qualcuno con questo username";
    		if(p.getFullName().equalsIgnoreCase(s.getFullName()))return "Esiste già un account intestato a questa persona";
    		if(p instanceof Student) {
    			if(((Student) p).getsNum().equals(s.getsNum()))return "Studente già iscritto";
    		}
    	}
    	peeps.add(s);
    	return null;
    }
    private String addTeacher(Teacher t) {
    	for(int i=0;i<peeps.size();i++) {
    		Person p=peeps.get(i);
			if(p.acc.getuName().equals(t.acc.getuName()))return "Esiste già qualcuno con questo username";
			if(p.getFullName().equalsIgnoreCase(t.getFullName()))return "Esiste già un account intestato a questa persona";
    	}
    	peeps.add(t);
    	return null;
    }

}
