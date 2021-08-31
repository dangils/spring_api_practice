// $(function(){
//     searchStart(0);
//
// });
//
// function searchStart(index){
//     console.log("index : " + index);
// }

// (function ($){
//     $(document).ready(function(){
//         console.log(1)
//         searchStart(0)
//     });
//
//     function searchStart(index){
//         console.log("index : " + index);
//     }
//
// })(jQuery);

(function ($) {

    let indexBtn=[]; // 인덱스 버튼
    let pagination = {
        total_pages : 0,
        total_elements : 0,
        current_page : 0,
        current_elements : 0
    };



    // 페이지 정보

    let showPage = new Vue({
        // (3) 뷰 객체에 showpage 라는 id 가 연결되어 아래쪽에 데이터 넣은후 화면에 렌더링
        el : '#showPage',
        data : {
            totalElements : {},
            currentPage : {}
        }
    });


    // 데이터 리스트
    let itemList = new Vue({
        // (3) 뷰 객체에 showpage 라는 id 가 연결되어 아래쪽에 데이터 넣은후 화면에 렌더링
        el : '#showPage',
        data : {
            itemList : {}
        }
    })



    $(document).ready(function(){
        searchStart(0)
    });

    function searchStart(index){
        console.log("index : " + index);


        $.get("/api/user?page="+index, function(response){  //index값을 요청후 그에 대한 결과 값이 담겨서 수행
    //$.get (get방식으로 ajax 수행 -> userApiController의 GetMapping 수행) (1) ajax로 데이터 전달
            console.dir(response);

            indexBtn = [];
            pagination = response.pagination; // response에서 가져온 pagination


            // 전체 페이지      (2) ajax를 통해 rest를 가져와서 뷰 객체에 전달 / // 뷰가 랜더링 될 페이지
            showPage.totalElements = pagination.current_elements;
            showPage.currentPage = pagination.current_page + 1; // 첫페이지 0 이므로 +1

            // 검색 데이터
            itemList.itemList = response.data;  //오브젝트의 데이터 값을 itemList에 넣어줌

        })
    }

})(jQuery);


/*
user.js가 수행
ready함수로 searchStart 0을 날림,
ajax를 통해 내부적으로 rest를 호출 (

 */



