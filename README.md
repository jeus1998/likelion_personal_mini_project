# 멋쟁이사자 백엔드 스쿨 5기 ♻️멋사마켓 미니 프로젝트 

```

💡 여러분들이 많이 사용하고 있는 🥕당근마켓, 중고나라 등을 착안하여 여러분들만의 중고 제품 거래 플랫폼을 만들어보는 미니 프로젝트입니다.

사용자가 중고 물품을 자유롭게 올리고, 댓글을 통해 소통하며, 최종적으로 구매 제안에 대하여 수락할 수 있는 형태의 중고 거래 플랫폼의 백엔드를 만들어봅시다.


```

##### 먼저 체크 해주세요!

```

1. gitingnore 파일을 수정하지 않았습니다.
2. 미션 수행하기만 보고 개발을 진행하다가 개발을 끝내고 제출 마감일인 7월 5일에 미션 제출하기 부분을 확인하여 
   구현해야 하는 기능을 분리하여 이슈 단위로 commit 하지 못하고 한 번에 commit 하였습니다.
3. 제출 내용에 PR 부분이 선택이고 Pull Requests 개념을 이해 못 해서 하지 않았습니다.
   이해한다면 추후에 PR 하겠습니다. (7/5 안에)

```
#### DAY 1 / 중고 물품 관리 요구사항

```
1. 누구든지 중고 거래를 목적으로 물품에 대한 정보를 등록할 수 있다. 
    1. 이때 반드시 포함되어야 하는 내용은 제목, 설명, 최소 가격, 작성자이다.
    2. 또한 사용자가 물품을 등록할 때, 비밀번호 항목을 추가해서 등록한다.
    3. 최초로 물품이 등록될 때, 중고 물품의 상태는 **판매중** 상태가 된다.
2. 등록된 물품 정보는 누구든지 열람할 수 있다. 
    1. 페이지 단위 조회가 가능하다.
    2. 전체 조회, 단일 조회 모두 가능하다.
3. 등록된 물품 정보는 수정이 가능하다. 
    1. 이때, 물품이 등록될 때 추가한 비밀번호를 첨부해야 한다.
4. 등록된 물품 정보에 이미지를 첨부할 수 있다.
    1. 이때, 물품이 등록될 때 추가한 비밀번호를 첨부해야 한다.
    2. 이미지를 관리하는 방법은 자율이다.
5. 등록된 물품 정보는 삭제가 가능하다. 
    1. 이때, 물품이 등록될 때 추가한 비밀번호를 첨부해야 한다.

```

#### 1. Market (멋사마켓 ERD중 sales_item에 해당) 
#### MarketController, MarketService, MarketRepository, MarketDto, MarketEntity

1. 물품에 대한 정보를 등록할 수 있다. (반드시 포함되어야 하는 내용은 제목, 설명, 최소가격, 작성자, 비밀번호)
 
 * @Valid 로 유효성 검사를 하였다. 

 * @NonNull을 MarketEntity에 반드시 포함되어야하는 필드에 붙였다. -> ex) 제목: title

 * 최초로 물품이 등록될 때, 물품의 상태 판매중 
 ```java
  MarketEntity marketEntity = new MarketEntity();
  marketEntity.setStatus("판매중");
 ```
2. 등록된 물품 정보는 누구든지 열람할 수 있다.

* 단일 조회
 itemId를 @PathVariable을 통해 받아서 있나 없나 JPA 쿼리 메서드인 findById(id)를 통해 찾고 있으면 해당 내용을 반환했다.

* 전체 조회 ( 페이지 단위 조회 )
@RequestParam으로 읽고 싶은 page와 limit를 받게 해줬다.

Page 클레스를 이용해서 페이지를 만들었고 모든 내용을 조회해야하니 findAll 쿼리 메서드를 활용하였다.


3. 등록된 물품 정보는 수정이 가능하다. 
 


   





