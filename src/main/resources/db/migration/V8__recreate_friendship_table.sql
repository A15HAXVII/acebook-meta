DROP TABLE IF EXISTS friendships;

CREATE TABLE friendships (
  id bigserial PRIMARY KEY,
  username_requester varchar(50) NOT NULL REFERENCES users(username) ON DELETE CASCADE,
  username_accepter varchar(50) NOT NULL REFERENCES users(username) ON DELETE CASCADE,
  status varchar(250) NOT NULL
);