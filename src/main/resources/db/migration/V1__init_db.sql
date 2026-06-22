CREATE TABLE roles (
   id SERIAL PRIMARY KEY,
   name VARCHAR(20) UNIQUE NOT NULL -- 'ROLE_ADMIN', 'ROLE_USER'
);

CREATE TABLE users (
   id BIGSERIAL PRIMARY KEY,
   email VARCHAR(100) UNIQUE NOT NULL,
   password VARCHAR(255), -- Cho phép NULL nếu đăng nhập bằng OAuth2 (Google/FB)
   full_name VARCHAR(100) NOT NULL,
   avatar_url TEXT,
   auth_provider VARCHAR(20) DEFAULT 'LOCAL', -- 'LOCAL', 'GOOGLE', 'FACEBOOK'
   provider_id VARCHAR(255), -- ID độc nhất do Google/FB trả về
   role_id INT NOT NULL,
   created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
   updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

   CONSTRAINT fk_user_role FOREIGN KEY (role_id) REFERENCES roles(id)
);

CREATE TABLE topics (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    description VARCHAR(255),
    user_id BIGINT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_topic_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

CREATE TABLE vocabularies (
      id BIGSERIAL PRIMARY KEY,
      word VARCHAR(100) NOT NULL,
      word_type VARCHAR(20) NOT NULL, -- 'Noun', 'Verb', 'Adjective'
      definition TEXT NOT NULL,
      sample_sentence TEXT,
      image_url TEXT, -- URL public từ Firebase Storage
      audio_url TEXT, -- URL public từ Firebase Storage
      topic_id BIGINT NOT NULL,
      created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
      updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

      CONSTRAINT fk_vocab_topic FOREIGN KEY (topic_id) REFERENCES topics(id) ON DELETE CASCADE
);