prompt PL/SQL Developer import file
prompt Created on 2018年5月21日 by zhaoxuezhong
set feedback off
set define off
prompt Dropping SYS_POSITION...
drop table SYS_POSITION cascade constraints;
prompt Dropping SYS_DEPARTMENT...
drop table SYS_DEPARTMENT cascade constraints;
prompt Dropping SYS_EMPLOYEE...
drop table SYS_EMPLOYEE cascade constraints;
prompt Dropping BIZ_CLAIM_VOUCHER...
drop table BIZ_CLAIM_VOUCHER cascade constraints;
prompt Dropping BIZ_CHECK_RESULT...
drop table BIZ_CHECK_RESULT cascade constraints;
prompt Dropping BIZ_CLAIM_VOUCHER_DETAIL...
drop table BIZ_CLAIM_VOUCHER_DETAIL cascade constraints;
prompt Dropping BIZ_CLAIM_VOUCHER_STATISTICS...
drop table BIZ_CLAIM_VOUCHER_STATISTICS cascade constraints;
prompt Dropping BIZ_CLAIM_VOUYEAR__STATISTICS...
drop table BIZ_CLAIM_VOUYEAR__STATISTICS cascade constraints;
prompt Dropping BIZ_LEAVE...
drop table BIZ_LEAVE cascade constraints;

CREATE SEQUENCE SEQ_VOUCHER INCREMENT BY 1 START WITH 100 NOMAXVALUE NOCYCLE CACHE 10;
CREATE SEQUENCE SEQ_VOUCHER_DETAIL INCREMENT BY 1 START WITH 100 NOMAXVALUE NOCYCLE CACHE 10;
CREATE SEQUENCE SEQ_CHECK_RESULT INCREMENT BY 1 START WITH 100 NOMAXVALUE NOCYCLE CACHE 10;
CREATE SEQUENCE SEQ_LEAVE INCREMENT BY 1 START WITH 100 NOMAXVALUE NOCYCLE CACHE 10;
CREATE SEQUENCE hibernate_sequence INCREMENT BY 1 START WITH 100 NOMAXVALUE NOCYCLE CACHE 10;

prompt Creating SYS_POSITION...
create table SYS_POSITION
(
  id      NUMBER(10) not null,
  name_cn VARCHAR2(50) not null,
  name_en VARCHAR2(50) not null
)
tablespace JBOADB
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table SYS_POSITION
  add constraint POSIT_ID primary key (ID)
  using index 
  tablespace JBOADB
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt Creating SYS_DEPARTMENT...
create table SYS_DEPARTMENT
(
  id   NUMBER(10) not null,
  name VARCHAR2(50) not null
)
tablespace JBOADB
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 8K
    minextents 1
    maxextents unlimited
  );
alter table SYS_DEPARTMENT
  add constraint DEPARTMENT_ID primary key (ID)
  using index 
  tablespace JBOADB
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt Creating SYS_EMPLOYEE...
create table SYS_EMPLOYEE
(
  sn            VARCHAR2(50) not null,
  position_id   NUMBER(10) not null,
  department_id NUMBER(10) not null,
  name          VARCHAR2(50) not null,
  password      VARCHAR2(50) not null,
  status        VARCHAR2(20) not null
)
tablespace JBOADB
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table SYS_EMPLOYEE
  add constraint SN primary key (SN)
  using index 
  tablespace JBOADB
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table SYS_EMPLOYEE
  add constraint DEPART_ID foreign key (DEPARTMENT_ID)
  references SYS_DEPARTMENT (ID);
alter table SYS_EMPLOYEE
  add constraint EMP_POSI_ID foreign key (POSITION_ID)
  references SYS_POSITION (ID);

prompt Creating BIZ_CLAIM_VOUCHER...
create table BIZ_CLAIM_VOUCHER
(
  id            NUMBER(10) not null,
  next_deal_sn  VARCHAR2(50),
  create_sn     VARCHAR2(50) not null,
  create_time   DATE not null,
  event         VARCHAR2(255) not null,
  total_account NUMBER(20,2) not null,
  status        VARCHAR2(20) not null,
  modify_time   DATE
)
tablespace JBOADB
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 8K
    minextents 1
    maxextents unlimited
  );
alter table BIZ_CLAIM_VOUCHER
  add constraint CLAIM_ID primary key (ID)
  using index 
  tablespace JBOADB
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table BIZ_CLAIM_VOUCHER
  add constraint E_SN foreign key (CREATE_SN)
  references SYS_EMPLOYEE (SN);
alter table BIZ_CLAIM_VOUCHER
  add constraint F_SN foreign key (NEXT_DEAL_SN)
  references SYS_EMPLOYEE (SN);

prompt Creating BIZ_CHECK_RESULT...
create table BIZ_CHECK_RESULT
(
  id         NUMBER(10) not null,
  claim_id   NUMBER(10) not null,
  check_time DATE not null,
  checker_sn VARCHAR2(50) not null,
  result     VARCHAR2(50) not null,
  comm       VARCHAR2(500) not null
)
tablespace JBOADB
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table BIZ_CHECK_RESULT
  add constraint CID primary key (ID)
  using index 
  tablespace JBOADB
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table BIZ_CHECK_RESULT
  add constraint FK_CLAIM_ID foreign key (CLAIM_ID)
  references BIZ_CLAIM_VOUCHER (ID);
alter table BIZ_CHECK_RESULT
  add constraint FK_EMPLOYEE_ID foreign key (CHECKER_SN)
  references SYS_EMPLOYEE (SN);

prompt Creating BIZ_CLAIM_VOUCHER_DETAIL...
create table BIZ_CLAIM_VOUCHER_DETAIL
(
  id      NUMBER(10) not null,
  main_id NUMBER(10) not null,
  item    VARCHAR2(20) not null,
  account NUMBER(20,2) not null,
  des     VARCHAR2(200) not null
)
tablespace JBOADB
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table BIZ_CLAIM_VOUCHER_DETAIL
  add constraint DETAIL_ID primary key (ID)
  using index 
  tablespace JBOADB
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table BIZ_CLAIM_VOUCHER_DETAIL
  add constraint CLAIME_ID foreign key (MAIN_ID)
  references BIZ_CLAIM_VOUCHER (ID);

prompt Creating BIZ_CLAIM_VOUCHER_STATISTICS...
create table BIZ_CLAIM_VOUCHER_STATISTICS
(
  id            NUMBER(10) not null,
  total_count   NUMBER(20,2) not null,
  year          NUMBER(4) not null,
  month         NUMBER(2) not null,
  department_id NUMBER(10) not null,
  modify_time   DATE not null
)
tablespace JBOADB
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table BIZ_CLAIM_VOUCHER_STATISTICS
  add constraint ID primary key (ID)
  using index 
  tablespace JBOADB
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table BIZ_CLAIM_VOUCHER_STATISTICS
  add constraint DEPTID_ID foreign key (DEPARTMENT_ID)
  references SYS_DEPARTMENT (ID);

