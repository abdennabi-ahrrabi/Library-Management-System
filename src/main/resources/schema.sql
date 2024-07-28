-- schema.sql
CREATE TABLE IF NOT EXISTS Authors (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    yearBorn INT
);

-- Add other table creation scripts here
