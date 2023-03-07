DROP TABLE IF EXISTS task_day;
DROP TABLE IF EXISTS day;
DROP TABLE IF EXISTS task;

CREATE TABLE day(
	day_id CHARACTER(2) PRIMARY KEY,
	day_name VARCHAR(10) NOT NULL
);

CREATE TABLE task(
	task_id serial PRIMARY KEY,
	task_category VARCHAR(20) NOT NULL,
	task_name VARCHAR(40) NOT NULL
);

CREATE TABLE task_day(
	task_id integer,
	day_id CHARACTER(1),
	CONSTRAINT PK_task_day PRIMARY KEY (task_id, day_id),
	CONSTRAINT FK_task FOREIGN KEY (task_id) REFERENCES task (task_id),
	CONSTRAINT FK_day FOREIGN KEY (day_id) REFERENCES day (day_id)
);
