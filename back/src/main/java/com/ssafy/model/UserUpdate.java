package com.ssafy.model;

import lombok.Data;

public @Data
class UserUpdate {
    private String email;
    private String nickname;
    private Boolean alarmFlag;
    private String img;
}
