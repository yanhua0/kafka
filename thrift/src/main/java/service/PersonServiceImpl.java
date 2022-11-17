package service;

import thrift.generated.DataException;
import thrift.generated.Person;
import thrift.generated.PersonService;

public class PersonServiceImpl implements PersonService.Iface  {
    @Override
    public Person getPersonByUsername(String username) throws DataException {
        System.out.println("Got Client Param: " + username);

        return new Person().setUsername(username).setAge(20).setMarried(false);
    }

    @Override
    public void savePerson(Person person) throws DataException {
        System.out.println("Got Client Param:");

        System.out.println(person.username);
        System.out.println(person.age);
        System.out.println(person.married);
    }
}
