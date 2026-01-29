-- seed initial data (idempotent via MERGE)
MERGE INTO users_apprentice (ID, COACHID, NAME, OTJWEEKLY_RATE, COURSE_START_DATE, COURSEID) KEY(ID) VALUES (1, 1, 'Yara Soler', 6, DATE '2025-01-01', 1);
MERGE INTO users_apprentice (ID, COACHID, NAME, OTJWEEKLY_RATE, COURSE_START_DATE, COURSEID) KEY(ID) VALUES (2, 1, 'Alex Data', 6, DATE '2025-02-01', 1);

-- categories
MERGE INTO category (ID, CATEGORY) KEY(ID) VALUES (1, 'Workshops & Training');
MERGE INTO category (ID, CATEGORY) KEY(ID) VALUES (2, 'Online Learning');
MERGE INTO category (ID, CATEGORY) KEY(ID) VALUES (3, 'Mentoring & Shadowing');
MERGE INTO category (ID, CATEGORY) KEY(ID) VALUES (4, 'Practical/Workplace Learning');
MERGE INTO category (ID, CATEGORY) KEY(ID) VALUES (5, 'Group Learning');
MERGE INTO category (ID, CATEGORY) KEY(ID) VALUES (6, 'Learning Support');
MERGE INTO category (ID, CATEGORY) KEY(ID) VALUES (7, 'OTHER');

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

-- Categories (exact list requested)
MERGE INTO category (ID, CATEGORY) KEY(ID) VALUES (1, 'Workshops & Training');
MERGE INTO category (ID, CATEGORY) KEY(ID) VALUES (2, 'Online Learning');
MERGE INTO category (ID, CATEGORY) KEY(ID) VALUES (3, 'Mentoring & Shadowing');
MERGE INTO category (ID, CATEGORY) KEY(ID) VALUES (4, 'Practical/Workplace Learning');
MERGE INTO category (ID, CATEGORY) KEY(ID) VALUES (5, 'Group Learning');
MERGE INTO category (ID, CATEGORY) KEY(ID) VALUES (6, 'OTHER');

-- Coaches (Sam is the primary/frequent coach)
MERGE INTO users_coach (ID, NAME) KEY(ID) VALUES (1, 'Sam Osborne');
MERGE INTO users_coach (ID, NAME) KEY(ID) VALUES (2, 'Nina Patel');
MERGE INTO users_coach (ID, NAME) KEY(ID) VALUES (3, 'Marco Ruiz');
MERGE INTO users_coach (ID, NAME) KEY(ID) VALUES (4, 'Elaine Turner');

-- Courses (OTJ true for all except Plumbing)
MERGE INTO course (ID, COURSE, IS_OFF_THE_JOB, TOTALOTJHOURS) KEY(ID) VALUES (1, 'Software Development', TRUE, 396);
MERGE INTO course (ID, COURSE, IS_OFF_THE_JOB, TOTALOTJHOURS) KEY(ID) VALUES (2, 'Marketing', TRUE, 240);
MERGE INTO course (ID, COURSE, IS_OFF_THE_JOB, TOTALOTJHOURS) KEY(ID) VALUES (3, 'HR', TRUE, 300);
MERGE INTO course (ID, COURSE, IS_OFF_THE_JOB, TOTALOTJHOURS) KEY(ID) VALUES (4, 'Plumbing', FALSE, 1200);
MERGE INTO course (ID, COURSE, IS_OFF_THE_JOB, TOTALOTJHOURS) KEY(ID) VALUES (5, 'Data Analytics', TRUE, 420);

