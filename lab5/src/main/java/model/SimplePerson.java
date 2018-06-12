package model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.Wither;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SimplePerson implements Person {

    String name;
    Country country;
    int age;
    float height;
    boolean isProgrammer;

    @Wither
    boolean isBroke;

    List<Contact> contacts;




    public void sayHello(Person person) {
        System.out.println("Hello, dear " + person.getName());
    }


}