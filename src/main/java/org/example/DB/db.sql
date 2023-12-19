DROP DATABASE projectToDo;
CREATE DATABASE projectToDo;
USE projectToDo;
DROP TABLE `member`;
CREATE TABLE `member`(
	id int UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
	userId varChar(100) NOT NULL UNIQUE,
	`password` varChar(100) NOT NULL,
	completeCount int UNSIGNED NOT NULL,
	regDate dateTime NOT NULL,
	updateDate dateTime NOT NULL
);

DROP TABLE toDoList ;
CREATE TABLE toDoList (
	id int UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
	memberId int UNSIGNED NOT NULL,
	toDoTitle varChar(100) NOT NULL,
	toDoExplain text NOT NULL,
	executionStatus boolean NOT NULL,
	regDate dateTime NOT NULL,
	updateDate dateTime NOT NULL
);
DROP TABLE toDoContents ;
CREATE TABLE toDoContents(
	id int UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
	listId int UNSIGNED NOT NULL,
	memberId int UNSIGNED NOT NULL,
	resetByCreateId int UNSIGNED NOT NULL,
	content text NOT NULL,
	executionStatus boolean NOT NULL,
	regDate dateTime NOT NULL,
	updateDate dateTime NOT NULL
);
SELECT * FROM `member`;
SELECT * FROM toDoList;
SELECT * FROM toDoContents;

INSERT INTO `member` SET userId = 'user1', password = '1234', completeCount = 1, regDate = now(), updateDate = now();

INSERT INTO toDoList SET memberId = 1, toDoTitle = 'testcomplete', toDoExplain = 'test0100', regDate = now(), updateDate = now(), executionStatus = false;
INSERT INTO toDoList SET memberId = 2, toDoTitle = 'test02', toDoExplain = 'test0200', regDate = now(), updateDate = now(), executionStatus = false;



INSERT INTO toDoContents SET memberId = 1, listId = 1, resetByCreateId = 6, content = 'testtt', regDate = now(), updateDate = now(), executionStatus = FALSE;

SELECT * FROM toDoList WHERE memberId = 1 and executionStatus = TRUE;
SELECT toDoContents.* FROM toDoList INNER JOIN toDoContents ON toDoList.Id = toDoContents.listId;
SELECT * FROM toDoContents WHERE listId = 1;
UPDATE toDoList SET toDoTitle = '%s', toDoExplain = '%s' WHERE id = %d;
UPDATE toDoList
SET toDoTitle = 'updated',
toDoExplain = 'ExUpdated'
WHERE id = 1;

SELECT * FROM toDoList WHERE id = 1;
SELECT * FROM toDoContents WHERE listId = 1 AND id = 1;
SELECT * FROM toDoContents WHERE listId = %d AND id = %d;
SELECT * FROM toDoContents WHERE listId = 1;

UPDATE toDoContents SET content = '%s' WHERE id = %d;
UPDATE toDoContents SET executionStatus = FALSE WHERE listId = 3 AND resetByCreateId =5;
UPDATE toDoList SET executionStatus = FALSE WHERE id = 1;
UPDATE `member` SET completeCount = 1 WHERE id = 1;
UPDATE toDoContents SET ex