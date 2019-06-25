CREATE TABLE IF NOT EXISTS `city` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `name` varchar(50) NOT NULL DEFAULT '',
    `description` varchar(1000) NOT NULL DEFAULT '',
    PRIMARY KEY (`id`)
)   ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `city` (`name`, `description`)
    VALUES
    ('москва', 'Не забудьте посетить Красную Площадь. Ну а в ЦУМ можно и не заходить)))'),
    ('минск', 'Обязательно посмотрите Верхний Город, Троицкое Предместье и Национальную библотеку'),
    ('санкт-петербург','Рекомендую посетить Петергоф, Эрмитаж и Екатеринский дворец');