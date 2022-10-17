package solution_test_task_for_geeksforless.view.dto.response;

import lombok.Getter;
import lombok.Setter;
import solution_test_task_for_geeksforless.persistence.entity.Expression;

public class ExpressionResponseDto extends ResponseDto {
    @Getter
    private Long id;

    @Getter
    @Setter
    private String expression;

    @Getter
    @Setter
    private double expressionResult;

    public ExpressionResponseDto(Expression expression) {
        this.id = expression.getId();
        this.expression = expression.getExpression();
        this.expressionResult = expression.getResultExpression();
    }
}