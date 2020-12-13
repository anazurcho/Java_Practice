package University.Server.Service;

import University.Common.UniversityUserDTO;
import University.Server.DAO.UniversityUserDAO;
import University.Server.DAO.UniversityUserDAOImpl;
import University.Server.Model.UniversityUser;
import University.Server.Model.UniversityUserException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class UniversityUserServiceImpl implements UniversityUserService {

    private UniversityUserDAO universityUserDAO;

    public UniversityUserServiceImpl() throws Exception {
        universityUserDAO = new UniversityUserDAOImpl();
    }

    @Override
    public void addUser(String username, String password1, String password2) throws UniversityUserException {
        if (password1.equals(password2)) {
            try {
                UniversityUser universityUser = new UniversityUser();
                universityUser.setUsername(username);
                universityUser.setPassword(password1);
                universityUser.setActive(true);
                universityUserDAO.addUser(universityUser);
            } catch (Exception ex) {
                throw new UniversityUserException("Unexpected Error");
            }
        } else {
            throw new UniversityUserException("Passwords is not equal");
        }
    }

    @Override
    public boolean authorize(String username, String password) throws Exception {
        try {
            return universityUserDAO.checkIfUserExists(username, password);
        } catch (Exception exception) {
            return false;
        }
    }

    public long getScoreForStudent(long id) throws Exception {
        try {
            return universityUserDAO.getScoreForStudent(id);
        } catch (Exception exception) {
            return 0;
        }
    }

    public void changeScoreForStudent(long id, long score) throws UniversityUserException {
        try {
            universityUserDAO.changeScoreForStudent(id, score);
        } catch (Exception ex) {
            throw new UniversityUserException("Unexpected Exception");
        }
    }

    public Map<Long, Boolean> getIdStatus(String username, String password) throws UniversityUserException {
        try {
            return universityUserDAO.getIdStatus(username, password);
        } catch (Exception exception) {
            throw new UniversityUserException("Unexpected Exception");
        }
    }

    @Override
    public List<UniversityUserDTO> getStudents() throws UniversityUserException {
        try {
            List<UniversityUser> allUniversityUsers = universityUserDAO.getStudents();

            List<UniversityUserDTO> dtos = new ArrayList<>();
            for (UniversityUser universityUser : allUniversityUsers) {
                UniversityUserDTO dto = new UniversityUserDTO();
                dto.setUsername(universityUser.getUsername());
                dto.setId(universityUser.getId());
                dto.setScore(universityUser.getScore());
                dtos.add(dto);
            }
            return dtos;
        } catch (Exception ex) {
            throw new UniversityUserException("Unexpected Exception");
        }
    }

    @Override
    public Map<Long, String> getLecturers() throws UniversityUserException {
        try {
            Map<Long, String> Lecturers = universityUserDAO.getLecturers();
            return Lecturers;
        } catch (Exception ex) {
            throw new UniversityUserException("Unexpected Exception");
        }
    }

    @Override
    public void deleteById(long id) throws Exception {
        try {
            universityUserDAO.deleteById(id);
        } catch (Exception ex) {
            throw new UniversityUserException("Unexpected Error");
        }
    }
}
