insert into user (user_id, username, email_address, password, provider_type, user_status, role)
values (1, 'admin', 'admin@test.com', 'admin', 'LOCAL', 0, 'ROLE_ADMIN');

insert into user (user_id, username, email_address, password, provider_type, user_status, role)
values (2, 'user', 'user@test.com', 'user', 'LOCAL', 0, 'ROLE_USER');

--주소
insert into address(id, user_id, zipcode, address_detail, address_name, recipient_name, recipient_phone, main_address)
values(1, 1, 11111, '115동 1302호', '인천광역시 연수구 원인재로 212', '이재석', '010-1234-1234', true);

-- Material
insert into material(id, material_name, surface_name)
values(1, 'Plastic', '무광')
insert into material(id, material_name, surface_name)
values(2, 'Plastic', '유광')

--

--주문
insert into user_order(id, order_number, user_id, total_price, receipt_id, method_name, status, order_status)
values(1, '2304011', 1, 5000, '6265f5cce38c300045508c75', null, null, '미결제');
