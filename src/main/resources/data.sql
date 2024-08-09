-- Inserting roles
INSERT INTO role (id, name) VALUES (1, 'ROLE_USER');
INSERT INTO role (id, name) VALUES (2, 'ROLE_ADMIN');

-- Inserting users
INSERT INTO users (id, username, password, enabled) VALUES (1, 'user1', '{bcrypt}$2a$10$7d4r8l8g.ycXj3pbnxO8fu5jjOeKZ0hv5uISyHK8.XQlUGec.5wWi', true);
INSERT INTO users (id, username, password, enabled) VALUES (2, 'admin', '{bcrypt}$2a$10$7d4r8l8g.ycXj3pbnxO8fu5jjOeKZ0hv5uISyHK8.XQlUGec.5wWi', true);

-- Inserting tasks
INSERT INTO task (id, title, description, status, user_id) VALUES (1, 'Task 1', 'Description for task 1', 'PENDING', 1);
INSERT INTO task (id, title, description, status, user_id) VALUES (2, 'Task 2', 'Description for task 2', 'COMPLETED', 1);

-- Assigning roles to users
INSERT INTO user_roles (user_id, role_id) VALUES (1, 1);
INSERT INTO user_roles (user_id, role_id) VALUES (2, 2);
