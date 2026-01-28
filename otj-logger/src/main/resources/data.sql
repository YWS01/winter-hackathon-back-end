-- seed initial data (idempotent via MERGE)
MERGE INTO users_apprentice (ID, COACHID, NAME, OTJWEEKLY_RATE, COURSE_START_DATE, COURSEID) KEY(ID) VALUES (1, 1, 'Yara Soler', 6, DATE '2025-01-01', 1);
MERGE INTO users_apprentice (ID, COACHID, NAME, OTJWEEKLY_RATE, COURSE_START_DATE, COURSEID) KEY(ID) VALUES (2, 1, 'Alex Data', 6, DATE '2025-02-01', 1);

MERGE INTO category (ID, CATEGORY) KEY(ID) VALUES (1, 'Conferences');

MERGE INTO users_coach (ID, NAME) KEY(ID) VALUES (1, 'Sam Osborne');

MERGE INTO course (ID, COURSE, IS_OFF_THE_JOB, TOTALOTJHOURS) KEY(ID) VALUES (1, 'Software Development', FALSE, 396);

-- Holidays: Yara existing, Alex with holidayMode = true to test behavior
MERGE INTO holiday (ID, APPRENTICEID, HOLIDAY_DAYS, HOLIDAY_MODE) KEY(ID) VALUES (1, 1, 5, FALSE);
MERGE INTO holiday (ID, APPRENTICEID, HOLIDAY_DAYS, HOLIDAY_MODE) KEY(ID) VALUES (2, 2, 2, TRUE);

-- Journal entries (add entry for Alex about a data talk)
MERGE INTO journal_entries (ID, APPRENTICEID, TITLE, DESCRIPTION, CREATION, LAST_UPDATED, CHECKED_BY_COACH_STATUS, CHECKEDBY_COACHID, CHECKEDBY_COACH_TIME, CATEGORYID) KEY(ID) VALUES
  (1, 1, 'AWS Summit', 'AWS Summit convention. As an apprentice, attending the AWS Summit was a valuable opportunity to deepen my understanding of cloud technologies and how they''re applied in real-world environments. Throughout the day, I explored sessions on serverless architecture, cloud security best practices, and cost-optimisation strategies, gaining a clearer picture of how to design scalable and efficient systems. I also learned about new AWS services, watched live demos, and spoke with engineers who shared insights into industry trends and practical problem-solving. The experience broadened my technical knowledge, boosted my confidence in using cloud tools, and inspired new ideas that I''m excited to bring back to my team.', TIMESTAMP '2025-12-22 00:00:00', TIMESTAMP '2025-12-22 00:00:00', NULL, NULL, NULL, 1);

MERGE INTO journal_entries (ID, APPRENTICEID, TITLE, DESCRIPTION, CREATION, LAST_UPDATED, CHECKED_BY_COACH_STATUS, CHECKEDBY_COACHID, CHECKEDBY_COACH_TIME, CATEGORYID) KEY(ID) VALUES
  (2, 1, 'OTJ activity - assessment support', 'Assisted with candidate assessment and practiced evidence collection and feedback.', TIMESTAMP '2026-01-19 00:00:00', TIMESTAMP '2026-01-19 00:00:00', NULL, NULL, NULL, NULL);

MERGE INTO journal_entries (ID, APPRENTICEID, TITLE, DESCRIPTION, CREATION, LAST_UPDATED, CHECKED_BY_COACH_STATUS, CHECKEDBY_COACHID, CHECKEDBY_COACH_TIME, CATEGORYID) KEY(ID) VALUES
  (3, 2, 'Data talk', 'Watched a talk about data online and learned a lot.', TIMESTAMP '2026-01-20 00:00:00', TIMESTAMP '2026-01-20 00:00:00', NULL, NULL, NULL, 1);

-- KSBs
MERGE INTO ksbs (ID, TYPE, NAME, DESCRIPTION) KEY(ID) VALUES (1, 'K', 'Cloud fundamentals', 'Understanding core cloud concepts: regions, compute, storage, networking.');
MERGE INTO ksbs (ID, TYPE, NAME, DESCRIPTION) KEY(ID) VALUES (2, 'S', 'Cloud security', 'Basic cloud security best-practices: IAM, least privilege, encryption.');
MERGE INTO ksbs (ID, TYPE, NAME, DESCRIPTION) KEY(ID) VALUES (3, 'B', 'Cost optimisation', 'Understanding cost drivers and simple optimisation strategies.');

-- journal_entry_ksbs
MERGE INTO journal_entry_ksbs (ID, KSBID, JOURNALID) KEY(ID) VALUES (1, 1, 1);
MERGE INTO journal_entry_ksbs (ID, KSBID, JOURNALID) KEY(ID) VALUES (2, 2, 1);
MERGE INTO journal_entry_ksbs (ID, KSBID, JOURNALID) KEY(ID) VALUES (3, 3, 1);
MERGE INTO journal_entry_ksbs (ID, KSBID, JOURNALID) KEY(ID) VALUES (4, 1, 2);

-- Timesheets: existing ones and one for Alex that logs 8 hours on 2026-01-20
MERGE INTO timesheets (ID, JOURNALID, START_DATE, END_DATE) KEY(ID) VALUES (1, 1, TIMESTAMP '2025-12-22 09:00:00', TIMESTAMP '2025-12-22 17:00:00');
MERGE INTO timesheets (ID, JOURNALID, START_DATE, END_DATE) KEY(ID) VALUES (2, 2, TIMESTAMP '2026-01-19 09:00:00', TIMESTAMP '2026-01-19 12:00:00');
MERGE INTO timesheets (ID, JOURNALID, START_DATE, END_DATE) KEY(ID) VALUES (3, 3, TIMESTAMP '2026-01-20 09:00:00', TIMESTAMP '2026-01-20 17:00:00');