-- Apprentices (existing plus new profiles)
MERGE INTO users_apprentice (ID, COACHID, NAME, OTJWEEKLY_RATE, COURSE_START_DATE, COURSEID) KEY(ID) VALUES (1, 1, 'Yara Soler', 6, DATE '2025-01-01', 1);
MERGE INTO users_apprentice (ID, COACHID, NAME, OTJWEEKLY_RATE, COURSE_START_DATE, COURSEID) KEY(ID) VALUES (2, 1, 'Alex Data', 6, DATE '2025-02-01', 1);
MERGE INTO users_apprentice (ID, COACHID, NAME, OTJWEEKLY_RATE, COURSE_START_DATE, COURSEID) KEY(ID) VALUES (3, 1, 'Harry Styles', 6, DATE '2024-02-15', 2);
MERGE INTO users_apprentice (ID, COACHID, NAME, OTJWEEKLY_RATE, COURSE_START_DATE, COURSEID) KEY(ID) VALUES (4, 3, 'Ariana Grande', 4, DATE '2023-09-01', 3);
MERGE INTO users_apprentice (ID, COACHID, NAME, OTJWEEKLY_RATE, COURSE_START_DATE, COURSEID) KEY(ID) VALUES (5, 1, 'Freddie Mercury', 40, DATE '2022-08-01', 4);
MERGE INTO users_apprentice (ID, COACHID, NAME, OTJWEEKLY_RATE, COURSE_START_DATE, COURSEID) KEY(ID) VALUES (6, 1, 'David Bowie', 6, DATE '2025-01-10', 1);
MERGE INTO users_apprentice (ID, COACHID, NAME, OTJWEEKLY_RATE, COURSE_START_DATE, COURSEID) KEY(ID) VALUES (7, 1, 'Dua Lipa', 8, DATE '2024-06-01', 5);
MERGE INTO users_apprentice (ID, COACHID, NAME, OTJWEEKLY_RATE, COURSE_START_DATE, COURSEID) KEY(ID) VALUES (8, 1, 'Billie Eilish', 6, DATE '2023-10-01', 1);

-- Holidays (one per apprentice to reflect holidayMode where applicable)
MERGE INTO holiday (ID, APPRENTICEID, HOLIDAY_DAYS, HOLIDAY_MODE) KEY(ID) VALUES (1, 1, 5, FALSE);
MERGE INTO holiday (ID, APPRENTICEID, HOLIDAY_DAYS, HOLIDAY_MODE) KEY(ID) VALUES (2, 2, 2, TRUE);
MERGE INTO holiday (ID, APPRENTICEID, HOLIDAY_DAYS, HOLIDAY_MODE) KEY(ID) VALUES (3, 3, 1, TRUE);
MERGE INTO holiday (ID, APPRENTICEID, HOLIDAY_DAYS, HOLIDAY_MODE) KEY(ID) VALUES (4, 4, 10, TRUE);
MERGE INTO holiday (ID, APPRENTICEID, HOLIDAY_DAYS, HOLIDAY_MODE) KEY(ID) VALUES (5, 5, 0, FALSE);
MERGE INTO holiday (ID, APPRENTICEID, HOLIDAY_DAYS, HOLIDAY_MODE) KEY(ID) VALUES (6, 6, 3, FALSE);
MERGE INTO holiday (ID, APPRENTICEID, HOLIDAY_DAYS, HOLIDAY_MODE) KEY(ID) VALUES (7, 7, 4, TRUE);
MERGE INTO holiday (ID, APPRENTICEID, HOLIDAY_DAYS, HOLIDAY_MODE) KEY(ID) VALUES (8, 8, 7, FALSE);

-- KSBs (example knowledge/skills/behaviours)
MERGE INTO ksbs (ID, TYPE, NAME, DESCRIPTION) KEY(ID) VALUES (1, 'K', 'Cloud fundamentals', 'Understanding core cloud concepts: regions, compute, storage, networking.');
MERGE INTO ksbs (ID, TYPE, NAME, DESCRIPTION) KEY(ID) VALUES (2, 'S', 'Cloud security', 'Basic cloud security best-practices: IAM, least privilege, encryption.');
MERGE INTO ksbs (ID, TYPE, NAME, DESCRIPTION) KEY(ID) VALUES (3, 'B', 'Cost optimisation', 'Understanding cost drivers and simple optimisation strategies.');

