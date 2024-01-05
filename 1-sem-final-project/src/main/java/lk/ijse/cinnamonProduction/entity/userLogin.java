package lk.ijse.cinnamonProduction.entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class userLogin {
    private String userId;
    private String userName;
    private String userTeleNo;
    private String userEmail;
    private String password;


    public userLogin(String userId, String userName, String userEmail, String password) {
    }
}
