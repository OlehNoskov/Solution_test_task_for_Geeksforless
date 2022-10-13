package solution_test_task_for_geeksforless.persistence.repository;

import org.springframework.stereotype.Repository;
import solution_test_task_for_geeksforless.persistence.entity.Expression;

@Repository
public interface ExpressionRepository extends AbstractRepository<Expression> {
}