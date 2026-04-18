CREATE TABLE IF NOT EXISTS tasks (
                                     id UUID PRIMARY KEY,
                                     name TEXT NOT NULL,
                                     description TEXT,
                                     status TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS time_records (
                                            id UUID PRIMARY KEY,
                                            employee_id UUID NOT NULL,
                                            task_id UUID REFERENCES tasks(id),
                                            start_time TIMESTAMP,
                                            end_time TIMESTAMP,
                                            description TEXT
);