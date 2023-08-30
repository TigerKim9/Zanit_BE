 ---
>#### (유저) 유저 로그인


POST `/loginOk`

```
  //form
id : name="userphone"
pw : name="userpassword"

```

<br>
 
 ---

 ---
>#### (유저) 유저 로그아웃


POST `/logout`

<br>
 
 ---
 
>#### (유저) 유저 회원가입


POST `/signup`

```js
user = {
        'userPhone' : String,
        'userPassword' : String,
        'userName' : String,
        'userGender' : boolean //0 : 여         1 : 남,
        'marketing' : boolean // 0 : 미동의     1:동의
}


```
<span style="color:brown">`리턴값`</span>
|<span style="color:red"> -1 </span>| <span style="color:red">0 </span>| 1 |
| --- | --- | --- |
| <span style="color:red">이미 존재하는 아이디</span> | <span style="color:red">회원가입 실패</span> | 회원가입 성공 |

- - -
<br>

>#### (유저)구독결제(bootPay)

POST `/subscribePay`
```js

```

---
<br>

> #### (유저)클릭한 바 하나의 정보

GET `/barInfo?barId=value`

```js
//barId = {
//        'barId' : Long,
//}
```
<span style="color:brown">`리턴값`</span>

```js
result.barUid           //Long 바 고유번호
result.barName          //String 바 이름
result.barLocation      //String 바 위치
result.barPics          //Multipartfile 바 사진 리스트
result.barMood          //바 분위기
result.barsCocktail     //바에 있는 칵테일 리스트
result.barPhone

```

---
<br>

> #### (유저)칵테일 전체 리스트

GET `/getCocktailList`

```js
보낼 값 없음.
```
<span style="color:brown">`리턴값`</span>

```js
result.barUid                   //Long 칵테일이 있는 바
result.cocktailName             //String 칵테일 이름
result.cocktailDetail           //String 칵테일 설명
result.recoUser                 //int 사용자 추천 유형
result.cocktailPrice            //int 칵테일 가격
result.cocktailPic              //MultiPartfile칵테일 사진

```

---
<br>

> #### (유저)칵테일 검색

GET `/searchCocktail?cocktailName=value`

```js
    String
```
<span style="color:brown">`리턴값`</span>

```js
result.barUid                   //Long 칵테일이 있는 바
result.cocktailName             //String 칵테일 이름
result.cocktailDetail           //String 칵테일 설명
result.recoUser                 //int 사용자 추천 유형
result.cocktailPrice            //int 칵테일 가격
result.cocktailPic              //MultiPartfile칵테일 사진

```

---
<br>



> #### (유저)쿠폰 사용하기

POST `/couponUse`

```js
coupon = {
        "couponUid" : Long,
        "userUid" : Long,      //백에서 로그인된 유저값을 넣을 수도 있음...향후 결정
        "usedBar" : Long,
        "usedCocktail" : Long,
        "coverCharge" : int,

}
```
<span style="color:brown">`리턴값`</span>

| <span style="color:red">0 </span>| 1 |
| --- | --- |
| <span style="color:red">사용 실패</span> | 사용 성공 |


---
















> #### (관리자)바 등록

POST `/registBar`
```js
bar = {
        'barUid' : Long,
        'barName' : String ,
        'barLocation' : String,
        'barPics' : Multipartfile,
        'barMood' : String,
        'barPhone' : String
}

```

<span style="color:brown">`리턴값`</span>

|<span style="color:red"> -2 | <span style="color:red">0 </span>| 1 |
| --- | --- | --- |
| <span style="color:red">세션만료&로그인오류 | <span style="color:red">회원가입 실패</span> | 회원가입 성공 |

---
<br>

> #### (관리자)칵테일 등록

POST `/registCocktail`
```js

//칵테일 사진이 여러개 일시
cocktailPics = []

cocktailPic = {
        'cocktailPic' : Multipartfile,
        
}
for문..
cocktailPics.append(cocktailPic)


cocktail = {
        'cocktailName' : String ,
        'cocktailDetail' : String,
        'recoUser' : int,       // 0: 입문자	1: 캐쥬얼드링커         2: 헤비드링커
        'cocktailPic' : cocktailPics
}



/*-------------------------------------*/
//칵테일 사진이 한개 일시


cocktail = {
        'cocktailName' : String ,
        'cocktailDetail' : String,
        'recoUser' : int,       // 0: 입문자	1: 캐쥬얼드링커         2: 헤비드링커
        'cocktailPic' : Multipartfile
}
```

<span style="color:brown">`리턴값`</span>

| <span style="color:red">0 </span>| 1 |
| --- | --- |
 | <span style="color:red">등록 실패</span> | 등록 성공 |


---
<br>
