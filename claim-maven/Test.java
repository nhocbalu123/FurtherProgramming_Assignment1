package org.claimsystem.g24fp.logic;

import org.claimsystem.g24fp.domain.user.User;

import java.sql.Connection;

public class Test {
    public static void main(String[] args) {
        // Initialize the DAOs

        // ----- CREATE TRIGGER
        // the user login to the system
        String userName = "c1234567"; // policy holder 1234567
        String passWord = "12345";

        // assume that the user can login successfully
        // then the user can perform some actions

        // createTrigger(conn, table, actionby=userName)
        // the user can perform some actions to the table. the action is recorded in the log table
        // for instance: the user can create a new claim
        // 1. TriggerHandle.executeTrigger(conn, "claim", userName);
        // 2. claimDB.add(conn, claimInfo);
        // Note: all the information for create and update are store in map. delele is store in string

        // only the admin can view the log table

    }
}
