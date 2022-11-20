-- ************************************** "phlogiston_u_roles"
INSERT INTO phlogiston_u_roles (name, description)
VALUES ('ROLE_ADMIN', 'Administrator');
INSERT INTO phlogiston_u_roles (name, description)
VALUES ('ROLE_USER', 'User');

-- ************************************** "phlogiston_users"
--- admin -----------------------(admin)
INSERT INTO phlogiston_users (username, password, active, email, last_name, first_name, organisation, create_date,
                              last_time_update_password, role_id, failed_login_counter, uuid)
VALUES ('admin', '$2a$10$2aRTUZ1FTW9Z8Is2VqJFlOSSi3MAyQ6yj8AFo1tMpaTIdx1k3Ed1q', true, 'admin@email.ru', 'Ad', 'min',
        'administration', '2022-12-31 23:59:40.687000 +00:00',
        '2022-12-31 23:59:40.687000 +00:00', 1, 0, '00000000-0000-0000-0000-000000000001');

--- user -----------------------(user)
INSERT INTO phlogiston_users (username, password, active, email, last_name, first_name, organisation, create_date,
                              last_time_update_password, role_id, failed_login_counter, uuid)
VALUES ('user', '$2a$10$iGLExD8.IkCCoL1ZvhIvDO9/1XyOM9EgLWOHkjsYzwOyVM6xrJaMu', true, 'user@email.ru', 'Us', 'er',
        'test', '2022-12-31 23:59:40.687000 +00:00',
        '2022-12-31 23:59:40.687000 +00:00', 2, 0, '00000000-0000-0000-0000-000000000002');
