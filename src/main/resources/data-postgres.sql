

insert into UserDetail (firstname, lastname, email, passhash, isactive) values ('akhilesh', 'krishnan', 'test@test.com', 'test', 'true') on conflict do nothing;
insert into UserDetail (firstname, lastname, email, passhash, isactive) values ('archana', 'narasimha prasar', 'test1@test.com', 'test', 'true')  on conflict do nothing;
insert into UserDetail (firstname, lastname, email, passhash, isactive) values ('atit', 'gaonkar', 'test3@test.com', 'test', 'true')  on conflict do nothing;