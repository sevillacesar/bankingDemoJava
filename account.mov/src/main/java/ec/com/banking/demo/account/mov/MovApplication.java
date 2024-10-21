package ec.com.banking.demo.account.mov;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "ec.com.banking.demo.account.mov.feign")
public class MovApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovApplication.class, args);
	}

}
