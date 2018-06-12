package dao;

import model.Country;

import java.util.List;

public interface CountryDao {

	 void save(Country country);

	 List<Country> getAllCountries();

	 Country getCountryByName(String name);

}