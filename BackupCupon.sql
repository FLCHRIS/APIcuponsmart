CREATE DATABASE  IF NOT EXISTS `smartcupon`;
USE `smartcupon`;

-- 
-- TABLA CATEGORIA
-- 

DROP TABLE IF EXISTS `categoria`;

CREATE TABLE `categoria` (
  `idCategoria` int NOT NULL,
  `categoria` varchar(20) NOT NULL,
  PRIMARY KEY (`idCategoria`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `categoria` VALUES 
  (1,'Aerolíneas'),
  (2,'Cafeterías'),
  (3,'Farmacias'),
  (4,'Supermercados'),
  (5,'Tiendas de Ropa'),
  (6,'Electrónicos'),
  (7,'Librerías'),
  (8,'Gimnasios'),
  (9,'Restaurantes');

-- 
-- TABLA CLIENTE
-- 

DROP TABLE IF EXISTS `cliente`;

CREATE TABLE `cliente` (
  `idCliente` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(20) NOT NULL,
  `apellidoPaterno` varchar(20) NOT NULL,
  `apellidoMaterno` varchar(20) NOT NULL,
  `telefono` varchar(10) NOT NULL,
  `correo` varchar(40) NOT NULL,
  `calle` varchar(20) NOT NULL,
  `numero` int NOT NULL,
  `contrasenia` varchar(20) NOT NULL,
  `fechaNacimiento` date NOT NULL,
  PRIMARY KEY (`idCliente`),
  UNIQUE KEY `correo` (`correo`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 
-- TABLA EMPRESA
-- 

DROP TABLE IF EXISTS `empresa`;

CREATE TABLE `empresa` (
  `idEmpresa` int NOT NULL AUTO_INCREMENT,
  `idUbicacion` int DEFAULT NULL,
  `nombre` varchar(20) NOT NULL,
  `nombreComercial` varchar(40) NOT NULL,
  `logo` longblob,
  `email` varchar(40) NOT NULL,
  `telefono` varchar(10) NOT NULL,
  `paginaWeb` varchar(40) DEFAULT NULL,
  `RFC` varchar(12) NOT NULL,
  `estatus` enum('activo','inactivo') DEFAULT 'activo',
  `nombreRepresentante` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`idEmpresa`),
  UNIQUE KEY `nombre` (`nombre`),
  UNIQUE KEY `nombreComercial` (`nombreComercial`),
  KEY `idUbicacion` (`idUbicacion`),
  CONSTRAINT `empresa_ibfk_1` FOREIGN KEY (`idUbicacion`) REFERENCES `ubicacion` (`idUbicacion`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 
-- TABLA PROMOCION
-- 

DROP TABLE IF EXISTS `promocion`;

CREATE TABLE `promocion` (
  `idPromocion` int NOT NULL AUTO_INCREMENT,
  `idCategoria` int DEFAULT NULL,
  `nombre` varchar(20) NOT NULL,
  `descripcion` text NOT NULL,
  `fotografia` longblob,
  `fechaInicio` date NOT NULL,
  `fechaFin` date NOT NULL,
  `restriccion` text,
  `tipoPromocion` enum('descuento','rebajado') NOT NULL,
  `porcentajeDescuento` float DEFAULT NULL,
  `precioRebajado` float DEFAULT NULL,
  `noCuponesMaximo` int NOT NULL,
  `codigo` varchar(8) NOT NULL,
  `estatus` enum('activo','inactivo') DEFAULT 'activo',
  PRIMARY KEY (`idPromocion`),
  UNIQUE KEY `codigo_UNIQUE` (`codigo`),
  KEY `idCategoria` (`idCategoria`),
  CONSTRAINT `promocion_ibfk_1` FOREIGN KEY (`idCategoria`) REFERENCES `categoria` (`idCategoria`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 
-- TABLA PROMOCION-SUCURSAL
-- 

DROP TABLE IF EXISTS `promocionsucursal`;

CREATE TABLE `promocionsucursal` (
  `idPromocion` int NOT NULL,
  `idSucursal` int NOT NULL,
  PRIMARY KEY (`idPromocion`,`idSucursal`),
  KEY `idSucursal` (`idSucursal`),
  CONSTRAINT `promocionsucursal_ibfk_1` FOREIGN KEY (`idPromocion`) REFERENCES `promocion` (`idPromocion`),
  CONSTRAINT `promocionsucursal_ibfk_2` FOREIGN KEY (`idSucursal`) REFERENCES `sucursal` (`idSucursal`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 
-- TABLA ROLLUSUARIO
-- 

DROP TABLE IF EXISTS `rollusuario`;

CREATE TABLE `rollusuario` (
  `idRollUsuario` int NOT NULL AUTO_INCREMENT,
  `roll` varchar(9) NOT NULL,
  PRIMARY KEY (`idRollUsuario`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `rollusuario` VALUES 
  (1,'comercial'),
  (2,'general');

-- 
-- TABLA SUCURSAL
-- 

DROP TABLE IF EXISTS `sucursal`;

CREATE TABLE `sucursal` (
  `idSucursal` int NOT NULL AUTO_INCREMENT,
  `idEmpresa` int DEFAULT NULL,
  `idUbicacion` int DEFAULT NULL,
  `nombre` varchar(40) NOT NULL,
  `colonia` varchar(40) NOT NULL,
  `telefono` varchar(10) NOT NULL,
  `latitud` float NOT NULL,
  `longitud` float NOT NULL,
  `nombreEncargado` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`idSucursal`),
  KEY `sucursal_ibfk_1` (`idEmpresa`),
  KEY `sucursal_ibfk_2` (`idUbicacion`),
  CONSTRAINT `sucursal_ibfk_1` FOREIGN KEY (`idEmpresa`) REFERENCES `empresa` (`idEmpresa`),
  CONSTRAINT `sucursal_ibfk_2` FOREIGN KEY (`idUbicacion`) REFERENCES `ubicacion` (`idUbicacion`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 
-- TABLA UBICACIÓN
-- 

DROP TABLE IF EXISTS `ubicacion`;

CREATE TABLE `ubicacion` (
  `idUbicacion` int NOT NULL AUTO_INCREMENT,
  `calle` varchar(20) NOT NULL,
  `numero` varchar(20) NOT NULL,
  `codigoPostal` varchar(5) NOT NULL,
  `ciudad` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`idUbicacion`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 
-- TABLA USUARIO
-- 

DROP TABLE IF EXISTS `usuario`;

CREATE TABLE `usuario` (
  `idUsuario` int NOT NULL AUTO_INCREMENT,
  `idEmpresa` int DEFAULT NULL,
  `idRollUsuario` int DEFAULT NULL,
  `nombre` varchar(20) NOT NULL,
  `apellidoPaterno` varchar(20) NOT NULL,
  `apellidoMaterno` varchar(20) NOT NULL,
  `curp` varchar(18) NOT NULL,
  `correo` varchar(40) NOT NULL,
  `userName` varchar(20) NOT NULL,
  `contrasenia` varchar(20) NOT NULL,
  PRIMARY KEY (`idUsuario`),
  UNIQUE KEY `curp` (`curp`),
  UNIQUE KEY `userName` (`userName`),
  KEY `idEmpresa` (`idEmpresa`),
  KEY `idRollUsuario` (`idRollUsuario`),
  CONSTRAINT `usuario_ibfk_1` FOREIGN KEY (`idEmpresa`) REFERENCES `empresa` (`idEmpresa`),
  CONSTRAINT `usuario_ibfk_2` FOREIGN KEY (`idRollUsuario`) REFERENCES `rollusuario` (`idRollUsuario`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 
-- CREACIÓN DEL USUARIO
-- 
create user 'adminusercupon'@'%' identified by 'ewd2q123';
grant all privileges on smartcupon.* to 'adminusercupon'@'%';
flush privileges;

--
-- CREACIÓN DE TRIGGERS
--

DELIMITER //

CREATE TRIGGER actualizar_estatus_cupon BEFORE UPDATE ON promocion
FOR EACH ROW
BEGIN
    IF NEW.noCuponesMaximo <= 0 AND NEW.estatus <> 'inactivo' THEN
        SET NEW.estatus = 'inactivo';
    END IF;
END //

DELIMITER ;


DROP TRIGGER actualizar_estatus_cupon_fecha;

DELIMITER //

CREATE TRIGGER actualizar_estatus_cupon_fecha BEFORE UPDATE ON promocion
FOR EACH ROW
BEGIN
    IF NEW.fechaFin < NOW() AND NEW.estatus <> 'inactivo' THEN
        SET NEW.estatus = 'inactivo';
    END IF;
END //

DELIMITER ;