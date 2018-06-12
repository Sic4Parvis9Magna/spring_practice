package aop;

import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.ImportResource;

import static lombok.AccessLevel.PRIVATE;

@Configuration
@AllArgsConstructor
@EnableAspectJAutoProxy
@ImportResource("application-context.xml")
@FieldDefaults(level = PRIVATE)
@ComponentScan({"model", "aop"})
public class JavaConfiguration {
}