-- Journal entries with detailed descriptions (what I learned, how it helps, evidence/next steps)
MERGE INTO journal_entries (ID, APPRENTICEID, TITLE, DESCRIPTION, CREATION, LAST_UPDATED, CHECKED_BY_COACH_STATUS, CHECKEDBY_COACHID, CHECKEDBY_COACH_TIME, CATEGORYID) KEY(ID) VALUES
  (1, 1, 'AWS Summit', 'What I learned: frameworks for serverless design, security controls and cost management. How this helps daily work: clearer design decisions for services and more effective cost-conscious choices. Evidence / next steps: notes and session slides added to team drive; will propose a small serverless proof-of-concept.', TIMESTAMP '2025-12-22 00:00:00', TIMESTAMP '2025-12-22 00:00:00', NULL, NULL, NULL, 1);

MERGE INTO journal_entries (ID, APPRENTICEID, TITLE, DESCRIPTION, CREATION, LAST_UPDATED, CHECKED_BY_COACH_STATUS, CHECKEDBY_COACHID, CHECKEDBY_COACH_TIME, CATEGORYID) KEY(ID) VALUES
  (2, 1, 'OTJ activity - assessment support', 'What I learned: candidate assessment techniques and evidence collection. How this helps daily work: improved quality of evidence and faster assessment cycles. Evidence / next steps: contributed to assessment notes; will co-run next assessment.', TIMESTAMP '2026-01-19 00:00:00', TIMESTAMP '2026-01-19 00:00:00', NULL, NULL, NULL, 6);

MERGE INTO journal_entries (ID, APPRENTICEID, TITLE, DESCRIPTION, CREATION, LAST_UPDATED, CHECKED_BY_COACH_STATUS, CHECKEDBY_COACHID, CHECKEDBY_COACH_TIME, CATEGORYID) KEY(ID) VALUES
  (3, 2, 'Data talk', 'What I learned: overview of practical analytics patterns and stakeholder storytelling. How this helps daily work: improved framing of analyses for non-technical stakeholders. Evidence / next steps: shared notes with team and updated the analysis template.', TIMESTAMP '2026-01-20 00:00:00', TIMESTAMP '2026-01-20 00:00:00', NULL, NULL, NULL, 2);

MERGE INTO journal_entries (ID, APPRENTICEID, TITLE, DESCRIPTION, CREATION, LAST_UPDATED, CHECKED_BY_COACH_STATUS, CHECKEDBY_COACHID, CHECKEDBY_COACH_TIME, CATEGORYID) KEY(ID) VALUES
  (4, 3, 'Social media campaign workshop', 'What I learned: frameworks for paid vs organic channels, briefing templates and content calendar templates. How this helps daily work: produce weekly calendars aligned to KPIs and optimise ad spend. Evidence / next steps: created a 1-week calendar used by the team; plan A/B tests for ad copy.', TIMESTAMP '2024-03-10 00:00:00', TIMESTAMP '2024-03-10 00:00:00', NULL, NULL, NULL, 1);

MERGE INTO journal_entries (ID, APPRENTICEID, TITLE, DESCRIPTION, CREATION, LAST_UPDATED, CHECKED_BY_COACH_STATUS, CHECKEDBY_COACHID, CHECKEDBY_COACH_TIME, CATEGORYID) KEY(ID) VALUES
  (5, 3, 'Mentored by marketing manager', 'What I learned: KPI selection, translating briefs into measurable objectives and prioritisation. How this helps daily work: better reporting and clearer campaign priorities. Evidence / next steps: drafted KPIs for current campaign; will present to manager next week.', TIMESTAMP '2024-05-22 00:00:00', TIMESTAMP '2024-05-22 00:00:00', NULL, NULL, NULL, 3);

MERGE INTO journal_entries (ID, APPRENTICEID, TITLE, DESCRIPTION, CREATION, LAST_UPDATED, CHECKED_BY_COACH_STATUS, CHECKEDBY_COACHID, CHECKEDBY_COACH_TIME, CATEGORYID) KEY(ID) VALUES
  (6, 3, 'Group brand sprint', 'What I learned: collaborative persona mapping and rapid prototyping of messaging. How this helps daily work: faster alignment with product and design on audience targeting. Evidence / next steps: included personas in campaign brief and will measure engagement by segment.', TIMESTAMP '2024-09-04 00:00:00', TIMESTAMP '2024-09-04 00:00:00', NULL, NULL, NULL, 5);

