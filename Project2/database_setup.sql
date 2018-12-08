CREATE TABLE products (
  sku            INTEGER      NOT NULL,
  item_count     INTEGER,
  threshold      INTEGER      NOT NULL,
  reorder_amount INTEGER,
  title          VARCHAR(20)  NOT NULL,
  description    varchar(250) NOT NULL,
  cost           DOUBLE       NOT NULL,
  category       VARCHAR(20)
);

CREATE TABLE categories (
  name        VARCHAR(20),
  description VARCHAR(250),
  FOREIGN KEY (name) REFERENCES products (category)
    ON UPDATE CASCADE
    ON DELETE RESTRICT
);