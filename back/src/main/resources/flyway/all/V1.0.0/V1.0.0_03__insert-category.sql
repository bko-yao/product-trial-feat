INSERT INTO category
(id, audit_create_by, audit_create_at, audit_update_by, audit_update_at, "version", code, designation)
VALUES (nextval('category_seq'::regclass), 'bkoffi', now(), NULL, NULL, 0, 'ACCESSORIES', 'Accessories');

INSERT INTO category
(id, audit_create_by, audit_create_at, audit_update_by, audit_update_at, "version", code, designation)
VALUES (nextval('category_seq'::regclass), 'bkoffi', now(), NULL, NULL, 0, 'CLOTHING', 'Clothing');

INSERT INTO category
(id, audit_create_by, audit_create_at, audit_update_by, audit_update_at, "version", code, designation)
VALUES (nextval('category_seq'::regclass), 'bkoffi', now(), NULL, NULL, 0, 'ELECTRONICS', 'Electronics');

INSERT INTO public.category
(id, audit_create_by, audit_create_at, audit_update_by, audit_update_at, "version", code, designation)
VALUES (nextval('category_seq'::regclass), 'bkoffi', now(), NULL, NULL, 0, 'FITNESS', 'Fitness');