MERGE INTO journal_entries (ID, APPRENTICEID, TITLE, DESCRIPTION, CREATION, LAST_UPDATED, CHECKED_BY_COACH_STATUS, CHECKEDBY_COACHID, CHECKEDBY_COACH_TIME, CATEGORYID) KEY(ID) VALUES
  (7, 4, 'On-site recruitment fair support', 'What I learned: rapid CV triage, structured pre-interview questioning and candidate engagement techniques. How this helps daily work: reduced shortlist time and improved first-stage interviews. Evidence / next steps: produced shortlist and updated pre-interview checklist for future events.', TIMESTAMP '2023-11-15 00:00:00', TIMESTAMP '2023-11-15 00:00:00', NULL, NULL, NULL, 4);

MERGE INTO journal_entries (ID, APPRENTICEID, TITLE, DESCRIPTION, CREATION, LAST_UPDATED, CHECKED_BY_COACH_STATUS, CHECKEDBY_COACHID, CHECKEDBY_COACH_TIME, CATEGORYID) KEY(ID) VALUES
  (8, 4, 'Online GDPR compliance module', 'What I learned: practical steps for data minimisation, retention schedules and handling SARs. How this helps daily work: informed updates to candidate data retention policy and daily personal data handling. Evidence / next steps: passed assessment; will update team SOP and run a training session.', TIMESTAMP '2024-02-02 00:00:00', TIMESTAMP '2024-02-02 00:00:00', NULL, NULL, NULL, 2);

MERGE INTO journal_entries (ID, APPRENTICEID, TITLE, DESCRIPTION, CREATION, LAST_UPDATED, CHECKED_BY_COACH_STATUS, CHECKEDBY_COACHID, CHECKEDBY_COACH_TIME, CATEGORYID) KEY(ID) VALUES
  (9, 4, 'Mentoring: employee relations case', 'What I learned: investigation steps, drafting formal HR communications and escalation paths. How this helps daily work: increased confidence drafting letters and documenting case notes. Evidence / next steps: drafted anonymised case note example for the team.', TIMESTAMP '2024-06-10 00:00:00', TIMESTAMP '2024-06-10 00:00:00', NULL, NULL, NULL, 3);

MERGE INTO journal_entries (ID, APPRENTICEID, TITLE, DESCRIPTION, CREATION, LAST_UPDATED, CHECKED_BY_COACH_STATUS, CHECKEDBY_COACHID, CHECKEDBY_COACH_TIME, CATEGORYID) KEY(ID) VALUES
  (10, 5, 'On-site pipe replacement', 'What I learned: end-to-end copper pipe replacement procedure, joint testing and pressure-check sequences. How this helps daily work: able to carry out typical domestic replacements independently and reduce supervisor time onsite. Evidence / next steps: completed under supervision and signed off; will perform two solo replacements next month.', TIMESTAMP '2023-10-20 00:00:00', TIMESTAMP '2023-10-20 00:00:00', NULL, NULL, NULL, 4);

MERGE INTO journal_entries (ID, APPRENTICEID, TITLE, DESCRIPTION, CREATION, LAST_UPDATED, CHECKED_BY_COACH_STATUS, CHECKEDBY_COACHID, CHECKEDBY_COACH_TIME, CATEGORYID) KEY(ID) VALUES
  (11, 5, 'Mentored: complex valve assembly', 'What I learned: assembly sequence for pressure-reducing valves and safety checks. How this helps daily work: reduces rework and improves first-time-right installations. Evidence / next steps: assisted on install; coach to sign evidence sheet after next independent install.', TIMESTAMP '2024-01-12 00:00:00', TIMESTAMP '2024-01-12 00:00:00', NULL, NULL, NULL, 3);

MERGE INTO journal_entries (ID, APPRENTICEID, TITLE, DESCRIPTION, CREATION, LAST_UPDATED, CHECKED_BY_COACH_STATUS, CHECKEDBY_COACHID, CHECKEDBY_COACH_TIME, CATEGORYID) KEY(ID) VALUES
  (12, 5, 'Group safety briefing & toolbox talk', 'What I learned: confined space risks, lockout-tagout and emergency response. How this helps daily work: improved site safety behaviour and hazard spotting for the team. Evidence / next steps: shared toolbox notes with crew.', TIMESTAMP '2024-04-03 00:00:00', TIMESTAMP '2024-04-03 00:00:00', NULL, NULL, NULL, 5);

