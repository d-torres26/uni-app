package application;

import java.io.BufferedReader;
import java.io.File;
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
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

public class SampleController {
	private boolean mBar=false, showInfo=false, showLoadMenu=false;
	private Person pLogged=new Person();
	private ArrayList<Person> peeps=new ArrayList<Person>();
	private ArrayList<Course> courses=new ArrayList<Course>();

    @FXML
    private Button searchP_btn;

    @FXML
    private Button searchC_btn;

    @FXML
    private Button showLists_btn;

    @FXML
    private AnchorPane menuBar;

    @FXML
    private AnchorPane basic_btns;

    @FXML
    private AnchorPane loadDataMenu_bsc;

    @FXML
    private AnchorPane loadDataMenu_lgd;

    @FXML
    private AnchorPane logged_btns;

    @FXML
    private AnchorPane uInfo;

    @FXML
    private Label uName_dspl;

    @FXML
    private Button logged_btn;

    @FXML
    private Button basic_btn;

    @FXML
    private Label title;

    @FXML
    void doClose(ActionEvent event) {
    	System.exit(0);

    }

    @FXML
    void doLoadData(ActionEvent event) {
    	showLoadMenu=!showLoadMenu;
    	if(basic_btns.isVisible()) loadDataMenu_bsc.setVisible(showLoadMenu);
    	else loadDataMenu_lgd.setVisible(showLoadMenu);

    }

    @FXML
    void doLoadStus(ActionEvent event) throws Exception{
    	FileChooser fc= new FileChooser();
    	fc.getExtensionFilters().add(new ExtensionFilter("All types","*.*"));
    	fc.getExtensionFilters().add(new ExtensionFilter("Text file","*.txt"));
    	fc.getExtensionFilters().add(new ExtensionFilter("Text file","*.csv"));
    	File f=fc.showOpenDialog(null);
    	if(f!=null) {
    		loadStus(f.getAbsolutePath());
    	}

    }

    @FXML
    void doLoadTeachs(ActionEvent event) throws Exception{
    	FileChooser fc= new FileChooser();
    	fc.getExtensionFilters().add(new ExtensionFilter("All types","*.*"));
    	fc.getExtensionFilters().add(new ExtensionFilter("Text file","*.txt"));
    	fc.getExtensionFilters().add(new ExtensionFilter("Text file","*.csv"));
    	File f=fc.showOpenDialog(null);
    	if(f!=null) {
    		loadTeachs(f.getAbsolutePath());
    	}

    }

    @FXML
    void doLoadCourses(ActionEvent event) throws Exception{
    	FileChooser fc= new FileChooser();
    	fc.getExtensionFilters().add(new ExtensionFilter("All types","*.*"));
    	fc.getExtensionFilters().add(new ExtensionFilter("Text file","*.txt"));
    	fc.getExtensionFilters().add(new ExtensionFilter("Text file","*.csv"));
    	File f=fc.showOpenDialog(null);
    	if(f!=null) {
    		loadCourses(f.getAbsolutePath());
    	}

    }

    @FXML
    void doLogIn(ActionEvent event) throws Exception{
    	FXMLLoader loader=new FXMLLoader(getClass().getResource("LoginUI.fxml"));
    	Parent LoginUI=(Parent)loader.load();
    	Scene nuovaScena=new Scene(LoginUI);
    	LoginUIController controller=loader.getController();
    	Stage palco=new Stage();
    	palco.setTitle("Finestra Log In/Sign In");
    	palco.setScene(nuovaScena);
    	palco.show();
    	palco.setMaxWidth(820);
    	palco.setMaxHeight(650);
    	palco.centerOnScreen();
    	palco.setResizable(false);
    	controller.load(peeps,  this);

    }

    @FXML
    void doLogout(ActionEvent event) {
    	logout();

    }

    @FXML
    void doSearchC(ActionEvent event) throws Exception{
    	FXMLLoader loader=new FXMLLoader(getClass().getResource("SearchCUI.fxml"));
    	Parent SearchCUI=(Parent)loader.load();
    	Scene nuovaScena=new Scene(SearchCUI);
    	SearchCUIController controller=loader.getController();
    	Stage palco=new Stage();
    	palco.setTitle("Finestra di ricerca di un corso");
    	palco.setScene(nuovaScena);
    	palco.show();
    	palco.setMaxWidth(820);
    	palco.setMaxHeight(650);
    	palco.centerOnScreen();
    	palco.setResizable(false);
    	controller.load(courses);

    }

