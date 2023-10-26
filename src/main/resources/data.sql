INSERT INTO tinder_user (email, nickname, gender, attraction, passion) VALUES ('josep@tecnocampus.cat', 'josep', 2, 3, 0);
INSERT INTO tinder_user (email, nickname, gender, attraction, passion) VALUES ('jordi@tecnocampus.cat', 'jordi', 0, 3, 0);
INSERT INTO tinder_user (email, nickname, gender, attraction, passion) VALUES ('maria@tecnocampus.cat', 'maria', 1, 0, 0);
INSERT INTO tinder_user (email, nickname, gender, attraction, passion) VALUES ('marta@tecnocampus.cat', 'marta', 1, 0, 0);
INSERT INTO tinder_user (email, nickname, gender, attraction, passion) VALUES ('pepe@tecnocampus.cat', 'pepeillo', 0, 3, 0);
INSERT INTO tinder_user (email, nickname, gender, attraction, passion) VALUES ('sonia@tecnocampus.cat', 'sonia', 1, 3, 0);

INSERT INTO tinder_like(origin_email, target_email, creation_date, matched) VALUES('josep@tecnocampus.cat', 'jordi@tecnocampus.cat', current_date(), false);
INSERT INTO tinder_like(origin_email, target_email, creation_date, matched) VALUES('josep@tecnocampus.cat', 'maria@tecnocampus.cat', current_date(), false);
INSERT INTO tinder_like(origin_email, target_email, creation_date, matched) VALUES('josep@tecnocampus.cat', 'marta@tecnocampus.cat', current_date(), false);
INSERT INTO tinder_like(origin_email, target_email, creation_date, matched) VALUES('jordi@tecnocampus.cat', 'maria@tecnocampus.cat', current_date(), false);
INSERT INTO tinder_like(origin_email, target_email, creation_date, matched) VALUES('jordi@tecnocampus.cat', 'marta@tecnocampus.cat', current_date(), false);
INSERT INTO tinder_like(origin_email, target_email, creation_date, matched) VALUES('marta@tecnocampus.cat', 'pepe@tecnocampus.cat', current_date(), false);

INSERT INTO role(name) VALUES ('ROLE_USER');
INSERT INTO role(name) VALUES ('ROLE_MODERATOR');
INSERT INTO role(name) VALUES ('ROLE_ADMIN');

INSERT INTO user_lab (email, username, password) VALUES ('josep@tecnocampus.cat', 'josep', '$2a$10$fVKfcc47q6lrNbeXangjYeY000dmjdjkdBxEOilqhapuTO5ZH0co2');
INSERT INTO user_lab (email, username, password) VALUES ('jordi@tecnocampus.cat', 'jordi', '$2a$10$fVKfcc47q6lrNbeXangjYeY000dmjdjkdBxEOilqhapuTO5ZH0co2');
INSERT INTO user_lab (email, username, password) VALUES ('maria@tecnocampus.cat', 'maria', '$2a$10$fVKfcc47q6lrNbeXangjYeY000dmjdjkdBxEOilqhapuTO5ZH0co2');
INSERT INTO user_lab (email, username, password) VALUES ('admin@tecnocampus.cat', 'admin', '$2a$10$fVKfcc47q6lrNbeXangjYeY000dmjdjkdBxEOilqhapuTO5ZH0co2');


INSERT INTO user_roles (USER_ID, ROLE_ID) VALUES (1, 1);
INSERT INTO user_roles (USER_ID, ROLE_ID) VALUES (2, 3);
INSERT INTO user_roles (USER_ID, ROLE_ID) VALUES (3, 1);
INSERT INTO user_roles (USER_ID, ROLE_ID) VALUES (3, 3);
INSERT INTO user_roles (USER_ID, ROLE_ID) VALUES (4, 3);


