package model;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.var;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;

@Configuration
@ExtendWith(SpringExtension.class)
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor(onConstructor = @__(@Autowired))
@ContextConfiguration("classpath:application-context.xml")
class SpringTCFAppTest {

    Person person;

    @Test
    @DisplayName("Simple TCF App test")
    void testInitPerson() {
        assertThat(getExpectedPerson(), Is.is(person));
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
