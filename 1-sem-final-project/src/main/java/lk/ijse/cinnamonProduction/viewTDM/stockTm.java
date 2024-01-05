package lk.ijse.cinnamonProduction.viewTDM;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class stockTm
{
    private String  itemId;
    private String itemName;
    private double itemQty;
    private String itemCategory;

}
