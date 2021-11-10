package com.gorkem.soccercase.constants;

public class Message {
    /*
    GLOBAL MESSAGES
    */
    public static final String PUBLIC_FAIL_RECORD_NOT_FOUND = "Footballer not found for this id : ";
    public static final String PUBLIC_AN_ERROR_OCCURED = "An error occurred.";
    /*
    FOOTBALLER BUSINESS MESSAGES
    */
    public static final String FOOTBALLER_FAIL_RETRIEVE_RECORDS = "There was a problem retrieving records from the footballer database.";
    public static final String FOOTBALLER_FAIL_SAVING_RECORD = "There was a problem saving the recording to the footballer database.";
    public static final String FOOTBALLER_FAIL_UPDATING_RECORD = "There was a problem saving the updated record to the footballer database.";
    public static final String FOOTBALLER_FAIL_DELETING_RECORD = "An error occurred while deleting a record from the footballer database.";
    /*
    TEAM BUSINESS MESSAGES
    */
    public static final String TEAM_FAIL_RETRIEVE_RECORDS = "There was a problem retrieving records from the team database.";
    public static final String TEAM_FAIL_SAVING_RECORD = "There was a problem saving the recording to the team database.";
    public static final String TEAM_FAIL_UPDATING_RECORD= "There was a problem saving the updated record to the team database.";
    public static final String TEAM_FAIL_DELETING_RECORD = "An error occurred while deleting a record from the team database.";
    /*
    FOOTBALLER VALIDATION MESSAGES
    */
    public static final String FOOTBALLER_FIRST_NAME_NOT_BLANK = "First name must not be empty or null.";
    public static final String FOOTBALLER_FIRST_NAME_MIN_SIZE_3 = "First name should not be less than 3.";
    public static final String FOOTBALLER_FIRST_NAME_MAX_SIZE_128 = "First name should not be greater than 128.";
    public static final String FOOTBALLER_LAST_NAME_NOT_BLANK = "Last name must not be empty or null.";
    public static final String FOOTBALLER_LAST_NAME_MIN_SIZE = "Last name should not be less than 3.";
    public static final String FOOTBALLER_LAST_NAME_MAX_SIZE = "Last name should not be greater than 128.";
    public static final String FOOTBALLER_AGE_NOT_EMPTY = "Age must not be empty.";
    public static final String FOOTBALLER_AGE_POSITIVE = "Age should not be negative.";
    public static final String FOOTBALLER_NATIONALITY_NOT_EMPTY = "Nationality must not be empty.";
    public static final String FOOTBALLER_POSITION_NOT_EMPTY = "Position must not be empty.";
    public static final String FOOTBALLER_TEAM_ID_NOT_BLANK = "Team id must not be empty or null.";
    /*
    TEAM VALIDATION MESSAGES
    */
    public static final String TEAM_NAME_NOT_BLANK = "Team name must not be empty or null.";
    public static final String TEAM_NAME_MIN_SIZE_3 = "Team name should not be less than 3.";
    public static final String TEAM_NAME_MAX_SIZE_128 = "Team name should not be greater than 128.";
}
