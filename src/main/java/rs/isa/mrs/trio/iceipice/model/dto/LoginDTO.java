package rs.isa.mrs.trio.iceipice.model.dto;

/**
 * Created by nikolalukic on 4/12/16.
 */
public class LoginDTO {

    private String email;

    private String password;

    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
