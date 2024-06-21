-- 模拟器路径表
CREATE TABLE IF NOT EXISTS simulator_paths (
    simulator_type int PRIMARY KEY,  -- 主键，模拟器类型
    simulator_path VARCHAR(255) NOT NULL      -- 模拟器路径
);


-- 插入模拟器路径数据
/*INSERT INTO simulator_paths (simulator_type, simulator_path) VALUES
(0, 'F:\andorid\leidian\LDPlayer9')*/