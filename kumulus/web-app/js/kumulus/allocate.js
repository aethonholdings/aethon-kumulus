/* 
 * Task allocation tools
 * 
 */

$(document).ready(function() {
    $("#getNext").click(function(){
        request(url("task", "getNext", ""), "", function(response) {
            if(response.success) {
                var newRow = "<tr><td><a href=" + url("backOffice", "performTask", response.data.taskId) + ">";
                newRow = newRow + response.data.documentLiteral + "</a></td>";
                newRow = newRow + "<td>" + response.data.taskType + "</td>"
                newRow = newRow + "<td>" + response.data.created + "</td></tr>"
                $("#userTaskList tbody").append(newRow);
                $("#userTaskCount").text(Number($("#userTaskCount").text())+1);
                $("#backOfficeTaskCount").text(Number($("#backOfficeTaskCount").text())-1);
                $("#"+response.data.taskType).text(Number($("#"+response.data.taskType).text())-1);
            }
        });
    });
})
