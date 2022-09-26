package com.InstiCab.models;

public class Driver {
    private Long driverId;
    private String licenseNumber;
    private String aadharNumber;
    private String accountNo;
    private String accountName;
    private String ifscCode;
    private String bankName;
    private User user;

    public Long getDriverId() {
        return driverId;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public String getAadharNumber() {
        return aadharNumber;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public String getAccountName() {
        return accountName;
    }

    public String getIfscCode() {
        return ifscCode;
    }

    public String getBankName() {
        return bankName;
    }

    public User getUser() {
        return user;
    }

    public void setDriverId(Long driverId) {
        this.driverId = driverId;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public void setAadharNumber(String aadharNumber) {
        this.aadharNumber = aadharNumber;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public void setIfscCode(String ifscCode) {
        this.ifscCode = ifscCode;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
