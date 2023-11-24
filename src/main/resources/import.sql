-- inserisco le foto
INSERT INTO db_photoalbum.photos (title, url, description, visibility, created_at) VALUES('Foto Tramonto', 'https://img.freepik.com/free-photo/sunset-aegean-sea-coast-ship-land-distance-water-greece_1268-16901.jpg', 'Una bellissima foto di un tramonto sul mare scattata dal noto fotografo Kenpa Zoid', TRUE, '2023-11-23 11:37:00')
INSERT INTO db_photoalbum.photos (title, url, description, visibility, created_at) VALUES('Foto Natura', 'https://img.freepik.com/free-photo/green-meadow-tree-sunset-beauty-generated-by-ai_188544-44226.jpg', "Foto dell'albero dove il supervedente Kenpazor passava i suoi pomeriggi", TRUE, '2023-11-23 12:11:00')
INSERT INTO db_photoalbum.photos (title, url, description, visibility, created_at) VALUES('Foto Metropoli', 'https://images.pexels.com/photos/707667/pexels-photo-707667.jpeg?auto=compress&cs=tinysrgb&w=1600', "Foto della metropoli fondata dall'immenso e divino guardone KenpaSight", TRUE, '2023-11-23 15:22:00')
INSERT INTO db_photoalbum.photos (title, url, description, visibility, created_at) VALUES('Foto Imbarcazione', 'https://www.terra-di-mezzo.it/wp-content/uploads/vacanze-in-barca-a-vela.jpeg', "Foto dell'imprenditore oculista KenpaSwim occupato nel gestire la sua imbarcazione oculistica", TRUE, '2023-11-22 09:52:00')

-- inserisco le categorie
INSERT INTO categories(name) VALUE('paesaggi')
INSERT INTO categories(name) VALUE('cibo')
INSERT INTO categories(name) VALUE('tramonto')
INSERT INTO categories(name) VALUE('città')

-- assegno alle foto delle categorie
INSERT INTO photos_categories (photo_id, categories_id) VALUES (1, 1)
INSERT INTO photos_categories (photo_id, categories_id) VALUES (1, 3)
INSERT INTO photos_categories (photo_id, categories_id) VALUES (2, 1)
INSERT INTO photos_categories (photo_id, categories_id) VALUES (3, 4)
INSERT INTO photos_categories (photo_id, categories_id) VALUES (4, 1)

-- inserisco i ruoli
INSERT INTO roles (id, name) VALUES(1, 'SUPERADMIN')
INSERT INTO roles (id, name) VALUES(2, 'ADMIN')
INSERT INTO roles (id, name) VALUES(3, 'USER')

-- inserisco gli utenti
INSERT INTO users (email, first_name, last_name, registered_at, password)VALUES('lamberto@email.com', 'Lamberto', 'Neri', '2023-11-23', '{noop}lamberto');
INSERT INTO users (email, first_name, last_name, registered_at, password)VALUES('pimpa@email.com', 'Pimpa', 'Neri', '2023-11-23', '{noop}pimpa');

-- assegno i ruoli agli utenti
INSERT INTO users_roles (user_id, roles_id) VALUES(1, 2);
INSERT INTO users_roles (user_id, roles_id) VALUES(1, 3);
INSERT INTO users_roles (user_id, roles_id) VALUES(2, 3);

-- inserisco i messaggi
INSERT INTO messages (email, message, title) VALUES("lamberto@email.com", "sono bello", "primo messaggio")
INSERT INTO messages (email, message, title) VALUES("pimpa@email.com", "sono bella", "secondo messaggio")