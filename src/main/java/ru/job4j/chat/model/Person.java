package ru.job4j.chat.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String password;

    private String name;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    private boolean enabled;

    public static Person of(String name, Role role, String password) {
        Person user = new Person();
        user.name = name;
        user.role = role;
        user.password = password;
        return user;
    }

    public static Person of(Long id, String name, String password) {
        Person user = new Person();
        user.id = id;
        user.name = name;
        user.password = password;
        return user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Person)) {
            return false;
        }
        Person person = (Person) o;
        return this.getId() == person.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getId());
    }
}
