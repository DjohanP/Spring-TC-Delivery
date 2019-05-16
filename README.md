# Spring-TC-Delivery

## Contributors.

1. **Renaldi Wahyudiono (05111540000044)**

2. **Djohan Prabowo     (05111540000067)**

3. **Aditya Dwicahyo   (05111540000087)**

---

## End Point

**USER**
| Method | End Point | Parameter | Description |
| ------------- | ------------- | ------------- | ------------- |
|GET| /user | - | Get all active users. |
|GET| /user/{id} | - | Get active users by id. |
|POST| /token | email, password | Login and get token. |
|POST| /revoke | email, password | Logout and delete token. |
|POST| /username | username | Check registered user using username. |
|POST| /email | email | Check registered user using email. |
|PUT| /password | oldpassword, newpassword, confirmpassword | Change password by user. |
|PUT| /biodata | username, name, email, phone_number | Change biodata by user. |
