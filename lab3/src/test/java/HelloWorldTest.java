import lombok.experimental.FieldDefaults;
import model.Contact;
import model.Country;
import model.Person;
import model.SimpleContact;
import model.SimpleCountry;
import model.SimplePerson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static lombok.AccessLevel.PRIVATE;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@FieldDefaults(level = PRIVATE)
class HelloWorldTest {

    static BeanFactory BEAN_FACTORY = new AnnotationConfigApplicationContext(
            JavaConfig.class);
    Person expectedPerson;

    @BeforeEach
    void setUp() {
        expectedPerson = BEAN_FACTORY.getBean("person", SimplePerson.class);

    }

    @Test
    void testInitPerson() {

        assertThat(expectedPerson, is(getExpectedPerson()));
    }

    private Person getExpectedPerson() {
        Country country = SimpleCountry
                .builder()
                .name("Russia")
                .codeName("RU")
                .build();
        List<Contact> contactList = new ArrayList<>();
        contactList.add(SimpleContact
                .builder()
                .type("EMAIL")
                .value("guava@google.com")
                .build());
        Person pers = SimplePerson
                .builder()
                .age(35)
                .height(12)
                .isProgrammer(true)
                .name("John Smith")
                .country(country)
                .contacts(contactList)
                .build();


        return pers;
    }


}
