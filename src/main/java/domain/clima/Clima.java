package domain.clima;

import java.util.List;


public class Clima {
    private List<DailyForecasts> dailyForecasts;

    public List<DailyForecasts> getDailyForecasts() {
        return dailyForecasts;
    }

    public void setDailyForecasts(List<DailyForecasts> dailyForecasts) {
        this.dailyForecasts = dailyForecasts;
    }
}
