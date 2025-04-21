package edu.westga.cs3211.hyre_defyer_project.server;

/**
 * The constant strings for the server communication
 * 
 * @author Alec Neal and Kate Anglin
 * @version Spring 2025
 */

public class Constants {

    public static final String IP_ADDRESS = "127.0.0.1";
    public static final int PORT = 4225;

    public static final String REQ_TYPE = "request_type";
    public static final String REQ_USERNAME = "username";
    public static final String REQ_PASSWORD = "password";
    public static final String REQ_BIO = "bio";
    public static final String REQ_SKILLS = "skills";
    public static final String REQ_CATEGORIES = "categories";
    public static final String REQ_SENDER = "sender";
    public static final String REQ_RECEIVER = "receiver";
    public static final String REQ_TEXT = "text";
    public static final String REQ_SEND_MESSAGE = "send message";
    public static final String REQ_CREATE_ACCOUNT = "create account";
    public static final String REQ_LOGIN = "login";
    public static final String REQ_GET_MESSAGES = "get messages";
    public static final String REQ_GET_MESSAGEABLE_USERS = "get messageable users";
    public static final String REQ_ADD_MESSAGEABLE_USER = "add messageable user";
    public static final String REQ_DELETE_CHAT = "delete chat";
    public static final String REQ_GET_FREELANCERS = "get freelancers";
    public static final String REQ_ADD_FREELANCER = "add freelancer";
    public static final String REQ_REMOVE_FREELANCER = "remove freelancer";
    public static final String REQ_GET_CATEGORIES = "get categories";
    public static final String REQ_DELETE_USER_FROM_SERVER = "delete user from server";
    public static final String REQ_SET_USER_BIO = "set user bio";
    public static final String REQ_RATE_FREELANCER = "rate freelancer";
    public static final String REQ_RATING = "rating";

    public static final String REP_SUCCESS = "success";
    public static final String REP_FAIL = "fail";
    public static final String REP_ERROR_DESCRIPTION = "error description";
    public static final String REP_MESSAGES = "messages";
    public static final String REP_USERS = "users";
    public static final String REP_FREELANCERS = "freelancers";
    public static final String REP_CATEGORIES = "categories";
    public static final String REP_RATING = "rating";

    public static final String SUCCESS_CODE = "success code";
}