ALTER TABLE channel
    ADD COLUMN created_at timestamp;

ALTER TABLE video
    ADD COLUMN created_at timestamp;

ALTER TABLE channel
    ADD COLUMN updated_at timestamp;

ALTER TABLE video
    ADD COLUMN updated_at timestamp;