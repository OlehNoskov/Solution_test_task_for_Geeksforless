package solution_test_task_for_geeksforless.service;

import solution_test_task_for_geeksforless.persistence.entity.Expression;
import solution_test_task_for_geeksforless.view.dto.request.ExpressionRequestDto;
import solution_test_task_for_geeksforless.view.dto.response.ExpressionResponseDto;

import java.util.List;

public interface ExpressionService extends BaseService<Expression> {
    void update(ExpressionRequestDto entity, Long id);

    ExpressionResponseDto getResponseExpressionById(Long id);

    List<Expression> findAllExpressionWithResultEqual(double number);

    List<Expression> findAllExpressionWithResultMore(double number);

    List<Expression> findAllExpressionWithResultLess(double number);
}