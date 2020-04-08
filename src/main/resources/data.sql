INSERT INTO Items_Category (category_name)
        VALUES ( 'narty' ),
               ( 'buty narciaskie' ),
               ( 'kaski' ),
               ( 'gogle' ),
               ( 'kije' ),
               ( 'deski snowboard' );

INSERT INTO Item (make, model, price, availability, qr_code, reservation_date, created_at, item_category_id)
        VALUES ( 'Atomic', 'Redster', '40', true, 'QR_7a108453-4916-4ef9-9eae-aa660dece76c', null, '20200323 03:29:34', 1 ),
               ( 'Atomic', 'Redster1', '40', false, 'QR_7a102553-4116-4ef9-9eae-aa660dece76c', null, '20200323 03:29:34', 1 ),
               ( 'Ressignol', 'Hero Elite', '40', false, 'QR_7b108453-4916-4af9-9ece-aa660dece76c', '20210101', '20200323 03:29:34', 1 ),
               ( 'Ressignol', 'Track', '30', true, 'QR_7a108142-4916-4ef9-9eae-aa660dece76c', null, '20200323 03:29:34', 2 ),
               ( 'Ressignol', 'Allspeed', '30', true, 'QR_8a108453-4906-2ef9-9eae-aa660dece76c', null, '20200323 03:29:34', 4 ),
               ( 'Ressignol', 'Kakask', '15', true, 'QR_7a505353-4916-4ef9-9eae-aa660dece76c', null, '20200323 03:29:34', 3 );
/*
INSERT INTO Client (name, lastname, pesel, personal_id_number)
        VALUES ( 'Brunon', 'Lemanski', 91010102992, 'ABC123456'),
               ( 'Hubert', 'Lemanski', 91010202992, 'ABC123451'),
               ( 'Wiktoria', 'Dabskowska', 91010102902, 'ABC123356');

INSERT INTO Rent (rent_date, return_date, cost, user_id, item_id, created_at)
        VALUES ( '20200324 12:32:25', null, null, 1, 2, '20200323 03:29:34' );
        */