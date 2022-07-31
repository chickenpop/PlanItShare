# PlanItShare
> 자바 웹 프로젝트로 여행일정관리 사이트를 구현했습니다

## 개발환경

<img src="https://img.shields.io/badge/Eclipse IDE-525C86?style=for-the-badge&logo=Eclipse IDE&logoColor=white"> <img src="https://img.shields.io/badge/JAVA-004088?style=for-the-badge&logo=JAVA&logoColor=white"> <img src="https://img.shields.io/badge/Oracle-F29051?style=for-the-badge&logo=Oracle&logoColor=white"> <img src="https://img.shields.io/badge/SQL Developer-534B86?style=for-the-badge&logo=SQL Developer&logoColor=white"> 

[OS & Server] Windows, Apache Tomcat

### 개인 업무

- BootStrap을 이용하여 디자인 되었습니다.

- 지역게시판
  - 관광명소 목록 조회 및 검색
  ![관광명소(이름검색)](https://user-images.githubusercontent.com/50548923/182029602-e855ffaf-9044-487c-8d93-f4a297eb83e6.PNG)
  ![관광명소(카테고리명검색)](https://user-images.githubusercontent.com/50548923/182029604-dbb39297-9740-4809-9b8c-adb599aabd24.PNG)
    - 검색어로 관광명소 목록을 출력합니다

  - 관광명소 상세 페이지(리뷰 및 관심 등록)
  ![관광명소(상세보기)](https://user-images.githubusercontent.com/50548923/182029600-36c7d6a1-16b5-4de8-b6f4-e99727770dcf.PNG)
  ![관광명소(관심등록)](https://user-images.githubusercontent.com/50548923/182029597-1c3da2cc-ec93-4717-908e-24703ce82878.PNG)
    - 관심등록에 fontawesome와 css 클래스를 사용하여 하트의 변화를 줍니다.
  ![관광명소(리뷰등록)](https://user-images.githubusercontent.com/50548923/182029598-2cb36d8e-655c-4103-a16a-e09c1ecdb87d.png)
    - 파일업로드 라이브러리를 사용해 리뷰에 사진을 넣을 수 있습니다. Ajax을 사용하여 비동기로 리뷰를 추가합니다
  
- 관리자페이지
  - 통계 조회
  ![관리자_통계확인](https://user-images.githubusercontent.com/50548923/182029569-794c16ee-6e8b-4615-ad88-bd2823fad16e.png)
    - 최근 일주일간 성별에 따른 회원가입자 수 정보를 가져와 차트에 표시하고, 남녀 비율을 계산합니다
  - 회원 관리
  ![관리자_회원조회](https://user-images.githubusercontent.com/50548923/182029567-9281e594-4562-4721-b531-429a749fdb03.png)
    - 회원 상태는 탈퇴회원, 일반회원 두가지로 조회 정보를 다르게 표시합니다

- 여행일정 
  - 일정 지도 보기
  ![일정(지도)](https://user-images.githubusercontent.com/50548923/182029554-7ee5ff7d-75e2-4e58-ae66-65e0efe870f2.png)
    - 카카오맵의 마커 제어와 인포 윈도우를 커스텀하여 구현하였습니다

  
### 제작 후기

- 웹 페이지 간의 데이터 응답과 요청, Ajax 사용하여 비동기적인 페이지 처리, 사용자에 따른 권한을 부여하기 위해 세션을 활용하여 리뷰 등록에 사용하였습니다. 또한, Map API에서 마커 기능과 인포 윈도우를 조합하여 일정이 지도에 표시, 차트에서 남녀 데이터 정보를 계산하기 위해 로직을 구성하는 등의 경험을 알 수 있었습니다. 
- 계획한 페이지들을 제작하면서, 예상치 못한 오류를 해결하고 배우면서 실력을 향상하고 다른 팀원이 동일한 문제가 발견했을 때, 원인과 해결법을 설명해주었습니다.
- 팀원들이 작성한 메소드를 활용하여 새로운 기능을 추가하는 경험과 공통으로 사용될 메뉴 요소들을 조각 페이지로 분리, 관리하고 폴더의 위치나 주소 등이 합의하는 과정을 통해 웹 페이지 하나를 제작하는 것에 많은 합의 필요하다는 것을 알게 해준 프로젝트입니다.

