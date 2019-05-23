package domain.clima;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Clima {

    private Integer maxima;
    private Integer minima;

    @JsonProperty("id_card")
    private long idCard;

}
