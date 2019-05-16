# Spring-TC-Delivery

## Contributors.

1. **Renaldi Wahyudiono (05111540000044)**

2. **Djohan Prabowo     (05111540000067)**

3. **Aditya Dwicahyo   (05111540000087)**

---

## End Point

**USER**
| Method | End Point | Parameter | Description |
| :--------- | :------------- |:-------------| -----|
|GET| /user | - | Get all active users. |
|GET| /user/{id} | - | Get active users by id. |
|POST| /token | email, password | Login and get token. |
|POST| /revoke | email, password | Logout and delete token. |
|POST| /username | username | Check registered user using username. |
|POST| /email | email | Check registered user using email. |
|PUT| /password | oldpassword, newpassword, confirmpassword | Change password by user. |
|PUT| /biodata | username, name, email, phone_number | Change biodata by user. |

---

**CUSTOMER**
| Method | End Point | Parameter | Description |
| :--------- | ------------- |:-------------| -----|
|GET| /customer/ | - | Get all active customers. |
|GET| /customer/{id} | - | Get active customers by id. |
|POST| /customer | username, name, email, password, phone_number | Register new customer. |

---

**RESTAURANT**
| Method | End Point | Parameter | Description |
| :--------- | ------------- |:-------------| -----|
|GET| /restaurant/ | - | Get all active restaurants. |
|GET| /restaurant/{id} | - | Get active restaurants by id. |
|POST| /restaurant | username, name, email, password, phone_number | Register new restaurant. |

---

**DRIVER**
| Method | End Point | Parameter | Description |
| :--------- | ------------- |:-------------| -----|
|GET| /driver/ | - | Get all active drivers. |
|GET| /driver/{id} | - | Get active drivers by id. |
|POST| /driver | username, name, email, password, phone_number | Register new driver. |

---

**ADMIN**
| Method | End Point | Parameter | Description |
| :--------- | ------------- |:-------------| -----|
|POST| /admin | username, name, email, password, phone_number | Register new admin. |
|PUT| /biodata/{id} | username, name, email, phone_number | Change biodata by admin. |
|PUT| /password/{id} | newpassword | Change password by admin. |
|DELETE| /user/{id} | - | Delete active user. |
