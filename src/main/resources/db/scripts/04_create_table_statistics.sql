CREATE TABLE IF NOT EXISTS STATISTICS(
    id              BIGINT PRIMARY KEY AUTO_INCREMENT,
    goal_id         BIGINT,
    GOAL_NAME       VARCHAR(255),
    habit_id        BIGINT,
    HABIT_NAME      VARCHAR(255),
    PROGRESS        VARCHAR(255),
    STATUS          ENUM ('IN_PROGRESS', 'PLANNED', 'COMPLETED', 'CANCELLED', 'FAILED'),
    FOREIGN KEY (goal_id) REFERENCES GOAL(id),
    FOREIGN KEY (habit_id) REFERENCES HABIT(id)
    );
