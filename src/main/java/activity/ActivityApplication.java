package activity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@SpringBootApplication

public class ActivityApplication {
	@GetMapping
	public String helloWorld() {
		return "O Van é um viadinho";
	}
	public static void main(String[] args) {
		SpringApplication.run(ActivityApplication.class, args);
		
	}

}
