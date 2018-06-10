import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource("application-context.xml")
@ComponentScan({"model"})
public class JavaConfig {

}
