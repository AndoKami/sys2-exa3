CREATE DATABASE sys2;

CREATE TABLE cli (
  id UUID PRIMARY KEY,
  firstname VARCHAR(255),
  connection_datetime TIMESTAMP WITH TIME ZONE
);
