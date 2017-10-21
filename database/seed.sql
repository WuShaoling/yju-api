USE phoenix;

INSERT INTO user (id, role, username) VALUES (1, '1', '张君义');  -- 1
INSERT INTO user (id, role, username) VALUES (2, '1', '何雨');    -- 2
INSERT INTO user (id, role, username) VALUES (3, '1', '施文');    -- 3
INSERT INTO user (id, role, username) VALUES (4, '1', '张礼庆');  -- 4
INSERT INTO user (id, role, username) VALUES (5, '2', '邱娟');    -- 5
INSERT INTO user (id, role, username) VALUES (6, '2', '王伟');    -- 6
INSERT INTO user (id, role, username) VALUES (7, '2', '杜庆峰');  -- 7
INSERT INTO user (id, role, username) VALUES (8, '3', '上帝');    -- 8

INSERT INTO student (id, user_id, sno, name, gender, birthday) VALUES (1, 1, '10001', '张君义', '1', '2017-07-01');
INSERT INTO student (id, user_id, sno, name, gender, birthday) VALUES (2, 2, '10002', '何雨', '1', '2017-07-01');
INSERT INTO student (id, user_id, sno, name, gender, birthday) VALUES (3, 3, '10003', '施文', '1', '2017-07-01');
INSERT INTO student (id, user_id, sno, name, gender, birthday) VALUES (4, 4, '10004', '张礼庆', '1', '2017-07-01');

INSERT INTO teacher (id, user_id, tno, name, gender, title, email, phone) VALUES (1, 5, '20001', '邱娟', '1', '3', 'abc@live.com', '1234');
INSERT INTO teacher (id, user_id, tno, name, gender, title, email, phone) VALUES (2, 6, '20002', '王伟', '1', '2', 'efd@live.com', '41234');
INSERT INTO teacher (id, user_id, tno, name, gender, title, email, phone) VALUES (3, 7, '20003', '杜庆峰', '1', '1', 'asd@live.com', '1234');

insert into manager (id, user_id, mno, name, gender, email, phone) values (1, 8, '40001', '上帝', 1, 'abc@gmail.com', '1234');

insert into course (id, teacher_id, name, description) values (1, 5, "大数据_邱", "邱老师大数据");   -- 1
insert into course (id, teacher_id, name, description) values (2, 5, "AI_邱", "邱老师AI");         -- 2
insert into course (id, teacher_id, name, description) values (3, 6, "大数据_王", "王老师大数据");   -- 3
insert into course (id, teacher_id, name, description) values (4, 6, "AI_邱", "王老师AI");         -- 4
insert into course (id, teacher_id, name, description) values (5, 6, "机器学习_邱", "王老师机器学习");-- 5
insert into course (id, teacher_id, name, description) values (6, 7, "软件工程_杜", "杜老师软件");   -- 6
insert into course (id, teacher_id, name, description) values (7, 7, "测试_杜", "杜老师测试");       -- 7

insert into resource(id, name, url, width, height) values (1, "邱大数据", "1.jpg", "", "");  -- 1
insert into resource(id, name, url, width, height) values (2, "邱AI", "2.jpg", "", "");     -- 2
insert into resource(id, name, url, width, height) values (3, "王大数据", "3.jpg", "", "");  -- 3
insert into resource(id, name, url, width, height) values (4, "王AI", "4.jpg", "", "");     -- 4
insert into resource(id, name, url, width, height) values (5, "王机器学习", "5.jpg", "", "");-- 5
insert into resource(id, name, url, width, height) values (6, "杜软件工程", "6.jpg", "", "");-- 6
insert into resource(id, name, url, width, height) values (7, "杜测试", "7.jpg", "", "");   -- 7

