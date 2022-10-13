package solution_test_task_for_geeksforless.service.impl;

import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;

import solution_test_task_for_geeksforless.persistence.entity.Expression;
import solution_test_task_for_geeksforless.persistence.repository.ExpressionRepository;
import solution_test_task_for_geeksforless.service.ExpressionService;

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
        expressionRepository.save(entity);
    }

    @Override
    public void update(Expression entity) {
        checkExist(expressionRepository, entity.getId());
        expressionRepository.save(entity);
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
}