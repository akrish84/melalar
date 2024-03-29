SET SEARCH_PATH=melalar;

CREATE TABLE IF NOT EXISTS User_Detail (
    id SERIAL NOT NULL PRIMARY KEY,
    first_Name VARCHAR(50) NOT NULL,
    last_Name VARCHAR(50) NOT NULL,
    email VARCHAR(62) NOT NULL UNIQUE,
    pass_hash VARCHAR(128) NOT NULL,
    is_Active BOOLEAN DEFAULT TRUE
);

CREATE TABLE IF NOT EXISTS User_Job_Application_Status (
    id SERIAL NOT NULL PRIMARY KEY,
    user_id INTEGER REFERENCES user_detail (id) ON DELETE CASCADE,
    status_name VARCHAR(50) NOT NULL UNIQUE  
);

CREATE TABLE IF NOT EXISTS Job_Resume (
    id SERIAL NOT NULL PRIMARY KEY,
    user_id INTEGER REFERENCES user_detail(id) ON DELETE CASCADE,
    file_name VARCHAR(50) NOT NULL,
    file_path VARCHAR(1000) NOT NULL,
    file_hash VARCHAR(1000) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS Job_Application (
    id SERIAL NOT NULL PRIMARY KEY,
    company_name VARCHAR(50) NOT NULL ,
    position_applied VARCHAR(50),
    aps_name VARCHAR(50),
    notes VARCHAR(1000),
    job_description VARCHAR(5000),
    date_applied BIGINT,
    user_id INTEGER REFERENCES user_detail(id) ON DELETE CASCADE,
    status_Id INTEGER REFERENCES user_job_application_status(id) ON DELETE CASCADE
 ); 

 CREATE TABLE IF NOT EXISTS Job_Application_Rank (
     job_application_id INTEGER REFERENCES job_application(id) ON DELETE CASCADE,
     rank INTEGER NOT NULL
 );

 CREATE TABLE IF NOT EXISTS Job_Application_Resume (
     job_application_id INTEGER REFERENCES Job_Application(id) ON DELETE CASCADE,
     resume_id INTEGER REFERENCES Job_Resume(id) ON DELETE CASCADE
 );


 ALTER TABLE user_job_application_status ADD COLUMN rank INTEGER;

 ALTER TABLE user_job_application_status DROP CONSTRAINT user_job_application_status_status_name_key;