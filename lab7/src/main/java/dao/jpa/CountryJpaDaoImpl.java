package dao.jpa;

import model.Country;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("countryJpaDao")
public class CountryJpaDaoImpl extends AbstractJpaDao {

	@Override
	public void save(Country country) {
		withEntityManager(entityManager -> entityManager.merge(country));
	}

	@Override
	public List<Country> getAllCountries() {
		return mapEntityManager(entityManager ->
				entityManager.createQuery(
						"select c from SimpleCountry c",
						Country.class)
						.getResultList());
	}

	@Override
	public Country getCountryByName(String name) {
		return mapEntityManager(entityManager ->
				entityManager.createQuery(
						"select c from SimpleCountry  where c.name = :name",
						Country.class)
						.setParameter("name", name)
						.getSingleResult()
		);
	}

}
