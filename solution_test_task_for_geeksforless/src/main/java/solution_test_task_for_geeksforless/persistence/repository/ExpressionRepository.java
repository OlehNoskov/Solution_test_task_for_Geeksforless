package solution_test_task_for_geeksforless.persistence.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import solution_test_task_for_geeksforless.persistence.entity.Expression;

import java.util.List;

@Repository
public interface ExpressionRepository extends AbstractRepository<Expression> {

    @Query(value = "SELECT * FROM expressions WHERE result = :number", nativeQuery = true)
    List<Expression> findAllExpressionWhereResultEqual(double number);

    @Query(value = "SELECT * FROM expressions WHERE result > :number", nativeQuery = true)
    List<Expression> findAllExpressionWhereResultMore(double number);

    @Query(value = "SELECT * FROM expressions WHERE result < :number", nativeQuery = true)
    List<Expression> findAllExpressionWhereResultLess(double number);
}