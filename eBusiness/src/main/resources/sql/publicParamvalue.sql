create table if not exists t_public_paramvalue (
    paramname varchar(100) NOT NULL,
    paramvalue varchar(2000),
    module varchar(100),
    memo varchar(500)
);

-- 系统环境变量配置
/*INSERT INTO t_public_paramvalue (paramname, paramvalue, module, memo)
VALUES ('config', '', 'system', '');*/

/*update t_public_paramvalue set paramvalue='{}' WHERE module = 'system' AND paramname='config'*/