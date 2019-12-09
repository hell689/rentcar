package domain;

import lombok.Data;
import lombok.ToString;

@Data
@ToString (exclude = "password")
public class User extends Entity {

    private static final long serialVersionUID = 1L;

    private String name;
    private String surname;
    private String login;
    private String password;
    private String address;
    private Role role;
}
