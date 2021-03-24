package com.mslabs.tangetco.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegistrationValidator {


    private static final String NAME_PATTERN = "^[a-zA-Z0-9]*$";// "^[a-zA-Z0-9]*$";
    private static final String MOBILE_PATTERN = "(0/91)?[6-9][0-9]{9}";
    private Pattern patternName;
    private Matcher matcherName;
    private Pattern patternMobile;
    private Matcher matcherMobile;

    public RegistrationValidator() {
        patternName = Pattern.compile(NAME_PATTERN);
        patternMobile = Pattern.compile(MOBILE_PATTERN);
    }

    public boolean validateName(final String name) {
//        if ("\\s{2,}".matches(name.trim())) {
//            matcherName = patternName.matcher(name);
//            return matcherName.matches();
//        }
        return true;
    }


    public boolean validateMobile(final String mobile) {
        matcherMobile = patternMobile.matcher(mobile);
        return matcherMobile.matches();
    }
}
