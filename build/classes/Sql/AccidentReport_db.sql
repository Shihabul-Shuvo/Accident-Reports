--create database AccidentReport
--use AccidentReport

CREATE TABLE users (
    userID int identity(1123212,11) primary key,
    username varchar(255)  not null,
    mobile varchar(255) not null,
    age int,
	gender varchar(10) ,
    password varchar(255),
	uImage image,
	joindate date DEFAULT getdate(),
	unique (mobile, username)
);

insert into users values('Shovo','01767323258',22,'male','1234','D:\Files\Java programs\FXML\AccidentReports\src\Images\Hatirjheel_bus.jpg',getdate());
insert into users values('sakib','01723321696',19,'male','1234','D:\Files\Java programs\FXML\AccidentReports\src\Images\Hatirjheel_flyover.jpg',getdate());

select * from users
--select password from users where username like 'shovo'
--delete from users where username like 'chris'
--drop table users

CREATE TABLE admin ( 
adminid int primary key identity(1001,1) not null, 
userid int foreign key references users(userid),
) 

CREATE TABLE message ( 
msgid int primary key identity(1001,1) not null, 
userid int foreign key references users(userid), 
text varchar(255),
date varchar(255) 
) 

CREATE TABLE Mods ( 
Modid int primary key identity(1001,1) not null, 
userid int foreign key references users(userid), 
) 

EXEC sp_rename 'Mod', 'Mods';
alter table message add date varchar(255) 
insert into Mods values(1123234) 

insert into users values('Shovo','01767323258',22,'male','1234',getdate()); 

--drop table reports

create table reports(
	reportID int identity(111, 13) primary key,
	upazilaname varchar(255),
	rUser varchar(255) not null,
	rDate varchar(10) not null,
	rType varchar(255) not null,
	rPlace varchar(255) not null,
	rInjured int not null,
	rDeath Int not null,
	rCause varchar(255) not null,
	rDescription varchar(255) not null,
	rImage varchar(255),
	isvarified varchar(20) default 'Pending'
)
insert into reports (ruser, rDate ,rtype, rplace, rinjured, rdeath, rcause, rDescription, rImage, isvarified)
			values ('Shovo', '06/09/2022', 'Chemical', 'dhaka', 57, 21, 'Speed', 'Two car....', 'D:\Files\Java programs\FXML\AccidentReports\src\Images\Hatirjheel_flyover.jpg', 'Yes')
insert into reports (ruser, rDate ,rtype, rplace, rinjured, rdeath, rcause, rDescription, rImage, isvarified)
			values ('Shovo', '07/09/2022', 'Road', 'Chittagong', 19, 0, 'Speed', 'Two car....', 'D:\Files\Java programs\FXML\AccidentReports\src\Images\Hatirjheel_flyover.jpg', 'Yes')
insert into reports (ruser, rDate ,rtype, rplace, rinjured, rdeath, rcause, rDescription, rImage, isvarified)
			values ('Shovo', '11/09/2022', 'Others', 'Comilla', 200, 111, 'Speed', 'Two car....', 'D:\Files\Java programs\FXML\AccidentReports\src\Images\Hatirjheel_flyover.jpg', 'Yes')
insert into reports (ruser, rDate ,rtype, rplace, rinjured, rdeath, rcause, rDescription, rImage, isvarified)
			values ('Shovo', '14/09/2022', 'Road', 'Jassore', 15, 3, 'Speed', 'Two car....', 'D:\Files\Java programs\FXML\AccidentReports\src\Images\Hatirjheel_flyover.jpg', 'Yes')
insert into reports (ruser, rDate ,rtype, rplace, rinjured, rdeath, rcause, rDescription, rImage, isvarified)
			values ('Shovo', '01/09/2022', 'Fire', 'Joldhaka', 150, 50, 'Speed', 'Two car....', 'D:\Files\Java programs\FXML\AccidentReports\src\Images\Hatirjheel_flyover.jpg', 'Yes')
insert into reports (ruser, rDate ,rtype, rplace, rinjured, rdeath, rcause, rDescription, rImage, isvarified)
			values ('Shovo', '13/09/2022', 'Road', 'Srinagar', 1, 1, 'Speed', 'Two car....', 'D:\Files\Java programs\FXML\AccidentReports\src\Images\Hatirjheel_flyover.jpg', 'Yes')
insert into reports (ruser, rDate ,rtype, rplace, rinjured, rdeath, rcause, rDescription, rImage, isvarified)
			values ('Shovo', '03/09/2022', 'severe', 'dhaka', 4, 10, 'Speed', 'Two car....', 'D:\Files\Java programs\FXML\AccidentReports\src\Images\Hatirjheel_flyover.jpg', 'Yes')
insert into reports (ruser, rDate ,rtype, rplace, rinjured, rdeath, rcause, rDescription, rImage, isvarified)
			values ('Shovo', '03/09/2022', 'severe', 'dhaka', 4, 10, 'Speed', 'Two car....', 'D:\Files\Java programs\FXML\AccidentReports\src\Images\Hatirjheel_flyover.jpg', 'Yes')
insert into reports (ruser, rDate ,rtype, rplace, rinjured, rdeath, rcause, rDescription, rImage, isvarified)
			values ('sakib', '03/09/2022', 'severe', 'dhaka', 4, 10, 'Speed', 'Two car....', 'D:\Files\Java programs\FXML\AccidentReports\src\Images\Hatirjheel_flyover.jpg', 'Yes')
insert into reports (ruser, rDate , rtype, rplace, rinjured, rdeath, rcause, rDescription, rImage, isvarified)
			values ('Nirjon', '03/09/2022', 'Bad', 'dhaka', 100, 100, 'Speed', 'Two car....', 'D:\Files\Java programs\FXML\AccidentReports\src\Images\Hatirjheel_bus.jpg','Declined')

select * from reports
Select * from reports where ruser = 'shovo' and isvarified = 'Pending'
Select * from reports where ruser = 'shovo' and isvarified = 'Yes'
select * from reports order by rdeath asc
select reportID,ruser,rdate,rtype,rplace,rinjured,rdeath,rcause,isvarified from 
reports where ruser = 'shovo' and isvarified = 'yes' order by rinjured desc

select reportID,Upazilaname,rdate,rtype,rplace,rinjured,rdeath,rcause from 
reports where isvarified = 'Yes' order by reportId desc

delete from reports where reportid = 150

select count(reportId) from reports

select upazilas.UpazilaName, from 

select * from districts 
select * from division
