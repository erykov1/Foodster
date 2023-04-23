# Foodster

## Create resources
In src/main/resources add
- folder certs
- application.properties

## Certs
here you should create 
- public.pem - public key 2048 bit
- private.pem - private key 2048 bit

## application.properties
example config : 
- spring.datasource.url=
- spring.datasource.username=
- spring.datasource.password=
- spring.jpa.hibernate.ddl-auto=create-drop
- spring.jpa.show-sql=true
- spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
- spring.jpa.properties.hibernate.format_sql=true

- rsa.private-key=classpath:certs/private.pem
- rsa.public-key=classpath:certs/public.pem

# Mail service
To start Project you should update class JavaMailSenderBean by setting your gmail username and password.
