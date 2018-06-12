
import dao.jpa.CountryJpaDaoImpl;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.val;
import model.Country;
import model.SimpleCountry;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsIn.in;
import static org.hamcrest.core.Is.is;

/**
 * Illustrates basic use of Hibernate as a JPA provider.
 */
@FieldDefaults(level = PRIVATE, makeFinal = true)
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = JavaConfig.class)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
class CountryDaoImplTest {

	CountryJpaDaoImpl countryJpaDao;

	@Test
	@DisplayName("test save country")
	void testSaveCountry() {
		// given
		val exampleCountry = new SimpleCountry("Australia", "AU");
		int initialSize = countryJpaDao.getAllCountries().size();

		// when
		countryJpaDao.save(exampleCountry);

		//then
		List<Country> countryList = countryJpaDao.getAllCountries();
		assertThat(countryList.size(), is(initialSize + 1));
		assertThat(exampleCountry, is(in(countryList)));
	}

	@Test
	void testGetAllCountries() {
		// given
		int initialSize = countryJpaDao.getAllCountries().size();

		// when
		countryJpaDao.save(new SimpleCountry("Canada", "CA"));

		// then
		List<Country> countryList = countryJpaDao.getAllCountries();
		assertThat(countryList.size(), is(initialSize + 1));
	}

	@Test
	void testGetCountryByName() {
		// given
		val exampleCountry = new SimpleCountry("Australia", "AU");
		countryJpaDao.save(exampleCountry);

		// when
		Country country = countryJpaDao.getCountryByName("Australia");

		//then
		assertThat(exampleCountry, is(country));
	}

}