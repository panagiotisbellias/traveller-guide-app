-- -------------------------------------------------------------
-- Initialize database for Traveller Guide app
-- -------------------------------------------------------------

-- 1️⃣ Create application user
CREATE USER traveluser WITH PASSWORD 'secret';

-- 2️⃣ Grant privileges on the database
GRANT ALL PRIVILEGES ON DATABASE travelguide TO traveluser;

-- 3️⃣ Create schema owned by traveluser
CREATE SCHEMA IF NOT EXISTS travelguide AUTHORIZATION traveluser;

-- 4️⃣ Switch session to traveluser to ensure table ownership
SET ROLE traveluser;
SET search_path = travelguide;

-- 5️⃣ Create tables inside the travelguide schema
CREATE TABLE IF NOT EXISTS travellers (
    id SERIAL PRIMARY KEY,
    key TEXT UNIQUE NOT NULL,
    data JSONB NOT NULL
);

-- 6️⃣ Optional: Add more tables here, all will be owned by traveluser
-- CREATE TABLE IF NOT EXISTS destinations (
--     id SERIAL PRIMARY KEY,
--     name TEXT NOT NULL,
--     info JSONB NOT NULL
-- );

-- -------------------------------------------------------------
-- Done. All tables are owned by traveluser and ready for the app
-- -------------------------------------------------------------
