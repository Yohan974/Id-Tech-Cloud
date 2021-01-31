package com.idtech.cloud;

public class Backup {
	private String backupID;
    private String peripheralID;
    private String backupDate;
    private int totalSize;

    public Backup(){

    }

    public Backup(String backupID, String peripheralID, String backupDate, int totalSize) {
        this.backupID = backupID;
        this.peripheralID = peripheralID;
        this.backupDate = backupDate;
        this.totalSize = totalSize;
    }

    public String getBackupID() {
        return backupID;
    }

    public void setBackupID(String backupID) {
        this.backupID = backupID;
    }

    public String getPeripheralID() {
        return peripheralID;
    }

    public void setPeripheralID(String peripheralID) {
        this.peripheralID = peripheralID;
    }

    public String getBackupDate() {
        return backupDate;
    }

    public void setBackupDate(String backupDate) {
        this.backupDate = backupDate;
    }

    public int getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(int totalSize) {
        this.totalSize = totalSize;
    }
}
