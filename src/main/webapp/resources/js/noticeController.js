
const notice = (function() {
    return{
        noticeList : {},

        getNoticeList : function() {
            axios.post(contextPath +"/notice/noticeLoad")
            .then(res => {

                this.noticeList = res.data.data;

                for(let i = 0; i < this.noticeList.length; i++) {
                    let html = '';

                    html += '<tr>' +
                        '<td>' + this.noticeList[i].bid + '</td>' +
                        '<td><a href = "notcieView?bid="' + this.noticeList[i].bid + '>' + this.noticeList[i].title + '</a></td>' +
                        '<td>' + this.noticeList[i].name + '</td>' +
                        '<td>' + this.noticeList[i].rdate + '</td>' +
                        '<td>' + this.noticeList[i].hit + '</td>' +
                        '<tr>';

                    $(".table #noticeList").append(html);
                }

            })
            .catch(err => {
                console.log(err);
            })
        }
    }
})();

let init = function() {
    notice.getNoticeList();
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