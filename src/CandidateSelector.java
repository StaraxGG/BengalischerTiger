import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * An implementation of CandidateSelector
 * in BengalischeTiger
 *
 * @author Nicolas
 * @version 1.0
 * @since 2018-Sep-01
 */
public class CandidateSelector {

    /* ---------------------------------------- Main ---------------------------------------------------------------- */

    public static void main(String[] args){

    }


    /* ---------------------------------------- Attributes ---------------------------------------------------------- */

    Map<String,Candidate> keys = new Hashtable<>();


    /* ---------------------------------------- Constants ----------------------------------------------------------- */



    /* ---------------------------------------- Constructors -------------------------------------------------------- */

    public CandidateSelector(List<? extends Candidate> list) {
        keys = list.stream().collect(Collectors.toMap(Candidate::getKey, Function.identity()));
    }

    /* ---------------------------------------- Methods ------------------------------------------------------------- */

    //TODO get List of Candidates with Score higher than and the subject xy

    /**
     * Returns a list containing all students taking a specified subject and a score at least as high as the given one.
     * @param score
     * @param subject
     * @return  List containing the results
     */

    public List<Candidate> filterStudents(double score, String subject){
        return keys.values().stream().filter(c -> c.getSubject().equals(subject) && c.getScore() >= score)
                                                .collect(Collectors.toList());
    }

    /**
     * Returns all students attenting to go to a chosen university sorted by their distance to it.
     * @param university
     * @return  List containing results
     */
    public List<Candidate> studentsSortedByDistance(String university){
        return keys.values().stream().filter(c -> c.getUniversityDistances().containsKey(university))
                                                .sorted(((o1, o2) -> o1.getUniversityDistances().get(university) >
                                                        o2.getUniversityDistances().get(university) ? 1 : -1)
                                                ).collect(Collectors.toList());
    }

    /**
     * Returns all Students with a speicified subject sorted by their scores.
     * @param subject
     * @return
     */
    public List<Candidate> studentsSortedByScore(String subject){
        return keys.values().stream().filter(c -> c.getSubject().equals(subject))
                                                            .sorted((c1,c2) -> c1.getScore() > c2.getScore() ? 1 : -1)
                                                            .collect(Collectors.toList());
    }


    //TODO how to sort ALL Students but only compare those with the same subject





    /* ---------------------------------------- S/Getters ----------------------------------------------------------- */

}
