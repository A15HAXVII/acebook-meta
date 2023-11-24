ALTER TABLE posts
ADD username varchar(50) NOT NULL REFERENCES users(username) ON DELETE CASCADE;