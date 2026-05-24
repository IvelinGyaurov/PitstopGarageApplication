-- Run once in MySQL if registration fails with "Field 'enabled' doesn't have a default value".
-- Removes duplicate column created when the entity used isActive instead of enabled.

ALTER TABLE users DROP COLUMN IF EXISTS is_active;
