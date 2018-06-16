package dao.jpa;

import dao.CountryDao;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import static lombok.AccessLevel.PRIVATE;

@FieldDefaults(level = PRIVATE)
public abstract class AbstractJpaDao implements CountryDao, JpaDao {

	@Getter
	@Setter(onMethod = @__(@PersistenceUnit))
	EntityManagerFactory emf;

}