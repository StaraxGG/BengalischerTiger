import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * An implementation of MainController
 * in BengalischerTiger
 *
 * @author Nicolas
 * @version 1.0
 * @since 2018-Sep-11
 */
public class MainController implements Initializable{

    /* ---------------------------------------- Main ---------------------------------------------------------------- */



    /* ---------------------------------------- Attributes ---------------------------------------------------------- */

    @FXML
    private ListView<String> listView;

    @FXML
    private Button actionButton;

    @FXML
    private Label lblFirstName;

    @FXML
    private Label lblLastName;

    @FXML
    private Label lblScore;

    @FXML
    private Label lblFirstUni;

    @FXML
    private Label lblSecondUni;

    @FXML
    private TextField myField;

    private ArrayList<Candidate> candidates;

    private ObservableList<String> obList;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //initialize values
        CandidateScanner sc = new CandidateScanner();
        String[] arr = {"sortList.txt"};
        sc.start(arr);
        candidates = new ArrayList<>(sc.getCandidates());

        //fill listView
        List<String> list = candidates.stream().map(Candidate::getLastName).collect(Collectors.toList());
        obList = FXCollections.observableList(list);
        listView.setItems(obList);
    }

    public void listChange(ActionEvent event){
        ObservableList<String> mylist = listView.getSelectionModel().getSelectedItems();
        String lastname = mylist.get(0);
        Predicate<Candidate> test = c -> c.getLastName().equals(lastname);
        Candidate theChosenOne = null;

        if(candidates.stream().anyMatch(test)){
            theChosenOne = candidates.stream().filter(test).findFirst().get();
            lblFirstName.setText(theChosenOne.getLastName());
            lblLastName.setText(theChosenOne.getFirstName());
            lblScore.setText(Double.toString(theChosenOne.getScore()));
            ArrayList<String> entrys = new ArrayList<>(theChosenOne.getUniversityDistances().keySet());
            lblFirstUni.setText(entrys.get(0));
            lblSecondUni.setText(entrys.get(1));
        }
    }

    public void fieldChanged(ActionEvent event){
        String text = myField.getText();
        if(!(text.equals(""))){
            ObservableList<String> newList = obList.stream()
                    .filter(c -> c.contains(text))
                    .collect(Collectors.toCollection(FXCollections::observableArrayList));
            listView.setItems(newList);
        }
        else{
            listView.setItems(obList);
        }
    }



    /* ---------------------------------------- Constants ----------------------------------------------------------- */



    /* ---------------------------------------- Constructors -------------------------------------------------------- */



    /* ---------------------------------------- Methods ------------------------------------------------------------- */



    /* ---------------------------------------- S/Getters ----------------------------------------------------------- */

}
