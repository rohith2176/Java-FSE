create database community_event_portal;
use community_event_portal;

-- =============================================
-- schema creation
-- =============================================

create table users(
	user_id INT PRIMARY KEY AUTO_INCREMENT,
    full_name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    city VARCHAR(100) NOT NULL,
    registration_date DATE NOT NULL
);

create table events(
	event_id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(200) NOT NULL,
    description TEXT,
    city VARCHAR(100) NOT NULL,
    start_date DATETIME NOT NULL,
    end_date DATETIME NOT NULL,
    status ENUM('upcoming', 'completed', 'cancelled'),
    organizer_id INT,
    foreign key(organizer_id) references users(user_id)
);

create table sessions(
	session_id INT PRIMARY KEY AUTO_INCREMENT,
    event_id INT,
    title VARCHAR(200) NOT NULL,
    speaker_name VARCHAR(100) NOT NULL,
    start_time DATETIME NOT NULL,
    end_time DATETIME NOT NULL,
    foreign key(event_id) references events(event_id)
);

create table registrations(
	registration_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT,
    event_id INT,
    registration_date DATE NOT NULL,
    foreign key(user_id) references users(user_id),
    foreign key(event_id) references events(event_id)
);

create table feedback(
	feedback_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT,
    event_id INT,
    rating INT CHECK(rating between 1 and 5),
    comments TEXT,
    feedback_date DATE NOT NULL,
    foreign key(user_id) references users(user_id),
    foreign key(event_id) references events(event_id)
);

create table resources(
	resource_id INT PRIMARY KEY AUTO_INCREMENT,
    event_id INT,
    resource_type ENUM('pdf', 'image', 'link'),
    resource_url VARCHAR(255) NOT NULL,
    uploaded_at DATETIME NOT NULL,
    foreign key(event_id) references events(event_id)
);

-- =============================================
-- sample data insertion
-- =============================================

insert into users values
(1, 'Alice Johnson', 'alice@example.com', 'New York', '2024-12-01'),
(2, 'Bob Smith', 'bob@example.com', 'Los Angeles', '2024-12-05'),
(3, 'Charlie Lee', 'charlie@example.com', 'Chicago', '2024-12-10'),
(4, 'Diana King', 'diana@example.com', 'New York', '2025-01-15'),
(5, 'Ethan Hunt', 'ethan@example.com', 'Los Angeles', '2025-02-01');

insert into events values
(1, 'Tech Innovators Meetup', 'A meetup for tech enthusiasts.', 'New York', '2025-06-10 10:00:00', '2025-06-10 16:00:00', 'upcoming', 1),
(2, 'AI & ML Conference', 'Conference on AI and ML advancements.', 'Chicago', '2025-05-15 09:00:00', '2025-05-15 17:00:00', 'completed', 3),
(3, 'Frontend Development Bootcamp', 'Hands-on training on frontend tech.', 'Los Angeles', '2025-07-01 10:00:00', '2025-07-03 16:00:00', 'upcoming', 2);

insert into sessions values
(1, 1, 'Opening Keynote', 'Dr. Tech', '2025-06-10 10:00:00', '2025-06-10 11:00:00'),
(2, 1, 'Future of Web Dev', 'Alice Johnson', '2025-06-10 11:15:00', '2025-06-10 12:30:00'),
(3, 2, 'AI in Healthcare', 'Charlie Lee', '2025-05-15 09:30:00', '2025-05-15 11:00:00'),
(4, 3, 'Intro to HTML5', 'Bob Smith', '2025-07-01 10:00:00', '2025-07-01 12:00:00');

insert into registrations values
(1, 1, 1, '2025-05-01'),
(2, 2, 1, '2025-05-02'),
(3, 3, 2, '2025-04-30'),
(4, 4, 2, '2025-04-28'),
(5, 5, 3, '2025-06-15');

insert into feedback values
(1, 3, 2, 4, 'Great insights!', '2025-05-16'),
(2, 4, 2, 5, 'Very informative.', '2025-05-16'),
(3, 2, 1, 3, 'Could be better.', '2025-06-11');

insert into resources values
(1, 1, 'pdf', 'https://portal.com/resources/tech_meetup_agenda.pdf', '2025-05-01 10:00:00'),
(2, 2, 'image', 'https://portal.com/resources/ai_poster.jpg', '2025-04-20 09:00:00'),
(3, 3, 'link', 'https://portal.com/resources/html5_docs', '2025-06-25 15:00:00');

-- =============================================
-- exercises
-- =============================================

-- 1. user upcoming events
-- show all upcoming events a user is registered for in their city, sorted by date
select u.full_name as user_name, e.title as event_title, e.city, e.start_date
from users as u
join registrations as r on u.user_id=r.user_id
join events as e on r.event_id=e.event_id
where e.status='upcoming'
and e.city=u.city
order by e.start_date;

-- 2. top rated events
-- events with highest average rating having at least 10 feedback submissions
select e.title as event_title, avg(f.rating) as average_rating, count(f.feedback_id) as total_feedback
from events as e
join feedback as f on e.event_id=f.event_id
group by e.event_id
having count(f.feedback_id) >= 10
order by average_rating desc;

-- 3. inactive users
-- users who have not registered for any events in the last 90 days
select u.user_id, u.full_name, u.email
from users as u
where u.user_id not in(
	select r.user_id
    from registrations as r
    where r.registration_date >= curdate() - interval 90 day
);

-- 4. peak session hours
-- count sessions scheduled between 10 AM to 12 PM for each event
select e.title as event_title, count(s.session_id) as session_count
from events as e
join sessions as s on e.event_id=s.event_id
where time(s.start_time) >= '10:00:00'
and time(s.start_time) < '12:00:00'
group by e.event_id;

