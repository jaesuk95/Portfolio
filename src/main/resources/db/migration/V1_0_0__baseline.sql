create table allowed_ip_address
(
    id            bigint auto_increment
        primary key,
    identity_name varchar(255) null,
    ip_address    varchar(255) null,
    permission    bit          not null,
    use_purpose   varchar(255) null,
    constraint UK_9auxtsowvmb5t7p0krysofuww
        unique (ip_address)
);

create table attachment
(
    id                bigint auto_increment
        primary key,
    activation        bit          not null,
    file_name         varchar(255) null,
    file_path         varchar(255) null,
    file_type         varchar(255) null,
    public_image      bit          not null,
    public_url        varchar(255) null,
    thumbnail_created bit          not null
);

create table product_category
(
    id            bigint auto_increment
        primary key,
    category_name varchar(255) null
);

create table product
(
    type        varchar(31)  not null,
    id          bigint auto_increment
        primary key,
    model_name  varchar(255) null,
    name        varchar(255) null,
    price       int          not null,
    phone_type  varchar(255) null,
    sale        bit          null,
    category_id bigint       null,
    constraint FK5cypb0k23bovo3rn1a5jqs6j4
        foreign key (category_id) references product_category (id)
);

create table user
(
    user_id       bigint auto_increment
        primary key,
    email_address varchar(100) not null,
    oauth_id      varchar(255) null,
    password      varchar(255) not null,
    phone_number  varchar(255) null,
    provider_type varchar(255) null,
    role          varchar(255) null,
    user_status   int          not null,
    username      varchar(50)  not null,
    constraint UK_sb8bbouer5wak8vyiiy4pf2bx
        unique (username)
);

create table address
(
    id              bigint auto_increment
        primary key,
    address_detail  varchar(255) null,
    address_name    varchar(255) null,
    main_address    bit          not null,
    recipient_name  varchar(255) null,
    recipient_phone varchar(255) null,
    zipcode         varchar(255) null,
    user_id         bigint       null,
    constraint FKda8tuywtf0gb6sedwk7la1pgi
        foreign key (user_id) references user (user_id)
);

create table email_confirmation
(
    id           bigint auto_increment
        primary key,
    confirmed_at datetime     null,
    created_at   datetime     not null,
    expires_at   datetime     not null,
    token        varchar(255) not null,
    user_id      bigint       not null,
    constraint FKotmu0hwd8qsh6h8kt1ge4vube
        foreign key (user_id) references user (user_id)
);

create table user_address
(
    user_user_id bigint not null,
    address_id   bigint not null,
    constraint UK_m3t1qb7j0fluav2a0kphxyoue
        unique (address_id),
    constraint FKcp5ewg2f9dl5bi95u5ebqu265
        foreign key (user_user_id) references user (user_id),
    constraint FKdaaxogn1ss81gkcsdn05wi6jp
        foreign key (address_id) references address (id)
);

create table user_order
(
    id                  bigint auto_increment
        primary key,
    cancel_reason       varchar(255) null,
    cancelled_at        varchar(255) null,
    method              varchar(255) null,
    method_name         varchar(255) null,
    order_number        varchar(255) null,
    order_status        int          null,
    pg                  varchar(255) null,
    pg_name             varchar(255) null,
    purchased_at        varchar(255) null,
    receipt_id          varchar(255) null,
    receipt_url         varchar(255) null,
    revoked_at          varchar(255) null,
    server_cancelled_at datetime     null,
    status              bigint       null,
    total_price         int          not null,
    user_id             bigint       null,
    constraint UK_dvq1fljb8e38wgkkyab2jrhl0
        unique (order_number),
    constraint FKj86u1x7csa8yd68ql2y1ibrou
        foreign key (user_id) references user (user_id)
);

create table order_detail
(
    id                  bigint auto_increment
        primary key,
    detail_status       int          null,
    option_json         longtext     null,
    order_detail_number varchar(255) null,
    product_price       int          not null,
    quantity            int          not null,
    address_id          bigint       null,
    product_id          bigint       null,
    order_id            bigint       null,
    constraint UK_7fcufwb0h07vvqk5owou5hqt5
        unique (order_detail_number),
    constraint FKb8bg2bkty0oksa3wiq5mp5qnc
        foreign key (product_id) references product (id),
    constraint FKe5y9i4lwrtp140e2nrghy92yf
        foreign key (order_id) references user_order (id),
    constraint FKworhp4pd7gwxpn8l493chbxe
        foreign key (address_id) references address (id)
);

