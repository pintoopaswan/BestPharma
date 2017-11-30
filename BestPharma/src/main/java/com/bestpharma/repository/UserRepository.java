/**
 * 
 */
package com.bestpharma.repository;

import java.sql.Timestamp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bestpharma.constant.BestPharmaQueryConstants;
import com.bestpharma.model.User;

/**
 * @author PPaswan
 *
 */

public interface UserRepository extends JpaRepository<User, Long> {

	User findByUsername(String username);

	User findByUsernameAndActiveTrue(String username);

	@Modifying(clearAutomatically = true)
	@Query(BestPharmaQueryConstants.CHANGE_STATUS_BY_ID)
	void changeStatusById(@Param("id") long id, @Param("status") String status, @Param("updatedBy") String updatedBy,
			@Param("updatedOn") Timestamp updatedOn);

	@Modifying(clearAutomatically = true)
	@Query(BestPharmaQueryConstants.CHANGE_PASSWORD)
	void changePassword(@Param("id") long id, @Param("newPassword") String newPassword,
			@Param("updatedBy") String updatedBy, @Param("updatedOn") Timestamp updatedOn);

}
