package ec.com.banking.neo.account.client.services.impl;

import ec.com.banking.neo.account.client.models.Person;
import ec.com.banking.neo.account.client.repositories.PersonRepository;
import ec.com.banking.neo.account.client.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author cesarsevilla
 * @project bankingDemoJava
 */

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Override
    public void insertPerson(Person person) {
        personRepository.save(person);
    }
}
