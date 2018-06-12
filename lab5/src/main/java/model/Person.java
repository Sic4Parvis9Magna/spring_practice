package model;

import java.util.List;

public interface Person {
    void setName(String name);

    String getName();

    boolean isBroke();

    int getAge();

    float getHeight();

    boolean isProgrammer();

    Person withBroke(boolean broke);

    List<Contact> getContacts();

    void sayHello(Person person);
}