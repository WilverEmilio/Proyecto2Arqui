-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 03-11-2023 a las 05:54:57
-- Versión del servidor: 10.4.28-MariaDB
-- Versión de PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `proyecto2`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `clientes`
--

CREATE TABLE `clientes` (
  `id` int(11) NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `apellido` varchar(255) NOT NULL,
  `puntos` int(11) NOT NULL,
  `create_ad` timestamp NOT NULL DEFAULT current_timestamp(),
  `update_ad` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `clientes`
--

INSERT INTO `clientes` (`id`, `nombre`, `apellido`, `puntos`, `create_ad`, `update_ad`) VALUES
(1, 'asdf', 'adsfgfh', 100, '2023-11-03 02:16:31', '2023-11-03 02:16:31'),
(2, 'Wilver Emilio', 'Xiá Ixcot', 500, '2023-11-03 02:16:31', '2023-11-03 02:16:31'),
(4, 'string', 'string', 0, '2023-11-03 03:25:32', '2023-11-03 03:25:32'),
(5, 'Wilver', 'Ixcot', 100, '2023-11-03 03:30:31', '2023-11-03 03:30:31'),
(6, 'William', 'Wilfredo', 10, '2023-11-03 03:31:38', '2023-11-03 03:31:38');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `sequelizemeta`
--

CREATE TABLE `sequelizemeta` (
  `name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `sequelizemeta`
--

INSERT INTO `sequelizemeta` (`name`) VALUES
('1-tanques.js'),
('2-people.js'),
('3-transacciones.js');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tanques`
--

CREATE TABLE `tanques` (
  `id` int(11) NOT NULL,
  `capacidad` decimal(10,2) NOT NULL,
  `nivel_actual` decimal(10,2) NOT NULL,
  `ubicacion` varchar(255) NOT NULL,
  `tipo_gasolina` varchar(255) NOT NULL,
  `create_ad` timestamp NOT NULL DEFAULT current_timestamp(),
  `update_ad` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `tanques`
--

INSERT INTO `tanques` (`id`, `capacidad`, `nivel_actual`, `ubicacion`, `tipo_gasolina`, `create_ad`, `update_ad`) VALUES
(2, 500.00, 400.00, 'En el lado sur de la gasolineria', 'Regular', '2023-11-02 23:41:21', '2023-11-02 23:41:21'),
(4, 500.00, 500.00, 'En el lado sur de la gasolineria', 'Regular', '2023-11-03 00:34:24', '2023-11-03 00:34:24'),
(5, 500.00, 200.00, 'En el lado sur de la gasolineria', 'Disel', '2023-11-03 00:48:42', '2023-11-03 00:48:42');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `transacciones`
--

CREATE TABLE `transacciones` (
  `id` int(11) NOT NULL,
  `cantidad_vendida` decimal(10,2) NOT NULL,
  `precio_por_galon` decimal(5,2) NOT NULL,
  `empleado` varchar(255) NOT NULL,
  `cliente_id` int(11) NOT NULL,
  `tanque_id` int(11) NOT NULL,
  `total` varchar(255) NOT NULL,
  `estado` int(11) NOT NULL,
  `createdAt` datetime NOT NULL,
  `updatedAt` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `transacciones`
--

INSERT INTO `transacciones` (`id`, `cantidad_vendida`, `precio_por_galon`, `empleado`, `cliente_id`, `tanque_id`, `total`, `estado`, `createdAt`, `updatedAt`) VALUES
(1, 50.00, 15.20, 'Wivler Emilio', 1, 1, '', 1, '2023-10-27 03:06:14', '2023-10-27 03:06:14'),
(4, 15.00, 5.20, 'Wilver Emilio', 1, 2, '', 1, '2023-11-02 22:18:54', '2023-11-02 22:18:54'),
(5, 15.00, 5.20, 'Wilver Emilio', 2, 2, '', 1, '2023-11-02 22:18:59', '2023-11-02 22:18:59'),
(6, 15.00, 5.20, 'Wilver Emilio', 2, 2, '', 1, '2023-11-02 22:19:04', '2023-11-02 22:19:04'),
(7, 15.00, 5.20, 'Wilver Emilio', 2, 2, '', 1, '2023-11-02 22:19:19', '2023-11-02 22:19:19'),
(8, 15.00, 5.20, 'Wilver Emilio', 2, 2, '', 1, '2023-11-02 22:20:17', '2023-11-02 22:20:17'),
(9, 15.00, 5.20, 'Wilver Emilio', 2, 2, '', 1, '2023-11-02 22:22:01', '2023-11-02 22:22:01'),
(10, 15.00, 5.20, 'Wilver Emilio', 2, 2, '', 1, '2023-11-02 22:23:09', '2023-11-02 22:23:09'),
(11, 15.00, 5.20, 'Wilver Emilio', 2, 2, '', 1, '2023-11-02 22:25:21', '2023-11-02 22:25:21'),
(12, 15.00, 5.20, 'Wilver Emilio', 2, 2, '', 1, '2023-11-02 22:26:09', '2023-11-02 22:26:09'),
(13, 5.00, 5.20, 'Hola', 5, 2, '', 1, '2023-11-02 22:38:07', '2023-11-02 22:38:07'),
(14, 5.00, 5.20, 'Hola', 5, 2, '', 1, '2023-11-02 22:42:50', '2023-11-02 22:42:50'),
(15, 5.00, 5.20, 'Hola', 2, 2, '', 1, '2023-11-02 22:43:20', '2023-11-02 22:43:20'),
(16, 5.00, 5.20, 'Hola', 2, 2, '', 1, '2023-11-02 22:43:35', '2023-11-02 22:43:35');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `clientes`
--
ALTER TABLE `clientes`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `sequelizemeta`
--
ALTER TABLE `sequelizemeta`
  ADD PRIMARY KEY (`name`),
  ADD UNIQUE KEY `name` (`name`);

--
-- Indices de la tabla `tanques`
--
ALTER TABLE `tanques`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `transacciones`
--
ALTER TABLE `transacciones`
  ADD PRIMARY KEY (`id`),
  ADD KEY `cliente_id` (`cliente_id`),
  ADD KEY `tanque_id` (`tanque_id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `clientes`
--
ALTER TABLE `clientes`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT de la tabla `tanques`
--
ALTER TABLE `tanques`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `transacciones`
--
ALTER TABLE `transacciones`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `transacciones`
--
ALTER TABLE `transacciones`
  ADD CONSTRAINT `transacciones_ibfk_1` FOREIGN KEY (`cliente_id`) REFERENCES `clientes` (`id`),
  ADD CONSTRAINT `transacciones_ibfk_2` FOREIGN KEY (`tanque_id`) REFERENCES `tanques` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
