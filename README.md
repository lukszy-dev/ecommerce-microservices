# ecommerce-microservices
🛍 E-commerce solution based on Microservice architecture using Spring Boot and Spring Cloud. 

## Services
- API Gateway
- Consul (service registry and config server)
- Authentication
- Product (catalog)
- User
- Order (cart support for authenticated users)

![Diagram](https://user-images.githubusercontent.com/5923943/156840078-baa65610-f4d6-43c5-ab97-a23cfa5028a4.png)

## Development

### Docker

```bash!

$ cd ecommerce-microservices
$ mvn clean install
$ docker-compose up -d
```

### Maven

Example for **Gateway** service:
```bash
$ cd ecommerce-microservices/gateway
$ mvn spring-boot:run
```

### Postman collection
[![Run in Postman](https://run.pstmn.io/button.svg)](https://app.getpostman.com/run-collection/86806c080fe384e60058?action=collection%2Fimport#?env%5Bmicroservices%5D=W3sia2V5IjoiYXV0aGVudGljYXRpb25fdG9rZW4iLCJ2YWx1ZSI6IiIsImVuYWJsZWQiOnRydWUsInNlc3Npb25WYWx1ZSI6IkJlYXJlci4uLiIsInNlc3Npb25JbmRleCI6MH0seyJrZXkiOiJob3N0IiwidmFsdWUiOiJsb2NhbGhvc3QiLCJlbmFibGVkIjp0cnVlLCJzZXNzaW9uVmFsdWUiOiJsb2NhbGhvc3QiLCJzZXNzaW9uSW5kZXgiOjF9XQ==)

## Todo

- [ ] Complete checkout process functionality
- [ ] Integration with Payment Provider


## Licensing

This code is licensed under the [MIT license](LICENSE.md). Check out the LICENSE file for more information.