insert into course_resource(id, course_id, resource_id, type) values (1, 1, 1, 1);  -- 1
insert into course_resource(id, course_id, resource_id, type) values (2, 2, 2, 1);  -- 2
insert into course_resource(id, course_id, resource_id, type) values (3, 3, 3, 1);  -- 3
insert into course_resource(id, course_id, resource_id, type) values (4, 4, 4, 1);  -- 4
insert into course_resource(id, course_id, resource_id, type) values (5, 5, 5, 1);  -- 5
insert into course_resource(id, course_id, resource_id, type) values (6, 6, 6, 1);  -- 6
insert into course_resource(id, course_id, resource_id, type) values (7, 7, 7, 1);  -- 7

insert into term (id, year, semester) values (1, "2014-2015", 1);
insert into term (id, year, semester) values (2, "2014-2015", 2);

insert into module (id, course_id, name) values (1, 1, "课时邱大数据-1");  -- 1
insert into module (id, course_id, name) values (2, 1, "课时邱大数据-2");  -- 2
insert into module (id, course_id, name) values (3, 2, "课时邱AI-1");     -- 3
insert into module (id, course_id, name) values (4, 2, "课时邱AI-2");     -- 4
insert into module (id, course_id, name) values (5, 2, "课时邱AI-3");     -- 5
insert into module (id, course_id, name) values (6, 3, "课时王大数据-1");  -- 6
insert into module (id, course_id, name) values (7, 4, "课时王AI-1");     -- 7
insert into module (id, course_id, name) values (8, 4, "课时王AI-2");     -- 8
insert into module (id, course_id, name) values (9, 4, "课时王AI-3");     -- 9
insert into module (id, course_id, name) values (10, 4, "课时王AI-4");     -- 10
insert into module (id, course_id, name) values (11, 5, "课时王机器学习-1");-- 11
insert into module (id, course_id, name) values (12, 5, "课时王机器学习-2");-- 12
insert into module (id, course_id, name) values (13, 6, "课时杜软件工程-1");-- 13
insert into module (id, course_id, name) values (14, 7, "课时杜测试-1");   -- 14

insert into class (id, term_id, course_id, date, duration, student_num, name) values (1, 1, 1, '2014-2-3', '1h20min', 20, '班1');-- 1
insert into class (id, term_id, course_id, date, duration, student_num, name) values (2, 2, 1, '2014-2-3', '1h20min', 20, '班2');-- 2
insert into class (id, term_id, course_id, date, duration, student_num, name) values (3, 1, 2, '2014-2-3', '1h20min', 20, '班3');-- 3
insert into class (id, term_id, course_id, date, duration, student_num, name) values (4, 2, 3, '2014-2-3', '1h20min', 20, '班4');-- 4
insert into class (id, term_id, course_id, date, duration, student_num, name) values (5, 1, 4, '2014-2-3', '1h20min', 20, '班5');-- 5
insert into class (id, term_id, course_id, date, duration, student_num, name) values (6, 2, 4, '2014-2-3', '1h20min', 20, '班6');-- 6
insert into class (id, term_id, course_id, date, duration, student_num, name) values (7, 1, 5, '2014-2-3', '1h20min', 20, '班7');-- 7
insert into class (id, term_id, course_id, date, duration, student_num, name) values (8, 2, 6, '2014-2-3', '1h20min', 20, '班8');-- 8

insert into experiment (id, module_id, name, description, cloudware_type, publish_date, deadline_date) values
  									(1, 1, "邱大数据-1-实验", "实验一", 1, '2014-03-02', '2014-03-04');  -- 1
insert into experiment (id, module_id, name, description, cloudware_type, publish_date, deadline_date) values
  									(2, 2, "邱大数据-2-实验", "实验一", 1, '2014-03-02', '2014-03-04');  -- 2
insert into experiment (id, module_id, name, description, cloudware_type, publish_date, deadline_date) values
  									(3, 3, "邱AI-1-实验", "实验一", 1, '2014-03-02', '2014-03-04');     -- 3
