CREATE TABLE public.ports (
	id bigserial NOT NULL,
	"name" varchar(255) NOT NULL,
	capacity int4 NOT NULL,
	CONSTRAINT port_pk PRIMARY KEY (id)
);

CREATE TABLE public.ships (
	id bigserial NOT NULL,
	"name" varchar(255) NOT NULL,
	port_id int8 NULL,
	status varchar(255) NOT NULL,
	CONSTRAINT ship_pk PRIMARY KEY (id),
	CONSTRAINT ship_fk_port FOREIGN KEY (port_id) REFERENCES ports(id)
);

INSERT INTO public.ports ("name",capacity) VALUES
	 ('Дальний',5),
	 ('Солнечный',10),
	 ('Восточный',7);

INSERT INTO public.ships ("name",port_id,status) VALUES
	 ('Проворный',1,'PORT'),
	 ('Санта Мария',1,'PORT'),
	 ('Туман',1,'PORT'),
	 ('Буря',1,'PORT'),
	 ('Сказочный',1,'PORT'),
	 ('Грозный',2,'PORT'),
	 ('Восток',2,'PORT'),
	 ('Кудесник',3,'PORT'),
	 ('Арктика',NULL,'SEA'),
	 ('Москва',3,'PORT');