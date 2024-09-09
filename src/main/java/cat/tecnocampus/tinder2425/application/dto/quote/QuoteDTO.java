package cat.tecnocampus.tinder2425.application.dto.quote;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class QuoteDTO {

    private String type;
    private ValueDTO valueDTO;

    public QuoteDTO() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ValueDTO getValue() {
        return valueDTO;
    }

    public void setValue(ValueDTO valueDTO) {
        this.valueDTO = valueDTO;
    }

    @Override
    public String toString() {
        return "Quote{" +
                "type='" + type + '\'' +
                ", value=" + valueDTO +
                '}';
    }
}