insert into experiment (id, module_id, name, description, cloudware_type, publish_date, deadline_date) values
  									(4, 4, "邱AI-2-实验", "实验一", 1, '2014-03-02', '2014-03-04');     -- 4
insert into experiment (id, module_id, name, description, cloudware_type, publish_date, deadline_date) values
  									(5, 5, "邱AI-3-实验", "实验一", 1, '2014-03-02', '2014-03-04');     -- 5
insert into experiment (id, module_id, name, description, cloudware_type, publish_date, deadline_date) values
  									(6, 6, "王大数据-1-实验", "实验一", 1, '2014-03-02', '2014-03-04');  -- 6
insert into experiment (id, module_id, name, description, cloudware_type, publish_date, deadline_date) values
  									(7, 7, "王AI-1-实验", "实验一", 1, '2014-03-02', '2014-03-04');     -- 7
insert into experiment (id, module_id, name, description, cloudware_type, publish_date, deadline_date) values
  									(8, 8, "王AI-2-实验", "实验一", 1, '2014-03-02', '2014-03-04');     -- 8
insert into experiment (id, module_id, name, description, cloudware_type, publish_date, deadline_date) values
  									(9, 9, "王AI-3-实验", "实验一", 1, '2014-03-02', '2014-03-04');     -- 9
insert into experiment (id, module_id, name, description, cloudware_type, publish_date, deadline_date) values
  									(10, 11, "王机器学习-1-实验", "实验一", 1, '2014-03-02', '2014-03-04');-- 10
insert into experiment (id, module_id, name, description, cloudware_type, publish_date, deadline_date) values
  									(11, 12, "王机器学习-2-实验", "实验一", 1, '2014-03-02', '2014-03-04');-- 11
insert into experiment (id, module_id, name, description, cloudware_type, publish_date, deadline_date) values
  									(12, 13, "杜软件工程-1-实验", "实验一", 1, '2014-03-02', '2014-03-04');-- 12

insert into homework (id, module_id, class_id, name, description, cloudware_type, publish_date, deadline_date) values
                    	(1, 1, 2, "课时邱大数据-1-班-2", "abc", 1, "2014-04-03", "2014-04-07"); -- 1
insert into homework (id, module_id, class_id, name, description, cloudware_type, publish_date, deadline_date) values
                    	(2, 2, 1, "课时邱大数据-2-班-1", "abc", 1, "2014-04-03", "2014-04-07"); -- 2
insert into homework (id, module_id, class_id, name, description, cloudware_type, publish_date, deadline_date) values
                    	(3, 2, 2, "课时邱大数据-2-班-2", "abc", 1, "2014-04-03", "2014-04-07"); -- 3
insert into homework (id, module_id, class_id, name, description, cloudware_type, publish_date, deadline_date) values
                    	(4, 3, 3, "课时邱AI-1-班-3", "abc", 1, "2014-04-03", "2014-04-07");    -- 4
insert into homework (id, module_id, class_id, name, description, cloudware_type, publish_date, deadline_date) values
                    	(5, 5, 3, "课时邱AI-3-班-3", "abc", 1, "2014-04-03", "2014-04-07");    -- 5
insert into homework (id, module_id, class_id, name, description, cloudware_type, publish_date, deadline_date) values
                    	(6, 8, 5, "课时王AI-2-班-5", "abc", 1, "2014-04-03", "2014-04-07");    -- 6
insert into homework (id, module_id, class_id, name, description, cloudware_type, publish_date, deadline_date) values
                    	(7, 13, 8, "课时杜软件工程-1-班-8", "abc", 1, "2014-04-03", "2014-04-07");-- 7

