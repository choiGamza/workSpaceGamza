
let login = function() {

}

let init = function() {

    axios.post(contextPath +"/notice/noticeLoad")
        .then(res => {
            console.log(res.data);
        })
        .catch(err => {
            console.log(err);
        })

}


document.addEventListener("DOMContentLoaded", function(){

    init();

    $(document).ready(function(){
        $("#myBtn").click(function(){
            $("#myModal").modal();
        });
    });
    $(document).ready(function() {
        $("#Btn").click(function() {
            $("#myModal2").modal();
        });
    });

});