prompt Creating BIZ_CLAIM_VOUYEAR__STATISTICS...
create table BIZ_CLAIM_VOUYEAR__STATISTICS
(
  id            NUMBER(10) not null,
  total_count   NUMBER(30,2) not null,
  year          NUMBER(4) not null,
  modify_time   DATE not null,
  department_id NUMBER(10) not null
)
tablespace JBOADB
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table BIZ_CLAIM_VOUYEAR__STATISTICS
  add constraint SID primary key (ID)
  using index 
  tablespace JBOADB
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
  alter table BIZ_CLAIM_VOUYEAR__STATISTICS
  add constraint FK_DEPTID_ID foreign key (DEPARTMENT_ID)
  references SYS_DEPARTMENT (ID);

prompt Creating BIZ_LEAVE...
create table BIZ_LEAVE
(
  id              NUMBER(10) not null,
  employee_sn     VARCHAR2(50) not null,
  starttime       DATE not null,
  endtime         DATE not null,
  leaveday        NUMBER(5,1) not null,
  reason          VARCHAR2(500) not null,
  status          VARCHAR2(20),
  leavetype       VARCHAR2(50),
  next_deal_sn    VARCHAR2(50),
  approve_opinion VARCHAR2(100),
  createtime      DATE,
  modifytime      DATE
)
tablespace JBOADB
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 8K
    minextents 1
    maxextents unlimited
  );
alter table BIZ_LEAVE
  add constraint LEAVE_ID primary key (ID)
  using index 
  tablespace JBOADB
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table BIZ_LEAVE
  add constraint FK_LEAVE_EMPSN foreign key (EMPLOYEE_SN)
  references SYS_EMPLOYEE (SN);
alter table BIZ_LEAVE
  add constraint FK_LEAVE_SN foreign key (NEXT_DEAL_SN)
  references SYS_EMPLOYEE (SN);

