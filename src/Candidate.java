import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * An implementation of Candidate
 * in BengalischeTiger
 *
 * The class is a simple representation of an candidate for different university's.
 * He has a final score for his high school accomplishments, a fist and a second university
 * he chose to apply for as well as a chosen subject.
 *
 * @author Nicolas
 * @version 1.0
 * @since 2018-Sep-01
 */
public class Candidate {

    /* ---------------------------------------- Main ---------------------------------------------------------------- */



    /* ---------------------------------------- Attributes ---------------------------------------------------------- */

    private String key;
    private String firstName;
    private String lastName;
    private double score;
    private String subject;
    private LinkedHashMap<String,Double> UniversityDistances = new LinkedHashMap<>();

    /* ---------------------------------------- Constants ----------------------------------------------------------- */



    /* ---------------------------------------- Constructors -------------------------------------------------------- */

    public Candidate(String firstName, String lastName, double score, String subject) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.key = lastName+firstName;
        this.score = score;
        this.subject = subject;

    }

    /* ---------------------------------------- Methods ------------------------------------------------------------- */

    @Override
    public String toString() {
        return "Candidate{" +
                "key='" + key + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", score=" + score +
                ", subject='" + subject + '\'' +
                ", Universitys = " + UniversityDistances +
                '}';
    }

    /* ---------------------------------------- S/Getters ----------------------------------------------------------- */

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public double getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public HashMap<String, Double> getUniversityDistances() {
        return UniversityDistances;
    }
}
