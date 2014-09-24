DROP TABLE IF EXISTS dataviz_data;
CREATE TABLE dataviz_data (
id_user int NOT NULL,
id_projet int NOT NULL,
date_vote timestamp NOT NULL,
arrondissement int NOT NULL,
age int NOT NULL,
PRIMARY KEY( id_user, id_projet )
);