import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * An implementation of CandidateScanner
 * in BengalischeTiger
 *
 * It is used to extract the informations of candidates out of an simple text file.
 * It should has the structure:
 *
 *      lastName;firstName;score;subject;firstUniversity;secondUniversity
 *
 * @author Nicolas
 * @version 1.0
 * @since 2018-Sep-01
 */
public class CandidateScanner {

    /* ---------------------------------------- Main ---------------------------------------------------------------- */

    /**
     * Main Method of the Class Candidate Scanner
     * @param args
     */

    public static void main(String[] args){
        CandidateScanner myScanner = new CandidateScanner();
        myScanner.start(args);
        CandidateSelector mySelector = new CandidateSelector(myScanner.getCandidates());
        mySelector.studentsSortedBySubjectThenByScore("Hogwarts").stream().forEach(System.out::println);
    }

    /**
     * The start Method scans the text file (placed in the location given by the user input through the
     * kdo arguments.
     * @param args
     */

    public void start(String[] args){
        try{
            if(args.length != 1){
                throw new WrongInputException("Input structure: kdo[location of input.txt]");
            }
            BufferedReader input =  new BufferedReader(new FileReader(args[0]));
            String line = null;

            while((line = input.readLine()) != null){

                String firstname, lastname, subject, firstUniversity, secondUniversity = null;
                Double score;

                String[] values = line.split(";");

                if(values.length != 8){
                    throw new WrongInputException("Your Text File has a wrong structure.");
                }
                firstname = values[0];
                lastname = values[1];
                score = Double.parseDouble(values[2]);
                subject = values[3];
                firstUniversity = values[4];
                secondUniversity = values[6];

                Candidate newCandidate = new Candidate(firstname,lastname,score,subject);
                newCandidate.getUniversityDistances().put(firstUniversity,Double.parseDouble(values[5]));
                newCandidate.getUniversityDistances().put(secondUniversity,Double.parseDouble(values[7]));

                candidates.add(newCandidate);
            }

            //candidates.stream().forEach(System.out::println);
        }

        catch (IOException e){
            System.err.println(e);
        }
        catch (Exception e) {
            System.err.println(e);
        }
    }

    /* ---------------------------------------- Attributes ---------------------------------------------------------- */

    private LinkedList<Candidate> candidates = new LinkedList<>();


    /* ---------------------------------------- Constants ----------------------------------------------------------- */



    /* ---------------------------------------- Constructors -------------------------------------------------------- */



    /* ---------------------------------------- Methods ------------------------------------------------------------- */



    /* ---------------------------------------- S/Getters ----------------------------------------------------------- */

    public LinkedList<Candidate> getCandidates() {
        return candidates;
    }
}
