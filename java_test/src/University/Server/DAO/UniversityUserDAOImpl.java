package University.Server.DAO;

import University.Server.Model.UniversityUser;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class UniversityUserDAOImpl implements UniversityUserDAO {

    private Connection connection;

    public UniversityUserDAOImpl() throws Exception {
        Driver driver = new org.postgresql.Driver();
        DriverManager.registerDriver(driver);

        connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/DataBase", "DataBaseOwner", "");
    }

    @Override
    public void addUser(UniversityUser universityUser) throws Exception {
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO university_user (username, password, active) VALUES (?, ?, ?)");
        preparedStatement.setString(1, universityUser.getUsername());
        preparedStatement.setString(2, universityUser.getPassword());
        preparedStatement.setBoolean(3, universityUser.isActive());
        preparedStatement.executeUpdate();

        preparedStatement.close();
    }

    @Override
    public void deleteById(long id) throws Exception {
        Statement statement = connection.createStatement();
        statement.executeUpdate("DELETE FROM university_user WHERE id = " + id);
        statement.close();
    }

    @Override
    public List<UniversityUser> getStudents() throws Exception {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM university_user where is_lecturer = false");

        List<UniversityUser> list = new ArrayList<>();
        while (resultSet.next()) {
            long id = resultSet.getLong("id");
            String username = resultSet.getString("username");
            String password = resultSet.getString("password");
            boolean active = resultSet.getBoolean("active");
            boolean is_lecturer = resultSet.getBoolean("is_lecturer");
            long score = resultSet.getLong("score");
            UniversityUser universityUser = new UniversityUser(id, username, password, active, is_lecturer, score);
            list.add(universityUser);
        }

        statement.close();

        return list;
    }

    @Override
    public Map<Long, String> getLecturers() throws Exception {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM university_user where is_lecturer = true");

        Map<Long, String> list_of_lecturers = new HashMap<>();

        while (resultSet.next()) {
            long id = resultSet.getLong("id");
            String username = resultSet.getString("username");
            list_of_lecturers.put(id, username);
        }

        statement.close();

        return list_of_lecturers;
    }

    @Override
    public boolean checkIfUserExists(String username, String password) throws Exception {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT password FROM university_user WHERE password = '" + password + "' and username = '" + username + "'");
        if (resultSet.next()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public long getScoreForStudent(long id) throws Exception {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT score FROM university_user WHERE id = '" + id + "'");
        if (resultSet.next()) {
            long user_score = resultSet.getLong("score");
            System.out.println(user_score);
            return user_score;
        } else {
            return 0;
        }
    }

    @Override
    public void changeScoreForStudent(long id, long score) throws Exception {
        Statement statement = connection.createStatement();
        statement.executeUpdate("update university_user set score = '" + score + "'  WHERE id = " + id);
        statement.close();
    }

    @Override
    public Map<Long, Boolean> getIdStatus(String username, String password) throws Exception {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT id, is_lecturer FROM university_user WHERE password = '" + password + "' and username = '" + username + "'");
//ფილტრს იმიტომ არ ვუკეთებ, რომ კლიენტის მხარეს ეს უკვე გავლილი აქვს და ვიცით რომ პასუხს აუცილებლად დააბრუნებს :)
        Map<Long, Boolean> user_info = new HashMap<>();
        if (resultSet.next()) {
            long id = resultSet.getLong("id");
            boolean is_lecturer = resultSet.getBoolean("is_lecturer");
            user_info.put(id, is_lecturer);
            return user_info;
        } else {
            return user_info;
        }
    }

    @Override
    public void closeConnection() throws Exception {
        connection.close();
    }
}
