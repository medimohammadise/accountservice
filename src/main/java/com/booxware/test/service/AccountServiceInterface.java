package com.booxware.test.service;

import com.booxware.test.domain.Account;
import com.booxware.test.exception.AccountServiceException;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * Service for account management.
 */
public interface AccountServiceInterface {

	/**
	 * Logs in the user, if the username exists and the password is correct.
	 * Updates the last login date
	 * 
	 * @param username
	 *            the User's name
	 * @param password
	 *            the clear text password
	 * @return the logged in account
	 * 
	 * @throws AccountServiceException
	 *             if any errors occur
	 */
	public Account login(String username, String password) throws AccountServiceException;

	/**
	 * Registers a new Account, if the username doesn't exist yet and logs in
	 * the user.
	 * 
	 * @param username
	 *            the User's name
	 * @param email
	 *            the email address of the user
	 * @param password
	 *            the clear text password
	 * @return the newly registered Account
	 * 
	 * @throws AccountServiceException
	 *             if any errors occur
	 */
	public Account register(String username, String email, String password) throws AccountServiceException;

	/**
	 * Deletes an Account, if the user exist.
	 * 
	 * @param username
	 *            the User's name
	 * 
	 * @throws AccountServiceException
	 *             if any errors occur
	 */
	public void deleteAccount(String username) throws AccountServiceException;

	/**
	 * Checks if a user has logged in since a provided timestamp.
	 * 
	 * @param date
	 * @return true if the user has logged in since the provided timestamp, else
	 *         false.
	 * @throws AccountServiceException
	 *             if any error occurs
	 */
	public boolean hasLoggedInSince(Timestamp date) throws AccountServiceException;

	public List<Account> getAllUserAccounts() throws AccountServiceException;
	public Account checkUserExists(String userName);
}
