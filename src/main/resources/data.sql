
 
INSERT INTO USER(first_name, last_name, username,password) VALUES
  ('Srabonti', 'Dangote', 'techbonti@gmail.com','techbonti');
INSERT INTO USER(first_name, last_name, username,password) VALUES
  ('Ron', 'Weasley', 'rweasley@gmail.com','rweasley');
INSERT INTO USER(first_name, last_name, username,password) VALUES
  ('Hermione', 'Granger', 'hgranger@gmail.com', 'hgranger');

INSERT INTO DOG(CREATED_TIME_STAMP,UPDATED_TIME_STAMP,NAME, BREED,WEIGHT,HEIGHT,COLOR,AGE,USER_ID)
VALUES ( CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),'Mickey Moo', 'Cocker Spaniel', '30','33','Brown', 3, select id from user where username='techbonti@gmail.com');


INSERT INTO DOG(CREATED_TIME_STAMP,UPDATED_TIME_STAMP,NAME, BREED,WEIGHT,HEIGHT,COLOR,AGE,USER_ID)
VALUES ( CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),'Scooby Doo', 'Labrador', '60','44','Brown', 8, select id from user where username='techbonti@gmail.com');

INSERT INTO DOG(CREATED_TIME_STAMP,UPDATED_TIME_STAMP,NAME, BREED,WEIGHT,HEIGHT,COLOR,AGE,USER_ID)
VALUES ( CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),'Scrappy Do', 'Chihuaua', '20','20','Cream', 1, select id from user where username='techbonti@gmail.com');

INSERT INTO DOG(CREATED_TIME_STAMP,UPDATED_TIME_STAMP,NAME, BREED,WEIGHT,HEIGHT,COLOR,AGE,USER_ID)
VALUES ( CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),'Pluto Mouse', 'Spaniel', '33','27','Tan', 4, select id from user where username='techbonti@gmail.com');

INSERT INTO DOG(CREATED_TIME_STAMP,UPDATED_TIME_STAMP,NAME, BREED,WEIGHT,HEIGHT,COLOR,AGE,USER_ID)
VALUES ( CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),'Goofy Gumbo', 'Labrador', '35','35','Dark Brown', 9, select id from user where username='techbonti@gmail.com');

INSERT INTO DOG(CREATED_TIME_STAMP,UPDATED_TIME_STAMP,NAME, BREED,WEIGHT,HEIGHT,COLOR,AGE,USER_ID)
VALUES ( CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),'Doobie Do', 'Bulldog', '29','35','Black and White', 6, select id from user where username='techbonti@gmail.com');


INSERT INTO DOG(CREATED_TIME_STAMP,UPDATED_TIME_STAMP,NAME, BREED,WEIGHT,HEIGHT,COLOR,AGE,USER_ID)
VALUES ( CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),'Loony Brown', 'Cocker Spaniel', '30','33','Brown', 3, select id from user where username='rweasley@gmail.com');


INSERT INTO DOG(CREATED_TIME_STAMP,UPDATED_TIME_STAMP,NAME, BREED,WEIGHT,HEIGHT,COLOR,AGE,USER_ID)
VALUES ( CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),'Buster Trotteville', 'Labrador', '60','44','Brown', 8, select id from user where username='rweasley@gmail.com');

INSERT INTO DOG(CREATED_TIME_STAMP,UPDATED_TIME_STAMP,NAME, BREED,WEIGHT,HEIGHT,COLOR,AGE,USER_ID)
VALUES ( CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),'Timmy Kirrin', 'Dalmatian', '20','20','Cream', 1, select id from user where username='rweasley@gmail.com');

INSERT INTO DOG(CREATED_TIME_STAMP,UPDATED_TIME_STAMP,NAME, BREED,WEIGHT,HEIGHT,COLOR,AGE,USER_ID)
VALUES ( CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),'Miranda Barnabas', 'Spaniel', '33','27','Tan', 4, select id from user where username='rweasley@gmail.com');

INSERT INTO DOG(CREATED_TIME_STAMP,UPDATED_TIME_STAMP,NAME, BREED,WEIGHT,HEIGHT,COLOR,AGE,USER_ID)
VALUES ( CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),'Kiki Mannering', 'Alsatian', '35','35','Dark Brown', 9, select id from user where username='rweasley@gmail.com');





 