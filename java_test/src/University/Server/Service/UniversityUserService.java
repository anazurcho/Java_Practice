package University.Server.Service;

import University.Common.UniversityUserDTO;
import University.Server.Model.UniversityUserException;

import java.util.List;
import java.util.Map;


public interface UniversityUserService {

    void addUser(String username, String password1, String password2) throws UniversityUserException;

    boolean authorize(String username, String password) throws Exception;

    Map<Long, Boolean> getIdStatus(String username, String password) throws UniversityUserException;

    void deleteById(long id) throws Exception;

    long getScoreForStudent(long id) throws Exception;

    void changeScoreForStudent(long id, long score) throws Exception;

    List<UniversityUserDTO> getStudents() throws UniversityUserException;

    Map<Long, String> getLecturers() throws UniversityUserException;
}