insert into resource(id, name, url, width, height) values (8, "邱大数据-1-班-2", "1.jpg", "", "");  -- 8
insert into resource(id, name, url, width, height) values (9, "邱大数据-2-班-1", "2.jpg", "", "");  -- 9
insert into resource(id, name, url, width, height) values (10, "邱大数据-1-班-3", "3.jpg", "", "");  -- 10
insert into resource(id, name, url, width, height) values (11, "邱AI-1-班-3", "4.jpg", "", "");     -- 11
insert into resource(id, name, url, width, height) values (12, "邱AI-3-班-3", "5.jpg", "", "");     -- 12
insert into resource(id, name, url, width, height) values (13, "王AI-2-班-5", "6.jpg", "", "");     -- 13
insert into resource(id, name, url, width, height) values (14, "杜软件工程-1-班-8", "7.jpg", "", "");-- 14

insert into homework_resource (id, homework_id, resource_id, type) values (1, 1, 8,  1);   -- 1
insert into homework_resource (id, homework_id, resource_id, type) values (2, 2, 9,  1);   -- 2
insert into homework_resource (id, homework_id, resource_id, type) values (3, 3, 10,  1);  -- 3
insert into homework_resource (id, homework_id, resource_id, type) values (4, 4, 11,  1);  -- 4
insert into homework_resource (id, homework_id, resource_id, type) values (5, 5, 12,  1);  -- 5
insert into homework_resource (id, homework_id, resource_id, type) values (6, 6, 13,  1);  -- 6
insert into homework_resource (id, homework_id, resource_id, type) values (7, 7, 14,  1);  -- 7

insert into cloudware (id, web_socket) values (1, "192.168.1.1"); -- 1
insert into cloudware (id, web_socket) values (2, "192.168.1.2"); -- 2
insert into cloudware (id, web_socket) values (3, "192.168.1.3"); -- 3
insert into cloudware (id, web_socket) values (4, "192.168.1.4"); -- 4
insert into cloudware (id, web_socket) values (5, "192.168.1.5"); -- 5
insert into cloudware (id, web_socket) values (6, "192.168.1.6"); -- 6
insert into cloudware (id, web_socket) values (7, "192.168.1.7"); -- 7
insert into cloudware (id, web_socket) values (8, "192.168.1.8"); -- 8
insert into cloudware (id, web_socket) values (9, "192.168.1.9"); -- 9
insert into cloudware (id, web_socket) values (10, "192.168.1.10"); -- 10

insert into student_experiment (id, student_id, experiment_id, cloudware_id) values (1, 1, 2, 1);
insert into student_experiment (id, student_id, experiment_id, cloudware_id) values (2, 1, 4, 2);
insert into student_experiment (id, student_id, experiment_id, cloudware_id) values (3, 2, 1, 3);
insert into student_experiment (id, student_id, experiment_id, cloudware_id) values (4, 2, 7, 4);
insert into student_experiment (id, student_id, experiment_id, cloudware_id) values (5, 2, 10, 5);
insert into student_experiment (id, student_id, experiment_id, cloudware_id) values (6, 3, 7, 6);
insert into student_experiment (id, student_id, experiment_id, cloudware_id) values (7, 3, 2, 7);
insert into student_experiment (id, student_id, experiment_id, cloudware_id) values (8, 3, 10, 8);
insert into student_experiment (id, student_id, experiment_id, cloudware_id) values (9, 4, 5, 9);
insert into student_experiment (id, student_id, experiment_id, cloudware_id) values (10, 4, 9, 10);

insert into student_class (id, student_id, class_id) values (1, 1, 1); -- 1
insert into student_class (id, student_id, class_id) values (2, 2, 1); -- 2
insert into student_class (id, student_id, class_id) values (3, 3, 1); -- 3
insert into student_class (id, student_id, class_id) values (4, 4, 1); -- 4
insert into student_class (id, student_id, class_id) values (5, 1, 2); -- 5
insert into student_class (id, student_id, class_id) values (6, 2, 2); -- 6
insert into student_class (id, student_id, class_id) values (7, 3, 2); -- 7
insert into student_class (id, student_id, class_id) values (8, 4, 2); -- 8
insert into student_class (id, student_id, class_id) values (9, 1, 5); -- 9
insert into student_class (id, student_id, class_id) values (10, 2, 5); -- 10
insert into student_class (id, student_id, class_id) values (11, 3, 5); -- 11
insert into student_class (id, student_id, class_id) values (12, 4, 5); -- 12
insert into student_class (id, student_id, class_id) values (13, 1, 8); -- 12

