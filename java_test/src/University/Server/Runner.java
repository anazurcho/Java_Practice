package University.Server;

import University.Server.DAO.UniversityUserDAO;
import University.Server.DAO.UniversityUserDAOImpl;
import University.Server.Model.UniversityUser;

import java.util.List;

public class Runner {

    public static void main(String[] args) throws Exception {
        UniversityUserDAO universityUserDAO = new UniversityUserDAOImpl();

//        universityUserDAO.addUser(new UniversityUser(0, "GROOT", "123", true, false, 0));

        System.out.println("========students==========");
        List<UniversityUser> universityUsers = universityUserDAO.getStudents();
        for (UniversityUser universityUser : universityUsers) {
            System.out.println(universityUser);
        };

//        Map<Long, String> lecturers = universityUserDAO.getLecturers();
//        System.out.println("========lecturers==========");
//        for (Long n : lecturers.keySet()) {
//            System.out.println(n + " ->  " + lecturers.get(n));
//        }
//        თუ წაშლა გვინდა
//        studentDAO.deleteById(1);

//        ცდა თუ არსებობს ეს მომხმარებელი ამ იუზერნეიმით და პაროლით
//        boolean UserTrue = universityUserDAO.checkIfUserExists("GROOT", "113");
//        System.out.println(UserTrue);

//        აიდი იუზერის რეგისტრაციიდან იღებს და სტატუსი სტუდენტია თუ ლექტორი
//        Map<Long, Boolean> id_Lecturer = universityUserDAO.getIdStatus("GROOT", "123");

//        System.out.println(id_Lecturer);
//        universityUserDAO.closeConnection();

    }

}