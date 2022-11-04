-- ************************************** "phlogiston_u_roles"
INSERT INTO phlogiston_u_roles (name, description) VALUES ('ADMIN_MAIN', 'Administrator');
INSERT INTO phlogiston_u_roles (name, description) VALUES ('USER', 'User');

-- ************************************** "phlogiston_user"
--- admin -----------------------(admin)
INSERT INTO phlogiston_user (login, password,  email,  last_name, first_name, organisation,  create_date, role_id)
VALUES ('admin', 'admin','admin@email.ru', 'Ad', 'min','administration', '2022-12-31 23:59:40.687000 +00:00', 1);

--- user -----------------------(user)
INSERT INTO phlogiston_user (login, password,  email,  last_name, first_name, organisation,  create_date, role_id)
VALUES ('user', 'user','user@email.ru', 'Us', 'er','test', '2022-12-31 23:59:40.687000 +00:00', 2);
