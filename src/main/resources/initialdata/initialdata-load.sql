DROP TABLE IF EXISTS user_account;

CREATE TABLE user_account (
	id IDENTITY PRIMARY KEY,
	username VARCHAR(32) NOT NULL,
    encryptedPassword BINARY(64) NOT NULL,
    salt VARCHAR(32) NOT NULL,
    email VARCHAR(200) NOT NULL,
    lastlogin DATE
);




INSERT INTO user_account(username,encryptedPassword,salt,email) VALUES ('user1', 33333,'Mr','user1@tipico.com');