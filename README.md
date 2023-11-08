## Simple-Spring-Backend
This is a simple rest server for 
Using: `Spring Boot`, `Spring Security`, `Spring Data JPA`.

Frontend for the application is here [simple-angular-frontend](https://github.com/scimbosh/simple-angular-frontend).

[Springdoc](http://localhost:8080/swagger-ui/index.html)

[Actuator](http://localhost:8080/actuator)

### Docker
[Docker repository](https://hub.docker.com/repository/docker/scimbosh/simple_spring_backend/general)

Run the application in docker: 

```bash
docker pull scimbosh/simple_spring_backend:simple_spring_backend-1.0.0
docker run -p 8080:8080 --name simple-spring-backend --pull missing scimbosh/simple_spring_backend:simple_spring_backend-1.0.0
```

### Versions:
- JDK 17.0.8
- Kotlin 1.8 (1.8.22)
- Gradle 8.2.1


Table of users:
```sql
CREATE TABLE IF NOT EXISTS public.users
(
    id bigint PRIMARY KEY  NOT NULL DEFAULT nextval('users_seq'::regclass),
    username character varying(100) COLLATE pg_catalog."default" NOT null UNIQUE,
    password character varying(100) COLLATE pg_catalog."default" NOT NULL,
	roles character varying[] COLLATE pg_catalog."default" NOT NULL
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.users
    OWNER to postgres;
```

Table of todo:
```sql
CREATE TABLE IF NOT EXISTS public.todos
(
    id integer PRIMARY KEY NOT NULL DEFAULT nextval('todos_seq'::regclass),
	userid bigint NOT NULL,
    username character varying(100),
    content character varying(1000) COLLATE pg_catalog."default",
    checked boolean
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.todos
    OWNER to postgres;
```