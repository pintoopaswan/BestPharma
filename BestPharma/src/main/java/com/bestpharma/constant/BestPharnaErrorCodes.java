package com.bestpharma.constant;
/**
 * @author PPaswan
 *
 */
public class BestPharnaErrorCodes {
	public final static int REGISTER_ERROR_CODE=1001;
	public final static String REGISTER_USER_ERROR_MSG="Error in registering user";
	
	public final static int UPDATE_ERROR_CODE=1002;
	public final static String UPDATE_USER_ERROR_MSG="Error in updating user";
	public final static String ACTIVATE_USER_ERROR_MSG="Error in activating user";
	public final static String DEACTIVATE_USER_ERROR_MSG="Error in deactivating user";
	public final static String CHANGE_PASSWORD_ERROR_MSG="Error in changing password";
	
	public final static int DUPLICATE_CODE=1003;
	public final static String DUPLICATE_USER_MSG="User already exist with the same username";
	
	public final static int INVALID_CODE=1004;
	public final static String INVALID_USER_MSG="Incorrect username";
	public final static String INVALID_PASSWORD_MSG="Incorrect password";
	public final static String INVALID_OLD_PASSWORD_MSG="Incorrect old password";
	
	public final static int NOT_FOUND_CODE=1005;
	public final static String ERROR_FETCHING_USER_LIST="Error in fetching user list";
	public final static String USER_LIST_EMPTY_MSG="No user found";
	
	public final static int EMPTY_FIELD_CODE=1006;
	
	public final static int DATABASE_ERROR_CODE=1007; 
	
	public final static int INACTIVE_CODE=1008; 
	public final static String INACTIVE_USER_MSG="User is inactive, please contact system administrator.";

}
