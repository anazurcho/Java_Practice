package University.Server.Model;


public class UniversityUser {

    private long id;

    private String username;

    private String password;

    private long score;

    private boolean is_lecturer;

    private boolean active;

    public UniversityUser() {

    }

    public UniversityUser(long id, String username, String password, boolean active, boolean is_lecturer, long score) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.active = active;
        this.is_lecturer = is_lecturer;
        this.score = score;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getScore() {
        return score;
    }

    public void setScore(long score) {
        this.score = score;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isIs_lecturer() {
        return is_lecturer;
    }

    public void setIs_lecturer(boolean is_lecturer) {
        this.is_lecturer = is_lecturer;
    }

    @Override
    public String toString() {
        return "UniversityUser{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", score=" + score +
                ", is_lecturer=" + is_lecturer +
                ", active=" + active +
                '}';
    }
}
