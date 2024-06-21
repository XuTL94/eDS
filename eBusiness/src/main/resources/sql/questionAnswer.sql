-- 自动回复题库表
CREATE TABLE IF NOT EXISTS QUESTION_ANSWER (
    question VARCHAR(255) PRIMARY KEY,  -- 主键，问题
    answer VARCHAR(255) NOT NULL        -- 答案
);
-- 插入模拟数据
/*INSERT INTO QUESTION_ANSWER (question, answer) VALUES
('你好', '你好，有什么我可以帮助您的吗？'),
('你是谁', '我是客服助手，为您提供帮助。'),
('你能做什么', '我可以回答您的问题，帮助解决问题。'),
('谢谢', '不客气，这是我的职责。'),
('再见', '再见，祝您有美好的一天！');*/