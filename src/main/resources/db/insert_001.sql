INSERT INTO role (role)
VALUES ('ROLE_USER');
INSERT INTO role (role)
VALUES ('ROLE_ADMIN');

INSERT INTO person (id, name, enabled, password, role_id)
VALUES (999,
        'user',
        TRUE,
        'testUserWithSpecialName_user_for_SpringMock_Post_Control_Post_Test',
        (SELECT id FROM role WHERE role = 'ROLE_ADMIN'));

INSERT INTO room (id, name, description, created, person_id)
VALUES (2000, 'room2000', 'pasta', now(), 999);

INSERT INTO message (id, description, room_id, person_id)
values (3000, 'Message for test!', 2000, 999);