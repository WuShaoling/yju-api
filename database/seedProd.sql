USE phoenix;

SET FOREIGN_KEY_CHECKS=0;
truncate table user;
truncate table student;
truncate table teacher;
truncate table manager;
truncate table course;
truncate table resource;
truncate table course_resource;
truncate table term;
truncate table module;
truncate table module_resource;
truncate table class;
truncate table experiment;
truncate table homework;
truncate table resource;
truncate table homework_resource;
truncate table cloudware;
truncate table student_experiment;
truncate table student_class;
truncate table student_homework;
truncate table student_homework_resource;
SET FOREIGN_KEY_CHECKS=1;

INSERT INTO user (id, role, username, password) VALUES (8, '3', '40001', '$2a$10$5sUW/.g7FmRh492930n4YOi/i9uFfH96Tus.4TaxfbiP43SrQq4su');    -- 8

insert into manager (id, user_id, mno, name, gender, email, phone) values (1, 8, '40001', '上帝', 1, 'abc@gmail.com', '1234');
