CREATE TABLE IF NOT EXISTS book (
  Id bigint(20) NOT NULL AUTO_INCREMENT,
  userId(20), NOT NULL
  title varchar(255), NOT NULL
  genre varchar(10), NOT NULL
  price real, NULL
  publisher varchar(255), NOT NULL
  content varchar(300), NOT NULL
  favorite real(5), NULL
  PRIMARY KEY (id),
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS loginUser(
  userId BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  bookId varchar(20), NOT NULL
  email VARCHAR(64) NOT NULL,
  password VARCHAR(128) NOT NULL
  username VARCHAR(64) NOT NULL,
);