CREATE TABLE hello_messages (
  id SERIAL PRIMARY KEY UNIQUE,
  message varchar(255) NOT NULL,
  sender varchar(100)
)