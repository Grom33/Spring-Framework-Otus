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


