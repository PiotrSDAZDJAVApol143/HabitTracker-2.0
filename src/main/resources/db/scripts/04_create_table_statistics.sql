CREATE TABLE IF NOT EXISTS STATISTICS(
    id              BIGINT PRIMARY KEY AUTO_INCREMENT,
    goal_id         BIGINT,
    habit_id        BIGINT,
    STATUS          ENUM ('IN_PROGRESS', 'PLANNED', 'COMPLETED', 'CANCELLED', 'FAILED'),
    FOREIGN KEY (goal_id) REFERENCES GOAL(id),
    FOREIGN KEY (habit_id) REFERENCES HABIT(id)
    );
