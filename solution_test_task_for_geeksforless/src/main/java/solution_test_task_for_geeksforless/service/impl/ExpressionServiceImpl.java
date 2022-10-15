package solution_test_task_for_geeksforless.service.impl;

import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;

import solution_test_task_for_geeksforless.persistence.entity.Expression;
import solution_test_task_for_geeksforless.persistence.repository.ExpressionRepository;
import solution_test_task_for_geeksforless.service.ExpressionService;
import solution_test_task_for_geeksforless.util.Calculation;
import solution_test_task_for_geeksforless.util.Lexeme;
import solution_test_task_for_geeksforless.util.LexemeBuffer;
import solution_test_task_for_geeksforless.util.Parser;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class ExpressionServiceImpl implements ExpressionService {
    private final ExpressionRepository expressionRepository;

    public ExpressionServiceImpl(ExpressionRepository expressionRepository) {
        this.expressionRepository = expressionRepository;
    }

    @Override
    public void create(Expression entity) {
        saveEntity(entity);
    }

    @Override
    public void update(Expression entity) {
        checkExist(expressionRepository, entity.getId());
        saveEntity(entity);
    }

    @Override
    public void delete(Long id) {
        expressionRepository.deleteById(id);
    }

    @Override
    public Expression findById(Long id) {
        return expressionRepository.findById(id).get();
    }

    @Override
    public List<Expression> findAll() {
        return Lists.newArrayList(expressionRepository.findAll());
    }

    private void checkExist(ExpressionRepository repository, Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("This expression is not found!");
        }
    }

    private void saveEntity(Expression entity) {
        if (!Parser.lexAnalyze(entity.getExpression()).isEmpty()) {
            List<Lexeme> lexemes = Parser.lexAnalyze(entity.getExpression());
            LexemeBuffer lexemeBuffer = new LexemeBuffer(lexemes);
            entity.setResultExpression(Calculation.expr(lexemeBuffer));
            expressionRepository.save(entity);
        }
    }

    @Override
    public List<Expression> searchExpressionMoreThanNumber(double number) {
        return expressionRepository.findAllExpressionWhereResultMore(number);
    }
}