package solution_test_task_for_geeksforless.view.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import solution_test_task_for_geeksforless.persistence.entity.Expression;
import solution_test_task_for_geeksforless.service.ExpressionService;
import solution_test_task_for_geeksforless.view.dto.request.SearcherRequestDto;
import solution_test_task_for_geeksforless.view.dto.request.ExpressionRequestDto;
import solution_test_task_for_geeksforless.view.dto.response.ExpressionResponseDto;

import java.util.List;

@Controller
@RequestMapping("/expressions")
public class ExpressionController {
    private final ExpressionService expressionService;
    private final SearcherRequestDto searcher = new SearcherRequestDto();

    public ExpressionController(ExpressionService expressionService) {
        this.expressionService = expressionService;
    }

    @GetMapping("/all")
    public String findAll(Model model) {
        model.addAttribute("expression", expressionService);
        return "pages/expression/expressions_all";
    }

    @GetMapping("/new")
    public String createExpression(Model model) {
        model.addAttribute("newExpression", new Expression());
        return "pages/expression/expression_new";
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
        return "pages/expression/expression_details";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable Long id, Model model) {
        ExpressionResponseDto expressionResponseDto = expressionService.getResponseExpressionById(id);
        model.addAttribute("expression", expressionResponseDto);
        return "pages/expression/expression_update";
    }

    @PostMapping("/update/{id}")
    public String updateExpression(@PathVariable Long id, @ModelAttribute("expression") ExpressionRequestDto expressionRequestDto) {
        expressionService.update(expressionRequestDto, id);
        return "redirect:/expressions/all";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        expressionService.delete(id);
        return "redirect:/expressions/all";
    }

    @GetMapping("/search")
    public String search(Model model) {
        model.addAttribute("search", new SearcherRequestDto());
        return "pages/search/search_expression";
    }

    @PostMapping("/search/equal")
    public String searchAllWithEqualResults(@ModelAttribute("search") SearcherRequestDto inputResult) {
        searcher.setSearchResult(inputResult.getSearchResult());
        return "redirect:/expressions/search/all/equal";
    }

    @PostMapping("/search/more")
    public String searchAllWithMoreResults(@ModelAttribute("search") SearcherRequestDto inputResult) {
        searcher.setSearchResult(inputResult.getSearchResult());
        return "redirect:/expressions/search/all/more";
    }

    @PostMapping("/search/less")
    public String searchAllWithLessResults(@ModelAttribute("search") SearcherRequestDto inputResult) {
        searcher.setSearchResult(inputResult.getSearchResult());
        return "redirect:/expressions/search/all/less";
    }

    @GetMapping("/search/all/equal")
    public String findAllExpressionsWithEqualResults(Model model) {
        List<Expression> findAllFromSearch = expressionService.findAllExpressionWithResultEqual(Double.parseDouble(searcher.getSearchResult()));
        model.addAttribute("expression", findAllFromSearch);
        return "pages/search/search_expression_all";
    }

    @GetMapping("/search/all/more")
    public String findAllExpressionsWithMoreResults(Model model) {
        List<Expression> findAllFromSearch = expressionService.findAllExpressionWithResultMore(Double.parseDouble(searcher.getSearchResult()));
        model.addAttribute("expression", findAllFromSearch);
        return "pages/search/search_expression_all";
    }

    @GetMapping("/search/all/less")
    public String findAllExpressionsWithLessResults(Model model) {
        List<Expression> findAllFromSearch = expressionService.findAllExpressionWithResultLess(Double.parseDouble(searcher.getSearchResult()));
        model.addAttribute("expression", findAllFromSearch);
        return "pages/search/search_expression_all";
    }
}