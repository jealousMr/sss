package art_server.art_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class ArtServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ArtServerApplication.class, args);
    }

}
