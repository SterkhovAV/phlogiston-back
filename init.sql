CREATE USER phlogiston_user WITH PASSWORD 'fos348c';
GRANT ALL PRIVILEGES ON DATABASE phlogiston to phlogiston_user;
\c phlogiston;
CREATE SCHEMA phlogiston;
GRANT ALL PRIVILEGES ON SCHEMA phlogiston to phlogiston_user;
