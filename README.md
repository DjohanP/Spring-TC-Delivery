# Spring-TC-Delivery

## Contributors.

1. **Renaldi Wahyudiono (05111540000044)**

2. **Djohan Prabowo     (05111540000067)**

3. **Aditya Dwi Cahyo   (05111540000087)**

---

## End Point

**GET**

* Get all active customers.

```
/customer/
```
* Get active customers by id.

```
/customer/{id}
```
* Get all active restaurants.

```
/restaurant/
```
* Get active restaurants by id.

```
/restaurant/{id}
```
* Get all active drivers.

```
/driver/
```
* Get active drivers by id.

```
/driver/{id}
```
* Get all active users.

```
/user/
```
* Get active users by id.

```
/user/{id}
```
------
**POST**

* Register new customer.
>Parameters : username, name, email, password, phone_number
>```
>/customer/register
>```

* Register new restaurant.
> Parameters : username, name, email, password, phone_number
>```
>/restaurant/register
>```

* Register new driver.
> Parameters : username, name, email, password, phone_number
>```
>/driver/register
>```

* Register new admin.
> Parameters : username, name, email, password, phone_number
>```
>/admin/register
>```

* Check registered user using username.
> Parameter : username
>```
>/username
>```

* Check registered user using email.
> Parameter : email
>```
>/email
>```

* Login and get token.
> Parameters : email, password
>```
>/token
>```

* Logout and delete token.
> Parameters : email, password
>```
>/revoke
>```

------

**PUT**

* Change password by user.
>Parameters : oldpassword, newpassword, confirmpassword
>```
>/password
>```

* Change password by admin.
>Parameter : newpassword
>```
>/password/{id}
>```

* Change biodata by user.
>Parameters : username, name, email, phone_number
>```
>/biodata
>```

* Change biodata by admin.
>Parameters : username, name, email, phone_number
>```
>/biodata/{id}
>```

------

**DELETE**

* Delete active user.

```
/user/{id}
```