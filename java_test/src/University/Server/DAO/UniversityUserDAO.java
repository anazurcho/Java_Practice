package University.Server.DAO;

import University.Server.Model.UniversityUser;

import java.util.List;
import java.util.Map;

public interface UniversityUserDAO {

    void addUser(UniversityUser universityUser) throws Exception;

    void deleteById(long id) throws Exception;

    long getScoreForStudent(long id)  throws Exception;

    void changeScoreForStudent(long id, long score)  throws Exception;

    List<UniversityUser> getStudents() throws Exception;

    Map<Long, String> getLecturers() throws Exception;

    boolean checkIfUserExists(String username, String password) throws Exception;

    Map<Long, Boolean> getIdStatus(String username, String password) throws Exception;

    void closeConnection() throws Exception;

}