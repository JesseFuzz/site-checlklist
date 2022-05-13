package activity.controller;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import activity.ActivityRepository;
import activity.model.Activity;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/api/activities")
public class ActivityController {
	
	@Autowired
	private ActivityRepository repository; 
	
	@PostMapping
	public Activity save(@Valid @RequestBody Activity activity) { // o meu request body eu mando em json
		return repository.save(activity);
	}
	
	@GetMapping
	public List<Activity> getAll(){
		return repository.findAll();
		
	}
	
	@GetMapping("{id}")//parametro   --> /api/activities/id pode ser 1, 2 etc e eu mando eles na URL, preciso dele para que o id do parametro desse método receba de fato o ID que eu passar lá
	public Activity getById(@PathVariable(name="id") Long id) {
		return repository
				.findById(id)
				.orElseThrow(//estudar Optional eg: Optional<String> e exceptions e DTOs
						() -> new ResponseStatusException(HttpStatus.NOT_FOUND)); //Lambda expression new=instância
		
	}
	@DeleteMapping("{id}") //quando a gente recebe esse parametro específico, precisamoos colocar o @PathVariable
	public void delete(@PathVariable Long id) {
		repository.deleteById(id);
	}
	
	@PatchMapping("{id}/done") //putmapping é uma atualização total e esse patchmapping é uma atualização parcial. como é opcional eu preciso colocar a propriedade que quero atualizar, que no caso é done
	public Activity markAsDone(@PathVariable Long id){ //findbyid retorna um optional e essa classe tem um método  MAP. esse método pega o objeto que está dentro do optional pelo tipo dele e aí retorna o mesmo objeto mas modificado
		return repository.findById(id).map(activity -> {
			activity.setDone(true);
			activity.setDoneDate(LocalDateTime.now());
			repository.save(activity);
			return activity;
		}).orElse(null); //método markAsDone recebe um id do tipo long e retorna buscando um id pelo repositório, usa o método map pra mapear o que acontecerá usa -> p dizer o que irá acontecer
	}

}
