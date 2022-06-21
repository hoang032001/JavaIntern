CREATE TABLE account(
                        id int not null auto_increment,
                        username varchar(50)  not null,
                        password varchar(150) not null ,
                        status int not null,
                        primary key (id),
                        constraint UC_Username unique (username)
);

#=============================================================================
CREATE TABLE horse(
                      id int not null auto_increment,
                      name varchar(255) not null ,
                      foaled datetime not null ,
                      primary key (id)
);
#=============================================================================
CREATE TABLE horse_account(
                              id int not null auto_increment,
                              horse_id int not null ,
                              account_id int not null ,
                              archive int not null,
                              primary key (id),
                              constraint FK_Account_HorseAccount foreign key (account_id) references account(id),
                              constraint FK_Horse_HorseAccount foreign key (horse_id) references horse(id)
);
#=============================================================================
CREATE TABLE trainer(
                        id int not null auto_increment,
                        name varchar(255) not null,
                        account_id int not null,
                        primary key (id),
                        constraint FK_AccountTrainer foreign key (account_id) references account(id)
);
