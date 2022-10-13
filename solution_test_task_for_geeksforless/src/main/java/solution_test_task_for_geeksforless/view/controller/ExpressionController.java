package solution_test_task_for_geeksforless.view.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import solution_test_task_for_geeksforless.persistence.entity.Expression;
import solution_test_task_for_geeksforless.service.ExpressionService;

@Controller
@RequestMapping("/expressions")
public class ExpressionController {
    private final ExpressionService expressionService;

    public ExpressionController(ExpressionService expressionService) {
        this.expressionService = expressionService;
    }

    @GetMapping("/new")
    public String createExpression(Model model) {
        model.addAttribute("newExpression", new Expression());
        return "pages/expression_new";
    }

    @PostMapping("/new")
    public String createNewExpression(@ModelAttribute("newExpression") Expression expression) {
        expressionService.create(expression);
        return "redirect:/expressions/new";
    }
}