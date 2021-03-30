package shop.Products;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class Product implements Serializable {

    private Integer id;
    private String name;
    private ProductGroup productGroup;
    private BigDecimal price;

}
