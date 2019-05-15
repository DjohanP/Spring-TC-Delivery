# Spring-TC-Delivery

## Contributors.

1. **Renaldi Wahyudiono (05111540000044)**

2. **Djohan Prabowo     (05111540000067)**

3. **Aditya Dwi Cahyo   (05111540000087)**

---

## End Point

**GET**
| Method | End Point | Parameter | Description |
| :---------: | ------------- |:-------------:| -----|
|GET| /customer | - | Get all active customers. |
|GET| /customer/{id} | - | Get active customers by id. |
|GET| /restaurant/ | - | Get all active restaurants. |
|GET| /restaurant/{id} | - | Get active restaurants by id. |
|GET| /driver/ | - | Get all active drivers. |
|GET| /driver/{id} | - | Get active drivers by id. |
|GET| /user/ | - | Get all active users. |
|GET| /user/{id} | - | Get active users by id. |

---

**POST**
| Method | End Point | Parameter | Description |
| :---------: | ------------- |:-------------:| -----|
|POST| /customer/register | username, name, email, password, phone_number | Register new customer. |
|POST| /restaurant/register | username, name, email, password, phone_number | Register new restaurant. |
|POST| /driver/register | username, name, email, password, phone_number | Register new driver. |
|POST| /admin/register | username, name, email, password, phone_number | Register new admin. |
|POST| /username | username | Check registered user using username. |
|POST| /email | email | Check registered user using email. |
|POST| /token | email, password | Login and get token. |
|POST| /revoke | email, password | Logout and delete token. |

---

**PUT**
| Method | End Point | Parameter | Description |
| :---------: | ------------- |:-------------:| -----|
|PUT| /password | oldpassword, newpassword, confirmpassword | Change password by user. |
|PUT| /password/{id} | newpassword | Change password by admin. |
|PUT| /biodata | username, name, email, phone_number | Change biodata by user. |
|PUT| /biodata/{id} | username, name, email, phone_number | Change biodata by admin. |

---

**DELETE**
| Method | End Point | Parameter | Description |
| :---------: | ------------- |:-------------:| -----|
|DELETE| /user/{id} | - | Delete active user. |