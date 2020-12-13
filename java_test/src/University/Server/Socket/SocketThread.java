package University.Server.Socket;

import University.Common.Command;
import University.Common.UniversityUserDTO;
import University.Server.Model.UniversityUserException;
import University.Server.Service.UniversityUserService;
import University.Server.Service.UniversityUserServiceImpl;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.Map;


public class SocketThread extends Thread {

    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;

    private UniversityUserService universityUserService;

    public SocketThread(Socket socket) throws Exception {
        this.out = new ObjectOutputStream(socket.getOutputStream());
        this.in = new ObjectInputStream(socket.getInputStream());
        this.socket = socket;
        this.universityUserService = new UniversityUserServiceImpl();
    }

    @Override
    public void run() {
        boolean finished = false;
        while (!finished) {
            try {
                Command command = (Command) in.readObject();
                switch (command) {
                    case ADD_USER:
                        String username = (String) in.readObject();
                        String password1 = (String) in.readObject();
                        String password2 = (String) in.readObject();
                        try {
                            universityUserService.addUser(username, password1, password2);
                            out.writeObject("Success");
                        } catch (UniversityUserException ex) {
                            out.writeObject(ex.getMessage());
                        }
                        out.reset();
                        out.flush();
                        break;
                    case AUTHORIZE:
                        username = (String) in.readObject();
                        String password = (String) in.readObject();
                        Boolean authorized = universityUserService.authorize(username, password);
                        out.writeObject(authorized);
                        out.reset();
                        out.flush();
                        break;
                    case GET_STUDENTS:
                        List<UniversityUserDTO> dtos = universityUserService.getStudents();
                        out.writeObject(dtos);
                        out.reset();
                        out.flush();
                        break;
                    case GET_LECTURER:
                        Map<Long, String> dto_lecturers = universityUserService.getLecturers();
                        out.writeObject(dto_lecturers);
                        out.reset();
                        out.flush();
                        break;
                    case GET_ID_STATUS:
                        String username_1 = (String) in.readObject();
                        String password_1 = (String) in.readObject();
                        Map<Long, Boolean> user_id_is_lecturer = universityUserService.getIdStatus(username_1, password_1);
                        out.writeObject(user_id_is_lecturer);
                        out.reset();
                        out.flush();
                        break;
                    case DELETE:
                        Long id = (Long) in.readObject();
                        try {
                            universityUserService.deleteById(id);
                            out.writeObject("Success");
                        } catch (UniversityUserException ex) {
                            out.writeObject(ex.getMessage());
                        }
                        out.reset();
                        out.flush();
                        break;
                    case GET_USER_SCORE:
                        Long student_id = (Long) in.readObject();
                        try {
                            long student_score = universityUserService.getScoreForStudent(student_id);
                            out.writeObject(student_score);
                        } catch (UniversityUserException ex) {
                            out.writeObject(ex.getMessage());
                        }
                        out.reset();
                        out.flush();
                        break;
                    case CHANGE_STUDENT_SCORE:
                        Long student_id_1 = (Long) in.readObject();
                        Long score_1 = (Long) in.readObject();
                        try {
                            universityUserService.changeScoreForStudent(student_id_1, score_1);
                            out.writeObject("Success");
                        } catch (UniversityUserException ex) {
                            out.writeObject(ex.getMessage());
                        }
                        out.reset();
                        out.flush();
                        break;
                    case EXIT:
                        out.close();
                        in.close();
                        socket.close();
                        finished = true;
                        break;
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
