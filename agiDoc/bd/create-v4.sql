create sequence SEQ_COMPANY
    nocache;

create sequence SEQ_PROCESSES
    nocache;

create sequence SEQ_CONTACTS
    nocache;

create sequence SEQ_ADDRESSES
    nocache;

create sequence SEQ_DEPARTMENTS
    nocache;

create sequence SEQ_USERS
    nocache;

create sequence SEQ_DOCUMENTS
    nocache;

create sequence SEQ_CONTACTS_ASSOCIATIONS
    nocache;

create sequence SEQ_ADDRESSES_ASSOCIATIONS
    nocache;

create sequence SEQ_DOCUMENTS_ASSOCIATIONS
    nocache;

create sequence SEQ_PERMISSION
    nocache;

create table COMPANY
(
    ID_COMPANY   NUMBER(38)    not null,
    COMPANY_NAME VARCHAR2(100) not null,
    CNPJ         VARCHAR2(14)  not null,
    TYPE         NUMBER(1)     not null,
    STATUS       NUMBER(1)     not null,
    constraint PK_COMPANY
        primary key (ID_COMPANY),
    unique (CNPJ),
    check (type IN (0, 1)),
    check (status IN (0, 1))
);

create table PROCESSES
(
    ID_PROCESS     NUMBER(38)    not null,
    PROCESS_NUMBER VARCHAR2(255) not null,
    TITLE          VARCHAR2(100) not null,
    DESCRIPTION    VARCHAR2(255) not null,
    STATUS         NUMBER(1)     not null,
    constraint PK_PROCESSES
        primary key (ID_PROCESS),
    check (STATUS IN (0, 1, 2, 3))
);

create table CONTACTS
(
    ID_CONTACT NUMBER(38)    not null,
    NAME       VARCHAR2(100) not null,
    EMAIL      VARCHAR2(100) not null,
    PHONE      VARCHAR2(50)  not null,
    PHONE_TYPE NUMBER(1)     not null,
    constraint PK_CONTACTS
        primary key (ID_CONTACT),
    unique (EMAIL),
    check (PHONE_TYPE IN (1, 2))
);

create table ADDRESSES
(
    ID_ADDRESS    NUMBER(38)    not null,
    STREET        VARCHAR2(255) not null,
    DISTRICT      VARCHAR2(255) not null,
    STREET_NUMBER NUMBER(10)    not null,
    COMPLEMENT    VARCHAR2(255) not null,
    CITY          VARCHAR2(100) not null,
    STATE         VARCHAR2(100) not null,
    ZIP_CODE      VARCHAR2(8)   not null,
    constraint PK_ADDRESSES
        primary key (ID_ADDRESS)
);

create table CONTACTS_ASSOCIATIONS
(
    ID_CONTACT_ASSOCIATION NUMBER(38) not null,
    ID_CONTACT             NUMBER(38) not null,
    ID_COMPANY             NUMBER(38),
    constraint PK_CONTACTS_ASSOCIATIONS
        primary key (ID_CONTACT_ASSOCIATION),
    unique (ID_CONTACT),
    constraint FK_CONT_ASSO_COMPANYS
        foreign key (ID_COMPANY) references COMPANY,
    constraint FK_CONT_ASSO_CONTACTS
        foreign key (ID_CONTACT) references CONTACTS,
    constraint CHK_CONT_ASSO_ATRIBUTION
        check ((CASE WHEN ID_COMPANY IS NOT NULL THEN 1 ELSE 0 END) = 1)
)

create table ADDRESSES_ASSOCIATIONS
(
    ID_ADDRESS_ASSOCIATION NUMBER(38) not null,
    ID_ADDRESS             NUMBER(38) not null,
    ID_COMPANY             NUMBER(38),
    constraint PK_ADDRESSES_ASSOCIATIONS
        primary key (ID_ADDRESS_ASSOCIATION),
    unique (ID_ADDRESS),
    constraint FK_ADDR_ASSO_ADDRESSES
        foreign key (ID_ADDRESS) references ADDRESSES,
    constraint FK_ADDR_ASSO_COMPANY
        foreign key (ID_COMPANY) references COMPANY,
    constraint CHK_ADDR_ASSO_ATRIBUTION
        check ((CASE WHEN ID_COMPANY IS NOT NULL THEN 1 ELSE 0 END) = 1)
)

create table PROCESS_ASSOCIATIONS
(
    ID_PROCESS NUMBER(38) not null,
    ID_COMPANY NUMBER(38) not null,
    constraint FK_ADDR_ASSO_PROCESS
        foreign key (ID_PROCESS) references PROCESSES,
    constraint FK_COM_ASSO_COMPANY
        foreign key (ID_COMPANY) references COMPANY
);

