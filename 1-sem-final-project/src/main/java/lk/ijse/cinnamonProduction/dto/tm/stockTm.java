package lk.ijse.cinnamonProduction.dto.tm;

import lombok.*;

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
