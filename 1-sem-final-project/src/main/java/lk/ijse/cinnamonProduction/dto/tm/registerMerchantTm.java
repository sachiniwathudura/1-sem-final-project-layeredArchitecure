package lk.ijse.cinnamonProduction.dto.tm;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class registerMerchantTm {
    private String merchantId;
    private String merchantName;
    private String homeNo;
    private String street;
    private String town;
    private String merchantTeleNo;
    private String merchantCategory;

   /* public registerMerchantTm(String merchantId, String merchantName, String merchantTeleNo, String merchantCategory, String homeNo, String street, String town) {
    }*/
}

