# ReviewUs

## 프로젝트 소개
소비자들이 사용한 제품, 장소, 서비스 등에 리뷰하여 정보를 공유하는 뉴스피드 웹 서비스를 구현하고자 하였으며,댓글 알바 없는 순수 리뷰 실 사용자 서비스를 만들기 위해 기획했습니다.

## 팀원 🤝

| [이동욱](https://github.com/kbsat) | [김성현](https://github.com/lazzzykim) | [이제원](https://github.com/jeawonlee0327) | [한정민](https://github.com/jeongminy) |
|:----------:|:----------:|:----------:|:----------:|
|![image](https://github.com/Moveuk/reviewus/assets/84966961/8666e7b4-4b6a-43a1-afaf-5492888384b3)|![image](https://github.com/Moveuk/reviewus/assets/84966961/9c5971ff-d2f3-42cd-a89d-b8c8f910271a)|![image](https://github.com/Moveuk/reviewus/assets/84966961/8bd2ab00-d030-4b1e-88b4-f023c10acbc1)|![image](https://github.com/Moveuk/reviewus/assets/84966961/c7f575c7-6564-4338-b403-b0a618567f5d)|

## ⚙️ Backend Stack

<img src="https://img.shields.io/badge/kotlin-7F52FF?style=for-the-badge&logo=kotlin&logoColor=white"> 
<img src="https://img.shields.io/badge/springboot-6DB33F?style=for-the-badge&logo=spring&logoColor=white"> 
<img src="https://img.shields.io/badge/amazonsimpleemailservice-DD344C?style=for-the-badge&logo=amazonsimpleemailservice&logoColor=white"> 


## 와이어 프레임 구상
![image](https://github.com/Moveuk/reviewus/assets/84966961/0f57b2a0-d3a1-493e-957f-1adb38534ce9)

## API
![image](https://github.com/Moveuk/reviewus/assets/84966961/2c4049fe-27e4-4b9a-97a8-6919ae757f96)

## ERD
![image](https://github.com/Moveuk/reviewus/assets/84966961/26480312-df73-4a41-93e2-a6c9b34cc1ad)

## 패키지 구조

![image](https://github.com/Moveuk/reviewus/assets/84966961/b9ad62da-a880-448d-8ccf-e6a3d77c56be)

## 기능 
- [x]  게시물 CRUD 기능 **→ 이제원님**
    - 게시물 작성, 조회, 수정, 삭제 기능
- [x]  뉴스피드 기능(메인 페이지/전체 조회 페이지) **→ 이제원님**
    - 뉴스피드 페이지
        - 사용자가 다른 사용자의 게시물을 한 눈에 볼 수 있는 뉴스피드 페이지가 있어야 합니다.
- [x]  상세보기 기능(디테일 페이지) **→ 이제원님**
    - 게시글의 상세 페이지
        - 게시글의 내용과 댓글을 볼 수 있는 상세페이지가 있어야 합니다.
- [x]  댓글 CRUD 기능 **→ 김성현님**
    - 댓글 작성, 조회, 수정, 삭제 기능
        - 사용자는 게시물에 댓글을 작성할 수 있고 본인의 댓글은 수정 및 삭제를 할 수 있어야 합니다.
        - 대댓글 기능
- [x]  사용자 인증/인가 기능 **→ 이동욱님**
    - 회원가입 기능
        - 새로운 사용자가 ID와 비밀번호의 형태로 서비스에 가입할 수 있어야 합니다.
            - 이 때, 비밀번호는 안전하게 암호화되어 저장되어야 합니다!
    - 로그인 및 로그아웃 기능
        - 사용자는 자신의 계정으로 서비스에 로그인하고 로그아웃할 수 있어야 합니다.
    - 인가 기능
        - 앞서 구현하신 게시물 CRUD 기능에 게시물과 댓글 조회를 제외한 나머지 기능들은 전부 인가(Authorization) 개념이 적용되어야 하며 이는 JWT와 같은 토큰으로 검증이 되어야 할 것입니다.
        - 예컨대, 내가 작성한 글을 남이 삭제할 수는 없어야 하고 오로지 본인만 삭제할 수 있어야 한다.
- [x]  프로필 관리 **→ 한정민님**
    - 프로필 수정 기능
        - 이름, 한 줄 소개와 같은 기본적인 정보를 볼 수 있어야 하며 수정할 수 있어야 합니다.
        - 비밀번호 수정 시에는 비밀번호를 한 번 더 입력받는 과정이 필요합니다.
- [x]  **좋아요 기능**
    - 게시물 및 댓글 좋아요/좋아요 취소 기능
        - 사용자가 게시물이나 댓글에 좋아요를 남기거나 취소할 수 있어야 합니다.
        - 이 때, 본인이 작성한 게시물과 댓글에 좋아요는 남길 수 없도록 해봅니다!
- [X]  **이메일 가입 및 인증 기능**
    - 이메일 가입 시 이메일 인증 기능을 포함하는 것이 좋습니다.
