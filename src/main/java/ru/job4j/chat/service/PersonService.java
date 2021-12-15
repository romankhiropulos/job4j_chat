package ru.job4j.chat.service;

import org.springframework.stereotype.Service;
import ru.job4j.chat.model.Person;
import ru.job4j.chat.repository.PersonRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> getAll() {
        return (List<Person>) personRepository.findAll();
    }

    public Person save(Person person) {
        return personRepository.save(person);
    }

    public Optional<Person> findByName(String name) {
        return personRepository.findByName(name);
    }

    public Optional<Person> findByNameAndPassword(String name, String password) {
        return personRepository.findByNameAndPassword(name, password);
    }

    public void deleteById(long id) {
        personRepository.deleteById(id);
    }

    public Optional<Person> findById(long id) {
        return personRepository.findById(id);
    }
}
