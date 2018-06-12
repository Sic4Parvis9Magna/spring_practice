package dao;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import lombok.val;
import model.Country;
import model.SimpleCountry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

import static commons.Java9.mapOf;
import static java.sql.Statement.RETURN_GENERATED_KEYS;
import static lombok.AccessLevel.PRIVATE;

@Repository
@FieldDefaults(level = PRIVATE, makeFinal = true)
//@AllArgsConstructor(onConstructor = @__(@Autowired))
public class JdbcCountryDao extends NamedParameterJdbcDaoSupport implements CountryDao {

	public static final String[][] COUNTRY_INIT_DATA = {
			{"Australia", "AU"},
			{"Canada", "CA"},
			{"France", "FR"},
			{"Hong Kong", "HK"},
			{"Iceland", "IC"},
			{"Japan", "JP"},
			{"Nepal", "NP"},
			{"Russian Federation", "RU"},
			{"Sweden", "SE"},
			{"Switzerland", "CH"},
			{"United Kingdom", "GB"},
			{"United States", "US"}};

	static String LOAD_COUNTRIES_SQL = "insert into country (name, code_name) values (?, ?)";
	static String GET_ALL_COUNTRIES_SQL = "select * from country";
	static String GET_COUNTRIES_BY_NAME_SQL = "select * from country where name like :name";
	static String GET_COUNTRY_BY_NAME_SQL = "select * from country where name = :name";
	static String GET_COUNTRY_BY_CODE_NAME_SQL = "select * from country where code_name = :codeName";
	static String UPDATE_COUNTRY_NAME_SQL = "update country SET name=:name where code_name=:code_name";

	static RowMapper<Country> COUNTRY_ROW_MAPPER = (rs, __) ->
			new SimpleCountry(
					rs.getInt("id"),
					rs.getString("name"),
					rs.getString("code_name")
			);

	@Autowired
	public JdbcCountryDao(DataSource dataSource) {
		setDataSource(dataSource);
	}

	@Override
	public void save(Country country) {
		country.setId(
				save(country.getName(), country.getCodeName())
		);
	}

	private long save(String name, String codeName) {
		val keyHolder = new GeneratedKeyHolder();
		getJdbcTemplate().update(
				connection -> {
					val ps = connection.prepareStatement(
							LOAD_COUNTRIES_SQL, RETURN_GENERATED_KEYS);
					ps.setString(1, name);
					ps.setString(2, codeName);
					return ps;
				},
				keyHolder);
		return keyHolder.getKey().longValue();
	}

	@Override
	public List<Country> getAllCountries() {
		return getNamedParameterJdbcTemplate().query(
				GET_ALL_COUNTRIES_SQL,
				COUNTRY_ROW_MAPPER);
	}

	public List<Country> getCountryListStartWith(String name) {
		return getNamedParameterJdbcTemplate().query(
				GET_COUNTRIES_BY_NAME_SQL,
				mapOf("name", name + "%"),
				COUNTRY_ROW_MAPPER);
	}

	public void updateCountryName(String codeName, String newCountryName) {
		getNamedParameterJdbcTemplate().update(UPDATE_COUNTRY_NAME_SQL,
				mapOf("code_name", codeName,
						"name", newCountryName));
	}

	public void loadCountries() {
		for (String[] countryData : COUNTRY_INIT_DATA)
			save(countryData[0], countryData[1]);
	}

	public Country getCountryByCodeName(String codeName) {
		return getNamedParameterJdbcTemplate()
				.queryForObject(GET_COUNTRY_BY_CODE_NAME_SQL,
						mapOf("codeName", codeName),
						COUNTRY_ROW_MAPPER);
	}

	@Override
	@SneakyThrows
	public Country getCountryByName(String name) {
		List<Country> countryList = getNamedParameterJdbcTemplate().query(
				GET_COUNTRY_BY_NAME_SQL,
				mapOf("name", name),
				COUNTRY_ROW_MAPPER);

		if (countryList.isEmpty())
			throw new CountryNotFoundException();

		return countryList.get(0);
	}
}