MERGE INTO journal_entries (ID, APPRENTICEID, TITLE, DESCRIPTION, CREATION, LAST_UPDATED, CHECKED_BY_COACH_STATUS, CHECKEDBY_COACHID, CHECKEDBY_COACH_TIME, CATEGORYID) KEY(ID) VALUES
  (13, 6, 'Pair-programming microservice', 'What I learned: designing a REST endpoint, contract-first approach and writing unit/integration tests. How this helps daily work: faster feature delivery with fewer regressions and better test coverage. Evidence / next steps: pushed branch with tests; will lead next refactor to increase coverage by 10%.', TIMESTAMP '2026-01-05 00:00:00', TIMESTAMP '2026-01-05 00:00:00', NULL, NULL, NULL, 4);

MERGE INTO journal_entries (ID, APPRENTICEID, TITLE, DESCRIPTION, CREATION, LAST_UPDATED, CHECKED_BY_COACH_STATUS, CHECKEDBY_COACHID, CHECKEDBY_COACH_TIME, CATEGORYID) KEY(ID) VALUES
  (14, 6, 'Online Spring Boot module', 'What I learned: Spring DI, lifecycle and common pitfalls with bean scopes. How this helps daily work: clearer use of components and fewer memory leaks; applied changes to reduce startup time. Evidence / next steps: refactored one service to use proper scopes.', TIMESTAMP '2025-11-20 00:00:00', TIMESTAMP '2025-11-20 00:00:00', NULL, NULL, NULL, 2);

MERGE INTO journal_entries (ID, APPRENTICEID, TITLE, DESCRIPTION, CREATION, LAST_UPDATED, CHECKED_BY_COACH_STATUS, CHECKEDBY_COACHID, CHECKEDBY_COACH_TIME, CATEGORYID) KEY(ID) VALUES
  (15, 6, 'Workshops: CI/CD pipeline', 'What I learned: building pipelines for automated tests, blue/green deploy patterns and rollback strategy. How this helps daily work: author CI steps that run in PRs and reduce manual deployment tasks. Evidence / next steps: drafted pipeline YAML and will run staging deploy next sprint.', TIMESTAMP '2025-12-02 00:00:00', TIMESTAMP '2025-12-02 00:00:00', NULL, NULL, NULL, 1);

MERGE INTO journal_entries (ID, APPRENTICEID, TITLE, DESCRIPTION, CREATION, LAST_UPDATED, CHECKED_BY_COACH_STATUS, CHECKEDBY_COACHID, CHECKEDBY_COACH_TIME, CATEGORYID) KEY(ID) VALUES
  (16, 7, 'Data cleaning workshop', 'What I learned: robust cleaning patterns, handling missing data and practical pandas idioms. How this helps daily work: reduces time prepping datasets and improves model inputs. Evidence / next steps: published cleaned dataset and notebook; will standardise in team repo.', TIMESTAMP '2024-07-12 00:00:00', TIMESTAMP '2024-07-12 00:00:00', NULL, NULL, NULL, 1);


-- Example extra timesheets for appended journal entries
MERGE INTO timesheets (ID, JOURNALID, START_DATE, END_DATE) KEY(ID) VALUES (4, 4, TIMESTAMP '2024-03-10 09:00:00', TIMESTAMP '2024-03-10 17:00:00');
MERGE INTO timesheets (ID, JOURNALID, START_DATE, END_DATE) KEY(ID) VALUES (5, 5, TIMESTAMP '2024-05-22 09:00:00', TIMESTAMP '2024-05-22 13:00:00');
MERGE INTO timesheets (ID, JOURNALID, START_DATE, END_DATE) KEY(ID) VALUES (6, 6, TIMESTAMP '2024-09-04 09:00:00', TIMESTAMP '2024-09-04 17:00:00');

-- End of appended mock data
