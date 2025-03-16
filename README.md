# Spring-Security-App.

# Spring Security Project with OAuth 2.0 (Planned)

Overview

This project is a Spring Security-based authentication and authorization system, designed as a client-server architecture. It includes essential security features like user authentication, authorization, and password reset. OAuth 2.0 integration is planned for future development.

## Features Implemented

User Registration & Authentication (Spring Security)

Password Reset Functionality

Client-Server Communication

Token-Based Authentication (JWT or Session-Based)

Planned Features

OAuth 2.0 Implementation

Role-Based Access Control (RBAC)

## Technologies Used

Java 11

Spring Boot

Spring Security

OAuth 2.0 (Planned)

JWT (If Implemented)

MySQL (Database)

H2 Database (For Testing)


## API Reference

#### Register User
```http
  POST /register
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `user`    | `string` | Register a new user and asign a token   |

#### Resend Token
```http
  POST /resendVerifyToken?token={token}
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `token`    | `string` | Resends the same token to user via email at console leve    |

#### Verify Token
```http
  POST /verifyRegistration?token={token}
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `token`    | `string` | Verify the token from databse    |

#### Reset password
```http
  POST /resetPassword
```
| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `email`    | `string` | reset the user password if enabled    |

#### Reset password
```http
  POST /savePassword?token={token}
```
| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `token`    | `string` | save the new token password   |





## Clone the repository:


git clone https://github.com/yashc0912/Spring-Security-App.git


Contribution & Future Development

Contributions are welcome! Future updates will include OAuth 2.0 authentication and additional security features.

