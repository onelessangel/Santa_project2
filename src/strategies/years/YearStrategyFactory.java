package strategies.years;

import enums.Strategy;

public final class YearStrategyFactory {
    private YearStrategyFactory() {
        // constructor for checkstyle
    }

    public static YearStrategy createStrategy(final Strategy strategy) {
        switch (strategy) {
            case ID:              return new IdYearStrategy();
            case NICE_SCORE:      return new NiceScoreYearStrategy();
            case NICE_SCORE_CITY: return new NiceScoreCityYearStrategy();
            default:              throw  new IllegalArgumentException("The strategy is not valid.");
        }
    }
}
