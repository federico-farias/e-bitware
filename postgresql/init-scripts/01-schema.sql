create table cajeros(
	codigo int generated always as identity primary key not null,
	nom_apels varchar(255)
);

create table productos(
	codigo int generated always as identity primary key not null,
	nombre varchar(100) not null,
	precio int not null
);

create table maquinas_registradoras(
	codigo int generated always as identity primary key not null,
	piso int not null
);

create table ventas(
	cajero int not null,
	maquina int not null,
	producto int not null,
	primary key (cajero, maquina, producto),
	foreign key (cajero) references cajeros(codigo) on delete cascade,
	foreign key (maquina) references maquinas_registradoras(codigo) on delete cascade,
	foreign key (producto) references productos(codigo) on delete cascade
);