create table USERS
(
    ID_USER      NUMBER(38)    not null,
    REGISTRATION VARCHAR2(100) not null,
    NAME         VARCHAR2(100) not null,
    USERNAME     VARCHAR2(100) not null,
    PASSWORD     VARCHAR2(100) not null,
    POSITION     VARCHAR2(100) not null,
    EMAIL        VARCHAR2(100) not null,
    STATUS       NUMBER(1)     not null,
    DEPARTMENT   NUMBER(1)     not null,
    constraint PK_USERS
        primary key (ID_USER),
    unique (REGISTRATION),
    unique (USERNAME),
    unique (EMAIL),
    check (status IN (0, 1)),
    check (DEPARTMENT IN (0, 1, 2, 3))
);

create table DOCUMENTS
(
    ID_DOCUMENT     NUMBER(38)   not null,
    PROTOCOL        VARCHAR2(50) not null,
    EXPIRATION_DATE DATE         not null,
    STATUS          NUMBER(1)    not null,
    IS_SIGNED       NUMBER(1)    not null,
    ATTACHMENT      BLOB,
    ID_SIGNATURE    NUMBER(38),
    constraint PK_DOCUMENTS
        primary key (ID_DOCUMENT),
    unique (PROTOCOL),
    constraint DOCUMENTS_USERS_ID_USER_FK
        foreign key (ID_SIGNATURE) references USERS,
    check (status IN (0, 1)),
    check (IS_SIGNED IN (0, 1))
);

create table DOCUMENTS_ASSOCIATIONS
(
    ID_DOCUMENT NUMBER(38) not null,
    ID_PROCESS  NUMBER(38) not null,
    constraint FK_DOC_ASSO_DOC
        foreign key (ID_DOCUMENT) references DOCUMENTS,
    constraint FK_PROCESS_ASSO_PROC
        foreign key (ID_PROCESS) references PROCESSES
);

create table USER_ASSOCIATIONS
(
    ID_USER    NUMBER(38) not null,
    ID_COMPANY NUMBER(38) not null,
    constraint FK_USER_ASSOCIATIONS_COMPANY
        foreign key (ID_COMPANY) references COMPANY,
    constraint FK_USER_ASSOCIATIONS_USER
        foreign key (ID_USER) references USERS
);

create table PERMISSION
(
    ID_PERMISSION NUMBER        not null,
    NAME          VARCHAR2(512) not null,
    primary key (ID_PERMISSION),
    unique (NAME)
);

create table USER_PERMISSION
(
    ID_USER       NUMBER not null,
    ID_PERMISSION NUMBER not null,
    primary key (ID_USER, ID_PERMISSION),
    constraint FK_USER_PERMISSION_PERMISSION
        foreign key (ID_PERMISSION) references PERMISSION,
    constraint FK_USER_PERMISSION_USER
        foreign key (ID_USER) references USERS
);

INSERT INTO PERMISSION (ID_PERMISSION, NAME)
VALUES (SEQ_PERMISSION.NEXTVAL, 'ROLE_SYSTEM_ADMIN')

INSERT INTO PERMISSION (ID_PERMISSION, NAME)
VALUES (SEQ_PERMISSION.NEXTVAL, 'ROLE_ADMIN_COMPETITOR')

INSERT INTO PERMISSION (ID_PERMISSION, NAME)
VALUES (SEQ_PERMISSION.NEXTVAL, 'ROLE_ADMIN_INSTITUTION')

INSERT INTO PERMISSION (ID_PERMISSION, NAME)
VALUES (SEQ_PERMISSION.NEXTVAL, 'ROLE_USER_COMPETITOR')

INSERT INTO PERMISSION (ID_PERMISSION, NAME)
VALUES (SEQ_PERMISSION.NEXTVAL, 'ROLE_USER_INSTITUTION')

INSERT INTO COMPANY (ID_COMPANY, COMPANY_NAME, CNPJ, TYPE, STATUS)
VALUES (1, 'Company Public', '12345678901234', 1, 0)

INSERT INTO USER_PERMISSION (ID_USER, ID_PERMISSION)
VALUES (1, 1);

INSERT INTO USERS (ID_USER, REGISTRATION, NAME, USERNAME, PASSWORD, POSITION, EMAIL, STATUS, DEPARTMENT)
VALUES (SEQ_USERS.NEXTVAL, 'EMP000', 'SystemAdmin', 'godMod', '560dbef7d389278231aa6855dc93ec711b54efec5cfc15edbe130bbf021a8baa6893d1c4d8e078b9', 'System Manager', 'john.doe@example.com', 1, 0);









