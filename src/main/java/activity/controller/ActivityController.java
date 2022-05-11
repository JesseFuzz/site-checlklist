package activity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import activity.ActivityRepository;
import activity.model.Activity;

@RestController
@RequestMapping("/api/activities")
public class ActivityController {
	
	@Autowired
	private ActivityRepository repository; 
	
	@PostMapping
	public Activity save(@RequestBody Activity activity) { // o meu request body eu mando em json
		return repository.save(activity);
		
	}
	
	@GetMapping("{id}")//parametro   --> /api/activities/id pode ser 1, 2 etc e eu mando eles na URL, preciso dele para que o id do parametro desse método receba de fato o ID que eu passar lá
	public Activity getById(@PathVariable(name="id") Long id) {
		return repository
				.findById(id)
				.orElseThrow(//estudar Optional eg: Optional<String> e exceptions e DTOs
						() -> new ResponseStatusException(HttpStatus.NOT_FOUND)); //Lambda expression new=instância
		
	}

}
