insert into author (`id`, `name`) values (1, 'Joshua Bloch');
insert into author (`id`, `name`) values (2, 'Brian Goetz');
insert into author (`id`, `name`) values (3, 'Doug Lea');

insert into GENRE (`id`, `NAME`) values (1, 'Patterns');
insert into GENRE (`id`, `NAME`) values (2, 'Java');
insert into GENRE (`id`, `NAME`) values (3, 'Concurrency');

insert into BOOK (`id`, `NAME`) values (1, 'Effective java');
insert into BOOK (`id`, `NAME`) values (2, 'Java concurrency in practice');
insert into BOOK (`id`, `NAME`) values (3, 'Concurrent Programming in Java');

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


