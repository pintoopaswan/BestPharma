package com.bestpharma.controller;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/login")
public class LoginController {
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	UserService userService;

	@RequestMapping(method = RequestMethod.POST, value = "/authenticate")
	public ResponseEntity<BigPharmaResponse> authenticateUser(@RequestBody User user, HttpServletResponse res) {
		logger.info("######## Authenticate User ########");
		BigPharmaResponse response = new BigPharmaResponse();
		try {
			User loggedUser = userService.authenticateUser(user.getUsername(), user.getPassword());
			response.setMessage("User autentication sucessful");
			response.setStatus(Status.SUCCESS);
			response.setData(loggedUser);
			logger.info("User autentication sucessful");
		} catch (BestPharmaException ex) {
			response.setStatus(Status.ERROR);
			response.setMessage(ex.getMessage());
			logger.error(ex.getMessage());

		} catch (Exception e) {
			response.setStatus(Status.ERROR);
			response.setMessage("Incorrect username or password");
			logger.error(e.getMessage());
		}
		return new ResponseEntity<BigPharmaResponse>(response, HttpStatus.OK);
	}

}
