



create table producto (
                         id SERIAL,
                         nombre varchar(255),
                         precio decimal(10,0),
                         descripcion varchar(255),
                         marca varchar(255),
                         categoria varchar(255),
                         constraint producto_pk primary key (id)
);

create table categoria (
                          id SERIAL,
                          descripcion varchar(255),
                          constraint categoria_pk primary key (id)
);