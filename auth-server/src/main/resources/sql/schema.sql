CREATE TABLE t_user (
  username VARCHAR(45) NULL,
  password TEXT NULL,
  PRIMARY KEY (username)
);

CREATE TABLE t_otp (
  username VARCHAR(45) NOT NULL,
  code VARCHAR(45) NULL,
  PRIMARY KEY (username)
);
