package University.Common;


import java.io.Serializable;

public class UniversityUserDTO implements Serializable {

    private static final long serialVersionUID = 101L;

    private Long id;

    private String username;

    private boolean active;

    private long score;

    public UniversityUserDTO() {
    }

    public UniversityUserDTO(long id,String username, boolean active, long score) {
        this.id = id;
        this.username = username;
        this.active = active;
        this.score = score;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setScore(long score) {
        this.score = score;
    }

    public String getUsername() {
        return username;
    }

    public long getScore() {
        return score;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
