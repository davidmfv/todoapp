ALTER TABLE tasks
DROP COLUMN type,
ADD COLUMN task_type_id BIGINT,
ADD CONSTRAINT fk_task_type
    FOREIGN KEY (task_type_id)
    REFERENCES task_types(id);