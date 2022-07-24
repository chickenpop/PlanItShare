<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Plan It Share</title>
<%@ include file="/WEB-INF/views/inc/asset.jsp"%>
<style>

   @font-face {
      font-family: 'Eulyoo1945-Regular';
      src:
         url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2102-01@1.0/Eulyoo1945-Regular.woff')
         format('woff');
      font-weight: normal;
      font-style: normal;
   }
   
   header {
      background-color: transparent;
   }
   
   section {
      width: 100%;
      margin: 0 auto;
      margin-bottom: 130px;
   }
   
   #side {
      width: 400px;
      height: 100vh;
      position: relative;
      float: left;
   }
   
   #side>div {
      margin-left: 130px;
      font-size: 50px;
      margin-top: 310px;
      width: 400px;
      font-family: 'Eulyoo1945-Regular';
   }

   main {
      padding: 0;
   }

   #main {
      display: flex;
   }

   #main #mainimg {
      flex-wrap: nowrap;
      width: 100%;
      height: 100vh;
      background-image: url('asset/image/main.jpg');
      background-size: cover;
      display: inline-block;
      margin-right: 0;
      
   }

   .searchbtn {
      background-color: #3392A6;
      border: none;
      width: 120px;
      margin-left: 130px;
      color: white;
      font-size: 20px;
      padding: 0;
   }
   
   .searchbtn:hover {
      opacity: .6;
      background-color: #3392A6;
      transition: .5s;
      color: white;
   }
   
   #search-box {
      margin: 0 auto;
      text-align: center;
      margin-top: 200px;
   }
   
   h2 {
      margin-top: 100px;
      margin-bottom: 50px;
      margin-right: 30px;
      text-align: center;
   }
   
   #search-box>* {
      display: inline-block;
      margin-right: 10px;
   }
   
   #search-box select {
      width: 100px;
   }
   
   #search-box input[type=text] {
      width: 300px;
   }
   
   #search-box input[type=button] {
      width: 80px;
   }
   
   #list {
      width: 1100px;
      margin: 0 auto;
   }
   
   .recommendlist {
      float: left;
      margin: 10px;
      text-align: center;
      cursor: pointer;
   }
   
   .recommendlist > img {
      width: 250px;
      height: 150px;
      background-size: contain;
      
   }
   
   
   #list::after {
      content: '';
      display: block;
      clear: both;
   }
   
   #planlist {
   
         width: 1000px;
         margin: 0 auto;
         
    }
   
   .plan {
         float: left;
         margin: 10px;
         text-align: center;
         cursor: pointer;
    }
   
   
   .plan> img {
         width: 300px;
         height: 200px;
         background-size: contain;
   }
   
   #planlist::after {
        content: '';
        display: block;
        clear: both;
    }
    
    .fa-solid.fa-heart {
         color:tomato;
    }
   
    .fa-solid {
         margin-left: 10px;
    }
    
   #top img {
      width: 60px;
      height: 60px;
      margin-top: 10px;
      margin-right: 150px;
      float: right;
   } 
   

</style>
</head>
<body>
   <main>
      <%@ include file="/WEB-INF/views/inc/header.jsp"%>
      <section>
      
         <div id="main">
            <div id="side">
               <div>
                  plan it <br>share
               </div>
               <input type="button" class="searchbtn" value="SEARCH"
                  data-item="#gosearch">
            </div>
            <div id="mainimg">
               <!-- <img src="/planitshare/asset/image/main.jpg"> -->
            </div>
         </div>
         

         <div id="search-box">
            <form id="form" method="POST">
               <table class="search" id="gosearch">
                  <tr>
                     <td>
                        <select name="column" class="form-control" style="width: 130px;">
                              <option value="name">여행지</option>
                              <option value="title">일정</option>
                        </select>
                     </td>
                     <td>
                     <input type="text" name="word" class="form-control" placeholder="도시명을 입력해주세요." required></td>
                     <td>
                        <button type="button" class="btn btn-secondary">
                           <i class="fa-solid fa-magnifying-glass"></i>
                        </button>
                     </td>
                  </tr>
                  <tr id="search-error"></tr>
               </table>
            </form>
         </div>

         <h2>추천여행지</h2>
         <div id="list">
            <c:forEach items="${rlist}" var="rdto">
               <div class="recommendlist"
                  onclick="location.href='/planitshare/city.do?cseq=${rdto.cseq}';">
                  <img src="/planitshare/asset/image/${rdto.image}">
                  <div>${rdto.name}</div>
               </div>
            </c:forEach>
         </div>
         
         <h2>인기 여행 일정</h2>
         <div id="planlist">
            <c:forEach items="${plist}" var="pdto">
               <div class="plan"
                  onclick="location.href='/planitshare/plan/view.do?seq=${pdto.seq}';">
                  <img src="/planitshare/asset/image/${pdto.image}">
                  <div class="text">
                         <div>${pdto.title}</div>
                         <span><i class="fa-solid fa-user"></i>${pdto.author}</span>
                         <span><i class="fa-solid fa-eye"></i>${pdto.readCount}</span>
                         <span><i class="fa-solid fa-heart"></i>${pdto.likecnt}</span>
                         <c:if test="${pdto.reviewcnt > 0}">
                         <span>(${pdto.reviewcnt})</span>
                         </c:if>
                     </div>
               </div>
            </c:forEach>
         </div>
         <div id="top"><a href="#side"><img src="/planitshare/asset/image/up.png"></a></div>



      </section>
   </main>



   <script>
   
      //스크롤 이동
      $('.searchbtn').click(function(event) {
         
         let time = Math.abs($(document).scrollTop() - $($(this).data('item')).position().top) * 0.5;
       
         $('html,body').animate({
                   scrollTop: $($(this).data('item')).position().top - 140
              }, time);
      });
   
      
      
      //스크롤을 내렸을때 헤더에 배경흰색 적용
      window.onscroll = function() {
         
         console.log(window.scrollY); 
         
         if (window.scrollY > 700) {
            
            $('header').css('background-color', 'white');
            
         } else if (window.scrollY < 700) {
            
            $('header').css('background-color', 'transparent');
         } 
         
      };
   
      //화면 상단으로 이동
      $('#top img').click(function(event) {
            event.preventDefault() //기본동작을 없애기

            let time = Math.abs($(document).scrollTop()) * 0.3;
            $('html, body').animate({
                scrollTop: 0
            }, time);
            

        });
      
      //검색이 틀렸을 경우 메세지 띄우기
      $('.btn-secondary').click(function() {
                  
         $.ajax({
                url: '/planitshare/main.do',
                type: 'POST',
                data: $('#form').serialize(),
                dataType: 'json',
                success: function(result) {
               
                   if (result.result == "1") {
                      $('#search-error').html('');
                      $('#search-error').append('<td colspan="3" style="color:red">검색결과가 없습니다.</td>');
                          
                   
                   } 
            
                },

            error: function(a,b,c) {
               console.log(a,b,c)
            }
            

         });

         
      });
      
   
   </script>


</body>
</html>