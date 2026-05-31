INSERT INTO course (id,name) VALUES (1,'Math');
INSERT INTO course (id,name) VALUES (2,'Science');
INSERT INTO course (id,name) VALUES (3,'Python');
INSERT INTO course (id,name) VALUES (4,'Public Speaking');
INSERT INTO course (id,name) VALUES (5,'Drawing');

INSERT INTO teacher (id,name) VALUES (1,'Madhuri');
INSERT INTO teacher (id,name) VALUES (2,'Emma');
INSERT INTO teacher (id,name) VALUES (3,'Hema');
INSERT INTO teacher (id,name) VALUES (4,'Rahul');
INSERT INTO teacher (id,name) VALUES (5,'Shobbhit');

INSERT INTO parent (id, name) VALUES (1, 'Parent A');
INSERT INTO parent (id, name) VALUES (2, 'Parent B');
INSERT INTO parent (id, name) VALUES (3, 'Parent C');
INSERT INTO parent (id, name) VALUES (4, 'Parent D');
INSERT INTO parent (id, name) VALUES (5, 'Parent E');




INSERT INTO offering (course_id, teacher_id, timezone)
VALUES (1, 1, 'Asia/Kolkata');

INSERT INTO offering (course_id, teacher_id, timezone)
VALUES (2, 2, 'Asia/Kolkata');
INSERT INTO offering (course_id, teacher_id, timezone)
VALUES (3, 3, 'Asia/Kolkata');
INSERT INTO offering (course_id, teacher_id, timezone)
VALUES (4, 4, 'Asia/Kolkata');
INSERT INTO offering (course_id, teacher_id, timezone)
VALUES (5, 5, 'Asia/Kolkata');

INSERT INTO sessions (offering_id, start_time, end_time)
VALUES (1, '2026-06-01 09:00:00', '2026-06-01 10:00:00');

INSERT INTO sessions (offering_id, start_time, end_time)
VALUES (2, '2026-06-01 09:30:00', '2026-06-01 10:30:00');

INSERT INTO sessions (offering_id, start_time, end_time)
VALUES (2, '2026-06-01 10:00:00', '2026-06-01 11:00:00');

INSERT INTO sessions (offering_id, start_time, end_time)
VALUES (3, '2026-06-01 11:00:00', '2026-06-01 12:00:00');
INSERT INTO sessions (offering_id, start_time, end_time)
VALUES (4, '2026-06-01 01:00:00', '2026-06-01 02:00:00');
INSERT INTO sessions (offering_id, start_time, end_time)
VALUES (5, '2026-06-01 03:00:00', '2026-06-01 04:00:00');

