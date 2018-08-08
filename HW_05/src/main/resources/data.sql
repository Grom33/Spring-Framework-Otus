insert into AUTHOR (`NAME`) values ('Joshua Bloch');
insert into AUTHOR (`NAME`) values ('Brian Goetz');
insert into AUTHOR (`NAME`) values ('Doug Lea');
insert into GENRE (`NAME`) values ('Patterns');
insert into GENRE (`NAME`) values ('Java');
insert into GENRE (`NAME`) values ('Concurency');
insert into BOOK (`NAME`, `GENRES`, `AUTHORS`) values ('Effective java', '1,2', '1');
insert into BOOK (`NAME`, `GENRES`, `AUTHORS`) values ('Java concurrency in practice', '2,3', '1,2,3');
insert into BOOK (`NAME`, `GENRES`, `AUTHORS`) values ('Concurrent Programming in Java', '1,2', '3');