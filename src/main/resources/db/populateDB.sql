DELETE FROM user_instances;
DELETE FROM users;
DELETE FROM user_roles;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password, enabled, archived) VALUES
                   ('User', 'user@yandex.ru', '{noop}password', true, false),
                   ('Admin', 'admin@gmail.com', '{noop}admin', true, false),
                   ('Archived user', 'noname@yandex.ru', 'password', false, true);

INSERT INTO user_roles (role, user_id) VALUES
                        ('ROLE_USER', 100000),
                        ('ROLE_USER', 100001),
                        ('ROLE_ADMIN', 100001),
                        ('ROLE_USER', 100002);

INSERT INTO user_instances (name, description, user_id, cpu, ram, hdd, os, osinstance_id, running_date) VALUES
                            ('Mini 01', 'Minimum configuration', 100000, 1, 2, 50, 'CENTOS', 10001, '2018-10-26T10:26:55'),
                            ('Mini 02', 'Minimum configuration', 100000, 1, 2, 50, 'CENTOS', 10025, NULL),
                            ('Mini 03', 'Minimum configuration', 100000, 1, 2, 50, 'CENTOS', 10002, '2018-10-27T15:26:55'),
                            ('Mini 04', 'Minimum configuration', 100000, 1, 2, 50, 'CENTOS', 10003, '2018-10-27T15:36:00'),
                            ('Mini 05', 'Minimum configuration', 100000, 1, 2, 50, 'CENTOS', 10020, NULL),
                            ('Mini 06', 'Minimum configuration', 100000, 1, 2, 50, 'CENTOS', 10004, '2018-10-27T11:15:55'),
                            ('Mini 07', 'Minimum configuration', 100000, 1, 2, 50, 'CENTOS', 10005, '2018-10-27T11:25:25'),
                            ('Mini 08', 'Minimum configuration', 100000, 1, 2, 50, 'CENTOS', 10006, '2018-10-27T11:29:00'),
                            ('Mini 09', 'Minimum configuration', 100000, 1, 2, 50, 'CENTOS', 10021, NULL),
                            ('Mini 10', 'Minimum configuration', 100000, 1, 2, 50, 'CENTOS', 10022, NULL),
                            ('Mini 11', 'Minimum configuration', 100000, 1, 2, 50, 'CENTOS', 10023, NULL),
                            ('Mini 12', 'Minimum configuration', 100000, 1, 2, 50, 'CENTOS', 10007, '2018-10-27T08:10:55'),
                            ('Mini 13', 'Minimum configuration', 100000, 1, 2, 50, 'CENTOS', 10008, '2018-10-27T10:26:55'),
                            ('Mini 14', 'Minimum configuration', 100000, 1, 2, 50, 'CENTOS', 10009, '2018-10-27T12:43:25'),
                            ('Mini 15', 'Minimum configuration', 100000, 1, 2, 50, 'CENTOS', 10010, '2018-10-27T14:25:05'),
                            ('Mini 16', 'Minimum configuration', 100000, 1, 2, 50, 'CENTOS', 10024, NULL),
                            ('Mini 17', 'Minimum configuration', 100000, 1, 2, 50, 'CENTOS', 10011, '2018-10-28T17:26:55'),
                            ('Mini 18', 'Minimum configuration', 100000, 1, 2, 50, 'CENTOS', 10012, '2018-10-28T17:29:51'),
                            ('Mini 19', 'Minimum configuration', 100000, 1, 2, 50, 'CENTOS', 10013, '2018-10-28T17:33:00'),
                            ('Mini 20', 'Minimum configuration', 100000, 1, 2, 50, 'CENTOS', 10014, '2018-10-28T18:01:31'),
                            ('Mini 22', 'Minimum configuration', 100000, 1, 2, 50, 'CENTOS', 10015, '2018-10-28T18:09:16'),
                            ('Mini 23', 'Minimum configuration', 100000, 1, 2, 50, 'CENTOS', 10016, '2018-10-28T18:15:19'),
                            ('Mini 24', 'Minimum configuration', 100000, 1, 2, 50, 'CENTOS', 10017, '2018-10-28T19:44:55'),
                            ('Mini 25', 'Minimum configuration', 100000, 1, 2, 50, 'CENTOS', 10018, '2018-10-28T19:53:20'),
                            ('Power', '8/8/1000', 100000, 8, 8, 1000, 'WINDOWS_2016', 10019, '2018-10-26T12:07:21');
