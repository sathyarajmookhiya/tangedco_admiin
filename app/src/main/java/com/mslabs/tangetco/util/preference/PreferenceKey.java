package com.mslabs.tangetco.util.preference;

/**
 * These unique keys are used to store value into shared preferences
 */
public interface PreferenceKey {

    String LOGGED_IN_USER_TOKEN_ID = "TOKEN_ID";
    String LOGGED_IN_USER_NUMBER = "USER_NUMBER";
    String LOGGED_IN_USER_ID = "USER_ID";
    String LOGGED_IN_PIN_PAD = "PIN_PAD";
    String IS_USER_AUTHENTICATED = "IS_USER_AUTHENTICATED";
    String DEVICE_SERIAL_NUMBER = "DEVICE_SERIAL_NUMBER";
    String LOGGED_IN_USER_PASSWORD = "LOGGED_IN_USER_PASSWORD";
    String HOME_NETWORK_SSID = "HOME_NETWORK_SSID";
    String INTRO_VISITED_FIRST_TIME = "INTRO_VISITED_FIRST_TIME";
    String GROUP_ID = "GROUP_ID";
    String DASHBOARD_ID = "DASHBOARD_ID";
    String COMPLAINT_ID = "COMPLAINT_ID";
    String LOGIN_DATA = "_LOGIN_DATA";
    String IMAGE_BASE_URL = "https://muthusoftlabs.com/sipcot/portal/webroot/uploads/road_cleaned_files/";
   // String IMAGE_BASE_URL = "https://sipcot.tn.gov.in/portal/uploads/road_cleaned_files/";
}
