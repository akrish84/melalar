SET SEARCH_PATH=melalar;

insert into User_Detail (first_name, last_name, email, pass_hash, is_active) values ('akhilesh', 'krishnan', 'test@test.com', 'test', 'true') on conflict do nothing;
insert into User_Detail (first_name, last_name, email, pass_hash, is_active) values ('archana', 'narasimha prasar', 'test1@test.com', 'test', 'true')  on conflict do nothing;
insert into User_Detail (first_name, last_name, email, pass_hash, is_active) values ('atit', 'gaonkar', 'test3@test.com', 'test', 'true')  on conflict do nothing;


insert into user_job_application_status(user_id, status_name) values(1,'applied') on conflict do nothing;
insert into user_job_application_status(user_id, status_name) values(1,'coding round') on conflict do nothing;
insert into user_job_application_status(user_id, status_name) values(1,'wishlist') on conflict do nothing;
