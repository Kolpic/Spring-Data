package com.softuni.gamestore.constants;

public enum Validations {
    ;
    public static final String EMAIL_PATTERN = "^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-]+)(\\.[a-zA-Z]{2,5}){1,2}$";
    public static final String EMAIL_NO_VALID_MESSAGE = "Incorrect email.";
    public static final String PASSWORD_VALIDATOR = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{6,}$";
    public static final String PASSWORD_NO_VALID_MESSAGE = "Incorrect username / password";
    public static final String PASSWORD_MISS_MATCH_MESSAGE = "Passwords are not matching";
    public static final String COMMAND_NOT_FOUND_MESSAGE = "Command not found";

}
