import dao.JdbcCountryDao;
import io.vavr.Tuple;
import model.Country;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import model.SimpleCountry;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static dao.JdbcCountryDao.COUNTRY_INIT_DATA;
import static lombok.AccessLevel.PRIVATE;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;


@FieldDefaults(level = PRIVATE)
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = JavaConfig.class)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
class JdbcTest {

    final JdbcCountryDao jdbcCountryDao;

    static List<Country> expectedCountryList;

    static List<Country> expectedCountryListStartsWithA;

    static Country countryWithChangedName;

    @BeforeAll
    static void setUpAll() {

        expectedCountryList = IntStream.range(0, COUNTRY_INIT_DATA.length)
                .mapToObj(i -> Tuple.of(i + 1, COUNTRY_INIT_DATA[i][0], COUNTRY_INIT_DATA[i][1]))
                .map(issTuple -> new SimpleCountry(issTuple._1, issTuple._2, issTuple._3))
                .collect(Collectors.toList());

        expectedCountryListStartsWithA = expectedCountryList.stream()
                .filter(country -> country.getName().startsWith("A"))
                .collect(Collectors.toList());

        countryWithChangedName = new SimpleCountry(8, "Russia", "RU");
    }

    @BeforeEach
    void setUp() {
        jdbcCountryDao.loadCountries();
    }

    @Test
    @DirtiesContext
    void testCountryList() {
        List<Country> countryList = jdbcCountryDao.getAllCountries();
        Assertions.assertNotNull(countryList);
        Assertions.assertEquals(expectedCountryList.size(), countryList.size());
        for (int i = 0; i < expectedCountryList.size(); i++)
            Assertions.assertEquals(expectedCountryList.get(i), countryList.get(i));
    }

    @Test
    @DirtiesContext
    void testCountryListStartsWithA() {
        List<Country> countryList = jdbcCountryDao.getCountryListStartWith("A");
        Assertions.assertNotNull(countryList);
        Assertions.assertEquals(expectedCountryListStartsWithA.size(), countryList.size());
        for (int i = 0; i < expectedCountryListStartsWithA.size(); i++)
            Assertions.assertEquals(expectedCountryListStartsWithA.get(i), countryList.get(i));
    }

    @Test
    @DirtiesContext
    void testCountryChange() {
        jdbcCountryDao.updateCountryName("RU", "Russia");
        assertThat(jdbcCountryDao.getCountryByCodeName("RU"), is(countryWithChangedName));
    }

}