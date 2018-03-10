package com.booxware.test.domain;


import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;


/**
 * The encryption can be very simple, we don't put much emphasis on the
 * encryption algorithm.
 */
@Entity
@Table(name = "user_account")
public class Account implements Serializable {
	public Account(){

	}
	public Account(String username,String encryptedPassword,String salt,String email,Date lastLogin){
		this.id=id;
		this.username=username;
		this.encryptedPassword=encryptedPassword;
		this.salt=salt;
		this.email=email;
		this.lastLogin=lastLogin;
	}
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private long id;

	private String username;

	private String encryptedPassword;

	private String salt;

	private String email;


	//@Column(columnDefinition = "DATE")
	private Date lastLogin;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEncryptedPassword() {
		return encryptedPassword;
	}

	public void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "userName: "+this.username+" email: "+this.email+" loginDataTime: "+this.lastLogin;
	}
}
