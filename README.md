# UserManagement Service
This is a hands-on practice for simple user management service that cover following functions :
RegisterUser(userName,email,password,salt)
Login(userName,password)
isLoggedInSince(DateTime)
DeleteUser

For the backend an embedded H2 Database is used.
There is one user pre-loaded on the initialization *init.sql* script:

username | password | email
---|---|---
user1 | user1 | user1@tipico.com

## How to run it
To build the project just execute maven `mvn clean package` commands from the project root, with this you'll get a generated war file on target folder.

If you want to run the project in an embedded Tomcat instance execute `mvn tomcat7:run` command also from the project root.
## RESTful API

The API provides the following interfaces

### Login
**POST api/account/login**
Request:
```
Headers:
Accept: application/json
Content-Type: application/json
Body: { "username":"user1", "password":"user1" }
```
Login success response:
```
Code: 200
Body: {
    "id": 2,
    "username": "user2",
    "encryptedPassword": "a77824e25601573f8046a28443b70e58",
    "salt": "Mr",
    "email": "user2@tipico.com",
    "lastLogin": "2018-03-11"
}
```
Login incorrect response:
```
Code: 500
Body: AccountServiceException: username or password is not valid
```

**GET api/account/listAll**
Request:
```
Headers:
Accept: application/json
Content-Type: application/json
```
sample success response:
```
Code: 200
Body: [
    {
        "id": 1,
        "username": "user1",
        "encryptedPassword": "12dbb457eaf690a147a398fc8ba4cf82",
        "salt": "Mr",
        "email": "user1@tipico.com",
        "lastLogin": "2018-03-11"
    },
    {
        "id": 2,
        "username": "user2",
        "encryptedPassword": "a77824e25601573f8046a28443b70e58",
        "salt": "Mr",
        "email": "user2@tipico.com",
        "lastLogin": "2018-03-11"
    }
]
```

**GET api/account/register**
Request:
```
Headers:
Accept: application/json
Content-Type: application/json
{
	"username":"user2",
	"password":"user2",
	"email":"user2@tipico.com"

}

```
sample success response:
```
Code: 200
Body: {
    "id": 2,
    "username": "user2",
    "encryptedPassword": "a77824e25601573f8046a28443b70e58",
    "salt": "Mr",
    "email": "user2@tipico.com",
    "lastLogin": null
}
```

**DELETE api/account/delete/user1**
Request:
```
Headers:
Accept: application/json
Content-Type: application/json


```
sample success response:
```
Code: 200
false  //shows user does not exist any more
```
sample unsucsessfull response:
```
Code: 200
true: shows user is still there
```

**GET api/account/loggedInAfter**
Request:
```
Headers:
Accept: application/json
Content-Type: application/json


```
sample success response:
```
Code: 200
true  //shows user logged in after this date
```
sample unsucsessfull response:
```
Code: 200
false: s//shows user has not logged in after this date
```

## How to test it

easiest way to test by using  **postman**  [default url is **http://localhost:8080**]
you can dowanload and import sample post man collection from here https://raw.githubusercontent.com/medimohammadise/accountservice/master/UserManagement.postman_collection.json

