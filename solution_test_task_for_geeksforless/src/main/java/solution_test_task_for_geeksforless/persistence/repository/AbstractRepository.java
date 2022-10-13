package solution_test_task_for_geeksforless.persistence.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import solution_test_task_for_geeksforless.persistence.entity.BaseEntity;

@NoRepositoryBean
public interface AbstractRepository<E extends BaseEntity> extends CrudRepository<E, Long> {
}