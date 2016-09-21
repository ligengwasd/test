<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title></title>
    <script type="application/javascript" src="http://127.0.0.1:8088/test/javascript/jquery.min.js"></script>
</head>
<body>
这是stock主页.<br/>
这是stock这是主页.<br/>
这是stock这是主页.<br/>

<script type="application/javascript">
    $(document).ready(function(){
        for(var i=0; i < 10000; i++){
            test();
        }
    })

    function test(){

        $.ajax({
            url:"/test1/bonus/getbonus",
            type:"GET"
            //"name":"456"
//            success:function(){
//                alert("success");
//            },
//            error:function(){
//                alert("error");
//            }
        })
    }
</script>
</body>
</html>
