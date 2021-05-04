insert into role set ID = 1, NAME = 'admin';
insert into role set ID = 2, NAME = 'user';

insert into user set login = 'admin', password = md5('admin'), name = 'Anton', ROLE_ID = 1;
insert into user set login = 'user1', password = md5('user1'), name = 'Someone';
insert into user set login = 'user2', password = md5('user2'), name = 'Alexey';
insert into user set login = 'user3', password = md5('user3'), name = 'Lera';
insert into user set login = 'user', password = md5('user'), name = 'User';

insert into news set caption = 'First news item', text = 'First news item text', time = '21/04/01 17:01:00';
insert into news set caption = 'Second news item', text = 'First news item text', time = '21/04/02 16:01:00';
insert into news set caption = 'Third news item', text = 'First news item text', time = '21/04/03 15:01:00';
insert into news set caption = 'Fourth news item', text = 'First news item text', time = '21/04/04 14:01:00';
insert into news set caption = 'Tournament starts soon!', text = 'New tournament starts in May 4th at 7p.m.', time = '21/04/11 22:01:00';

insert into chat set message = 'First message', user_id = 1, time = '21/04/07 15:01:57';
insert into chat set message = 'Second message', user_id = 2, time = '21/04/07 16:01:57';
insert into chat set message = 'Last message', user_id = 1, time = '21/04/07 18:01:57';