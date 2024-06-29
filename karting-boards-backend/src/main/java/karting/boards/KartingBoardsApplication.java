package karting.boards;

import karting.boards.config.RsaKeyProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(RsaKeyProperties.class)
public class KartingBoardsApplication {
	public static void main(String[] args) {
		SpringApplication.run(KartingBoardsApplication.class, args);
	}

}