    @FXML
    void doSearchP(ActionEvent event) throws Exception{
    	FXMLLoader loader=new FXMLLoader(getClass().getResource("SearchPUI.fxml"));
    	Parent SearchPUI=(Parent)loader.load();
    	Scene nuovaScena=new Scene(SearchPUI);
    	SearchPUIController controller=loader.getController();
    	Stage palco=new Stage();
    	palco.setTitle("Finestra di ricerca di una persona");
    	palco.setScene(nuovaScena);
    	palco.show();
    	palco.setMaxWidth(820);
    	palco.setMaxHeight(650);
    	palco.centerOnScreen();
    	palco.setResizable(false);
    	controller.load(peeps, courses);

    }

    @FXML
    void doShowLists(ActionEvent event) throws Exception{
    	FXMLLoader loader=new FXMLLoader(getClass().getResource("ListsUI.fxml"));
    	Parent ListsUI=(Parent)loader.load();
    	Scene nuovaScena=new Scene(ListsUI);
    	ListsUIController controller=loader.getController();
    	Stage palco=new Stage();
    	palco.setTitle("Mostra liste");
    	palco.setScene(nuovaScena);
    	palco.show();
    	palco.setMaxWidth(820);
    	palco.setMaxHeight(650);
    	palco.centerOnScreen();
    	palco.setResizable(false);
    	controller.load(peeps, courses);
    	//fare nuovo fxml
    	//scrivere sul file di log che sono state mostrate le liste e quali

    }

    @FXML
    void doShowLogFile(ActionEvent event) throws Exception{
    	FXMLLoader loader=new FXMLLoader(getClass().getResource("ShowLogUI.fxml"));
    	Parent ShowLogUI=(Parent)loader.load();
    	Scene nuovaScena=new Scene(ShowLogUI);
    	ShowLogUIController controller=loader.getController();
    	Stage palco=new Stage();
    	palco.setTitle("Finestra di visualizzazione del log");
    	palco.setScene(nuovaScena);
    	palco.show();
    	palco.setMaxWidth(820);
    	palco.setMaxHeight(650);
    	palco.centerOnScreen();
    	palco.setResizable(false);
    	controller.load();

    }

    @FXML
    void doShowMenu(ActionEvent event) {
    	mBar=!mBar;
    	if(mBar) menuBar.setTranslateX(-260);
    	else menuBar.setTranslateX(0);

    }

    @FXML
    void doShowPArea(ActionEvent event) throws Exception{
    	if(pLogged instanceof Student) {
    		FXMLLoader loader=new FXMLLoader(getClass().getResource("PAreaUI_stu.fxml"));
        	Parent PAreaUI_stu=(Parent)loader.load();
        	Scene nuovaScena=new Scene(PAreaUI_stu);
        	PAreaUI_stuController controller=loader.getController();
        	Stage palco=new Stage();
        	palco.setTitle("Area Personale");
        	palco.setScene(nuovaScena);
        	palco.show();
        	palco.setMaxWidth(820);
        	palco.setMaxHeight(650);
        	palco.centerOnScreen();
        	palco.setResizable(false);
        	controller.load(pLogged, courses, peeps, this);
    	}else {
    		FXMLLoader loader=new FXMLLoader(getClass().getResource("PAreaUI_teach.fxml"));
        	Parent PAreaUI_tach=(Parent)loader.load();
        	Scene nuovaScena=new Scene(PAreaUI_tach);
        	PAreaUI_teachController controller=loader.getController();
        	Stage palco=new Stage();
        	palco.setTitle("Area Personale");
        	palco.setScene(nuovaScena);
        	palco.show();
        	palco.setMaxWidth(820);
        	palco.setMaxHeight(650);
        	palco.centerOnScreen();
        	palco.setResizable(false);
        	controller.load(pLogged, courses, peeps, this);
    	}

    }

    @FXML
    void doShowUInfo(ActionEvent event) {
    	showInfo=!showInfo;
    	if(showInfo)uInfo.setVisible(true);
    	else uInfo.setVisible(false);
    	uName_dspl.setText(pLogged.getFullName());

    }

