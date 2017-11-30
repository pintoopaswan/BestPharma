package com.bestpharma.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bestpharma.exception.BestPharmaException;
import com.bestpharma.model.BigPharmaResponse;
import com.bestpharma.model.BigPharmaResponse.Status;
import com.bestpharma.model.User;
import com.bestpharma.service.UserService;


/**
 * @author PPaswan
 *
 */
@RestController
@RequestMapping("/user")
public class UserController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	UserService userService;
	
	@RequestMapping(method = RequestMethod.POST, value = "/register")
	public ResponseEntity<BigPharmaResponse> registerUser(@RequestBody User user, HttpServletResponse res){
		logger.info("######## Register User ########");
		BigPharmaResponse response=new BigPharmaResponse();
		try {
			long id=userService.registerUser(user);
			response.setMessage("User registered sucessfully");
			response.setStatus(Status.SUCCESS);
			response.setData(id);
			logger.info("User registered sucessfully");
		} catch(BestPharmaException ex){
			response.setStatus(Status.ERROR);
			response.setMessage(ex.getMessage());
			logger.error(ex.getMessage());
			
		}catch (Exception e) {
			response.setStatus(Status.ERROR);
			response.setMessage("User registration failed.");
			logger.error(e.getMessage());
		}
		
		return new ResponseEntity<BigPharmaResponse>(response, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/update")
	public ResponseEntity<BigPharmaResponse> updateUser(@RequestBody User user, HttpServletResponse res){
		logger.info("######## Update User ########");
		BigPharmaResponse response=new BigPharmaResponse();
		try {
			long id=userService.updateUser(user);
			response.setMessage("User updated sucessfully");
			response.setStatus(Status.SUCCESS);
			response.setData(id);
			logger.info("User updated sucessfully");
		} catch(BestPharmaException ex){
			response.setStatus(Status.ERROR);
			response.setMessage(ex.getMessage());
			logger.error(ex.getMessage());
			
		}catch (Exception e) {
			response.setStatus(Status.ERROR);
			response.setMessage("User updated failed.");
			logger.error(e.getMessage());
		}
		
		return new ResponseEntity<BigPharmaResponse>(response, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/activate")
	public ResponseEntity<BigPharmaResponse> activateUser(@RequestBody Map<String, String> user){
		logger.info("######## Activate User ########");
		BigPharmaResponse response=new BigPharmaResponse();
		try {
			userService.activateUser(Long.parseLong(user.get("id")), user.get("updatedBy"));
			response.setMessage("User activated sucessfully");
			response.setStatus(Status.SUCCESS);
			logger.info("User activated sucessfully");
		} catch(BestPharmaException ex){
			response.setStatus(Status.ERROR);
			response.setMessage(ex.getMessage());
			logger.error(ex.getMessage());
			
		}catch (Exception e) {
			response.setStatus(Status.ERROR);
			response.setMessage("User activation failed");
			logger.error(e.getMessage());
		}
		
		return new ResponseEntity<BigPharmaResponse>(response, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/deactivate")
	public ResponseEntity<BigPharmaResponse> deactivateUser(@RequestBody Map<String, String> user){
		logger.info("######## Deactivate User ########");
		BigPharmaResponse response=new BigPharmaResponse();
		try {
			userService.deactivateUser(Long.parseLong(user.get("id")), user.get("updatedBy"));
			response.setMessage("User deactivated sucessfully");
			response.setStatus(Status.SUCCESS);
			logger.info("User deactivated sucessfully");
		} catch(BestPharmaException ex){
			response.setStatus(Status.ERROR);
			response.setMessage(ex.getMessage());
			logger.error(ex.getMessage());
			
		}catch (Exception e) {
			response.setStatus(Status.ERROR);
			response.setMessage("User deactivation failed");
			logger.error(e.getMessage());
		}
		
		return new ResponseEntity<BigPharmaResponse>(response, HttpStatus.OK);
	}
	
	@RequestMapping("/list")
	public ResponseEntity<BigPharmaResponse> getAllUser(){
		logger.info("######## User List ########");
		BigPharmaResponse response=new BigPharmaResponse();
		List<User> userList=null;
		try {
			userList=userService.getAllUsers();
			response.setData(userList);
			response.setMessage("User list fetched successfully");
			response.setStatus(Status.SUCCESS);
			logger.info("User registered sucessfully");
		} catch(BestPharmaException ex){
			response.setStatus(Status.ERROR);
			response.setMessage("User list fetched successfully");
			logger.error("Exception in fetching user list: " + ex.getMessage());
			
		}catch (Exception e) {
			response.setStatus(Status.ERROR);
			response.setMessage("Error in fetching user list");
			logger.error(e.getMessage());
		}
		return new ResponseEntity<BigPharmaResponse>(response, HttpStatus.OK);
	}
	
	@RequestMapping("/{id}")
	public ResponseEntity<BigPharmaResponse> getUserById(@PathVariable Long id){
		logger.info("######## Get User By ID ########");
		BigPharmaResponse response=new BigPharmaResponse();
		User user=null;
		try {
			user=userService.getUserById(id);
			response.setData(user);
			response.setMessage("User details fetched successfully");
			response.setStatus(Status.SUCCESS);
			logger.info("User details fetched successfully");
		} catch(BestPharmaException ex){
			response.setStatus(Status.ERROR);
			response.setMessage(ex.getMessage());
			logger.error(ex.getMessage());
			
		}catch (Exception e) {
			response.setStatus(Status.ERROR);
			response.setMessage("Error in fetching user details");
			logger.error(e.getMessage());
		}
		return new ResponseEntity<BigPharmaResponse>(response, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/change-password")
	public ResponseEntity<BigPharmaResponse> changePassword(@RequestBody Map<String, String> user){
		logger.info("######## Change Password ########");
		BigPharmaResponse response=new BigPharmaResponse();
		try {
			userService.changePassword(Long.parseLong(user.get("id")),user.get("oldPassword"),user.get("newPassword"), user.get("updatedBy"));
			response.setMessage("Password changed sucessfully");
			response.setStatus(Status.SUCCESS);
			logger.info("Password changed sucessfully");
		} catch(BestPharmaException ex){
			response.setStatus(Status.ERROR);
			response.setMessage(ex.getMessage());
			logger.error(ex.getMessage());
			
		}catch (Exception e) {
			response.setStatus(Status.ERROR);
			response.setMessage("Password changed failed");
			logger.error(e.getMessage());
		}
		
		return new ResponseEntity<BigPharmaResponse>(response, HttpStatus.OK);
	}

}
