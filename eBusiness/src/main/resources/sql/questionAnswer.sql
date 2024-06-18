-- 自动回复题库表
CREATE TABLE IF NOT EXISTS QUESTION_ANSWER (
    question VARCHAR(255) PRIMARY KEY,  -- 主键，问题
    answer VARCHAR(255) NOT NULL        -- 答案
);
