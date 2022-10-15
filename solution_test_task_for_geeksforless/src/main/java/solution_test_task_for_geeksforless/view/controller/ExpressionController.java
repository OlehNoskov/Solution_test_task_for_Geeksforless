package solution_test_task_for_geeksforless.view.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import solution_test_task_for_geeksforless.persistence.entity.Expression;
import solution_test_task_for_geeksforless.service.ExpressionService;
import solution_test_task_for_geeksforless.util.Searcher;

import java.util.List;

@Controller
@RequestMapping("/expressions")
public class ExpressionController {
    private final ExpressionService expressionService;
    private  Searcher searcher = new Searcher();

    public ExpressionController(ExpressionService expressionService) {
        this.expressionService = expressionService;
    }

    @GetMapping("/all")
    public String findAll(Model model) {
        model.addAttribute("expression", expressionService);
        return "pages/expressions_all";
    }

    @GetMapping("/new")
    public String createExpression(Model model) {
        model.addAttribute("newExpression", new Expression());
        return "pages/expression_new";
    }

    @PostMapping("/new")
    public String createNewExpression(@ModelAttribute("newExpression") Expression expression) {
        expressionService.create(expression);
        return "redirect:/expressions/all";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable Long id, Model model) {
        Expression expression = expressionService.findById(id);
        model.addAttribute("expression", expression);
        return "pages/expression_details";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable Long id, Model model) {
        Expression expression = expressionService.findById(id);
        model.addAttribute("expression", expression);
        return "pages/expression_update";
    }

    @PostMapping("/update/{id}")
    public String updateExpression(@PathVariable Long id) {
        Expression expression = expressionService.findById(id);
        expressionService.update(expression);
        return "redirect:/expressions/all";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        expressionService.delete(id);
        return "redirect:/expressions/all";
    }

    @GetMapping("/search")
    public String search(Model model) {
        model.addAttribute("search", new Searcher());
        return "pages/search_expression";
    }

    @PostMapping("/search")
    public String findAllRedirectSearchUsers(@ModelAttribute("search") Searcher inputResult) {
        searcher.setSearchResult(inputResult.getSearchResult());
        System.out.println(searcher.getSearchResult());
        return "redirect:/expressions/search/all";
    }

    @GetMapping("/search/all")
    public String searchAll(Model model) {
        List<Expression> findAllFromSearch = expressionService.searchExpressionMoreThanNumber(searcher.getParse());
        model.addAttribute("expression", findAllFromSearch);
        return "pages/search_expression_all";
    }
}