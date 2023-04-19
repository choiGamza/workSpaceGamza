
let login = function() {

    let frm = document.querySelector("#loginForm")

    let id = $("#userId").val();
    let pw = $("#userPw").val();

    let data = {userId : id, userPw : pw};

    axios.post("login/loginStart", data)
    .then(res => {
        if(res.data.Success == "true") {
            location.href = "home";
        }else{
            location.href = "fail";
        }
    })
    .catch(err => {
        console.log(err)
    })
}

let init = function() {
    // 여기에 공지사항 & 게시물 바로 표시 되게 스크립트 작성
}


document.addEventListener("DOMContentLoaded", function(){

    $("#pwBtn").click(function() {
        $("#myModal").modal();
    })

});