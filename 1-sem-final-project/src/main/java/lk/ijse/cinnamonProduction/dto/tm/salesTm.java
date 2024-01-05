package lk.ijse.cinnamonProduction.dto.tm;

import java.util.Date;

import lombok.*;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class salesTm {

    private String salesNo;
    private Date date;

    public salesTm(String salesNo, String date) {
    }
}
