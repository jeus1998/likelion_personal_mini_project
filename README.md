# 멋쟁이사자 백엔드 스쿨 5기 ♻️멋사마켓 미니 프로젝트 

```

💡 여러분들이 많이 사용하고 있는 🥕당근마켓, 중고나라 등을 착안하여 여러분들만의 중고 제품 거래 플랫폼을 만들어보는 미니 프로젝트입니다.

사용자가 중고 물품을 자유롭게 올리고, 댓글을 통해 소통하며, 최종적으로 구매 제안에 대하여 수락할 수 있는 형태의 중고 거래 플랫폼의 백엔드를 만들어봅시다.


```

##### 먼저 체크 해주세요!

```

1. 미션 수행하기만 보고 개발을 진행하다가 개발을 끝내고 제출 마감일인 7월 5일에 미션 제출하기 부분을 확인하여 
   구현해야 하는 기능을 분리하여 이슈 단위로 commit 하지 못하고 한 번에 commit 하였습니다.
2. 제출 내용에 PR 부분이 선택이고 Pull Requests 개념을 이해 못 해서 하지 않았습니다.
   이해한다면 추후에 PR 하겠습니다. (7/5 안에)
3. 모든 요청에 대한 응답은 정상 동작시 endPoints에 맞게 하였고 에러가 발생하면 그에 맞게 throw를 했다.
4. ERD중 comment에 해당하는 부분을 endPoints와 다르게 url에 password를 실어서 요청하는 방식으로 하였습니다.

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
 * @NonNull을 MarketEntity에 반드시 포함되어야하는 필드에 붙였다.
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

3. 등록된 물품 정보는 수정이 가능하다. (이때, 물품이 등록될 때 추가한 비밀번호를 첨부해야 한다)
#### 기능추가 ( 업데이트 하고 싶은 필드만 업데이트 하도록) 

* itemid를 @PathVariable로 받아서 repository에서 찾는 방식을 활용했다. 
* MarketDto 타입으로 dto 객체에 비밀번호를 받아오고 equals를 사용한 문자열 비교를 통해서 원래 있던 
entity 비밀번호와 dto에 있는 비밀번호 즉 (고객이 입력한 비밀번호) 가 틀리면 동작 못하게 throw 했다.
* endPoints 에서는 업데이트를 위해 모든 필드를 RequestBody를 통해 보냈지만 내 코드는 삼항 연산자를 통해서 업데이트 하고싶은 필드만 업데이트 하도록 하였다. 

 4. 등록된 물품 정보에 이미지를 첨부할 수 있다. (이때, 물품이 등록될 때 추가한 비밀번호를 첨부해야 한다)

 * 3번 요구사항에서 사용한 비밀번호 비교 방식을 똑같이 적용하였다.
 * 받아온 이미지는 media 파일 경로에 저장되도록 코드를 작성하였다.

5. 등록된 물품 정보는 삭제가 가능하다.  (이때, 물품이 등록될 때 추가한 비밀번호를 첨부해야 한다)

* 3번,4번 요구사항에서 사용한 비밀번호 비교 방식을 똑같이 적용하였다.
* and 연산자 && 로 writer또한 올바르게 보내야만 동작하도록 구현하였다.
* 삭제는 repository 쿼리 메서드인 deleteById를 통해 구현하였다.

#### DAY 2 / 중고 물품 댓글 요구사항

```
1. 등록된 물품에 대한 질문을 위하여 댓글을 등록할 수 있다. 
    1. 이때 반드시 포함되어야 하는 내용은 대상 물품, 댓글 내용, 작성자이다.
    2. 또한 댓글을 등록할 때, 비밀번호 항목을 추가해서 등록한다.
2. 등록된 댓글은 누구든지 열람할 수 있다. 
    1. 페이지 단위 조회가 가능하다.
3. 등록된 댓글은 수정이 가능하다. 
    1. 이때, 댓글이 등록될 때 추가한 비밀번호를 첨부해야 한다.
4. 등록된 댓글은 삭제가 가능하다. 
    1. 이때, 댓글이 등록될 때 추가한 비밀번호를 첨부해야 한다.
5. 댓글에는 초기에 비워져 있는 답글 항목이 존재한다. 
    1. 만약 댓글이 등록된 대상 물품을 등록한 사람일 경우, 물품을 등록할 때 사용한 비밀번호를 첨부할 경우 답글 항목을 수정할 수 있다.
    2. 답글은 댓글에 포함된 공개 정보이다.
```

#### 2. Comment (멋사마켓 ERD중 comment에 해당) 
#### CommentController, CommentService, CommentRepository, CommentDto, CommentEntity

1. 등록된 물품에 대한 질문을 위하여 댓글을 등록할 수 있다. (반드시 포함되어야 하는 내용은 대상물품, 댓글내용, 작성자, 비밀번호)

* @Valid 로 유효성 검사를 하였다.
* @NonNull을 CommentEntity에 반드시 포함되어야하는 필드에 붙였다.

2. 등록된 댓글은 누구든지 열람할 수 있다. ( 해당 아이템에 대한 모든 댓글 조회 , 페이지 단위 조회)

* @RequestParam으로 읽고 싶은 page와 limit를 받게 해줬다.
* @PathVariable로 조회 하고 싶은 아이템의 id를 받았다.
* Page 클레스를 이용해서 페이지를 만들었고 모든 내용을 조회해야하니 findAll 쿼리 메서드를 활용하였다.
* 모든 Entity를 가져오고 item_id에 해당하는 값들만 찾아내기 위해 새롭게 List<CommentEntity>를 만들고 for each 문을 돌렸다.
```java
List<CommentEntity> filteredCommentEntityList = new ArrayList<>();
        for (CommentEntity comment : commentEntityList) {
            if (comment.getItem_id() == itemId) {
                filteredCommentEntityList.add(comment);
            }
        }
