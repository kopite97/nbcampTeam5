# ⛺ 캠프 관리 프로그램
프로그램 역할 : 내배캠 스프링 수강생들을 관리하는 프로그램
* 프로젝트 기간 : 2024.05.01 ~ 2024.05.09 (9일)
* 참여자 : 노석준(👑), 윤다빈, 이세원, 한해정, 최영주
<br>

## 프로젝트 소개
<details>
<summary> 📑 요구사항 정의 </summary>

* 과목
  * 필수 과목 : Java, 객체지향, Spring, JPA, MySQL
  * 선택 과목 : 디자인 패턴, Spring Security, Redis, MongoDB
 
* 조건
  * 최소 3개 이상의 필수 과목, 2개 이상의 선택 과목을 선택
  * 캠프 기간동안 선택한 과목별로 10회의 시험
  * 캠프 매니저는 수강생을 등록 및 관리
  * 캠프 매니저는 수강생들의 과목과 시험 점수를 등록 및 관리
  * 점수 데이터 타입 : 정수형
  * 등급 산정 기준
    * 필수 과목 : A(95 ~ 100), B(90 ~ 94), C(80 ~ 89), D(70 ~ 79), F(60 ~ 69), N(60점 미만)
    * 선택 과목 : A(90 ~ 100), B(80 ~ 89), C(70 ~ 79), D(60 ~ 69), F(50 ~ 59), N(50점 미만)

* 모델 정보 예시
  * 수강생 : 고유 번호, 이름, 과목 목록
  * 점수 : 과목 고유 번호, 수강생 고유 번호, 회차, 점수, 등급(A, B, C, D, E, F, N)
  * 과목 : 고유 번호, 과목명, 과목타입(필수, 선택)

</details>
<details>
<summary> ⚙ 기능 명세서 </summary>

<br>

*✔ 필수 기능 / ➕ 추가 기능*  
* 수강생 관리
  
  ✔ 수강생 정보 등록[고유 번호, 이름, 과목 목록] (고유 번호 중복X)  
  ✔ 수강생 목록 조회[고유 번호, 이름]  
  ➕ 수강생 상태 관리[상태 종류 : Green, Red, Yellow]  
  ➕ 수강생 정보 조회[고유 번호, 이름, 상태, 선택한 과목명]  
  ➕ 수강생 정보 수정[이름 상태] (이름 또는 상태를 입력받아 수정)  
  ➕ 상태별 수강생 목록 조회[고유 번호, 이름]  
  ➕ 수강생 삭제 (해당 수강생의 점수 기록도 함께 삭제)
  
* 점수 관리
  * 등록하려는 과목의 회차 점수가 이미 등록되어 있다면 등록X, 과목의 회차 점수 중복되어 등록X  
  * 회차에 10초과 및 1미만의 수 저장X (회차 범위 : 1 ~ 10)  
  * 점수에 100초과 및 음수 저장X (점수 범위 : 0 ~ 100)

  ✔ 수강생의 과목별 시험 회차 및 점수 등록 (점수를 등록하면 자동으로 등급이 추가 저장)  
  ✔ 수강생의 과목별 회차 점수 수정  
  ✔ 수강생의 특정 과목 회차별 등급 조회[회차, 등급]  
  ➕ 수강생의 과목별 평균 등급 조회[과목명, 평균 등급]  
  ➕ 특정 상태 수강생들의 필수 과목 평균 등급 조회[수강생 이름, 필수 과목 평균 등급]  

</details>
<br>

## 👩‍💻👨‍💻 팀원 구성
| 노석준 | 윤다빈 | 이세원 | 한해정 | 최영주 |
|:---:|:---:|:---:|:---:|:---:|
| <img src="https://ca.slack-edge.com/T06B9PCLY1E-U06ME1DLG8G-2b4034c1de43-512" height="150"/> | <img src="https://ca.slack-edge.com/T06B9PCLY1E-U06AU1D51EY-471f7c218a7a-512" height="150"/> | <img src="https://ca.slack-edge.com/T06B9PCLY1E-U06RHFEUZN3-59f988f87922-512" height="150"/> | <img src="https://ca.slack-edge.com/T06B9PCLY1E-U06KBF4M4AF-06e417b77203-512" height="150"/> |<img src="https://ca.slack-edge.com/T06B9PCLY1E-U06KADG3X1P-122afee5e3ca-512" height="150"/>  |
| [@kopite97](https://github.com/kopite97) | [@yoodab](https://github.com/yoodab) | [@leesw1945](https://github.com/leesw1945) | [@HaejungHan](https://github.com/HaejungHan) | [@ysy56](https://github.com/ysy56) |
<br>

## 🤝 역할 분담
* 노석준 : Initializer, StudentManager
* 윤다빈 : Student
* 이세원 : Score
* 한해정 : DisplayManager
* 최영주 : Subject
<br>

## 🏤 클래스 다이어그램
// 클래스 다이어그램 이미지 추가 예정  
<br>

## 🌊 흐름도
// 흐름도 이미지 추가 예정  
<br>

## 🏗 프로젝트 구조
```
├── src/camp
│    ├── Controller
│    │   ├── InitializeManager.java
│    │   └── StudentManager.java
│    ├── Model
│    │   ├── Score.java
│    │   ├── ScoreRank.java
│    │   ├── State.java
│    │   ├── Student.java
│    │   ├── Subject.java
│    │   └── SubjectType.java
│    ├── View
│    │   └── DisplayManager.java
│    └── CampManagementApplication.java
├── .gitignore
├── camp-management-app.iml
└── nbcampTeam5.iml
```
<br>

## 🙄 신경쓴 부분
* 객체지향적 설계?
* 혼자 할 수 있더라도 함께하는 협력
<br>

## 😫 트러블 슈팅
* 문제 상황  
  * 의도했던 기능 : 내용 작성  
  * 발생한 현상(트러블) : 내용 작성  

* 트러블 원인 추론  
// 내용 작성  

* 해결방법  
// 내용 작성  

<br>

## 🙌 프로젝트 후기

#### 👑 노석준
// 내용 작성
#### 🧢 윤다빈
// 내용 작성
#### 🐊 이세원
// 내용 작성
#### 🐰 한해정
// 내용 작성
#### 👳‍♀️ 최영주
// 내용 작성
