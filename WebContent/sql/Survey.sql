select 'Creating database if it doesnot exist' as ' ';

create database if not exists SurveyDB;

use SurveyDB;

drop table Survey, Question, Answer, Statistic;

/* Create Survey Table */
select 'Creating survey table' as ' ';
create table if not exists Survey (
  survey_id smallint not null primary key,
  survey_name varchar(255) not null
);

describe Survey;

select 'Creating Question table' as ' ';
/* Create Question table */
create table if not exists Question (
    survey_id	smallint	not null,
    question_id	smallint	not null,
    question_text varchar(255) not null,
    primary key (question_id, survey_id),
    constraint FK_question_survey 
    foreign key (survey_id)
    references Survey (survey_id)
);

describe Question;

select 'Creating Answer table' as ' ';
/* Answer table */
create table if not exists Answer (
    survey_id	smallint	not null,
    answer_id   smallint    not null,
    answer_text varchar(255) not null,
    primary key (answer_id, survey_id),
    constraint FK_answer_survey 
    foreign key(survey_id)
    references Survey(survey_id)
);

describe Answer;

select 'Creating Statistic table' as ' ';
/* Statistic table */
create table if not exists Statistic (
    survey_id   smallint    not null,
    question_id smallint    not null,
    answer_id   smallint    not null,
    number_of_answer    smallint    null,
    primary key(survey_id, question_id, answer_id),

    constraint FK_statistic_survey 
    foreign key(survey_id)
    references Survey(survey_id),

    constraint FK_statistic_question 
    foreign key (question_id)
    references Question(question_id),

    constraint FK_statistic_answer 
    foreign key (answer_id) 
    references Answer(answer_id)
);

describe Statistic;


/* Insert data */


/* Insert data into Survey Table */
select 'Inserting data into Survey table' as ' ';
insert into Survey (survey_id, survey_name)
values(1,'Employee Satisfaction Survey');


/* Insert data */
select 'Inserting data into Question table' as ' ' ;
insert into Question (survey_id, question_id, question_text)
values(1,1,'The physical work environment is appropriate for the kind of work I do'),
(1,2,'I know what is expected of me at work'),
(1,3,'I am willing to go above and beyond normal job requirements to meet organizational goals'),
(1,4, 'If offered a comparable position with similar pay and benefits at a different company, I would stay within my comapny'),
(1,5, 'I feel appreciated for the contribution I make'),
(1, 6, 'The amount of work expected of me is reasonable'),
(1,7, 'I am able to maintain a balance between my personal and work activities thats right for me'),
(1,8,'I have access to the information I need to do my job'),
(1,9,'Most days, I feel that Ive accomplished something worthwhile'),
(1,10, 'Considering both my performance and the required skills for my job, I feel my total compensation is fair');


/* Insert data into Answer table */
select 'Insert data into Answer table' as ' ';
insert into Answer (survey_id, answer_id, answer_text)
values(1, 1, 'Strongly agree'),
(1, 2, 'Somewhat agree'),
(1,3, 'Neither agree/disagree'),
(1,4, 'Somewhat disagree'),
(1,5, 'Strongly disagree');
