package com.booxware.test.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

/**
 * The encryption can be very simple, we don't put much emphasis on the
 * encryption algorithm.
 */
@Entity
@Table(name = "user_account")
public class Account implements Serializable {
	public Account(){

	}
	public Account(String username,byte[] encryptedPassword,String salt,String email,LocalDate lastLogin){
		this.id=id;
		this.username=username;
		this.encryptedPassword=encryptedPassword;
		this.salt=salt;
		this.email=email;
		this.lastLogin=lastLogin;
	}
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int id;

	private String username;

	private byte[] encryptedPassword;

	private String salt;

	private String email;

	private LocalDate lastLogin;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public byte[] getEncryptedPassword() {
		return encryptedPassword;
	}

	public void setEncryptedPassword(byte[] encryptedPassword) {
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

	public LocalDate getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(LocalDate lastLogin) {
		this.lastLogin = lastLogin;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