    public void load() {
    	mBar=false;
    	menuBar.setTranslateX(0);
    	loadDataMenu_bsc.setVisible(false);
    	loadDataMenu_lgd.setVisible(false);

    }
    public void loadData() throws Exception{
    	loadStus("data/students.csv");
    	loadTeachs("data/teachers.csv");
    	loadCourses("data/courses.csv");
    	/*br=new BufferedReader(new FileReader("data/log.csv"));
    	s=br.readLine();
    	while(s!=null) {
    		log+=s;
    		s=br.readLine();
    	}
    	br.close();*///mettere nel fxml del log
    }
    public void loadData(Person pLogged) {
    	this.pLogged=pLogged;
    }
    /**///mettere negli fxml
    public void update() {
    	load();
    	if(pLogged.getfName()!=null) {
    		basic_btn.setVisible(false);
        	logged_btn.setVisible(true);
    		basic_btns.setVisible(false);
        	logged_btns.setVisible(true);
        	if(pLogged.acc.getaType()=='s') {
        	}else if(pLogged.acc.getaType()=='t') {
        		
        	}
    	}else {
    		uInfo.setVisible(false);
        	logged_btn.setVisible(false);
    		basic_btn.setVisible(true);
    		logged_btns.setVisible(false);
    		basic_btns.setVisible(true);
    	}
    	//System.out.print("boh " +c);
    }
    private boolean addPerson(Person p) throws Exception{
    	for(int i=0;i<peeps.size();i++) {
    		Person p1=peeps.get(i);
    		if(p1.equals(p))return false;
    	}
    	peeps.add(p);
    	return true;
    }
    private void addCourse(Course c) {
    	for(int i=0;i<courses.size();i++) {
    		Course c1=courses.get(i);
    		if(c1.equals(c))return;
    	}
    	courses.add(c);
    }
    private void loadStus(String file) throws Exception{
    	boolean t=false;
    	BufferedReader br=new BufferedReader(new FileReader(file));
    	String s=br.readLine();
    	while(s!=null && !s.isEmpty()) {
    		String vS[]=s.split(";");
    		Student st=new Student(vS[0],vS[1],vS[2],vS[3],vS[4]);
    		for(int i=5;i<vS.length;i+=4) {
    			st.addExam(new Exam(vS[5], vS[6], Integer.parseInt(vS[7]),vS[8]));
    		}
    		t=addPerson(st);
    		s=br.readLine();
    	}
    	br.close();
    	String s1="";
    	if(t) {
			for(int i=0;i<peeps.size();i++) {
				if(peeps.get(i) instanceof Student) {
					s1+=peeps.get(i).saveToFile()+"\n";
				}
			}
		}
    	saveToFile(s1,"data/students.csv");
    }
    private void loadTeachs(String file) throws Exception{
    	boolean t=false;
    	BufferedReader br=new BufferedReader(new FileReader(file));
    	String s=br.readLine();
    	while(s!=null && !s.isEmpty()) {
    		String vS[]=s.split(";");
    		t=addPerson(new Teacher(vS[0],vS[1],vS[2],vS[3]));
    		s=br.readLine();
    	}
    	br.close();
    	String s1="";
    	if(t) {
			for(int i=0;i<peeps.size();i++) {
				if(peeps.get(i) instanceof Teacher) {
					s1+=peeps.get(i).saveToFile()+"\n";
				}
			}
		}
		saveToFile(s1,"data/teachers.csv");
    }
    private void loadCourses(String file) throws Exception{
    	BufferedReader br=new BufferedReader(new FileReader(file));
    	String s=br.readLine();
    	while(s!=null && !s.isEmpty()) {
    		String vS[]=s.split(";");
    		Course c=new Course(vS[0],vS[1],Integer.parseInt(vS[2]),vS[3].equalsIgnoreCase("true"));
    		for(int i=4;i<vS.length;i++) {
    			c.addStu(vS[i]);
    		}
    		addCourse(c);
    		s=br.readLine();
    	}
    	br.close();
    }
    private void saveToFile(String txt, String file) throws Exception{
    	PrintWriter wri=new PrintWriter(new FileWriter(file));
    	wri.println(txt);
    	wri.close();
    }
    void logout(){
    	pLogged=new Person();
    	update();
    }

}
