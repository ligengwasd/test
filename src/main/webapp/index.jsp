<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title></title>
    <script type="application/javascript" src="http://127.0.0.1:8081/test/javascript/jquery.min.js"></script>
</head>
<body>
这是主页.<br/>
这是主页.<br/>
这是主页.<br/>
<form action="main" method="post">
    <input type="text" name="id" value="100"/>
    <input type="text" name="name" value="ligeng"/><br/>
    <input type="text" name="unitPrice" value="100000"/>
    <input type="text" name="birth" value="2015-10-10"/>

    <input type="submit" value="submit"/>
</form>

<br/>
<input value="asdsfregnverdgmvtrhmngtr" type="button" onclick="test()">
<script type="application/javascript">
    $(document).ready(function(){})

    function test(){
//        var ss = '{"list":[{"id":3,"name":"煤","unitPrice":3},{"id":2,"name":"电","unitPrice":0.5}],"list2":[{"id":4,"name":"煤","unitPrice":3}],"total":10}';
//        var ss = '[{"id":3,"name":"煤","unitPrice":3},{"id":2,"name":"电","unitPrice":0.5}]';
//        var ss = '[{"id":3,"name":"煤","unitPrice":3},{"id":2,"name":"电","unitPrice":0.5}]';
//        var ss = "[{'id':3,'name':'煤','unitPrice':3},{'id':2,'name':'电','unitPrice':0.5}]";
        var ss = '{"id":"12"}';
        $.ajax({
            url:"main?id=100",
            type:"POST",
            contentType:"application/json;charset=utf-8",
            data:ss,
            //"name":"456"
            success:function(){
                alert("success");
            },
            error:function(){
                alert("error");
            }
        })
    }
</script>
</body>
</html>
