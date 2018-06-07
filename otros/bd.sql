/*
	DROP TABLE hv_estudios;
	DROP TABLE hv_personas;

*/
CREATE TABLE hv_personas (
	id bigserial NOT NULL,
	numero_identificacion character varying(255),
	nombre character varying(255),
	lugar_nacimiento character varying(255),
	facultad character varying(255),
	programa character varying(255),
	fecha_inicio DATE,
	fecha_fin DATE,
	anios integer,
	escalafon character varying(255) NOT NULL,
	tipo_vinculacion character varying(255) NOT NULL,
	creado boolean default false,
	fusionado boolean default false,
	CONSTRAINT hv_personas_pkey PRIMARY KEY (id)
)
WITH (
	OIDS=FALSE
);

CREATE TABLE hv_estudios (
	id bigserial NOT NULL,
	universidad character varying(255),
	titulo character varying(255),
	anio integer,
	id_persona bigint NOT NULL,
	CONSTRAINT hv_estudios_pkey PRIMARY KEY (id),
	CONSTRAINT hv_estudios_id_persona_fkey FOREIGN KEY (id_persona)
		REFERENCES hv_personas (id) MATCH SIMPLE
		ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
	OIDS=FALSE
);

/*
 insert into hv_personas(numero_identificacion,nombre, lugar_nacimiento, facultad, programa, fecha_inicio, fecha_fin, anios, escalafon,tipo_vinculacion) values ('1085258548','CHRISTIAN SEGUNDO ORTEGA', 'PASTO', 'FACULTAD DE INGENIERÍA', 'INGENIERÍA DE SISTEMAS', '2013-02-13', '2018-12-31', 5, 'nivel 100','TIEMPO COMPLETO');
  INSERT INTO hv_estudios(universidad, titulo, anio, id_persona) values
  ('Universidad de nariño', 'Tecnólogo en computación', '2008', 1),
  ('Universidad abierta y a distancia', 'Ingeniero de sistemas', '2016', 1),
  ('Universidad de caldas', 'Magister en ingeniería computacional', '2019', 1);
*/