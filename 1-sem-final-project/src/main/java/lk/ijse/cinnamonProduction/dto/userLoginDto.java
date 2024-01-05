package lk.ijse.cinnamonProduction.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class userLoginDto {
    private String userId;
    private String userName;
    private String userTeleNo;
    private String userEmail;
    private String password;


    public userLoginDto(String userId, String userName, String userEmail, String password) {
    }
}
