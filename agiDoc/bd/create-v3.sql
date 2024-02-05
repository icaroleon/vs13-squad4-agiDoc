create sequence VS_13_EQUIPE_4.SEQ_COMPANY
    nocache
/

create sequence VS_13_EQUIPE_4.SEQ_PROCESSES
    nocache
/

create sequence VS_13_EQUIPE_4.SEQ_CONTACTS
    nocache
/

create sequence VS_13_EQUIPE_4.SEQ_ADDRESSES
    nocache
/

create sequence VS_13_EQUIPE_4.SEQ_DEPARTMENTS
    nocache
/

create sequence VS_13_EQUIPE_4.SEQ_USERS
    nocache
/

create sequence VS_13_EQUIPE_4.SEQ_DOCUMENTS
    nocache
/

create sequence VS_13_EQUIPE_4.SEQ_CONTACTS_ASSOCIATIONS
    nocache
/

create sequence VS_13_EQUIPE_4.SEQ_ADDRESSES_ASSOCIATIONS
    nocache
/

create sequence VS_13_EQUIPE_4.SEQ_DOCUMENTS_ASSOCIATIONS
    nocache
/

create table VS_13_EQUIPE_4.COMPANY
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
)
/

create table VS_13_EQUIPE_4.PROCESSES
(
    ID_PROCESS     NUMBER(38)    not null,
    PROCESS_NUMBER VARCHAR2(255) not null,
    TITLE          VARCHAR2(100) not null,
    DESCRIPTION    VARCHAR2(255) not null,
    STATUS         NUMBER(1)     not null,
    ID_COMPANY     NUMBER(38)    not null,
    constraint PK_PROCESSES
        primary key (ID_PROCESS),
    check (STATUS IN (0, 1, 2, 3))
)
/

create table VS_13_EQUIPE_4.CONTACTS
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
)
/

create table VS_13_EQUIPE_4.ADDRESSES
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
)
/

create table VS_13_EQUIPE_4.CONTACTS_ASSOCIATIONS
(
    ID_CONTACT_ASSOCIATION NUMBER(38) not null,
    ID_CONTACT             NUMBER(38) not null,
    ID_COMPANY             NUMBER(38),
    constraint PK_CONTACTS_ASSOCIATIONS
        primary key (ID_CONTACT_ASSOCIATION),
    unique (ID_CONTACT),
    constraint FK_CONT_ASSO_COMPANYS
        foreign key (ID_COMPANY) references VS_13_EQUIPE_4.COMPANY,
    constraint FK_CONT_ASSO_CONTACTS
        foreign key (ID_CONTACT) references VS_13_EQUIPE_4.CONTACTS,
    constraint CHK_CONT_ASSO_ATRIBUTION
        check ((CASE WHEN ID_COMPANY IS NOT NULL THEN 1 ELSE 0 END) = 1)
)
/

create table VS_13_EQUIPE_4.ADDRESSES_ASSOCIATIONS
(
    ID_ADDRESS_ASSOCIATION NUMBER(38) not null,
    ID_ADDRESS             NUMBER(38) not null,
    ID_COMPANY             NUMBER(38),
    constraint PK_ADDRESSES_ASSOCIATIONS
        primary key (ID_ADDRESS_ASSOCIATION),
    unique (ID_ADDRESS),
    constraint FK_ADDR_ASSO_ADDRESSES
        foreign key (ID_ADDRESS) references VS_13_EQUIPE_4.ADDRESSES,
    constraint FK_ADDR_ASSO_COMPANY
        foreign key (ID_COMPANY) references VS_13_EQUIPE_4.COMPANY,
    constraint CHK_ADDR_ASSO_ATRIBUTION
        check ((CASE WHEN ID_COMPANY IS NOT NULL THEN 1 ELSE 0 END) = 1)
)
/

create table VS_13_EQUIPE_4.PROCESS_ASSOCIATIONS
(
    ID_PROCESS NUMBER(38) not null,
    ID_COMPANY NUMBER(38) not null,
    constraint FK_ADDR_ASSO_PROCESS
        foreign key (ID_PROCESS) references VS_13_EQUIPE_4.PROCESSES,
    constraint FK_COM_ASSO_COMPANY
        foreign key (ID_PROCESS) references VS_13_EQUIPE_4.COMPANY
)
/

create table VS_13_EQUIPE_4.USERS
(
    ID_USER      NUMBER(38)    not null,
    REGISTRATION VARCHAR2(100) not null,
    NAME         VARCHAR2(100) not null,
    USERNAME     VARCHAR2(100) not null,
    PASSWORD     VARCHAR2(100) not null,
    PERMISSION   NUMBER(1)     not null,
    POSITION     VARCHAR2(100) not null,
    EMAIL        VARCHAR2(100) not null,
    STATUS       NUMBER(1)     not null,
    DEPARTMENT   NUMBER(1)     not null,
    constraint PK_USERS
        primary key (ID_USER),
    unique (REGISTRATION),
    unique (USERNAME),
    unique (EMAIL),
    check (permission IN (0, 1, 2)),
    check (status IN (0, 1)),
    check (DEPARTMENT IN (0, 1, 2, 3))
)
/

create table VS_13_EQUIPE_4.DOCUMENTS
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
        foreign key (ID_SIGNATURE) references VS_13_EQUIPE_4.USERS,
    check (status IN (0, 1)),
    check (IS_SIGNED IN (0, 1))
)
/

create table VS_13_EQUIPE_4.DOCUMENTS_ASSOCIATIONS
(
    ID_DOCUMENT NUMBER(38) not null,
    ID_PROCESS  NUMBER(38),
    constraint FK_DOC_ASSO_DOC
        foreign key (ID_DOCUMENT) references VS_13_EQUIPE_4.DOCUMENTS,
    constraint FK_PROCESS_ASSO_PROC
        foreign key (ID_PROCESS) references VS_13_EQUIPE_4.PROCESSES
)
/

create table VS_13_EQUIPE_4.USER_ASSOCIATIONS
(
    ID_USER    NUMBER(38)    not null,
    ID_COMPANY NUMBER(38),
    NAME       VARCHAR2(255) not null,
    STATUS     NUMBER(1)     not null,
    constraint FK_USER_ASSOCIATIONS_COMPANY
        foreign key (ID_COMPANY) references VS_13_EQUIPE_4.COMPANY,
    constraint FK_USER_ASSOCIATIONS_USER
        foreign key (ID_USER) references VS_13_EQUIPE_4.USERS,
    check (status IN (0, 1))
)
/

