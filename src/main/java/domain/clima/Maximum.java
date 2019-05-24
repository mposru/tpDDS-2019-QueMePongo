package domain.clima;

public class Maximum {
    private Integer value;
    //private String unit;

    public void setValue(Integer value) {
        //this.value = 5*((value-32)/9);
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    /*public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getUnit() {
        return unit;
    }*/
}
