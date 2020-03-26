/**
 * Created by codedrinker on 2019/6/1.
 */

/**
 * 提交回复
 */
function post() {

    var questionId = $("#question_id").val();
    console.log(questionId);
    var content = $("#comment_content").val();
    console.log(content);
    comment2target(questionId, 1, content);

}

function comment2target(targetId, type, content) {
    if (!content) {
        alert("不能回复空内容~~~");
        return;
    }

    $.ajax({
        type: "POST",
        url: "/comment",
        contentType: 'application/json',
        data: JSON.stringify({
            "parentId": targetId,
            "content": content,
            "type": type
        }),
        success: function (response) {
            if(response.code==200){
                $("#comment_section").hide();
                window.location.reload();
            }

            alert(response.message);
        },
        dataType: "json"
    });
}