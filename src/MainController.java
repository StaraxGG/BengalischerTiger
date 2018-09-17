import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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

        listView.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> listChange(new ActionEvent()));
    }


    /**
     * Used to display detailed information of one selected candidate in the listView.
     * @param event
     */
    public void listChange(ActionEvent event){

        //Get selected item as lastname String
        ObservableList<String> mylist = listView.getSelectionModel().getSelectedItems();
        String lastname = mylist.get(0);

        //Predicate to filter items by lastname
        Predicate<Candidate> test = c -> c.getLastName().equals(lastname);
        Candidate theChosenOne = null;

        //Find candidate with lastname and fill labels with detailed information
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

    /**
     * Used to filter list by input in the TextFiled
     * @param event
     */
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
