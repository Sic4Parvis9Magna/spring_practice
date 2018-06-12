package model;

import aop.Loggable;
import org.springframework.stereotype.Component;

@Component("bar")
public class ApuBar implements Bar {

    @Override
    @Loggable
	public Drink sellSquishee(Person person) {
        if (person.isBroke())
            throw new CustomerBrokenException();

        System.out.println("Here is your Squishee");
        return () -> "Usual Squishee";
    }
}