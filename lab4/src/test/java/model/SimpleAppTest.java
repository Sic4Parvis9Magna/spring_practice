package model;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.var;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@FieldDefaults(level = AccessLevel.PRIVATE)
class SimpleAppTest {

    static final String APPLICATION_CONTEXT_XML_FILE_NAME = "classpath:application-context.xml";

    private AbstractApplicationContext context;

    Person expectedPerson;

    @BeforeEach
    void setUp() {
        context = new ClassPathXmlApplicationContext(
                APPLICATION_CONTEXT_XML_FILE_NAME);
        expectedPerson = getExpectedPerson();
    }

    @Test
    @DisplayName("Simple App test")
    void testInitPerson() {
        Person person = (SimplePerson) context.getBean("person");
        assertThat(expectedPerson, is(person));
    }

    private Person getExpectedPerson() {
        var country = SimpleCountry
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
        var pers = SimplePerson
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
