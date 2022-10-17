package solution_test_task_for_geeksforless.service;

import solution_test_task_for_geeksforless.persistence.entity.Expression;

import java.util.List;

public interface ExpressionService extends BaseService<Expression> {
    List<Expression> findAllExpressionWithResultEqual(double number);

    List<Expression> findAllExpressionWithResultMore(double number);

    List<Expression> findAllExpressionWithResultLess(double number);
}