package shop.Currency;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Currency {
    private String name;
    private Double course;
    private CurrencyCode code;
    private Double multiplicity;
}
