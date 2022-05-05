import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
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
	public Activity Save(@ RequestBody Activity activity) {
		return repository.save(Activity);
		
	}
	
	@GetMapping("{id}")
	public Activity getById(Long id) {
		return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		
	}

}
