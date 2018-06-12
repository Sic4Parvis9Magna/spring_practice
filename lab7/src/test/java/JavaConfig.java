import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.val;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.ImportResource;
import org.springframework.instrument.classloading.InstrumentationLoadTimeWeaver;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.sql.DataSource;

import static lombok.AccessLevel.PRIVATE;
import static org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType.H2;

@Configuration
@AllArgsConstructor
@EnableAspectJAutoProxy
@ImportResource("application-context.xml")
@FieldDefaults(level = PRIVATE)
@ComponentScan({"model", "dao","dao.jpa"})
public class JavaConfig {

    InstrumentationLoadTimeWeaver instrumentationLoadTimeWeaver;



    @Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
                .generateUniqueName(true)
                .setType(H2)
                .setScriptEncoding("UTF-8")
                .ignoreFailedDrops(true)
                .addScript("db-schema.sql")
                .build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean() {
        val bean = new LocalContainerEntityManagerFactoryBean();
        bean.setDataSource(dataSource());
        bean.setPersistenceUnitName("springframework.lab.orm.jpa");
        bean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        bean.setLoadTimeWeaver(instrumentationLoadTimeWeaver);
        return bean;
    }
}
