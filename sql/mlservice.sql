/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     4/13/2019 9:16:10 AM                         */
/*==============================================================*/


drop table if exists t_keys;

drop table if exists t_order;

drop table if exists t_pay_package;

drop table if exists t_prodinfo;

drop table if exists t_product;

drop table if exists t_user;

drop table if exists t_userctl;

/*==============================================================*/
/* Table: t_keys                                                */
/*==============================================================*/
create table t_keys
(
   kid                  int not null auto_increment,
   uid                  int,
   ppid                 int,
   appkey               varchar(16),
   status               int,
   count                int,
   primary key (kid)
);

/*==============================================================*/
/* Table: t_order                                               */
/*==============================================================*/
create table t_order
(
   oid                  int not null auto_increment,
   ppid                 int,
   uid                  int,
   time_stamp           datetime,
   pay_sum              float,
   primary key (oid)
);

/*==============================================================*/
/* Table: t_pay_package                                         */
/*==============================================================*/
create table t_pay_package
(
   ppid                 int not null auto_increment ,
   pid                  int not null,
   batch                int,
   type                 int,
   price                float,
   primary key (ppid)
);

/*==============================================================*/
/* Table: t_prodinfo                                            */
/*==============================================================*/
create table t_prodinfo
(
   pid                  int not null,
   title                varchar(100),
   pict                 varchar(200),
   descr                varchar(500),
   detail               varchar(1000),
   primary key (pid)
);

/*==============================================================*/
/* Table: t_product                                             */
/*==============================================================*/
create table t_product
(
   pid                  int not null auto_increment,
   prodname             varchar(100),
   prodpict_path        varchar(200),
   prod_service         varchar(100),
   primary key (pid)
);

/*==============================================================*/
/* Table: t_user                                                */
/*==============================================================*/
create table t_user
(
   uid                  int not null auto_increment,
   username             varchar(100),
   nickname             varchar(100),
   email                char(100) not null,
   password             varchar(200) not null,
   realname             varchar(100),
   isMale               bool,
   contact              varchar(200),
   address              varchar(300),
   status               int,
   primary key (uid)
);

/*==============================================================*/
/* Table: t_userctl                                             */
/*==============================================================*/
create table t_userctl
(
   uid                  int not null,
   type                 int not null,
   status               int not null,
   primary key (uid)
);

alter table t_keys add constraint FK_Reference_7 foreign key (uid)
      references t_user (uid) on delete restrict on update restrict;

alter table t_keys add constraint FK_Reference_8 foreign key (ppid)
      references t_pay_package (ppid) on delete restrict on update restrict;

alter table t_order add constraint FK_Reference_5 foreign key (uid)
      references t_user (uid) on delete restrict on update restrict;

alter table t_order add constraint FK_Reference_6 foreign key (ppid)
      references t_pay_package (ppid) on delete restrict on update restrict;

alter table t_pay_package add constraint FK_Reference_3 foreign key (pid)
      references t_product (pid) on delete restrict on update restrict;

alter table t_prodinfo add constraint FK_Reference_2 foreign key (pid)
      references t_product (pid) on delete restrict on update restrict;

alter table t_userctl add constraint FK_Reference_1 foreign key (uid)
      references t_user (uid) on delete restrict on update restrict;

