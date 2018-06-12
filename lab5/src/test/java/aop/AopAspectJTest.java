package aop;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import model.ApuBar;
import model.Bar;
import model.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static aop.TestUtils.fromSystemOut;
import static lombok.AccessLevel.PRIVATE;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


@ExtendWith(SpringExtension.class)
@FieldDefaults(level = PRIVATE, makeFinal = true)
@ContextConfiguration(classes = JavaConfiguration.class)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
class AopAspectJTest {


    Bar bar;
    Person person;

    @NonFinal
    String fromSout;

    @BeforeEach
    void setUp() {
        fromSout = fromSystemOut(() -> bar.sellSquishee(person));
    }

    @Test
    void testBeforeAdvice() {
        assertTrue(fromSout.contains("Hello"), "Before advice is not good enought...");
        assertTrue(fromSout.contains("How are you doing?"), "Before advice is not good enought...");
    }

    @Test
    void testAfterAdvice() {
        assertTrue(fromSout.contains("Good Bye!"), "After advice is not good enought...");
    }

    @Test
    void testAfterReturningAdvice() {
        assertTrue(fromSout.contains("Good Enough?"), "Customer is broken");
    }

    @Test
    void testAroundAdvice() {
        assertTrue(fromSout.contains("Hi!"), "Around advice is not good enought...");
        assertTrue(fromSout.contains("See you!"), "Around advice is not good enought...");

    }

    @Test
    void testAllAdvices() {
        assertFalse(bar instanceof ApuBar, "barObject instanceof ApuBar");
    }
}