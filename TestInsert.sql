INSERT INTO cli (id, firstname, connection_datetime)
VALUES (uuid_generate_v4(), 'John Doe', NOW());

INSERT INTO cli (id, firstname, connection_datetime)
VALUES (uuid_generate_v4(), 'Jane Smith', NOW());

-- this will be a test for modifying the base
UPDATE cli
SET firstname = 'ando R'
WHERE id = '6b41e10f-a8f7-48eb-b00e-aae1ae0181ee';

--delete using the uuid
DELETE FROM cli
WHERE id = 'the id of the one that will be deleted';

