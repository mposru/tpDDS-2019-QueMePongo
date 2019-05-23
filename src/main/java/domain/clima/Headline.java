package domain.clima;

public class Headline {
    private Integer effectiveEpochDate; //Timestamp comienzo
    //private Integer severity;
    //private String category;
    private Integer endEpochDate; //Timestamp final

    public Integer getEffectiveEpochDate() {
        return effectiveEpochDate;
    }

    public void setEffectiveEpochDate(Integer effectiveEpochDate) {
        this.effectiveEpochDate = effectiveEpochDate;
    }

    public Integer getEndEpochDate() {
        return endEpochDate;
    }

    public void setEndEpochDate(Integer endEpochDate) {
        this.endEpochDate = endEpochDate;
    }
}
