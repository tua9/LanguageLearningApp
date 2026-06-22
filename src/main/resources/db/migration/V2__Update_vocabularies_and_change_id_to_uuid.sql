-- =======================================================
-- MIGRATION V2: ALTER ID TO UUID & UPDATE VOCABULARIES
-- =======================================================

-- 1. DROP EXISTING FOREIGN KEYS
ALTER TABLE users DROP CONSTRAINT IF EXISTS fk_user_role;
ALTER TABLE topics DROP CONSTRAINT IF EXISTS fk_topic_user;
ALTER TABLE vocabularies DROP CONSTRAINT IF EXISTS fk_vocab_topic;

-- 2. UPDATE VOCABULARIES STRUCTURE
ALTER TABLE vocabularies
    ALTER COLUMN topic_id DROP NOT NULL,
DROP COLUMN IF EXISTS definition,
    ADD COLUMN IF NOT EXISTS level VARCHAR(10),
    ADD COLUMN IF NOT EXISTS pronunciation VARCHAR(255);

-- 3. CONVERT ID & FOREIGN KEYS TO UUID
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

-- 3.1 table: roles
ALTER TABLE roles
    ALTER COLUMN id DROP DEFAULT,
ALTER COLUMN id SET DATA TYPE UUID USING gen_random_uuid(),
    ALTER COLUMN id SET DEFAULT gen_random_uuid();

-- 3.2 table: users
ALTER TABLE users
    ALTER COLUMN id DROP DEFAULT,
ALTER COLUMN id SET DATA TYPE UUID USING gen_random_uuid(),
    ALTER COLUMN id SET DEFAULT gen_random_uuid(),
    ALTER COLUMN role_id SET DATA TYPE UUID USING NULL,
    ALTER COLUMN role_id SET NOT NULL;

-- 3.3 table: topics
ALTER TABLE topics
    ALTER COLUMN id DROP DEFAULT,
ALTER COLUMN id SET DATA TYPE UUID USING gen_random_uuid(),
    ALTER COLUMN id SET DEFAULT gen_random_uuid(),
    ALTER COLUMN user_id SET DATA TYPE UUID USING NULL,
    ALTER COLUMN user_id SET NOT NULL;

-- 3.4 table: vocabularies
ALTER TABLE vocabularies
    ALTER COLUMN id DROP DEFAULT,
ALTER COLUMN id SET DATA TYPE UUID USING gen_random_uuid(),
    ALTER COLUMN id SET DEFAULT gen_random_uuid(),
    ALTER COLUMN topic_id SET DATA TYPE UUID USING NULL;

-- 4. RE-CREATE FOREIGN KEY CONSTRAINTS
ALTER TABLE users
    ADD CONSTRAINT fk_user_role FOREIGN KEY (role_id) REFERENCES roles(id);

ALTER TABLE topics
    ADD CONSTRAINT fk_topic_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE;

ALTER TABLE vocabularies
    ADD CONSTRAINT fk_vocab_topic FOREIGN KEY (topic_id) REFERENCES topics(id) ON DELETE SET NULL;