package com.project.datetime_utility_starter.utils.enums;

import lombok.Getter;

@Getter
public enum Timezones {

    PST("America/Los_Angeles"),
    EST("America/New_York"),
    CST("America/Chicago"),
    MST("America/Denver"),
    GMT("GMT"),
    UTC("UTC"),
    IST("Asia/Kolkata"),
    CET("Europe/Paris"),
    EET("Europe/Istanbul"),
    JST("Asia/Tokyo");

    private final String zoneId;
    Timezones(String zoneId){
        this.zoneId=zoneId;
    }

}
