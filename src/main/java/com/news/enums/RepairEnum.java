package com.news.enums;

/**
 * @author Jun
 * @date 2023/04/08 11:16
 * @Description:
 */

public enum RepairEnum {

//    电脑
    COMPUTER("电脑"),

    HYDROPOWER("水电"),

    DOORS_AND_WINDOWS("门窗"),

    OTHER("其他");

    String value;

    RepairEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