prompt Disabling triggers for SYS_POSITION...
alter table SYS_POSITION disable all triggers;
prompt Disabling triggers for SYS_DEPARTMENT...
alter table SYS_DEPARTMENT disable all triggers;
prompt Disabling triggers for SYS_EMPLOYEE...
alter table SYS_EMPLOYEE disable all triggers;
prompt Disabling triggers for BIZ_CLAIM_VOUCHER...
alter table BIZ_CLAIM_VOUCHER disable all triggers;
prompt Disabling triggers for BIZ_CHECK_RESULT...
alter table BIZ_CHECK_RESULT disable all triggers;
prompt Disabling triggers for BIZ_CLAIM_VOUCHER_DETAIL...
alter table BIZ_CLAIM_VOUCHER_DETAIL disable all triggers;
prompt Disabling triggers for BIZ_CLAIM_VOUCHER_STATISTICS...
alter table BIZ_CLAIM_VOUCHER_STATISTICS disable all triggers;
prompt Disabling triggers for BIZ_CLAIM_VOUYEAR__STATISTICS...
alter table BIZ_CLAIM_VOUYEAR__STATISTICS disable all triggers;
prompt Disabling triggers for BIZ_LEAVE...
alter table BIZ_LEAVE disable all triggers;
prompt Disabling foreign key constraints for SYS_EMPLOYEE...
alter table SYS_EMPLOYEE disable constraint DEPART_ID;
alter table SYS_EMPLOYEE disable constraint EMP_POSI_ID;
prompt Disabling foreign key constraints for BIZ_CLAIM_VOUCHER...
alter table BIZ_CLAIM_VOUCHER disable constraint E_SN;
alter table BIZ_CLAIM_VOUCHER disable constraint F_SN;
prompt Disabling foreign key constraints for BIZ_CHECK_RESULT...
alter table BIZ_CHECK_RESULT disable constraint FK_CLAIM_ID;
alter table BIZ_CHECK_RESULT disable constraint FK_EMPLOYEE_ID;
prompt Disabling foreign key constraints for BIZ_CLAIM_VOUCHER_DETAIL...
alter table BIZ_CLAIM_VOUCHER_DETAIL disable constraint CLAIME_ID;
prompt Disabling foreign key constraints for BIZ_CLAIM_VOUCHER_STATISTICS...
alter table BIZ_CLAIM_VOUCHER_STATISTICS disable constraint DEPTID_ID;
prompt Disabling foreign key constraints for BIZ_LEAVE...
alter table BIZ_LEAVE disable constraint FK_LEAVE_EMPSN;
alter table BIZ_LEAVE disable constraint FK_LEAVE_SN;
prompt Loading SYS_POSITION...
insert into SYS_POSITION (id, name_cn, name_en)
values (1, '员工', 'staff');
insert into SYS_POSITION (id, name_cn, name_en)
values (2, '部门经理', 'manager');
insert into SYS_POSITION (id, name_cn, name_en)
values (3, '总经理', 'generalmanager');
insert into SYS_POSITION (id, name_cn, name_en)
values (4, '财务', 'cashier');
commit;
prompt 4 records loaded
prompt Loading SYS_DEPARTMENT...
insert into SYS_DEPARTMENT (id, name)
values (1, '人事部');
insert into SYS_DEPARTMENT (id, name)
values (2, '平台研发部');
insert into SYS_DEPARTMENT (id, name)
values (3, '产品设计部');
insert into SYS_DEPARTMENT (id, name)
values (4, '财务部');
insert into SYS_DEPARTMENT (id, name)
values (5, '总裁办公室');
commit;
prompt 5 records loaded
prompt Loading SYS_EMPLOYEE...
insert into SYS_EMPLOYEE (sn, position_id, department_id, name, password, status)
values ('017', 1, 1, '李小伟', '123', '在职');
insert into SYS_EMPLOYEE (sn, position_id, department_id, name, password, status)
values ('001', 1, 2, '张平', '123', '在职');
insert into SYS_EMPLOYEE (sn, position_id, department_id, name, password, status)
values ('002', 2, 2, '叶宁', '123', '在职');
insert into SYS_EMPLOYEE (sn, position_id, department_id, name, password, status)
values ('003', 3, 3, '李伟', '123', '在职');
insert into SYS_EMPLOYEE (sn, position_id, department_id, name, password, status)
values ('004', 4, 4, '王小明', '123', '离职');
insert into SYS_EMPLOYEE (sn, position_id, department_id, name, password, status)
values ('005', 1, 3, '林风', '123', '在职');
insert into SYS_EMPLOYEE (sn, position_id, department_id, name, password, status)
values ('006', 1, 3, '张大明', '123', '在职');
insert into SYS_EMPLOYEE (sn, position_id, department_id, name, password, status)
values ('011', 1, 1, '李大伟1', '123', '在职');
insert into SYS_EMPLOYEE (sn, position_id, department_id, name, password, status)
values ('007', 1, 1, '李大伟', '123', '在职');
insert into SYS_EMPLOYEE (sn, position_id, department_id, name, password, status)
values ('008', 3, 5, '张总', '123', '在职');
insert into SYS_EMPLOYEE (sn, position_id, department_id, name, password, status)
values ('009', 4, 4, '李峰', '123', '在职');
insert into SYS_EMPLOYEE (sn, position_id, department_id, name, password, status)
values ('018', 1, 1, '李大伟', '123', '在职');
commit;
prompt 12 records loaded
prompt Loading BIZ_CLAIM_VOUCHER...
insert into BIZ_CLAIM_VOUCHER (id, next_deal_sn, create_sn, create_time, event, total_account, status, modify_time)
values (103, null, '001', to_date('06-05-2018', 'dd-mm-yyyy'), '工作', 40, '已提交', to_date('09-05-2018', 'dd-mm-yyyy'));
insert into BIZ_CLAIM_VOUCHER (id, next_deal_sn, create_sn, create_time, event, total_account, status, modify_time)
values (104, null, '001', to_date('06-05-2018', 'dd-mm-yyyy'), '34', 93, '已提交', to_date('09-05-2018', 'dd-mm-yyyy'));
insert into BIZ_CLAIM_VOUCHER (id, next_deal_sn, create_sn, create_time, event, total_account, status, modify_time)
values (105, null, '001', to_date('06-05-2018', 'dd-mm-yyyy'), '1', 57, '已提交', to_date('09-05-2018', 'dd-mm-yyyy'));
insert into BIZ_CLAIM_VOUCHER (id, next_deal_sn, create_sn, create_time, event, total_account, status, modify_time)
values (247, '001', '001', to_date('19-05-2018', 'dd-mm-yyyy'), '345', 435, '已提交', to_date('19-05-2018', 'dd-mm-yyyy'));
insert into BIZ_CLAIM_VOUCHER (id, next_deal_sn, create_sn, create_time, event, total_account, status, modify_time)
values (249, '001', '001', to_date('19-05-2018', 'dd-mm-yyyy'), '536', 23123, '已提交', to_date('19-05-2018', 'dd-mm-yyyy'));
insert into BIZ_CLAIM_VOUCHER (id, next_deal_sn, create_sn, create_time, event, total_account, status, modify_time)
values (251, null, '001', to_date('19-05-2018', 'dd-mm-yyyy'), '1231', 123213, '已终止', to_date('19-05-2018', 'dd-mm-yyyy'));
insert into BIZ_CLAIM_VOUCHER (id, next_deal_sn, create_sn, create_time, event, total_account, status, modify_time)
values (100, null, '001', to_date('06-09-2013 13:29:30', 'dd-mm-yyyy hh24:mi:ss'), '出差', 600, '已终止', to_date('13-09-2013 15:29:06', 'dd-mm-yyyy hh24:mi:ss'));
insert into BIZ_CLAIM_VOUCHER (id, next_deal_sn, create_sn, create_time, event, total_account, status, modify_time)
values (102, '001', '001', to_date('06-09-2013 13:41:50', 'dd-mm-yyyy hh24:mi:ss'), '公司业务', 6500.23, '新创建', to_date('13-09-2013 15:02:09', 'dd-mm-yyyy hh24:mi:ss'));
insert into BIZ_CLAIM_VOUCHER (id, next_deal_sn, create_sn, create_time, event, total_account, status, modify_time)
values (1, null, '001', to_date('02-07-2013 08:30:00', 'dd-mm-yyyy hh24:mi:ss'), '出差', 200.55, '已付款', to_date('27-08-2013', 'dd-mm-yyyy'));
insert into BIZ_CLAIM_VOUCHER (id, next_deal_sn, create_sn, create_time, event, total_account, status, modify_time)
values (2, null, '005', to_date('08-08-2013 09:30:00', 'dd-mm-yyyy hh24:mi:ss'), '出差', 1200.01, '已付款', to_date('29-08-2013', 'dd-mm-yyyy'));
insert into BIZ_CLAIM_VOUCHER (id, next_deal_sn, create_sn, create_time, event, total_account, status, modify_time)
values (3, '004', '006', to_date('08-08-2013 10:30:00', 'dd-mm-yyyy hh24:mi:ss'), '会见客户', 8535.59, '已审批', to_date('18-09-2013 11:18:54', 'dd-mm-yyyy hh24:mi:ss'));
insert into BIZ_CLAIM_VOUCHER (id, next_deal_sn, create_sn, create_time, event, total_account, status, modify_time)
values (4, null, '001', to_date('29-08-2013 08:00:00', 'dd-mm-yyyy hh24:mi:ss'), '周末加班', 128.01, '已付款', to_date('02-09-2013 09:00:00', 'dd-mm-yyyy hh24:mi:ss'));
insert into BIZ_CLAIM_VOUCHER (id, next_deal_sn, create_sn, create_time, event, total_account, status, modify_time)
values (7, '004', '001', to_date('03-09-2013 05:00:00', 'dd-mm-yyyy hh24:mi:ss'), '周末加班', 5132.51, '已审批', to_date('18-09-2013 11:19:03', 'dd-mm-yyyy hh24:mi:ss'));
insert into BIZ_CLAIM_VOUCHER (id, next_deal_sn, create_sn, create_time, event, total_account, status, modify_time)
values (8, '001', '001', to_date('03-09-2013 08:00:00', 'dd-mm-yyyy hh24:mi:ss'), '周末加班', 132.51, '已打回', to_date('03-09-2013 09:00:00', 'dd-mm-yyyy hh24:mi:ss'));
insert into BIZ_CLAIM_VOUCHER (id, next_deal_sn, create_sn, create_time, event, total_account, status, modify_time)
values (6, null, '002', to_date('01-07-2013', 'dd-mm-yyyy'), '周末加班', 400, '已付款', to_date('20-07-2013', 'dd-mm-yyyy'));
insert into BIZ_CLAIM_VOUCHER (id, next_deal_sn, create_sn, create_time, event, total_account, status, modify_time)
values (151, null, '017', to_date('14-10-2013 17:34:29', 'dd-mm-yyyy hh24:mi:ss'), '外出', 50, '新创建', null);
insert into BIZ_CLAIM_VOUCHER (id, next_deal_sn, create_sn, create_time, event, total_account, status, modify_time)
values (129, '001', '001', to_date('10-09-2013 14:05:47', 'dd-mm-yyyy hh24:mi:ss'), '这交伯费用', 100, '新创建', null);
insert into BIZ_CLAIM_VOUCHER (id, next_deal_sn, create_sn, create_time, event, total_account, status, modify_time)
values (133, '001', '001', to_date('13-09-2013 15:21:52', 'dd-mm-yyyy hh24:mi:ss'), '拜访客户', 888, '新创建', null);
insert into BIZ_CLAIM_VOUCHER (id, next_deal_sn, create_sn, create_time, event, total_account, status, modify_time)
values (132, '001', '001', to_date('13-09-2013 15:18:39', 'dd-mm-yyyy hh24:mi:ss'), '拜访客户', 388, '新创建', to_date('13-09-2013 15:19:15', 'dd-mm-yyyy hh24:mi:ss'));
insert into BIZ_CLAIM_VOUCHER (id, next_deal_sn, create_sn, create_time, event, total_account, status, modify_time)
values (181, null, '003', to_date('22-10-2013 17:27:55', 'dd-mm-yyyy hh24:mi:ss'), '出差！', 100, '新创建', null);
insert into BIZ_CLAIM_VOUCHER (id, next_deal_sn, create_sn, create_time, event, total_account, status, modify_time)
values (192, null, '003', to_date('23-10-2013 10:52:09', 'dd-mm-yyyy hh24:mi:ss'), '出差！', 100, '新创建', null);
insert into BIZ_CLAIM_VOUCHER (id, next_deal_sn, create_sn, create_time, event, total_account, status, modify_time)
values (198, null, '001', to_date('23-10-2013 13:45:32', 'dd-mm-yyyy hh24:mi:ss'), '123', 44, '新创建', to_date('23-10-2013 13:47:24', 'dd-mm-yyyy hh24:mi:ss'));
insert into BIZ_CLAIM_VOUCHER (id, next_deal_sn, create_sn, create_time, event, total_account, status, modify_time)
values (175, null, '001', to_date('22-10-2013 16:13:03', 'dd-mm-yyyy hh24:mi:ss'), '123', 12, '新创建', null);
insert into BIZ_CLAIM_VOUCHER (id, next_deal_sn, create_sn, create_time, event, total_account, status, modify_time)
values (5, null, '001', to_date('01-08-2013', 'dd-mm-yyyy'), '周末加班', 45.01, '已终止', to_date('31-08-2013', 'dd-mm-yyyy'));
insert into BIZ_CLAIM_VOUCHER (id, next_deal_sn, create_sn, create_time, event, total_account, status, modify_time)
values (107, null, '001', to_date('06-09-2013', 'dd-mm-yyyy'), '拜访客户', 1000, '已付款', to_date('18-09-2013', 'dd-mm-yyyy'));
insert into BIZ_CLAIM_VOUCHER (id, next_deal_sn, create_sn, create_time, event, total_account, status, modify_time)
values (106, '004', '001', to_date('06-09-2013', 'dd-mm-yyyy'), '拜访客户', 700, '已审批', null);
insert into BIZ_CLAIM_VOUCHER (id, next_deal_sn, create_sn, create_time, event, total_account, status, modify_time)
values (108, '004', '001', to_date('06-09-2013 16:16:56', 'dd-mm-yyyy hh24:mi:ss'), '出差', 1599, '已审批', to_date('18-09-2013 11:12:42', 'dd-mm-yyyy hh24:mi:ss'));
insert into BIZ_CLAIM_VOUCHER (id, next_deal_sn, create_sn, create_time, event, total_account, status, modify_time)
values (124, '001', '001', to_date('10-09-2013 13:01:28', 'dd-mm-yyyy hh24:mi:ss'), '加班', 104, '新创建', to_date('13-09-2013 15:20:12', 'dd-mm-yyyy hh24:mi:ss'));
insert into BIZ_CLAIM_VOUCHER (id, next_deal_sn, create_sn, create_time, event, total_account, status, modify_time)
values (126, '001', '001', to_date('10-09-2013 13:41:57', 'dd-mm-yyyy hh24:mi:ss'), '拜访客户', 1000, '新创建', null);
insert into BIZ_CLAIM_VOUCHER (id, next_deal_sn, create_sn, create_time, event, total_account, status, modify_time)
values (130, '001', '001', to_date('10-09-2013 14:08:25', 'dd-mm-yyyy hh24:mi:ss'), '拜访客户', 100, '新创建', null);
insert into BIZ_CLAIM_VOUCHER (id, next_deal_sn, create_sn, create_time, event, total_account, status, modify_time)
values (171, null, '003', to_date('22-10-2013 16:06:53', 'dd-mm-yyyy hh24:mi:ss'), '出差！', 100, '新创建', null);
insert into BIZ_CLAIM_VOUCHER (id, next_deal_sn, create_sn, create_time, event, total_account, status, modify_time)
values (173, null, '001', to_date('22-10-2013 16:12:36', 'dd-mm-yyyy hh24:mi:ss'), '撒旦发射点发', 15, '新创建', null);
insert into BIZ_CLAIM_VOUCHER (id, next_deal_sn, create_sn, create_time, event, total_account, status, modify_time)
values (180, null, '003', to_date('22-10-2013 16:55:36', 'dd-mm-yyyy hh24:mi:ss'), '出差！', 100, '新创建', null);
insert into BIZ_CLAIM_VOUCHER (id, next_deal_sn, create_sn, create_time, event, total_account, status, modify_time)
values (191, null, '003', to_date('23-10-2013 10:51:39', 'dd-mm-yyyy hh24:mi:ss'), '出差！', 100, '新创建', null);
insert into BIZ_CLAIM_VOUCHER (id, next_deal_sn, create_sn, create_time, event, total_account, status, modify_time)
values (196, null, '001', to_date('23-10-2013 11:28:42', 'dd-mm-yyyy hh24:mi:ss'), '123123123', 369, '新创建', to_date('23-10-2013 13:37:12', 'dd-mm-yyyy hh24:mi:ss'));
insert into BIZ_CLAIM_VOUCHER (id, next_deal_sn, create_sn, create_time, event, total_account, status, modify_time)
values (118, null, '001', to_date('06-05-2018', 'dd-mm-yyyy'), '321 322', 643, '新创建', null);
insert into BIZ_CLAIM_VOUCHER (id, next_deal_sn, create_sn, create_time, event, total_account, status, modify_time)
values (121, '001', '001', to_date('06-05-2018', 'dd-mm-yyyy'), '99 100', 199, '新创建', null);
insert into BIZ_CLAIM_VOUCHER (id, next_deal_sn, create_sn, create_time, event, total_account, status, modify_time)
values (215, null, '001', to_date('06-05-2018', 'dd-mm-yyyy'), '11', 11, '已付款', null);
insert into BIZ_CLAIM_VOUCHER (id, next_deal_sn, create_sn, create_time, event, total_account, status, modify_time)
values (101, null, '001', to_date('06-05-2018', 'dd-mm-yyyy'), '工作', 300, '新创建', null);
insert into BIZ_CLAIM_VOUCHER (id, next_deal_sn, create_sn, create_time, event, total_account, status, modify_time)
values (217, '002', '001', to_date('06-05-2018', 'dd-mm-yyyy'), '2', 2, '已提交', null);
insert into BIZ_CLAIM_VOUCHER (id, next_deal_sn, create_sn, create_time, event, total_account, status, modify_time)
values (233, null, '001', to_date('10-05-2018', 'dd-mm-yyyy'), '出差', 6300, '已付款', to_date('10-05-2018', 'dd-mm-yyyy'));
insert into BIZ_CLAIM_VOUCHER (id, next_deal_sn, create_sn, create_time, event, total_account, status, modify_time)
values (244, '001', '001', to_date('19-05-2018', 'dd-mm-yyyy'), '工作了', 345, '已提交', to_date('19-05-2018', 'dd-mm-yyyy'));
commit;
prompt 42 records loaded
prompt Loading BIZ_CHECK_RESULT...
insert into BIZ_CHECK_RESULT (id, claim_id, check_time, checker_sn, result, comm)
values (239, 233, to_date('10-05-2018', 'dd-mm-yyyy'), '002', '通过', '我同意了');
insert into BIZ_CHECK_RESULT (id, claim_id, check_time, checker_sn, result, comm)
values (240, 233, to_date('10-05-2018', 'dd-mm-yyyy'), '003', '通过', '注意安全');
insert into BIZ_CHECK_RESULT (id, claim_id, check_time, checker_sn, result, comm)
values (241, 233, to_date('10-05-2018', 'dd-mm-yyyy'), '004', '通过', '已阅');
insert into BIZ_CHECK_RESULT (id, claim_id, check_time, checker_sn, result, comm)
values (253, 251, to_date('19-05-2018', 'dd-mm-yyyy'), '002', '拒绝', 'butoyi');
insert into BIZ_CHECK_RESULT (id, claim_id, check_time, checker_sn, result, comm)
values (254, 215, to_date('19-05-2018', 'dd-mm-yyyy'), '002', '通过', '哈哈');
insert into BIZ_CHECK_RESULT (id, claim_id, check_time, checker_sn, result, comm)
values (255, 215, to_date('19-05-2018', 'dd-mm-yyyy'), '004', '通过', 'xixi');
insert into BIZ_CHECK_RESULT (id, claim_id, check_time, checker_sn, result, comm)
values (1, 1, to_date('02-09-2013 09:02:02', 'dd-mm-yyyy hh24:mi:ss'), '002', '通过', '同意');
insert into BIZ_CHECK_RESULT (id, claim_id, check_time, checker_sn, result, comm)
values (2, 1, to_date('02-09-2013 11:02:02', 'dd-mm-yyyy hh24:mi:ss'), '004', '通过', '同意');
insert into BIZ_CHECK_RESULT (id, claim_id, check_time, checker_sn, result, comm)
values (101, 4, to_date('04-09-2013 17:12:27', 'dd-mm-yyyy hh24:mi:ss'), '004', '通过', '没问题啊');
insert into BIZ_CHECK_RESULT (id, claim_id, check_time, checker_sn, result, comm)
values (142, 108, to_date('18-09-2013 11:12:42', 'dd-mm-yyyy hh24:mi:ss'), '002', '通过', '同意');
insert into BIZ_CHECK_RESULT (id, claim_id, check_time, checker_sn, result, comm)
values (143, 7, to_date('18-09-2013 11:13:05', 'dd-mm-yyyy hh24:mi:ss'), '002', '通过', '同意');
insert into BIZ_CHECK_RESULT (id, claim_id, check_time, checker_sn, result, comm)
values (144, 107, to_date('18-09-2013 11:18:16', 'dd-mm-yyyy hh24:mi:ss'), '002', '通过', '同意');
insert into BIZ_CHECK_RESULT (id, claim_id, check_time, checker_sn, result, comm)
values (145, 3, to_date('18-09-2013 11:18:54', 'dd-mm-yyyy hh24:mi:ss'), '003', '通过', '同意');
insert into BIZ_CHECK_RESULT (id, claim_id, check_time, checker_sn, result, comm)
values (146, 7, to_date('18-09-2013 11:19:03', 'dd-mm-yyyy hh24:mi:ss'), '003', '通过', '同意');
insert into BIZ_CHECK_RESULT (id, claim_id, check_time, checker_sn, result, comm)
values (121, 100, to_date('13-09-2013 15:29:06', 'dd-mm-yyyy hh24:mi:ss'), '002', '拒绝', '同意');
insert into BIZ_CHECK_RESULT (id, claim_id, check_time, checker_sn, result, comm)
values (100, 4, to_date('04-09-2013 17:00:13', 'dd-mm-yyyy hh24:mi:ss'), '002', '通过', '同意');
insert into BIZ_CHECK_RESULT (id, claim_id, check_time, checker_sn, result, comm)
values (238, 107, to_date('10-05-2018', 'dd-mm-yyyy'), '004', '通过', '好的');
insert into BIZ_CHECK_RESULT (id, claim_id, check_time, checker_sn, result, comm)
values (229, 5, to_date('10-05-2018', 'dd-mm-yyyy'), '002', '拒绝', '拒绝');
insert into BIZ_CHECK_RESULT (id, claim_id, check_time, checker_sn, result, comm)
values (232, 106, to_date('10-05-2018', 'dd-mm-yyyy'), '002', '通过', '同意');
commit;
prompt 19 records loaded
prompt Loading BIZ_CLAIM_VOUCHER_DETAIL...
insert into BIZ_CLAIM_VOUCHER_DETAIL (id, main_id, item, account, des)
values (226, 103, '城际交通费', 3, '3');
insert into BIZ_CLAIM_VOUCHER_DETAIL (id, main_id, item, account, des)
values (227, 105, '城际交通费', 56, '56');
insert into BIZ_CLAIM_VOUCHER_DETAIL (id, main_id, item, account, des)
values (237, 233, '礼品费', 300, '礼品');
insert into BIZ_CLAIM_VOUCHER_DETAIL (id, main_id, item, account, des)
values (248, 247, '城际交通费', 435, '345');
insert into BIZ_CLAIM_VOUCHER_DETAIL (id, main_id, item, account, des)
values (250, 249, '城际交通费', 23123, '12323');
insert into BIZ_CLAIM_VOUCHER_DETAIL (id, main_id, item, account, des)
values (252, 251, '城际交通费', 123213, '12312');
insert into BIZ_CLAIM_VOUCHER_DETAIL (id, main_id, item, account, des)
values (107, 108, '礼品费', 600, '送礼');
insert into BIZ_CLAIM_VOUCHER_DETAIL (id, main_id, item, account, des)
values (102, 102, '城际交通费', 200, '回家');
insert into BIZ_CLAIM_VOUCHER_DETAIL (id, main_id, item, account, des)
values (103, 102, '交际餐费', 300, '吃饭钱');
insert into BIZ_CLAIM_VOUCHER_DETAIL (id, main_id, item, account, des)
values (104, 102, '办公费', 6000, '购买书柜');
insert into BIZ_CLAIM_VOUCHER_DETAIL (id, main_id, item, account, des)
values (10, 7, '城际交通费', 4598, '往返机票');
insert into BIZ_CLAIM_VOUCHER_DETAIL (id, main_id, item, account, des)
values (11, 7, '交际餐费', 534.51, '餐饮');
insert into BIZ_CLAIM_VOUCHER_DETAIL (id, main_id, item, account, des)
values (1, 1, '住宿', 100, '市内住宿');
insert into BIZ_CLAIM_VOUCHER_DETAIL (id, main_id, item, account, des)
values (2, 1, '交际餐费', 180, '请客户吃饭');
insert into BIZ_CLAIM_VOUCHER_DETAIL (id, main_id, item, account, des)
values (3, 2, '交通', 300, '市内交通');
insert into BIZ_CLAIM_VOUCHER_DETAIL (id, main_id, item, account, des)
values (4, 2, '住宿', 600, '市内住宿');
insert into BIZ_CLAIM_VOUCHER_DETAIL (id, main_id, item, account, des)
values (5, 2, '餐饮', 300, '餐饮');
insert into BIZ_CLAIM_VOUCHER_DETAIL (id, main_id, item, account, des)
values (6, 3, '交通', 100, '市内交通');
insert into BIZ_CLAIM_VOUCHER_DETAIL (id, main_id, item, account, des)
values (7, 3, '餐饮', 700, '餐饮');
insert into BIZ_CLAIM_VOUCHER_DETAIL (id, main_id, item, account, des)
values (128, 124, '城际交通费', 4, 'test');
insert into BIZ_CLAIM_VOUCHER_DETAIL (id, main_id, item, account, des)
values (125, 132, '城际交通费', 111, 'test');
insert into BIZ_CLAIM_VOUCHER_DETAIL (id, main_id, item, account, des)
values (126, 132, '城际交通费', 222, 'test1');
insert into BIZ_CLAIM_VOUCHER_DETAIL (id, main_id, item, account, des)
values (127, 132, '城际交通费', 55, 'test2');
insert into BIZ_CLAIM_VOUCHER_DETAIL (id, main_id, item, account, des)
values (129, 133, '礼品费', 888, '送礼');
insert into BIZ_CLAIM_VOUCHER_DETAIL (id, main_id, item, account, des)
values (8, 1, '市内交通费', 20, '加班回家');
insert into BIZ_CLAIM_VOUCHER_DETAIL (id, main_id, item, account, des)
values (9, 4, '市内交通费', 20, '加班回家');
insert into BIZ_CLAIM_VOUCHER_DETAIL (id, main_id, item, account, des)
values (105, 107, '城际交通费', 300, '立');
insert into BIZ_CLAIM_VOUCHER_DETAIL (id, main_id, item, account, des)
values (106, 108, '城际交通费', 999, '交通费');
insert into BIZ_CLAIM_VOUCHER_DETAIL (id, main_id, item, account, des)
values (124, 130, '城际交通费', 100, '费用');
insert into BIZ_CLAIM_VOUCHER_DETAIL (id, main_id, item, account, des)
values (154, 198, '城际交通费', 22, '22');
insert into BIZ_CLAIM_VOUCHER_DETAIL (id, main_id, item, account, des)
values (155, 198, '城际交通费', 11, '11');
insert into BIZ_CLAIM_VOUCHER_DETAIL (id, main_id, item, account, des)
values (119, 118, '城际交通费', 322, '322');
insert into BIZ_CLAIM_VOUCHER_DETAIL (id, main_id, item, account, des)
values (120, 118, '城际交通费', 321, '321');
insert into BIZ_CLAIM_VOUCHER_DETAIL (id, main_id, item, account, des)
values (122, 121, '城际交通费', 99, '99');
insert into BIZ_CLAIM_VOUCHER_DETAIL (id, main_id, item, account, des)
values (123, 121, '城际交通费', 100, '100');
insert into BIZ_CLAIM_VOUCHER_DETAIL (id, main_id, item, account, des)
values (216, 215, '城际交通费', 11, '112');
insert into BIZ_CLAIM_VOUCHER_DETAIL (id, main_id, item, account, des)
values (223, 103, '城际交通费', 2, '2');
insert into BIZ_CLAIM_VOUCHER_DETAIL (id, main_id, item, account, des)
values (224, 103, '城际交通费', 1, '1');
insert into BIZ_CLAIM_VOUCHER_DETAIL (id, main_id, item, account, des)
values (218, 217, '城际交通费', 2, '2');
insert into BIZ_CLAIM_VOUCHER_DETAIL (id, main_id, item, account, des)
values (225, 104, '城际交通费', 59, '59');
insert into BIZ_CLAIM_VOUCHER_DETAIL (id, main_id, item, account, des)
values (234, 233, '住宿费', 1000, '住宿');
insert into BIZ_CLAIM_VOUCHER_DETAIL (id, main_id, item, account, des)
values (235, 233, '交际餐费', 3000, '餐费');
insert into BIZ_CLAIM_VOUCHER_DETAIL (id, main_id, item, account, des)
values (236, 233, '城际交通费', 2000, '乘车');
insert into BIZ_CLAIM_VOUCHER_DETAIL (id, main_id, item, account, des)
values (245, 244, '城际交通费', 45, '45');
insert into BIZ_CLAIM_VOUCHER_DETAIL (id, main_id, item, account, des)
values (246, 244, '礼品费', 300, '300');
commit;
prompt 45 records loaded
prompt Loading BIZ_CLAIM_VOUCHER_STATISTICS...
insert into BIZ_CLAIM_VOUCHER_STATISTICS (id, total_count, year, month, department_id, modify_time)
values (1, 400, 2013, 7, 2, to_date('15-08-2013 16:34:00', 'dd-mm-yyyy hh24:mi:ss'));
insert into BIZ_CLAIM_VOUCHER_STATISTICS (id, total_count, year, month, department_id, modify_time)
values (2, 200.55, 2013, 8, 2, to_date('18-09-2013 16:34:56', 'dd-mm-yyyy hh24:mi:ss'));
insert into BIZ_CLAIM_VOUCHER_STATISTICS (id, total_count, year, month, department_id, modify_time)
values (3, 2000.02, 2013, 8, 3, to_date('18-09-2013 16:34:56', 'dd-mm-yyyy hh24:mi:ss'));
insert into BIZ_CLAIM_VOUCHER_STATISTICS (id, total_count, year, month, department_id, modify_time)
values (4, 128.01, 2013, 9, 2, to_date('15-10-2013 16:37:58', 'dd-mm-yyyy hh24:mi:ss'));
commit;
prompt 4 records loaded
prompt Loading BIZ_CLAIM_VOUYEAR__STATISTICS...
insert into BIZ_CLAIM_VOUYEAR__STATISTICS (id, total_count, year, modify_time, department_id)
values (5, 728.56, 2013, to_date('15-02-2014 16:40:36', 'dd-mm-yyyy hh24:mi:ss'), 2);
insert into BIZ_CLAIM_VOUYEAR__STATISTICS (id, total_count, year, modify_time, department_id)
values (6, 2000.02, 2013, to_date('15-02-2014 16:40:36', 'dd-mm-yyyy hh24:mi:ss'), 3);
commit;
prompt 2 records loaded
prompt Loading BIZ_LEAVE...
insert into BIZ_LEAVE (id, employee_sn, starttime, endtime, leaveday, reason, status, leavetype, next_deal_sn, approve_opinion, createtime, modifytime)
values (105, '001', to_date('09-09-2013', 'dd-mm-yyyy'), to_date('10-09-2013', 'dd-mm-yyyy'), 1, '请假', '待审批', '年假', '002', null, to_date('05-09-2013 16:49:56', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into BIZ_LEAVE (id, employee_sn, starttime, endtime, leaveday, reason, status, leavetype, next_deal_sn, approve_opinion, createtime, modifytime)
values (121, '001', to_date('06-09-2013', 'dd-mm-yyyy'), to_date('07-09-2013', 'dd-mm-yyyy'), 1, '请假', '待审批', '事假', '002', null, to_date('06-09-2013 09:02:40', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into BIZ_LEAVE (id, employee_sn, starttime, endtime, leaveday, reason, status, leavetype, next_deal_sn, approve_opinion, createtime, modifytime)
values (136, '001', to_date('10-09-2013 10:54:07', 'dd-mm-yyyy hh24:mi:ss'), to_date('11-09-2013 10:54:07', 'dd-mm-yyyy hh24:mi:ss'), 1, '2', '待审批', '年假', '002', null, to_date('09-09-2013 10:54:20', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into BIZ_LEAVE (id, employee_sn, starttime, endtime, leaveday, reason, status, leavetype, next_deal_sn, approve_opinion, createtime, modifytime)
values (139, '001', to_date('23-09-2013 11:15:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('24-09-2013 11:15:00', 'dd-mm-yyyy hh24:mi:ss'), 1, '1', '待审批', '年假', '002', null, to_date('09-09-2013 11:15:11', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into BIZ_LEAVE (id, employee_sn, starttime, endtime, leaveday, reason, status, leavetype, next_deal_sn, approve_opinion, createtime, modifytime)
values (143, '001', to_date('30-09-2013 15:09:50', 'dd-mm-yyyy hh24:mi:ss'), to_date('01-10-2013 15:09:50', 'dd-mm-yyyy hh24:mi:ss'), 1, 'ttt', '待审批', '年假', '002', null, to_date('09-09-2013 15:10:08', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into BIZ_LEAVE (id, employee_sn, starttime, endtime, leaveday, reason, status, leavetype, next_deal_sn, approve_opinion, createtime, modifytime)
values (1, '001', to_date('02-08-2013 08:30:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('02-08-2013 00:30:00', 'dd-mm-yyyy hh24:mi:ss'), .5, '家里有事', '已审批', '事假', '002', '同意', to_date('01-08-2013 10:02:58', 'dd-mm-yyyy hh24:mi:ss'), to_date('02-08-2013 22:58:02', 'dd-mm-yyyy hh24:mi:ss'));
insert into BIZ_LEAVE (id, employee_sn, starttime, endtime, leaveday, reason, status, leavetype, next_deal_sn, approve_opinion, createtime, modifytime)
values (2, '001', to_date('06-08-2013 08:30:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('07-08-2013 17:30:00', 'dd-mm-yyyy hh24:mi:ss'), 2, '感冒', '已打回', '病假', null, '同意', to_date('01-08-2013 10:02:58', 'dd-mm-yyyy hh24:mi:ss'), to_date('02-08-2013 22:58:02', 'dd-mm-yyyy hh24:mi:ss'));
insert into BIZ_LEAVE (id, employee_sn, starttime, endtime, leaveday, reason, status, leavetype, next_deal_sn, approve_opinion, createtime, modifytime)
values (3, '001', to_date('09-08-2013 08:30:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('09-08-2013 17:30:00', 'dd-mm-yyyy hh24:mi:ss'), 1, '感冒', '已审批 ', '病假', '002', '同意', to_date('01-08-2013 10:02:58', 'dd-mm-yyyy hh24:mi:ss'), to_date('05-09-2013 15:49:04', 'dd-mm-yyyy hh24:mi:ss'));
insert into BIZ_LEAVE (id, employee_sn, starttime, endtime, leaveday, reason, status, leavetype, next_deal_sn, approve_opinion, createtime, modifytime)
values (4, '004', to_date('13-08-2013 08:30:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('14-08-2013 17:30:00', 'dd-mm-yyyy hh24:mi:ss'), 2, '事假', '已审批', '事假', null, null, null, null);
insert into BIZ_LEAVE (id, employee_sn, starttime, endtime, leaveday, reason, status, leavetype, next_deal_sn, approve_opinion, createtime, modifytime)
values (101, '001', to_date('13-08-2013', 'dd-mm-yyyy'), to_date('13-08-2013', 'dd-mm-yyyy'), 1, '请假', '已审批 ', '年假', '002', '通过', to_date('05-09-2013 14:31:19', 'dd-mm-yyyy hh24:mi:ss'), to_date('05-09-2013 15:21:23', 'dd-mm-yyyy hh24:mi:ss'));
insert into BIZ_LEAVE (id, employee_sn, starttime, endtime, leaveday, reason, status, leavetype, next_deal_sn, approve_opinion, createtime, modifytime)
values (127, '001', to_date('18-02-2014', 'dd-mm-yyyy'), to_date('20-02-2014', 'dd-mm-yyyy'), 2, '44', '已打回', '年假', '002', '不同意', to_date('18-02-2014', 'dd-mm-yyyy'), to_date('13-05-2018', 'dd-mm-yyyy'));
insert into BIZ_LEAVE (id, employee_sn, starttime, endtime, leaveday, reason, status, leavetype, next_deal_sn, approve_opinion, createtime, modifytime)
values (102, '001', to_date('13-08-2013', 'dd-mm-yyyy'), to_date('13-08-2013', 'dd-mm-yyyy'), 1, '请假大', '待审批', '事假', '002', null, to_date('05-09-2013 14:38:50', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into BIZ_LEAVE (id, employee_sn, starttime, endtime, leaveday, reason, status, leavetype, next_deal_sn, approve_opinion, createtime, modifytime)
values (104, '001', to_date('05-09-2013', 'dd-mm-yyyy'), to_date('06-09-2013', 'dd-mm-yyyy'), 1, '结婚', '已打回 ', '婚假', '002', '回吧', to_date('05-09-2013 15:26:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('05-09-2013 15:42:31', 'dd-mm-yyyy hh24:mi:ss'));
insert into BIZ_LEAVE (id, employee_sn, starttime, endtime, leaveday, reason, status, leavetype, next_deal_sn, approve_opinion, createtime, modifytime)
values (122, '001', to_date('06-09-2013', 'dd-mm-yyyy'), to_date('08-09-2013', 'dd-mm-yyyy'), 2, '田', '待审批', '年假', '002', null, to_date('06-09-2013 09:15:18', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into BIZ_LEAVE (id, employee_sn, starttime, endtime, leaveday, reason, status, leavetype, next_deal_sn, approve_opinion, createtime, modifytime)
values (124, '001', to_date('05-09-2013 09:20:00', 'dd-mm-yyyy hh24:mi:ss'), to_date('07-09-2013 09:20:00', 'dd-mm-yyyy hh24:mi:ss'), 2, '年假', '待审批', '年假', '002', null, to_date('06-09-2013 09:20:25', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into BIZ_LEAVE (id, employee_sn, starttime, endtime, leaveday, reason, status, leavetype, next_deal_sn, approve_opinion, createtime, modifytime)
values (125, '001', to_date('06-09-2013 14:19:47', 'dd-mm-yyyy hh24:mi:ss'), to_date('07-09-2013 14:19:50', 'dd-mm-yyyy hh24:mi:ss'), 1, '婚假', '待审批', '婚假', '002', null, to_date('06-09-2013 14:20:10', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into BIZ_LEAVE (id, employee_sn, starttime, endtime, leaveday, reason, status, leavetype, next_deal_sn, approve_opinion, createtime, modifytime)
values (140, '001', to_date('11-09-2013 14:51:35', 'dd-mm-yyyy hh24:mi:ss'), to_date('12-09-2013 14:51:35', 'dd-mm-yyyy hh24:mi:ss'), 2, 'tt', '待审批', '年假', '002', null, to_date('09-09-2013 14:51:48', 'dd-mm-yyyy hh24:mi:ss'), null);
insert into BIZ_LEAVE (id, employee_sn, starttime, endtime, leaveday, reason, status, leavetype, next_deal_sn, approve_opinion, createtime, modifytime)
values (243, '001', to_date('15-05-2018', 'dd-mm-yyyy'), to_date('18-05-2018', 'dd-mm-yyyy'), 3, '有事', '已审批', '事假', '002', '同意', to_date('13-05-2018', 'dd-mm-yyyy'), to_date('13-05-2018', 'dd-mm-yyyy'));
commit;
prompt 18 records loaded
prompt Enabling foreign key constraints for SYS_EMPLOYEE...
alter table SYS_EMPLOYEE enable constraint DEPART_ID;
alter table SYS_EMPLOYEE enable constraint EMP_POSI_ID;
prompt Enabling foreign key constraints for BIZ_CLAIM_VOUCHER...
alter table BIZ_CLAIM_VOUCHER enable constraint E_SN;
alter table BIZ_CLAIM_VOUCHER enable constraint F_SN;
prompt Enabling foreign key constraints for BIZ_CHECK_RESULT...
alter table BIZ_CHECK_RESULT enable constraint FK_CLAIM_ID;
alter table BIZ_CHECK_RESULT enable constraint FK_EMPLOYEE_ID;
prompt Enabling foreign key constraints for BIZ_CLAIM_VOUCHER_DETAIL...
alter table BIZ_CLAIM_VOUCHER_DETAIL enable constraint CLAIME_ID;
prompt Enabling foreign key constraints for BIZ_CLAIM_VOUCHER_STATISTICS...
alter table BIZ_CLAIM_VOUCHER_STATISTICS enable constraint DEPTID_ID;
prompt Enabling foreign key constraints for BIZ_LEAVE...
alter table BIZ_LEAVE enable constraint FK_LEAVE_EMPSN;
alter table BIZ_LEAVE enable constraint FK_LEAVE_SN;
prompt Enabling triggers for SYS_POSITION...
alter table SYS_POSITION enable all triggers;
prompt Enabling triggers for SYS_DEPARTMENT...
alter table SYS_DEPARTMENT enable all triggers;
prompt Enabling triggers for SYS_EMPLOYEE...
alter table SYS_EMPLOYEE enable all triggers;
prompt Enabling triggers for BIZ_CLAIM_VOUCHER...
alter table BIZ_CLAIM_VOUCHER enable all triggers;
prompt Enabling triggers for BIZ_CHECK_RESULT...
alter table BIZ_CHECK_RESULT enable all triggers;
prompt Enabling triggers for BIZ_CLAIM_VOUCHER_DETAIL...
alter table BIZ_CLAIM_VOUCHER_DETAIL enable all triggers;
prompt Enabling triggers for BIZ_CLAIM_VOUCHER_STATISTICS...
alter table BIZ_CLAIM_VOUCHER_STATISTICS enable all triggers;
prompt Enabling triggers for BIZ_CLAIM_VOUYEAR__STATISTICS...
alter table BIZ_CLAIM_VOUYEAR__STATISTICS enable all triggers;
prompt Enabling triggers for BIZ_LEAVE...
alter table BIZ_LEAVE enable all triggers;
set feedback on
set define on
prompt Done.
