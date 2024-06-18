-- 自动回复率记录表
CREATE TABLE IF NOT EXISTS RESPONSE_RATE_RECORD (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,  -- 主键，自动递增
    type VARCHAR(50) NOT NULL,             -- 类型 0商家  1顾客
    device_id VARCHAR(255) NOT NULL,       -- 设备ID
    chat_name VARCHAR(255) NOT NULL        -- 聊天对象名称
);
