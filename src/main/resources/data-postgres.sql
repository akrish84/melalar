

insert into User_Detail (first_name, last_name, email, pass_hash, is_active) values ('akhilesh', 'krishnan', 'test@test.com', 'test', 'true') on conflict do nothing;
insert into User_Detail (first_name, last_name, email, pass_hash, is_active) values ('archana', 'narasimha prasar', 'test1@test.com', 'test', 'true')  on conflict do nothing;
insert into User_Detail (first_name, last_name, email, pass_hash, is_active) values ('atit', 'gaonkar', 'test3@test.com', 'test', 'true')  on conflict do nothing;


insert into job_application_status(status_name) values('applied');
insert into job_application_status(status_name) values('coding round');
insert into job_application_status(status_name) values('wishlist');
