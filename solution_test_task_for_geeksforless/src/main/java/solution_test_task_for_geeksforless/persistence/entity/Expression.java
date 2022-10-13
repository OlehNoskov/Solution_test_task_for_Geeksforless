package solution_test_task_for_geeksforless.persistence.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "expressions")
@EqualsAndHashCode(callSuper = true)
public class Expression extends BaseEntity {

    @Column(name = "expression")
    private String expression;
    @Column(name = "result")
    private double resultExpression;

    public Expression() {
        super();
    }
}