-- 5. most active cities
-- top 5 cities with the highest number of distinct user registrations
select u.city, count(distinct r.user_id) as total_registrations
from users as u
join registrations as r on u.user_id=r.user_id
group by u.city
order by total_registrations desc
limit 5;

-- 6. event resource summary
-- number of resources (pdfs, images, links) uploaded for each event
select e.title as event_title,
    sum(case when r.resource_type='pdf' then 1 else 0 end) as pdfs,
    sum(case when r.resource_type='image' then 1 else 0 end) as images,
    sum(case when r.resource_type='link' then 1 else 0 end) as links,
    count(r.resource_id) as total_resources
from events as e
left join resources as r on e.event_id=r.event_id
group by e.event_id;

-- 7. low feedback alerts
-- users who gave feedback with rating less than 3, along with comments and event names
select u.full_name as user_name, e.title as event_title, f.rating, f.comments
from feedback as f
join users as u on f.user_id=u.user_id
join events as e on f.event_id=e.event_id
where f.rating < 3;

-- 8. sessions per upcoming event
-- upcoming events with count of sessions scheduled for them
select e.title as event_title, count(s.session_id) as session_count
from events as e
left join sessions as s on e.event_id=s.event_id
where e.status='upcoming'
group by e.event_id;

-- 9. organizer event summary
-- for each organizer, number of events created and their current status
select u.full_name as organizer_name, e.status, count(e.event_id) as event_count
from users as u
join events as e on u.user_id=e.organizer_id
group by u.user_id, e.status;

-- 10. feedback gap
-- events that had registrations but received no feedback
select e.title as event_title, count(r.registration_id) as total_registrations
from events as e
join registrations as r on e.event_id=r.event_id
where e.event_id not in(
    select distinct f.event_id
    from feedback as f
)
group by e.event_id;

-- 11. daily new user count
-- number of users who registered each day in the last 7 days
select registration_date, count(user_id) as new_users
from users
where registration_date >= curdate() - interval 7 day
group by registration_date
order by registration_date;

-- 12. event with maximum sessions
-- event(s) with the highest number of sessions
select e.title as event_title, count(s.session_id) as session_count
from events as e
join sessions as s on e.event_id=s.event_id
group by e.event_id
having count(s.session_id) = (
    select max(session_count)
    from (
        select count(session_id) as session_count
        from sessions
        group by event_id
    ) as session_counts
);

-- 13. average rating per city
-- average feedback rating of events conducted in each city
select e.city, avg(f.rating) as average_rating
from events as e
join feedback as f on e.event_id=f.event_id
group by e.city;

-- 14. most registered events
-- top 3 events based on total number of user registrations
select e.title as event_title, count(r.registration_id) as total_registrations
from events as e
join registrations as r on e.event_id=r.event_id
group by e.event_id
order by total_registrations desc
limit 3;

-- 15. event session time conflict
-- overlapping sessions within the same event
select a.session_id as session_1, b.session_id as session_2, a.event_id, a.title as session_1_title, b.title as session_2_title
from sessions as a
join sessions as b on a.event_id=b.event_id
and a.session_id < b.session_id
and a.start_time < b.end_time
and a.end_time > b.start_time;

-- 16. unregistered active users
-- users who created account in last 30 days but haven't registered for any events
select u.user_id, u.full_name, u.email, u.registration_date
from users as u
where u.registration_date >= curdate() - interval 30 day
and u.user_id not in(
    select distinct r.user_id
    from registrations as r
);

-- 17. multi-session speakers
-- speakers handling more than one session across all events
select speaker_name, count(session_id) as session_count
from sessions
group by speaker_name
having count(session_id) > 1;

-- 18. resource availability check
-- events that do not have any resources uploaded
select e.title as event_title
from events as e
where e.event_id not in(
    select distinct r.event_id
    from resources as r
);

-- 19. completed events with feedback summary
-- for completed events, total registrations and average feedback rating
select e.title as event_title,
    count(distinct r.registration_id) as total_registrations,
    avg(f.rating) as average_rating
from events as e
left join registrations as r on e.event_id=r.event_id
left join feedback as f on e.event_id=f.event_id
where e.status='completed'
group by e.event_id;

-- 20. user engagement index
-- for each user, how many events they attended and how many feedbacks they submitted
select u.full_name as user_name,
    count(distinct r.event_id) as events_attended,
    count(distinct f.feedback_id) as feedbacks_submitted
from users as u
left join registrations as r on u.user_id=r.user_id
left join feedback as f on u.user_id=f.user_id
group by u.user_id;

-- 21. top feedback providers
-- top 5 users who submitted most feedback entries
select u.full_name as user_name, count(f.feedback_id) as feedback_count
from users as u
join feedback as f on u.user_id=f.user_id
group by u.user_id
order by feedback_count desc
limit 5;

-- 22. duplicate registrations check
-- detect if a user has been registered more than once for the same event
select user_id, event_id, count(*) as registration_count
from registrations
group by user_id, event_id
having count(*) > 1;

-- 23. registration trends
-- month-wise registration count trend over the past 12 months
select date_format(registration_date, '%Y-%m') as month, count(*) as total_registrations
from registrations
where registration_date >= curdate() - interval 12 month
group by date_format(registration_date, '%Y-%m')
order by month;

-- 24. average session duration per event
-- average duration (in minutes) of sessions in each event
select e.title as event_title,
    avg(timestampdiff(minute, s.start_time, s.end_time)) as avg_duration_minutes
from events as e
join sessions as s on e.event_id=s.event_id
group by e.event_id;

-- 25. events without sessions
-- all events that currently have no sessions scheduled
select e.title as event_title
from events as e
where e.event_id not in(
    select distinct s.event_id
    from sessions as s
);
