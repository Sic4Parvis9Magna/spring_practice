package aop;

import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import model.Bar;
import model.CustomerBrokenException;
import model.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static lombok.AccessLevel.PRIVATE;
import static org.junit.jupiter.api.Assertions.assertThrows;


@ExtendWith(SpringExtension.class)
@FieldDefaults(level = PRIVATE)
@ContextConfiguration(classes = JavaConfiguration.class)
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AopAspectJExceptionTest {

	 Bar bar;

     Person person;

    @BeforeEach
     void setUp()  {

     person = person.withBroke(true);
    }

    @Test
    void testAfterThrowingAdvice() {

        String fromSystemOut = TestUtils.fromSystemOut(() ->
                assertThrows(CustomerBrokenException.class, () ->
                        bar.sellSquishee(person)));

        Assertions.assertTrue(fromSystemOut.contains("Hmmm..."), "Customer is not broken ");
    }
}