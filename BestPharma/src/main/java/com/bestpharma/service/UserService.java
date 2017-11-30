package com.bestpharma.service;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

import com.bestpharma.exception.BestPharmaException;
import com.bestpharma.model.User;


/**
 * @author PPaswan
 *
 */
public interface UserService {
	public User authenticateUser(String username,String password) throws BestPharmaException,NoSuchAlgorithmException, InvalidKeySpecException;
	public long registerUser(User user) throws BestPharmaException;
	public long updateUser(User user) throws BestPharmaException;
	public List<User> getAllUsers() throws BestPharmaException;
	public User getUserById(Long id) throws BestPharmaException;
	public void activateUser(long id,String updatedBy) throws BestPharmaException;
	public void deactivateUser(long id,String updatedBy) throws BestPharmaException;
	public void changePassword(long id,String oldPassword,String newPassword,String updatedBy) throws BestPharmaException;

}
