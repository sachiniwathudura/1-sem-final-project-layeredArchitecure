package lk.ijse.cinnamonProduction.viewTDM;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class salesTm {

    private String salesNo;
    private Date date;

    public salesTm(String salesNo, String date) {
    }
}
