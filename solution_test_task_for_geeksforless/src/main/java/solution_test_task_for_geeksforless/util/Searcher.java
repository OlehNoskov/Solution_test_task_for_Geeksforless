package solution_test_task_for_geeksforless.util;

import lombok.Getter;
import lombok.Setter;

public class Searcher {
    @Getter
    @Setter
    private String searchResult;

    public double getParse() {
        return Double.parseDouble(searchResult);
    }
}