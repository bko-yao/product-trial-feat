-- Create table category (V1.0.0)
CREATE TABLE category
(
    id              INT8 PRIMARY KEY,
    audit_create_by VARCHAR(100),
    audit_create_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    audit_update_by VARCHAR(100),
    audit_update_at TIMESTAMP WITHOUT TIME ZONE,
    version         INTEGER DEFAULT 0,
    code            VARCHAR(30),
    designation     VARCHAR(255)
);

-- Créer la séquence
DROP SEQUENCE IF EXISTS category_seq;
CREATE SEQUENCE category_seq START WITH 1 INCREMENT BY 1;
ALTER TABLE category
    ALTER COLUMN id SET DEFAULT nextval('category_seq');

-- Créer les index
CREATE INDEX c_code_idx ON category (code);