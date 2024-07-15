-- Create table product (V1.0.0)
CREATE TABLE product
(
    id               INT8 PRIMARY KEY,
    audit_create_by  VARCHAR(100),
    audit_create_at  TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    audit_update_by  VARCHAR(100),
    audit_update_at  TIMESTAMP WITHOUT TIME ZONE,
    version          INTEGER DEFAULT 0,
    code             VARCHAR(255) NOT NULL,
    name             VARCHAR(255) NOT NULL,
    description      VARCHAR(255) NOT NULL,
    price            INT4         NOT NULL,
    quantity         INT4         NOT NULL,
    inventory_status VARCHAR(255) NOT NULL,
    image            VARCHAR(255),
    rating           INT4,
    category_id      INT8         NOT NULL,
    CONSTRAINT p_category_id FOREIGN KEY (category_id) REFERENCES category (id)
);

-- Créer la séquence
DROP SEQUENCE IF EXISTS product_seq;
CREATE SEQUENCE product_seq START WITH 1 INCREMENT BY 1;
ALTER TABLE product
    ALTER COLUMN id SET DEFAULT nextval('product_seq');

-- Créer les index
CREATE INDEX p_category_id_idx on product (category_id);