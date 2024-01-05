package lk.ijse.cinnamonProduction.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

    @NoArgsConstructor
    @AllArgsConstructor
    @Data

    public class stockDto {
        private String  itemId;
        private String itemName;
        private double itemQty;
        private String itemCategory;


}
