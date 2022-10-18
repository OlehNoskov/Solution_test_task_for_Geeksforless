package solution_test_task_for_geeksforless;

import org.junit.Assert;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import solution_test_task_for_geeksforless.persistence.entity.Expression;
import solution_test_task_for_geeksforless.persistence.repository.ExpressionRepository;
import solution_test_task_for_geeksforless.service.ExpressionService;

@RunWith(SpringRunner.class)
@SpringBootTest
class ExpressionServiceTest {
    @Autowired
    private ExpressionService expressionService;

    @MockBean
    private ExpressionRepository expressionRepository;

    @Test
    @Order(1)
    void createExpressionWithValidExpression() {
        Expression expression = new Expression();
        expression.setExpression("1+1+(10+10)");
        boolean isExpressionCreated = expressionService.create(expression);

        Assert.assertTrue(isExpressionCreated);
        Mockito.verify(expressionRepository, Mockito.times(1)).save(expression);
    }

    @Test
    @Order(2)
    void createExpressionWithInvalidExpression() {
        Expression expression = new Expression();
        expression.setExpression("1+1+(10+10))");
        boolean isExpressionCreated = expressionService.create(expression);

        Assert.assertFalse(isExpressionCreated);
    }

    @Test
    @Order(3)
    void checkResultExpressionWhereExpressionIsValid() {
        Expression expression = new Expression();
        expression.setExpression("1+1+(10+10)");
        expressionService.create(expression);
        double expectedResult = 22.0;

        Assert.assertEquals(expectedResult, expression.getResultExpression(), 0);
        Mockito.verify(expressionRepository, Mockito.times(1)).save(expression);
    }

    @Test
    @Order(4)
    void checkResultExpressionWhereExpressionIsInvalid() {
        Expression expression = new Expression();
        expression.setExpression("1+1+(10+10))+100-/2");
        expressionService.create(expression);
        double expectedResult = 0;

        Assert.assertEquals(expectedResult, expression.getResultExpression(), 0);
        Mockito.verify(expressionRepository, Mockito.times(0)).save(expression);
    }
}