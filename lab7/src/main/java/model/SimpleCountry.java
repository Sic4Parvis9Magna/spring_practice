package model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Component("country")
@Table(name = "country")
@EqualsAndHashCode(exclude = "id")
public class SimpleCountry implements Country {

    @Id
    @GeneratedValue
    long id;
    String name;
    String codeName;

    public SimpleCountry(String name, String codeName) {
        this(0L, name, codeName);
    }

    public SimpleCountry setId(long id) {
        this.id = id;
        return this;
    }

    public SimpleCountry setName(String name) {
        this.name = name;
        return this;
    }

    public SimpleCountry setCodeName(String codeName) {
        this.codeName = codeName;
        return this;
    }
}
