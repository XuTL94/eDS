create table if not exists t_public_paramvalue (
    paramname varchar(100) NOT NULL,
    paramvalue varchar(2000),
    module varchar(100),
    memo varchar(500)
);
