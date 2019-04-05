package br.tonini.tasklist.app.rest.v1.task;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = { "http://localhost:4200", "https://getonini.github.io" }, allowedHeaders = "*")
public class TaskController {

	@Autowired
	private TaskRepository taskRepository;

	@GetMapping("/tasks")
	public List<Task> getTasks() {
		return taskRepository.findAllOrderByIdAsc();
	}
	
	@GetMapping("/task/{id}")
	public Task getTask(@PathVariable Long id) {
		return findById(id);
	}

	private Task findById(Long id) {
		Optional<Task> tasks = taskRepository.findById(id);
		if (tasks != null) {
			return tasks.get();
		}
		return null;
	}

	@DeleteMapping("/task/{id}")
	public boolean deleteTask(@PathVariable Long id) {
		taskRepository.deleteById(id);
		return true;
	}

	@PutMapping("/task")
	public Task updateTask(@RequestBody Task task) {
		ajustarDataConclusao(task);
		task.setLastUpdateDate(new Date());
		return taskRepository.save(task);
	}

	@PutMapping("/task/conclude")
	public Task concludeTask(@RequestBody Task task) {
		ajustarDataConclusao(task);
		return taskRepository.save(task);
	}

	private void ajustarDataConclusao(Task task) {
		task.setConclusionDate(task.getStatus() ? new Date() : null);
	}

	//com a anotacao validated, validamos os campos que marcamos como notBlank e envolvidos no contexto AddTaskStep
	@PostMapping("/task")
	public Task createTask(@RequestBody @Validated(Task.AddTaskStep.class) Task task) {
		task.setCreationDate(new Date());
		return taskRepository.save(task);
	}

}
