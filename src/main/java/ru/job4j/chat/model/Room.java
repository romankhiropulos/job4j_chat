package ru.job4j.chat.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "room")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @Column(name = "description")
    private String description;

    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "room", fetch = FetchType.LAZY)
    private final List<Message> messages = new ArrayList<>();

    public static Room of(long id, String name, String description, Date created, Person person) {
        Room room = new Room();
        room.setId(id);
        room.setName(name);
        room.setDescription(description);
        room.setCreated(created);
        room.setPerson(person);
        return room;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public List<Message> getMessages() {
        return messages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Room)) {
            return false;
        }
        Room room = (Room) o;
        return this.getId() == room.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getId());
    }
}