```
* itemId에 맞는 값만 페이징 처리하였다. subList메서드를 활용하였다.
```java
 int startIndex = Math.toIntExact(pageable.getOffset());
 int endIndex = Math.min(startIndex + Math.toIntExact(pageable.getPageSize()), filteredCommentEntityList.size());
 List<CommentEntity> pagedCommentEntityList = filteredCommentEntityList.subList(startIndex, endIndex);
```
* List<CommentEntity> 에서 반환값인 List<CommentDto>로 변환 해주었다. CommentDto의 fromEntity 메서드를 활용하였다.
* 최종 반환값인 Page<CommentDto>에 맞게 반환

3. 등록된 댓글은 수정이 가능하다.  (이때, 댓글이 등록될 때 추가한 비밀번호를 첨부해야 한다)
* @PathVariable을 활용해서 url에 password를 실어서 요청을 해서 비밀번호를 받는 방식으로 하였다.
* endPoints 대로 코드를 구현하면 commentsId만 맞으면 동작이 가능한데 itemId랑 해당 commentsId가 틀린데 동작하는게 이상해서
  commentId에 해당하는 itemId면 동작하도록 구현하였다.
  먼저 repository 메서드인 findById로 해당 comment가 있나 확인하였다.
```java
 if (password.equals(commentEntity.getPassword()) && itemId == commentEntity.getItem_id())
```
  password와 itemID가 맞으면 동작 

4. 등록된 댓글은 삭제가 가능하다.  (이때, 댓글이 등록될 때 추가한 비밀번호를 첨부해야 한다)
* 3번 업데이트와 똑같이 구성하였고 commentId가 존재하고 itemId와 password 가 일치하면 deleteById()메서드를 통해 삭제 하였다.

5. 댓글에는 초기에 비워져 있는 답글 항목이 존재한다. ( 물품을 등록할 때 사용한 비밀번호를 첨부할 경우 답글 항목을 수정할 수 있다.)
* @PathVariable을 통해서 password를 받았다. @PathVariable을 통해서 commentsId를 받았다. 
* 먼저 commetsId를 commentRepository.findById() 매개변수로 줘서 해당 comment가 있나 확인하였다.
* 그에 해당하는 itemId로 marketRepository.findById() 매개변수로 줘서 marketEntity에 값을 저장하였다.
* 받은 password와 저장되어있는 비밀번호를 비교하였다. 
* 비밀번호가 맞으면 dto에 실어온 reply를 set하고 틀리면 404error를 throw하였다.

#### DAY 3 / 구매 제안 요구사항

```
1. 등록된 물품에 대하여 구매 제안을 등록할 수 있다. 
    1. 이때 반드시 포함되어야 하는 내용은 **대상 물품, 제안 가격, 작성자**이다.
    2. 또한 구매 제안을 등록할 때, 비밀번호 항목을 추가해서 등록한다.
    3. 구매 제안이 등록될 때, 제안의 상태는 **제안** 상태가 된다.
2. 구매 제안은 대상 물품의 주인과 등록한 사용자만 조회할 수 있다.
    1. 대상 물품의 주인은, 대상 물품을 등록할 때 사용한 **작성자와 비밀번호**를 첨부해야 한다. 이때 물품에 등록된 모든 구매 제안이 확인 가능하다. 페이지 기능을 지원한다.
    2. 등록한 사용자는, 조회를 위해서 자신이 사용한 **작성자와 비밀번호**를 첨부해야 한다. 이때 자신이 등록한 구매 제안만 확인이 가능하다. 페이지 기능을 지원한다.
3. 등록된 제안은 수정이 가능하다. 
    1. 이때, 제안이 등록될때 추가한 **작성자와 비밀번호**를 첨부해야 한다.
4. 등록된 제안은 삭제가 가능하다. 
    1. 이때, 제안이 등록될때 추가한 **작성자와 비밀번호**를 첨부해야 한다.
5. 대상 물품의 주인은 구매 제안을 수락할 수 있다. 
    1. 이를 위해서 제안의 대상 물품을 등록할 때 사용한 **작성자와 비밀번호**를 첨부해야 한다.
    2. 이때 구매 제안의 상태는 **수락**이 된다.
6. 대상 물품의 주인은 구매 제안을 거절할 수 있다. 
    1. 이를 위해서 제안의 대상 물품을 등록할 때 사용한 **작성자와 비밀번호**를 첨부해야 한다.
    2. 이때 구매 제안의 상태는 **거절**이 ****된다.
7. 구매 제안을 등록한 사용자는, 자신이 등록한 제안이 수락 상태일 경우, 구매 확정을 할 수 있다. 
    1. 이를 위해서 제안을 등록할 때 사용한 **작성자와 비밀번호**를 첨부해야 한다.
    2. 이때 구매 제안의 상태는 **확정** 상태가 된다.
    3. 구매 제안이 확정될 경우, 대상 물품의 상태는 **판매 완료**가 된다.
    4. 구매 제안이 확정될 경우, 확정되지 않은 다른 구매 제안의 상태는 모두 **거절**이 된다.
```

#### 3. Negotiation (멋사마켓 ERD중 Negotiation에 해당) 
#### NegotiationController,NegotiationService, NegotiationRepository, NegotiationDto, NegotiationEntity

#### NegotiationRepository에 메서드 생성
```java
List<NegotiationEntity> findByPasswordAndWriter(String password, String writer);
List<NegotiationEntity> findByStatusAndItemId(String status, Long itemId);
```






