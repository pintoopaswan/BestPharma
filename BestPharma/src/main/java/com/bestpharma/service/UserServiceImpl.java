/**
 * 
 */
package com.bestpharma.service;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.bestpharma.constant.BestPharnaErrorCodes;
import com.bestpharma.exception.BestPharmaException;
import com.bestpharma.model.User;
import com.bestpharma.repository.UserRepository;

/**
 * @author PPaswan
 *
 */
@Service
public class UserServiceImpl implements UserService {
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public User authenticateUser(String username, String password)
			throws BestPharmaException, NoSuchAlgorithmException, InvalidKeySpecException {
		User user = null;
		try {
			user = userRepo.findByUsername(username);
			if (null == user) {
				throw new BestPharmaException(BestPharnaErrorCodes.INVALID_CODE, BestPharnaErrorCodes.INVALID_USER_MSG);
			} else if (null == userRepo.findByUsernameAndActiveTrue(username)) {
				throw new BestPharmaException(BestPharnaErrorCodes.INACTIVE_CODE,
						BestPharnaErrorCodes.INACTIVE_USER_MSG);
			} else {
				if (!bCryptPasswordEncoder.matches(password, user.getPassword())) {
					throw new BestPharmaException(BestPharnaErrorCodes.INVALID_CODE,
							BestPharnaErrorCodes.INVALID_PASSWORD_MSG);
				}

			}
		} catch (BestPharmaException e) {
			throw e;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new BestPharmaException(BestPharnaErrorCodes.INVALID_CODE, "Incorrect username or password");
		}
		return user;
	}

	@Override
	public long registerUser(User user) throws BestPharmaException {
		String encryptedPassword = null;
		try {
			encryptedPassword = bCryptPasswordEncoder.encode(user.getPassword());
			user.setPassword(encryptedPassword);
			user = userRepo.saveAndFlush(user);
		} catch (DataIntegrityViolationException ex) {
			logger.error(ex.getMessage());
			throw new BestPharmaException(BestPharnaErrorCodes.DUPLICATE_CODE, BestPharnaErrorCodes.DUPLICATE_USER_MSG);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new BestPharmaException(BestPharnaErrorCodes.REGISTER_ERROR_CODE,
					BestPharnaErrorCodes.REGISTER_USER_ERROR_MSG);
		}

		return user.getId();
	}

	@Override
	public List<User> getAllUsers() throws BestPharmaException {
		List<User> userList = new ArrayList<>();
		try {
			userRepo.findAll().forEach(userList::add);
			if (userList.isEmpty() || userList.size() == 0) {
				throw new BestPharmaException(BestPharnaErrorCodes.NOT_FOUND_CODE,
						BestPharnaErrorCodes.USER_LIST_EMPTY_MSG);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new BestPharmaException(BestPharnaErrorCodes.NOT_FOUND_CODE,
					BestPharnaErrorCodes.ERROR_FETCHING_USER_LIST);
		}
		return userList;
	}

	@Override
	public User getUserById(Long id) throws BestPharmaException {
		User user = null;
		try {
			user = userRepo.findOne(id);
			if (null == user) {
				throw new BestPharmaException(BestPharnaErrorCodes.NOT_FOUND_CODE,
						BestPharnaErrorCodes.USER_LIST_EMPTY_MSG);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new BestPharmaException(BestPharnaErrorCodes.NOT_FOUND_CODE,
					BestPharnaErrorCodes.USER_LIST_EMPTY_MSG);
		}
		return user;
	}

	@Transactional
	@Override
	public long updateUser(User user) throws BestPharmaException {
		String encryptedPassword = null;
		try {
			encryptedPassword = bCryptPasswordEncoder.encode(user.getPassword());
			user.setPassword(encryptedPassword);
			user.setUpdatedOn(java.sql.Timestamp.valueOf(java.time.LocalDateTime.now()));
			user = userRepo.saveAndFlush(user);
		} catch (DataIntegrityViolationException ex) {
			logger.error(ex.getMessage());
			throw new BestPharmaException(BestPharnaErrorCodes.DUPLICATE_CODE, BestPharnaErrorCodes.DUPLICATE_USER_MSG);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new BestPharmaException(BestPharnaErrorCodes.UPDATE_ERROR_CODE,
					BestPharnaErrorCodes.UPDATE_USER_ERROR_MSG);
		}

		return user.getId();
	}

	@Transactional
	@Override
	public void activateUser(long id, String updatedBy) throws BestPharmaException {
		String status = "Y";
		try {
			userRepo.changeStatusById(id, status, updatedBy, java.sql.Timestamp.valueOf(java.time.LocalDateTime.now()));
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new BestPharmaException(BestPharnaErrorCodes.UPDATE_ERROR_CODE,
					BestPharnaErrorCodes.ACTIVATE_USER_ERROR_MSG);
		}
	}

	@Transactional
	@Override
	public void deactivateUser(long id, String updatedBy) throws BestPharmaException {
		String status = "N";
		try {
			userRepo.changeStatusById(id, status, updatedBy, java.sql.Timestamp.valueOf(java.time.LocalDateTime.now()));
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new BestPharmaException(BestPharnaErrorCodes.UPDATE_ERROR_CODE,
					BestPharnaErrorCodes.DEACTIVATE_USER_ERROR_MSG);
		}
	}

	@Transactional
	@Override
	public void changePassword(long id, String oldPassword, String newPassword, String updatedBy)
			throws BestPharmaException {
		String encryptedNewPassword = null;
		User user = null;
		try {
			user = userRepo.findOne(id);
			if (bCryptPasswordEncoder.matches(oldPassword, user.getPassword())) {
				encryptedNewPassword = bCryptPasswordEncoder.encode(newPassword);
				userRepo.changePassword(id, encryptedNewPassword, updatedBy,
						java.sql.Timestamp.valueOf(java.time.LocalDateTime.now()));

			} else {
				throw new BestPharmaException(BestPharnaErrorCodes.INVALID_CODE,
						BestPharnaErrorCodes.INVALID_OLD_PASSWORD_MSG);
			}
		} catch (BestPharmaException e) {
			throw e;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new BestPharmaException(BestPharnaErrorCodes.UPDATE_ERROR_CODE,
					BestPharnaErrorCodes.CHANGE_PASSWORD_ERROR_MSG);
		}

	}

}
