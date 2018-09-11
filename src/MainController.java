import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
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
    private ComboBox<String> comboBox;

    @FXML
    private Label myLabel1;

    private ArrayList<Candidate> candidates;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //initialize values
        CandidateScanner sc = new CandidateScanner();
        String[] arr = {"sortList.txt"};
        sc.start(arr);
        candidates = new ArrayList<>(sc.getCandidates());

        //fill combobox
        List<String> list = candidates.stream().map(Candidate::getLastName).collect(Collectors.toList());
        ObservableList<String> obList = FXCollections.observableList(list);
        comboBox.setItems(obList);
    }

    public void comboChange(ActionEvent event){
        String lastname = comboBox.getValue();
        double score = 0;
        Predicate<Candidate> test = c -> c.getLastName().equals(lastname);

        if(candidates.stream().anyMatch(test)){
            score = candidates.stream().filter(test).findFirst().get().getScore();
        }
        myLabel1.setText(Double.toString(score));
    }

    /* ---------------------------------------- Constants ----------------------------------------------------------- */



    /* ---------------------------------------- Constructors -------------------------------------------------------- */



    /* ---------------------------------------- Methods ------------------------------------------------------------- */



    /* ---------------------------------------- S/Getters ----------------------------------------------------------- */

}
