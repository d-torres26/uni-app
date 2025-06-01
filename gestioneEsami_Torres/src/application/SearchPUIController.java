package application;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class SearchPUIController {
	private ArrayList<Person> peeps;
	private ArrayList<Course> courses;
	private String s, txt;
	private Person p;
	private Student st;

    @FXML
    private AnchorPane searchBar;

    @FXML
    private TextField input;

    @FXML
    private Button search_btn;

    @FXML
    private AnchorPane stu;

    @FXML
    private AnchorPane mat;

    @FXML
    private TextArea output;

    @FXML
    private TableView<Exam> tabEsami;

    @FXML
    private TableColumn<Exam, String> desc;

    @FXML
    private TableColumn<Exam, String> corso;

    @FXML
    private TableColumn<Exam, String> prof;

    @FXML
    private TableColumn<Exam, String> voto;

    @FXML
    private TableView<Course> tabCorsi;

    @FXML
    private TableColumn<Course, String> descCorso;

    @FXML
    private TableColumn<Course, String> sem;

    @FXML
    void doSearch(ActionEvent event) {
    	s=input.getText();
    	load();
    	if(!(s.isEmpty()||s==null)) {
    		try {
    			Long.parseLong(s);
    	    	mat.setVisible(true);
            	st=pPresByMat(s);
    		}catch(Exception e) {
    			p=pPres(s);
    			if(p!=null) {
    				if(p instanceof Student) {
    	    	    	stu.setVisible(true);
    				}else {
    					tabCorsi.setVisible(true);
    					descCorso.setCellValueFactory(new PropertyValueFactory<Course, String>("desc"));
    					sem.setCellValueFactory(new PropertyValueFactory<Course, String>("sem"));
    					tabCorsi.setItems(FXCollections.observableList(getCourses(s)));
    				}
    			}else {
    				output.setText("Persona insesistente");
    				//mettere errore che la persona non esiste
    			}
    		}
    	}else{
    		output.setText("Inserire il nome di una persona o la matricola di uno studente");
    		//mettere errore che manca il nome
    	}

    }

    @FXML
    void doShowCourses(ActionEvent event) {
    	tabEsami.setVisible(false);
    	tabCorsi.setVisible(false);
    	txt="Lo studente è iscritto ai seguenti corsi:\n";
    	for(int i=0;i<courses.size();i++) {
    		Course c=courses.get(i);
    		if(c.searchStu(s))txt+=c.getDesc()+"\n";
    	}
    	output.setText(txt);

    }

    @FXML
    void doShowExams(ActionEvent event) {
    	tabCorsi.setVisible(false);
    	st=(Student)p;
    	tabEsami.setVisible(true);
    	desc.setCellValueFactory(new PropertyValueFactory<Exam, String>("desc"));
    	corso.setCellValueFactory(new PropertyValueFactory<Exam, String>("course"));
    	prof.setCellValueFactory(new PropertyValueFactory<Exam, String>("prof"));
    	voto.setCellValueFactory(new PropertyValueFactory<Exam, String>("votoF"));
    	tabEsami.setItems(FXCollections.observableList(st.getExams()));

    }

    @FXML
    void doShowMedia(ActionEvent event) {
    	tabEsami.setVisible(false);
    	tabCorsi.setVisible(false);
    	txt="La media dello studente è:"+st.getMedia();
    	output.setText(txt);

    }

    @FXML
    void doShowNumOfExams(ActionEvent event) {
    	tabEsami.setVisible(false);
    	tabCorsi.setVisible(false);
    	txt="Il numero di esami sostenuti è:"+st.getNumExams();
    	output.setText(txt);

    }
    public void load(ArrayList<Person> peeps, ArrayList<Course> courses) {
    	this.peeps=peeps;
    	this.courses=courses;
    	load();
    }
    public void load() {
    	stu.setVisible(false);
    	mat.setVisible(false);
    	output.setEditable(false);
    	output.setText("");
    	tabEsami.setVisible(false);
    	tabCorsi.setVisible(false);
    }
    private Person pPres(String fullName) {
    	for(int i=0;i<peeps.size();i++) {
    		if(peeps.get(i).getFullName().equalsIgnoreCase(fullName))return peeps.get(i);
    	}
    	return null;
    }
    private Student pPresByMat(String mat) {
    	for(int i=0;i<peeps.size();i++) {
    		Person p=peeps.get(i);
    		if(p instanceof Student) {
    			if(((Student)p).getsNum().equals(mat))return (Student)p;
    		}
    	}
    	return null;
    }
    private ArrayList<Course> getCourses(String fullName) {
    	ArrayList<Course> cL=new ArrayList<Course>();
    	for(int i=0;i<courses.size();i++) {
    		Course c=courses.get(i);
    		if(c.getProf().equalsIgnoreCase(fullName)) {
    			cL.add(c);
    		}
    	}
    	return cL;
    }

}
