CREATE TABLE users (
  id SERIAL PRIMARY KEY UNIQUE,
  email varchar(255) UNIQUE NOT NULL,
  secret varchar(100) NOT NULL
)