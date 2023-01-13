package org.example.library.services;

import org.example.library.model.Person;
import org.example.library.repositories.PeopleRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PeopleService {
    private final PeopleRepository peopleRepository;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    public List<Person> findAll() {
        return peopleRepository.findAll();
    }

    public Person findById(int id) {
        Person person = peopleRepository.findById(id).get();
        if (person == null)
            return person;
        Hibernate.initialize(person.getBooks());
        return person;
    }

    @Transactional
    public void save(Person person) {
        peopleRepository.save(person);
    }

    public Optional<Person> findByName(String name) {
        return peopleRepository.findPersonByName(name);
    }

    @Transactional
    public void update(Person person) {
        peopleRepository.save(person);
    }

    @Transactional
    public void delete(int id) {
        peopleRepository.deleteById(id);
    }
}
