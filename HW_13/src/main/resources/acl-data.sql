INSERT INTO acl_sid (id, principal, sid) VALUES
(1, 1, 'user'),
(2, 1, 'admin'),
(3, 0, 'ROLE_ADMIN'),
(4, 1, 'editor'),;

INSERT INTO acl_class (id, class) VALUES
(1, 'ru.otus.gromov.domain.Book');


INSERT INTO acl_object_identity (id, object_id_class, object_id_identity, parent_object, owner_sid, entries_inheriting) VALUES
(1, 1, 1, NULL, 3, 0),
(2, 1, 2, NULL, 3, 0),
(3, 1, 3, NULL, 3, 0);

INSERT INTO acl_entry (id, acl_object_identity, ace_order, sid, mask, granting, audit_success, audit_failure) VALUES
(1, 1, 1, 1, 1, 1, 1, 1),
(2, 1, 2, 1, 2, 1, 1, 1),
(3, 1, 3, 3, 1, 1, 1, 1),
(4, 2, 1, 2, 1, 1, 1, 1),
(5, 2, 2, 3, 1, 1, 1, 1),
(6, 3, 1, 3, 1, 1, 1, 1),
(7, 3, 2, 3, 2, 1, 1, 1),
(8, 3, 3, 1, 1, 1, 1, 1);

insert into author (`id`, `name`) values (1, 'Joshua Bloch');
insert into author (`id`, `name`) values (2, 'Brian Goetz');
insert into author (`id`, `name`) values (3, 'Doug Lea');
insert into author (`id`, `name`) values (4, 'Test test');

insert into GENRE (`id`, `NAME`) values (1, 'Patterns');
insert into GENRE (`id`, `NAME`) values (2, 'Java');
insert into GENRE (`id`, `NAME`) values (3, 'Concurrency');
insert into GENRE (`id`, `NAME`) values (4, 'TESTTEST');

insert into BOOK (`id`, `NAME`, `description`) values (1, 'Effective java', 'AAAAAAAAAAAA');
insert into BOOK (`id`, `NAME`, `description`) values (2, 'Java concurrency in practice', 'BBBBBBBBBB');
insert into BOOK (`id`, `NAME`, `description`) values (3, 'Concurrent Programming in Java', 'CCCCCCCCCCCCC');

insert into COMMENT (`id`, `review`, `book_id`) values (1, 'Ваще огонь!', 1);
insert into COMMENT (`id`, `review`, `book_id`) values (2, 'Круто!', 1);
insert into COMMENT (`id`, `review`, `book_id`) values (3, 'Хрень!', 1);
insert into COMMENT (`id`, `review`, `book_id`) values (4, 'Ну нормально.', 2);
insert into COMMENT (`id`, `review`, `book_id`) values (5, 'Так себе!', 2);
insert into COMMENT (`id`, `review`, `book_id`) values (6, 'Отлично!', 3);
insert into COMMENT (`id`, `review`, `book_id`) values (7, 'Что курил автор?', 3);


insert into BOOK_GENRE (`BOOK_ID`, `GENRE_ID`) values (1, 1);
insert into BOOK_GENRE (`BOOK_ID`, `GENRE_ID`) values (1, 2);

insert into BOOK_GENRE (`BOOK_ID`, `GENRE_ID`) values (2, 2);
insert into BOOK_GENRE (`BOOK_ID`, `GENRE_ID`) values (2, 3);

insert into BOOK_GENRE (`BOOK_ID`, `GENRE_ID`) values (3, 1);
insert into BOOK_GENRE (`BOOK_ID`, `GENRE_ID`) values (3, 2);

insert into BOOK_AUTHOR (`BOOK_ID`, `AUTHOR_ID`) values (1, 1);
insert into BOOK_AUTHOR (`BOOK_ID`, `AUTHOR_ID`) values (2, 1);
insert into BOOK_AUTHOR (`BOOK_ID`, `AUTHOR_ID`) values (2, 2);
insert into BOOK_AUTHOR (`BOOK_ID`, `AUTHOR_ID`) values (2, 3);
insert into BOOK_AUTHOR (`BOOK_ID`, `AUTHOR_ID`) values (3, 3);

insert into lib_User (`id`, `userName`, `password`, `role`) values (1, 'Admin', 'password', 'ADMIN');
insert into lib_User (`id`, `userName`, `password`, `role`) values (2, 'user', 'password', 'USER');
insert into lib_User (`id`, `userName`, `password`, `role`) values (3, 'free', 'password', 'USER');