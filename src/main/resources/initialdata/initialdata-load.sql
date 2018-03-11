DROP TABLE IF EXISTS user_account;

CREATE TABLE user_account (
	id IDENTITY PRIMARY KEY,
	username VARCHAR(32) NOT NULL,
    encryptedPassword VARCHAR(100) NOT NULL,
    salt VARCHAR(32) NOT NULL,
    email VARCHAR(200) NOT NULL,
    lastlogin timestamp
);




INSERT INTO user_account(username,encryptedPassword,salt,email) VALUES ('user1', '12dbb457eaf690a147a398fc8ba4cf82','Mr','user1@tipico.com');