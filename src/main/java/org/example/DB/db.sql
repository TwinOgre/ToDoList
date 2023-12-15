DROP DATABASE projectToDo;
CREATE DATABASE projectToDo;
USE projectToDo;

CREATE TABLE `member`(
	id int UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
	userId varChar(100) NOT NULL UNIQUE,
	`password` varChar(100) NOT NULL,
	successCount int UNSIGNED NOT NULL,
	regDate dateTime NOT NULL,
	updateDate dateTime NOT NULL
);


CREATE TABLE toDoList (
	id int UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
	memberId int UNSIGNED NOT NULL,
	toDoTitle varChar(100) NOT NULL,
	toDoExplain text NOT NULL,
	executionStatus boolean NOT NULL,
	regDate dateTime NOT NULL,
	updateDate dateTime NOT NULL
);
CREATE TABLE toDoContents(
	id int UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
	listId int UNSIGNED NOT NULL,
	memberId int UNSIGNED NOT NULL,
	content text NOT NULL,
	executionStatus boolean NOT NULL,
	regDate dateTime NOT NULL,
	updateDate dateTime NOT NULL
);
SELECT * FROM `member`;
SELECT * FROM toDoList;
SELECT * FROM toDoContents;
INSERT INTO `member` SET userId = 'user1', password = '1234', successCount = 0, regDate = now(), updateDate = now();

INSERT INTO toDoList SET memberId = 1, toDoTitle = 'test01', toDoExplain = 'test0100', regDate = now(), updateDate = now(), executionStatus = TRUE;

INSERT INTO toDoContents SET memberId = 1, listId = 1,  content = 'test01testContent', regDate = now(), updateDate = now(), executionStatus = TRUE;
