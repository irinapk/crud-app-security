package web.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotEmpty(message = "User name cannot be empty")
    @Size(min = 2, max = 30, message = "Name should consist of 2 to 30 characteres")
    @Column(name = "name")
    private String name;

    @NotEmpty(message = "Lat name cannot be empty")
    @Size(min = 2, max = 30, message = "Last name should consist of 2 to 30 characteres")
    @Column(name = "last_name")
    private String lastName;

    public User(int id, String name, String lastName) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
    }
    public User(String name, String lastName) {
        this.name = name;
        this.lastName = lastName;
    }

    public User() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "User "+ getId() + ": " + getName() + " " + getLastName();
    }
}
