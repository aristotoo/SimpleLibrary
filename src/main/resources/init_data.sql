truncate table data.person restart identity cascade ;
ALTER SEQUENCE person_person_id_seq
    RESTART WITH 1;
ALTER SEQUENCE book_book_id_seq
    RESTART WITH 1;
