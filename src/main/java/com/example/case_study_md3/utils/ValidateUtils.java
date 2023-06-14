package com.example.case_study_md3.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateUtils {
    private static String REGEX;
    public static boolean isProductNameValid(String productName){
        REGEX = "^[a-zA-Z\\s]{7,25}$";
        Pattern pattern = Pattern.compile(REGEX);
        Matcher matcher = pattern.matcher(productName);
        return matcher.matches();
    }
    public static boolean isDescriptionValid(String description){
        REGEX = "^[a-zA-Z][a-zA-Z0-9\\s.,!?-]{8,245}$";
        return Pattern.matches(REGEX,description);
    }

    public static boolean isAddressValid(String address){
        REGEX = "^[a-zA-Z0-9\\s.,]{3,245}$";
        return Pattern.matches(REGEX,address);
    }
    public static boolean isDOBValid(String dob){
        REGEX = "^(19|20)\\d{2}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01])$";
        return Pattern.matches(REGEX,dob);
    }
    public static boolean passwordValidate(String password){
        REGEX = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$";
        Pattern pattern = Pattern.compile(REGEX);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
}
