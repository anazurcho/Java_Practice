package University.Client;

import University.Common.Command;
import University.Common.UniversityUserDTO;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        Socket socket = new Socket("localhost", 8081);

        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

        List<UniversityUserDTO> dtos;
        Map<Long, String> result;

        while (true) {
            System.out.println("1. მომხმარებლის დამატება");
            System.out.println("2. ავტორიზაცია");
            System.out.println("0. exit");

            String option = scanner.nextLine();
            if (option.equals("0")) {
                out.writeObject(Command.EXIT);
                out.close();
                in.close();
                socket.close();
                break;
            }
            switch (option) {
                case "1":
                    System.out.println("სახელი:");
                    String username = scanner.nextLine();
                    System.out.println("პაროლი:");
                    String password1 = scanner.nextLine();
                    System.out.println("გაიმეორეთ პაროლი:");
                    String password2 = scanner.nextLine();

                    out.writeObject(Command.ADD_USER);
                    out.writeObject(username);
                    out.writeObject(password1);
                    out.writeObject(password2);
                    out.reset();
                    out.flush();

                    String message = (String) in.readObject();
                    System.out.println(message);
                    break;
                case "2":
                    System.out.println("სახელი:");
                    username = scanner.nextLine();
                    System.out.println("პაროლი:");
                    String password = scanner.nextLine();

                    out.writeObject(Command.AUTHORIZE);
                    out.writeObject(username);
                    out.writeObject(password);
                    Boolean authorized = (Boolean) in.readObject();

                    if (authorized) {
                        out.writeObject(Command.GET_ID_STATUS);
                        out.writeObject(username);
                        out.writeObject(password);
                        Map<Long, Boolean> userIdLecturer = (Map<Long, Boolean>) in.readObject();
//                        ვიგებთ user სტუდენტი არის თუ ლექტორი, ასევე მის აიდს ვიღებთ
                        long id = userIdLecturer.entrySet().stream().findFirst().get().getKey();
                        Boolean is_lecturer = userIdLecturer.entrySet().stream().findFirst().get().getValue();
                        if (is_lecturer) {
                            while (true) {
                                System.out.println("Success lecturer");
                                System.out.println("1. სტუდენტების ნახვა");
                                System.out.println("2. სტუდენტის წაშლა");
                                System.out.println("3. სტუდენტისთვის ქულის დაწერა");
                                option = scanner.nextLine();
                                switch (option) {
                                    case "1":
                                        System.out.println("შენ ახლა სტუდენტებს ნახულობ");
                                        out.writeObject(Command.GET_STUDENTS);
                                        dtos = (List<UniversityUserDTO>) in.readObject();
                                        for (UniversityUserDTO dto : dtos) {
                                            System.out.println(dto.getId() + "   " + dto.getUsername() + "  ->  " + dto.getScore());
                                        }
                                        out.reset();
                                        out.flush();
                                        break;
                                    case "2":
                                        System.out.println("სტუდენტის აიდი რომლის წაშლაც გინდათ:");
                                        long deleteStudent = Long.parseLong(scanner.nextLine());
                                        out.writeObject(Command.DELETE);
                                        out.writeObject(deleteStudent);
                                        System.out.println("წარმატებით წაიშალა");
                                        out.reset();
                                        out.flush();
                                        break;
                                    case "3":
                                        System.out.println("სტუდენტის აიდი ვისაც ქულას უწერთ :");
                                        long student_id = Long.parseLong(scanner.nextLine());
                                        System.out.println("შეიყვანეთ ქულა:");
                                        long score = Long.parseLong(scanner.nextLine());
                                        out.writeObject(Command.CHANGE_STUDENT_SCORE);
                                        out.writeObject(student_id);
                                        out.writeObject(score);
                                        System.out.println("წარმატებით დაეწერა სტუდენტს ქულა");
                                        out.reset();
                                        out.flush();
                                        break;
                                }
                            }
                        } else {
                            while (true){
                                System.out.println("Success Student");
                                System.out.println("1. ქულის ნახვა");
                                System.out.println("2. ლექტორების ნახვა");
                                option = scanner.nextLine();
                                switch (option) {
                                    case "1":
                                        System.out.println("შენ ახლა შენს ქულას ნახულობ");
                                        out.writeObject(Command.GET_USER_SCORE);
                                        out.writeObject(id);
                                        Long score = (Long) in.readObject();
                                        System.out.println("შენი ქულა არის :" + score);
                                        break;
                                    case "2":
                                        System.out.println("შენ ახლა ლექტორების ნახულობ");
                                        out.writeObject(Command.GET_LECTURER);
                                        result = (Map<Long, String>) in.readObject();
                                        for (Long n : result.keySet()) {
                                            System.out.println(n + " ->   " + result.get(n));
                                        }
                                        break;
                                }
                            }
                        }
                    } else {
                        System.out.println("Failure");
                    }
                    break;

            }
        }
    }

}
