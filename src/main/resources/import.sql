-- Script SQL para pré-carregar dados no banco H2
-- Este arquivo será executado automaticamente pelo Spring Boot na inicialização

-- Inserir autores primeiro
INSERT INTO author (id, first_name, last_name, email) VALUES (1, 'George', 'Orwell', 'george.orwell@email.com');
INSERT INTO author (id, first_name, last_name, email) VALUES (2, 'J.K.', 'Rowling', 'jk.rowling@email.com');
INSERT INTO author (id, first_name, last_name, email) VALUES (3, 'Stephen', 'King', 'stephen.king@email.com');
INSERT INTO author (id, first_name, last_name, email) VALUES (4, 'Agatha', 'Christie', 'agatha.christie@email.com');
INSERT INTO author (id, first_name, last_name, email) VALUES (5, 'Isaac', 'Asimov', 'isaac.asimov@email.com');

-- Inserir livros após os autores
INSERT INTO book (title, isbn, page_count, author_id) VALUES ('1984', '978-0-452-28423-4', 328, 1);
INSERT INTO book (title, isbn, page_count, author_id) VALUES ('Animal Farm', '978-0-452-28424-1', 112, 1);
INSERT INTO book (title, isbn, page_count, author_id) VALUES ('Harry Potter and the Philosopher''s Stone', '978-0-7475-3269-9', 223, 2);
INSERT INTO book (title, isbn, page_count, author_id) VALUES ('Harry Potter and the Chamber of Secrets', '978-0-7475-3849-3', 251, 2);
INSERT INTO book (title, isbn, page_count, author_id) VALUES ('The Shining', '978-0-385-12167-5', 447, 3);
INSERT INTO book (title, isbn, page_count, author_id) VALUES ('It', '978-0-670-81302-4', 1138, 3);
INSERT INTO book (title, isbn, page_count, author_id) VALUES ('Murder on the Orient Express', '978-0-00-711926-0', 256, 4);
INSERT INTO book (title, isbn, page_count, author_id) VALUES ('The Murder of Roger Ackroyd', '978-0-00-711925-3', 312, 4);
INSERT INTO book (title, isbn, page_count, author_id) VALUES ('Foundation', '978-0-553-29335-0', 244, 5);
INSERT INTO book (title, isbn, page_count, author_id) VALUES ('I, Robot', '978-0-553-29438-8', 253, 5);

-- Os IDs serão gerados automaticamente pelo Hibernate