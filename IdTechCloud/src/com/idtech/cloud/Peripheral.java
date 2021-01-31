package com.idtech.cloud;

public class Peripheral {
    private String peripheralID;
    private String type;
    private String name;
    private String ipAddress;
    private String operatingSystem;
    private String directoryPath;
    private int userID;

    public Peripheral() {

    }

    public Peripheral(String peripheralID, String type, String name, String ipAddress, String operatingSystem, String directoryPath, int userID) {
        this.setPeripheralID (peripheralID);
        this.setType (type);
        this.setName (name);
        this.setIpAddress (ipAddress);
        this.setOperatingSystem (operatingSystem);
        this.setDirectoryPath (directoryPath);
        this.setUserID (userID);
    }

    public String getPeripheralID() {
        return peripheralID;
    }

    public void setPeripheralID(String peripheralID) {
        this.peripheralID = peripheralID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type ) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name ) {
        this.name = name;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress ) {
        this.ipAddress = ipAddress;
    }

    public String getOperatingSystem() {
        return operatingSystem;
    }

    public void setOperatingSystem(String operatingSystem ) {
        this.operatingSystem = operatingSystem;
    }

    public String getDirectoryPath() {
        return directoryPath;
    }

    public void setDirectoryPath(String directoryPath) {
        this.directoryPath = directoryPath;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int customerID ) {
        this.userID = customerID;
    }
}
