package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class ShowLogUIController {

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
    public void load() throws Exception{
    	BufferedReader br=new BufferedReader(new FileReader("data/log.csv"));
    	String s=br.readLine(),s1="";
    	if(s!=null)s1=s+"\n";
    	while(s!=null) {
    		s=br.readLine();
    		if(s!=null)s1+=s+"\n";
    	}
    	br.close();
    	output.setText(s1);
    	output.setEditable(false);
    }

}
