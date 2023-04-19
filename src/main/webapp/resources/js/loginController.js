
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

    // $.ajax({
    //     type : "POST",
    //     url : "login/loginStart",
    //     data : JSON.stringify(data),
    //     contentType : "application/json",
    //     dataType : "json",
    //     success : function(result) {
    //         if(result.Success == "true") {
    //             location.href = "home";
    //         }else{
    //             location.href = "fail";
    //         }
    //     },
    //     error : function(e) {
    //         console.log(e);
    //     }
    // })
}


document.addEventListener("DOMContentLoaded", function(){

    $("#pwBtn").click(function() {
        $("#myModal").modal();
    })

    $("#login").click(function() {
        login();
        // $("testModal").show();
    })
});