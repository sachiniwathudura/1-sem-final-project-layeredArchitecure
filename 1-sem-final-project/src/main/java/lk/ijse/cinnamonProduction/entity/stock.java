package lk.ijse.cinnamonProduction.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class stock {
    private String  itemId;
    private String itemName;
    private double itemQty;
    private String itemCategory;


}
