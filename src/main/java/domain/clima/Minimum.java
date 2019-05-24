package domain.clima;

public class Minimum {
    private Integer value;
    //private String unit;

    public void setValue(Integer value) {
        this.value = 5*((value-32)/9);
    }

    public Integer getValue() {
        return value;
    }

   /* public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getUnit() {
        return unit;
    }*/
}
