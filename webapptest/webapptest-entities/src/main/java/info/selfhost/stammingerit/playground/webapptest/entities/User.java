package info.selfhost.stammingerit.playground.webapptest.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "TST_USER")
public class User extends BaseEntity{
    @Column(name = "NAME", unique = true)
    private String name;
    @Column(name = "PASSWORD", nullable = false)
    private String password;
    @Column(name = "SALT", nullable = false)
    private String salt;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

}