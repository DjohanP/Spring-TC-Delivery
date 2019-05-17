# Spring-TC-Delivery

## Contributors.

1. **Renaldi Wahyudiono (05111540000044)**

2. **Djohan Prabowo     (05111540000067)**

3. **Aditya Dwicahyo   (05111540000087)**

---

## End Point

**User**
---

| Method | End Point | Parameter | Privilege | Description |
| ------------- | ------------- | ------------- | ------------- | ------------- |
|GET| /user/{id} | - | User | Get active user by id. |
|POST| /token | email, password | - |Login and get token. |
|POST| /revoke | - | User |Logout and delete token. |
|POST| /username | username | User |Check registered user using username. |
|POST| /email | email | User |Check registered user using email. |
|PUT| /password | oldpassword, newpassword, confirmpassword | User |Change password by user. |
|PUT| /biodata | username, name, email, phone_number | User |Change biodata by user. |

**CUSTOMER**
---

| Method | End Point | Parameter | Privilege | Description |
| ------------- | ------------- | ------------- | ------------- | ------------- |
|GET| /customer/ | - | User |Get all active customers. |
|GET| /customer/{id} | - | User |Get active customer by id. |
|POST| /customer | username, name, email, password, phone_number | - |Register new customer. |

**RESTAURANT**
---

| Method | End Point | Parameter | Privilege | Description |
| ------------- | ------------- | ------------- | ------------- | ------------- |
|GET| /restaurant/ | - | User | Get all active restaurants. |
|GET| /restaurant/{id} | - | User | Get active restaurant by id. |
|POST| /restaurant | username, name, email, password, phone_number | Admin |Register new restaurant. |

**DRIVER**
---

| Method | End Point | Parameter | Privilege | Description |
| ------------- | ------------- | ------------- | ------------- | ------------- |
|GET| /driver/ | - | User | Get all active drivers. |
|GET| /driver/{id} | - | User | Get active driver by id. |
|POST| /driver | username, name, email, password, phone_number | Admin |Register new driver. |

**ADMIN**
---
| Method | End Point | Parameter | Privilege |Description |
| ------------- | ------------- | ------------- | ------------- | ------------- |
|GET| /user | - | Admin |Get all active and deleted users. |
|POST| /admin | username, name, email, password, phone_number | Admin | Register new admin. |
|PUT| /biodata/{id} | username, name, email, phone_number | Admin |Change biodata by admin. |
|PUT| /password/{id} | newpassword | Admin | Change password by admin. |
|DELETE| /user/{id} | - | Admin | Delete active user. |
