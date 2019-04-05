package br.tonini.tasklist.app.rest.v1.task;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TaskRepository extends JpaRepository<Task, Long> {
	
	@Query("select t from Task t order by t.id asc")
	public List<Task> findAllOrderByIdAsc();

}
