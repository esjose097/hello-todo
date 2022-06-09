package academy.learnprogramming.entity;


import javax.inject.Named;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 
 * @author jose casal
 */
@Entity
@Table(name = "TodoUser")

@NamedQuery(name = User.FIND_ALL_USERS, query = "select u from User u order by u.fullName")
@NamedQuery(name = User.FIND_USER_BY_EMAIL, query = "select u from User u where u.email = :email")
@NamedQuery(name = User.FIND_USER_BY_PASSWORD, query = "select u from User u where u.password = :password")
public class User extends AbstractEntity{

    public static final String FIND_ALL_USERS = "User.findAllUsers";
    public static final String FIND_USER_BY_EMAIL = "User.findByEmail";
    public static final String FIND_USER_BY_PASSWORD = "User.findByPassword";


    @NotNull(message = "Full name must be set")
//    @Pattern(regexp = "", message = "Full name must be in alphabets")
    private String fullName; //123456

    @NotNull(message = "Email must be set")
    @Email(message = "Email must be in the form user@domain.com")
    private String email;

    @NotNull(message = "Password cannot be empty")
    @Size(min = 8, message = "Password must have a minimum size of 8 characters")
//    @Pattern(regexp = "", message = "Password be a combination of alphabets, numbers and special characters")
    private String password; //$%#@!


    private String salt;


    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
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
