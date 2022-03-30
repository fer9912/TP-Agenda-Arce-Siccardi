CREATE DATABASE `agenda`;
USE agenda;

CREATE TABLE `personas`
(
  `idPersona` int(11) NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(45) NOT NULL,
  `Telefono` varchar(20) NOT NULL,
   `Email` VARCHAR(25) NULL,
   `Fecha_Cumpleaños` DATE NULL,
   `Domicilio` int NULL,
   `Musica_Preferida` VARCHAR(25) NULL,
   `Medio_de_transporte_Preferido` VARCHAR(25) NULL,
CREATE DATABASE `agenda`
USE agenda;

CREATE TABLE `personas`
(
  `idPersona` int(11) NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(45) NOT NULL,
  `Telefono` varchar(20) NOT NULL,
   `Email` VARCHAR(25) NULL,
   `Fecha_Cumpleaños` DATE NULL,
   `Domicilio` int NULL,
   `Musica_Preferida` VARCHAR(25) NULL,
   `Medio_de_transporte_Preferido` VARCHAR(25) NULL,
   `Tipo_Contacto` int(11) NULL,
  PRIMARY KEY (`idPersona`)
);

CREATE TABLE `domicilio`
(
  `idDomicilio` int(11) NOT NULL AUTO_INCREMENT,
  `Calle` varchar(45) NOT NULL,
  `Altura` varchar(20) NOT NULL,
  `Piso` varchar(45)  NULL,
  `Tipo_Domicilio` varchar(20) NULL,
  `Localidad` int(11) NULL,  
  PRIMARY KEY (`idDomicilio`)
);

ALTER TABLE personas ADD CONSTRAINT fk_domicilio FOREIGN KEY (domicilio) REFERENCES domicilio(idDomicilio);

CREATE TABLE `localidad`
(
  `idLocalidad` int(11) NOT NULL AUTO_INCREMENT,
  `Localidad` varchar(20) NOT NULL,
  `Provincia` varchar(20) NULL,
  `Pais` varchar(20)  NULL,
  PRIMARY KEY (`idLocalidad`)
);

ALTER TABLE domicilio ADD CONSTRAINT fk_localidad FOREIGN KEY (Localidad) REFERENCES localidad(idLocalidad);


CREATE TABLE `tipo_contacto`
(
  `idTipo_Contacto` int(11) NOT NULL AUTO_INCREMENT,
  `Tipo_Contacto` varchar(20) NOT null,
  PRIMARY KEY (`idTipo_Contacto`)
);

ALTER TABLE personas ADD CONSTRAINT fk_tipo FOREIGN KEY (Tipo_Contacto) REFERENCES tipo_contacto(idTipo_Contacto);

