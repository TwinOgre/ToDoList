## ToDoList

### 할일목록을 작성하여 동기부여를 돕는 텍스트 게시판 

<br />
- CRUD를 바탕으로한 텍스트 게시판
<br />
- 완료체크를 통한 할일목록 관리
<br />
- 사용자별 완료체크시 증가하는 완료횟수를 통해 동기부여
<br />
- 미완료된 할일목록 호출 / 완료된 할일목록 호출을 통해 해야할 일들과 지금까지 해온 일들의 목록을 한눈에 볼 수 있다.

<br/>


## 🛠 개발환경
- **언어:** JAVA
- Intellij IDEA Community Edition 2023.03.01
### 버전 관리
- **버전 관리 시스템:** Git
- **리모트 저장소:** GitHub을 사용하여 소스 코드 관리.
### 데이터베이스
- **데이터베이스:** MySQL
<br/>
<br/>

## ☁️ ERD

![ERD](https://i.imgur.com/nPDFrZr.png)

<br>
<br>

## 👀 시연영상
[![이미지 텍스트](스크린샷 이미지)](유투브링크)

[![Video Label](http://img.youtube.com/vi/'유튜브주소의id'/0.jpg)](https://youtu.be/'유튜브주소의id')

## 🔥 트러블 슈팅

### 🚨 Issue 1
### 🚧 중단이 안되는 문제

A. 이슈 내역
<br>
회원가입과 로그인을 제외한 메서드들의 내부 상단에 loigincheck() 메서드가 있다.
logincheck() 메서드는 로그인 유무를 확인하고 로그인 시 통과, 비로그인 시 중단하는 역할을 한다.
그런데 비로그인 시 기존 메서드 탈출이 되지 않는다.

위와 같은 문제로 toDoList(할일목록)은 할일목록이 없을 때 출력을 하지 않고 중단해야 하는데 일을 하지 않는 문제가 발생했다.
<br>
문제점 설명
문제는 return이 메서드 안에 존재하여 logincheck와 toDoList 두 메서드만 끝나지 상위 메서드는 끝나지 않는것이 문제다.
위 두 메서드들을 메서드로 사용하지 않고 코드로 풀어 적을 수는 있겠지만 logincheck()와 toDoList() 두 메서드 모두 여러 곳에서 사용하고 있고, 특히 toDoList()는 그 코드의 길이가 길어 이를 풀어 적게 되면 상위메서드가 난잡해진다.
<br>
## 🛑 원인
- return이 loginCheck()와 toDoList() 만 끝냄.
<br>
<br>

## 🚥 해결
- Container에 static boolean 형태의 플래그변수 tryFlag를 만들어 해결하였다.
```java
private static boolean tryFlag;
```
- 이 tryFlag 변수는 loginCheck()와 toDoList()가 실행을 시작할 때 setter를 사용해 false로 초기화 된다.
```java
Container.setTryFlag(false);
```
- 메서드가 성공적으로 진행완료되면(loginCheck는 로그인 확인, toDoList는 할일목록이 존재해 출력) tryFlag 변수가 true로 초기화 된다.
```java
Container.setTryFlag(true);
```

- 위 두 메서드의 다음자리에 tryFlag가 false인지 true인지 확인하고 [탈출/진행]을 결정한다.
```java
memberController.loginCheck();
        if(Container.isTryFlag() == false){
            return;
        }
```

### 🚨 Issue 2
### 🚧 세부항목이 사라지는(안사라짐) 문제

A. 이슈 내역
<br>
complete() 세부항목 완료체크 구현 및 기능 검사 중.
create()에서 추가한 세부항목이 표시가 안된다.
<br>
문제점 설명
1. create()의 기능고장, 혹은 toDoList(), toDoContentsList() 등의 
2. 세부항목이 표시가 안되면 세부항목을 완료할 수 없다.
<br>
## 🛑 원인
- create() 중 세부항목의 구현 시 title(제목) 만을 가지고 데이터를 찾은 것이 문제였다.
=> title이 동일한 경우 이전의 글에 세부항목이 추가되고 있었다.
```java
// 문제의 코드
toDoListService.memberFindByTitle(title);
```
<br>
<br>

## 🚥 해결
- title과 briefDescription(간략설명)을 같이 매개변수로 대입해서 글을 찾도록 하였다.
```java
ToDoList toDoList = toDoListService.memberFindByTitle(title,briefDescription);
```
<br>
<br>

### 🚨 Issue 3
### 🚧 완료된 상태의 글이 중복 완료됨

A. 이슈 내역
<br>
complete() 메서드에서 완료된 상태의 글을 요청할 수 있다.
예) 1번 할일이 완료된 상태일때 '1' 입력 시 완료횟수가 증가함.


<br>
문제점 설명
1. 설계는 완료처리된 글은 호출시 "이미 완료처리된 글입니다" 라고 출력되게 하는 것이다.
2. 위와 같은 완료처리시 사용자의 completeCount(완료횟수)가 증가하는 문제가 있다.

<br>

## 🛑 원인


<br>
1. 예외처리를 하지 않음
<br>

## 🚥 해결
- 현재 로그인한 사용자의 아이디와 완료하고자 적은 completeId를 매개변수로 하여 ToDoList 타입의 글을 불러왔다.
- 현재 로그인한 사용자가 입력한 completeId 값의 글 을 가지고 있지 않다면 toDoList 변수는 널값이다.
- 만약 completeId에 해당하는 id의 글을 가지고 있다 하더라도 글의 executionStatus(실행여부)가 완료된상태(false)라면 안내메시지를 출력하고 complete()메서드를 종료시킬 것이다.
```java
ToDoList toDoList = toDoListService.toDoListFindByIdAndUserId(Container.getLoginedMember().getId(), completeId);
if (toDoList == null) {
            System.out.println(completeId + "번 글은 존재하지 않습니다.");
            Container.getScanner().nextLine();
            return;
        } else if(toDoList.isExecutionStatus() == false){
            System.out.println(completeId + "번 글은 이미 완료된 상태입니다.");
            Container.getScanner().nextLine();
            return;
        }
```
 
