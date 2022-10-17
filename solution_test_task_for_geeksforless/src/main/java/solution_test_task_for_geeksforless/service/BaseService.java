package solution_test_task_for_geeksforless.service;

import solution_test_task_for_geeksforless.persistence.entity.BaseEntity;

import java.util.List;

public interface BaseService<ENTITY extends BaseEntity> {
    void create(ENTITY entity);

    void delete(Long id);

    ENTITY findById(Long id);

    List<ENTITY> findAll();
}