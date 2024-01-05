package lk.ijse.cinnamonProduction.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data

public class registerMerchant {
private String merchantId;
private String merchantName;
private String homeNo;
private String street;
private String town;
private String merchantTeleNo;
private String merchantCategory;

}
