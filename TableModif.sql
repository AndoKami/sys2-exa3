/**
it's about changing the basics of psql adding a function that will generate
automaticly a uuid
for taht u'll to create an extension using this
***CREATE EXTENSION IF NOT EXISTS "uuid-ossp";****

*/

-- Supprimez la contrainte de clé primaire existante
ALTER TABLE cli
DROP CONSTRAINT cli_pkey;

-- Ajoutez une nouvelle colonne `id_new` de type `uuid` avec la valeur par défaut uuid_generate_v4()
ALTER TABLE cli
ADD COLUMN id_new UUID DEFAULT uuid_generate_v4();

-- Mettez à jour la nouvelle colonne `id_new` avec les valeurs de l'ancienne colonne `id`
UPDATE cli
SET id_new = id;

-- Supprimez l'ancienne colonne `id`
ALTER TABLE cli
DROP COLUMN id;

-- Renommez la nouvelle colonne `id_new` en `id`
ALTER TABLE cli
RENAME COLUMN id_new TO id;

-- Ajoutez une nouvelle contrainte de clé primaire sur la colonne `id`
ALTER TABLE cli
ADD PRIMARY KEY (id);
