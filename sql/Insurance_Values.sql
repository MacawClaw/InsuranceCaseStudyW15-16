Insert into roles(role_name)
values ("ROLE_ADJUSTER"), ("ROLE_CLIENT");

Insert into users(username,user_password,enabled,user_fname,user_lname,user_email,user_phone,user_address)
values ("adjuster44","$2a$10$iBZBa83v1opAeX/rdnCZdeBGnT09.pHyz8FfX2trmsKJYoJUDEY.2", 1, "Insurance", "Adjuster" , "me@test.com", "202-918-2132", "1 Hello World Rd. Exclamation,KS 60000"),
("csampson", "$2a$10$WMbYurzgTFjYpMVLusLJy.DPSRQbk0l899VoYfJqQSCz.Atlpz.x.", 1, "Candide", "Sampson", "me@test.com", "202-918-2132", "1 Hello World Rd. Exclamation,KS 60000"),
("cscudworth", "$2a$10$g/Z2D9xFP5ZkLYnLl055reeVe8Hka8/7706cl7YjHauGS0QaQCiFi", 1, "Cinnamon", "Scudworth", "me@test.com", "202-918-2132","1 Hello World Rd. Exclamation,KS 60000"),
("hblight", "$2a$10$iBZBa83v1opAeX/rdnCZdeBGnT09.pHyz8FfX2trmsKJYoJUDEY.2", 1, "Henrietta", "Blight", "john@something.com", "281-932-6753","2086 Hash Ln. Houston,TX 77032"),
("jdark", "$2a$10$iBZBa83v1opAeX/rdnCZdeBGnT09.pHyz8FfX2trmsKJYoJUDEY.2", 1, "Joan", "Dark", "joan@something.com", "703-532-6652", "2337 Exception St. Catch,VA 20036"),
("jsmith", "$2a$10$iBZBa83v1opAeX/rdnCZdeBGnT09.pHyz8FfX2trmsKJYoJUDEY.2", 1, "John", "Smith", "john@something.com", "402-388-7090", "4300 Compliment Ave. Byte,NE 68801"),
("ogarcia", "$2a$10$iBZBa83v1opAeX/rdnCZdeBGnT09.pHyz8FfX2trmsKJYoJUDEY.2", 1, "Omar", "Garcia", "omar@something.com", "202-918-2132","4269 Hello World Rd. Exclamation,KS 67003");

Insert into users_roles(role_id, username)
values (2,"csampson"),
(1,"adjuster44"),
(2,"adjuster44"),
(2,"cscudworth"),
(2,"jsmith"),
(2,"jdark"),
(2,"hblight");

Insert into vehicles(vehicle_year, vehicle_make, vehicle_model, vehicle_vin, username)
values (2008, "Nissan", "Altima", "1N4AL21E68N441282", "ogarcia"),
(2002, "Ford", "Escape", "1FMYU02B32KA63604", "ogarcia"),
(2011, "Toyota", "Prius", "JTDKN3DUXB0303475", "jsmith"),
(2003, "Toyota", "Highlander", "JTEHF21A430098874", "hblight"),
(2003, "Chevrolet", "Suburban", "1GNFK16T93R304740", "jsmith");

Insert into claims(status_id, claim_description, claim_cost, adjuster_notes, vehicle_vin, username)
values (1, "My car was hit by a deer", 1200.00, "Please submit an estimate by the body shop", "1N4AL21E68N441282", "ogarcia"),
(1, "A tree fell on my car", 1600.00, "Please submit an estimate by the body shop", "1N4AL21E68N441282", "ogarcia"),
(1, "I was t-boned by another car", 1700.00, "Please submit an estimate by the body shop", "1FMYU02B32KA63604", "ogarcia");

Insert into claims_status(status_name)
values("Filed"),("Being Evaluated"),("Approved"),("Denied"),("Returned to Client"), ("Updated"), ("Withdrawn");

