package org.claimsystem.g24fp.domain;

import java.time.LocalDateTime;

public class Log {
    private String logID;
    private String userName;
    private String actionType;
    private String alterTable;
    private String alterColumn;
    private String entityID;
    private String oldValue; // Assuming JSONB can be represented as String in Java
    private String newValue; // Assuming JSONB can be represented as String in Java
    private LocalDateTime actionTime;

    // Constructor, getters and setters
    public Log(String logID, String userName, String actionType, String alterTable, String alterColumn, String entityID, String oldValue, String newValue, LocalDateTime actionTime) {
        this.logID = logID;
        this.userName = userName;
        this.actionType = actionType;
        this.alterTable = alterTable;
        this.alterColumn = alterColumn;
        this.entityID = entityID;
        this.oldValue = oldValue;
        this.newValue = newValue;
        this.actionTime = actionTime;
    }

    public String getLogID() {
        return logID;
    }

    public void setLogID(String logID) {
        this.logID = logID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public String getAlterTable() {
        return alterTable;
    }

    public void setAlterTable(String alterTable) {
        this.alterTable = alterTable;
    }

    public String getAlterColumn() {
        return alterColumn;
    }

    public void setAlterColumn(String alterColumn) {
        this.alterColumn = alterColumn;
    }

    public String getEntityID() {
        return entityID;
    }

    public void setEntityID(String entityID) {
        this.entityID = entityID;
    }

    public String getOldValue() {
        return oldValue;
    }

    public void setOldValue(String oldValue) {
        this.oldValue = oldValue;
    }

    public String getNewValue() {
        return newValue;
    }

    public void setNewValue(String newValue) {
        this.newValue = newValue;
    }

    public LocalDateTime getActionTime() {
        return actionTime;
    }

    public void setActionTime(LocalDateTime actionTime) {
        this.actionTime = actionTime;
    }
}
