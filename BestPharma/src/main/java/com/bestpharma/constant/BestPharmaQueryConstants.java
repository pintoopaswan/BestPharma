package com.bestpharma.constant;
/**
 * @author PPaswan
 *
 */
public class BestPharmaQueryConstants {
	
	public final static String CHANGE_STATUS_BY_ID="update User u set u.active= :status,u.updatedBy=:updatedBy,u.updatedOn=:updatedOn where u.id = :id";
	public final static String CHANGE_PASSWORD="update User u set u.password= :newPassword,u.updatedBy=:updatedBy,u.updatedOn=:updatedOn where u.id = :id";

}
