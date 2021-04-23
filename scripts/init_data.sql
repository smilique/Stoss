insert into role set ID = 1, NAME = 'admin';
insert into role set ID = 2, NAME = 'user';

insert into user set login = 'admin', password = md5('admin'), name = 'Anton', ROLE_ID = 1, points = 0, balance = 0;
insert into user set login = 'nobody', password = md5('error01'), name = 'Someone', ROLE_ID = 2, points = 0, balance = 0;
insert into user set login = 'aleeya', password = md5('alc'), name = 'Alexey', ROLE_ID = 2, points = 0, balance = 0;
insert into user set login = 'nice', password = md5('12qw45f1'), name = 'Lera', ROLE_ID = 2, points = 0, balance = 0;
insert into user set login = 'backupper', password = md5('1q2w43q'), name = 'Igor', ROLE_ID = 2, points = 0, balance = 0;

