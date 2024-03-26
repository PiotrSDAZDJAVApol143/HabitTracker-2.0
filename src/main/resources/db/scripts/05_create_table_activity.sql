CREATE TABLE IF NOT EXISTS ACTIVITY (
    id               BIGINT PRIMARY KEY AUTO_INCREMENT,
    ACTIVITY_NAME    VARCHAR(255),
    TIME_OF_ACTIVITY TIME,
    DATE_OF_ACTIVITY DATE NOT NULL,
    DURATION         INT,
    habit_id         BIGINT NOT NULL,
    statistics_id    BIGINT,
    FOREIGN KEY (habit_id) REFERENCES HABIT(id),
    FOREIGN KEY (statistics_id) REFERENCES STATISTICS(id)
    );