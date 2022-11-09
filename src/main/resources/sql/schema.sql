create table if not exists t_workout (
    id int not null auto_increment,
    user varchar(45) null,
    start datetime null,
    end datetime null,
    difficulty int null,
    primary key (id)
);
