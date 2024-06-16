package com.xtl.automation.enums;

public enum AppPackageEnum {
    PDD_CUSTOMER("pddCustomer", "拼多多顾客版"),
    PDD_BUSINESS("pddBusiness", "拼多多商家版");

    private String code;
    private String name;

    AppPackageEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