insert into cloudware (id, web_socket) values (11, "192.168.1.11"); -- 11
insert into cloudware (id, web_socket) values (12, "192.168.1.12"); -- 12
insert into cloudware (id, web_socket) values (13, "192.168.1.13"); -- 13
insert into cloudware (id, web_socket) values (14, "192.168.1.14"); -- 14
insert into cloudware (id, web_socket) values (15, "192.168.1.15"); -- 15
insert into cloudware (id, web_socket) values (16, "192.168.1.16"); -- 16
insert into cloudware (id, web_socket) values (17, "192.168.1.17"); -- 17

insert into student_homework (id, student_id, homework_id, cloudware_id, comment, score, submission_date, lastEdit_date) values
  										(1, 1, 2, 11, "good", 100, "2014-07-01", "2014-07-01"); -- 1
insert into student_homework (id, student_id, homework_id, cloudware_id, comment, score, submission_date, lastEdit_date) values
  										(2, 2, 2, 12, "good", 100, "2014-07-01", "2014-07-01"); -- 2
insert into student_homework (id, student_id, homework_id, cloudware_id, comment, score, submission_date, lastEdit_date) values
  										(3, 3, 1, 13, "good", 100, "2014-07-01", "2014-07-01"); -- 3
insert into student_homework (id, student_id, homework_id, cloudware_id, comment, score, submission_date, lastEdit_date) values
  										(4, 1, 1, 14, "good", 100, "2014-07-01", "2014-07-01"); -- 4
insert into student_homework (id, student_id, homework_id, cloudware_id, comment, score, submission_date, lastEdit_date) values
  										(5, 2, 6, 15, "good", 100, "2014-07-01", "2014-07-01"); -- 5
insert into student_homework (id, student_id, homework_id, cloudware_id, comment, score, submission_date, lastEdit_date) values
  										(6, 3, 6, 16, "good", 100, "2014-07-01", "2014-07-01"); -- 6
insert into student_homework (id, student_id, homework_id, cloudware_id, comment, score, submission_date, lastEdit_date) values
  										(7, 4, 6, 17, "good", 100, "2014-07-01", "2014-07-01"); -- 7

insert into resource(id, name, url, width, height) values (15, "作业1", "1.pdf", "", "");  -- 15
insert into resource(id, name, url, width, height) values (16, "作业2", "2.pdf", "", "");  -- 16
insert into resource(id, name, url, width, height) values (17, "作业3", "3.pdf", "", "");  -- 17
insert into resource(id, name, url, width, height) values (18, "作业4", "4.pdf", "", "");  -- 18
insert into resource(id, name, url, width, height) values (19, "作业5", "5.pdf", "", "");  -- 19
insert into resource(id, name, url, width, height) values (20, "作业6", "6.pdf", "", "");  -- 20
insert into resource(id, name, url, width, height) values (21, "作业7", "7.pdf", "", "");  -- 21

insert into student_homework_resource (id, student_homework_id, resource_id, type) values (1, 1, 15, 2);
insert into student_homework_resource (id, student_homework_id, resource_id, type) values (2, 2, 16, 2);
insert into student_homework_resource (id, student_homework_id, resource_id, type) values (3, 3, 17, 2);
insert into student_homework_resource (id, student_homework_id, resource_id, type) values (4, 4, 18, 2);
insert into student_homework_resource (id, student_homework_id, resource_id, type) values (5, 5, 19, 2);
insert into student_homework_resource (id, student_homework_id, resource_id, type) values (6, 6, 20, 2);
insert into student_homework_resource (id, student_homework_id, resource_id, type) values (7, 7, 21, 2);
