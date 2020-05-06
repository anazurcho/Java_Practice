<%--
  Created by IntelliJ IDEA.
  User: anzu
  Date: 22.04.20
  Time: 15:08
  To change this template use File | Settings | File Templates.
--%>
<%--
Created by IntelliJ IDEA.
User: admin
Date: 4/14/2020
Time: 9:08 AM
To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<style>
  input{
    background: #f2f2f2;
    width: 100%;
    border: 0;
    margin: 0 0 15px;
    padding: 15px;
    box-sizing: border-box;
    font-size: 14px;
  }
  .box{
    position: relative;
    background: #FFFFFF;
    max-width: 360px;
    margin: 10px auto 100px;
    padding: 45px;
    text-align: center;
    box-shadow: 0 0 20px 0;
  }
  .btn{
    background: #43A047;
    width: 100%;
    border:0;
    padding: 15px;
    color: #FFFFFF;
  }
</style>
<head>
  <title>ChatBot</title>
</head>
<body>

<br>

<form>
  <div class="container">
    <div class="box">
      <h4 style="text-align:center">ChatBot</h4>
      <div style="width: 100%">
        <textarea id="messageTextArea" rows="10" cols="40"> </textarea>
        <input id="textMessage" type="text">
        <input onclick="sendMessage();" value="Send Message" type="button">
      </div>
    </div>
  </div>
</form>


<script>

  var webSocket = new WebSocket("ws://localhost:8080/test_3_war_exploded/chatbot");
  var messageTextArea = document.getElementById("messageTextArea");

  webSocket.onopen = function (message) {
    processOpen(message);
  };
  webSocket.onmessage = function (message) {
    processMessage(message);
  };
  webSocket.onclose = function (message) {
    processClose(message);
  };
  webSocket.onerror = function (message) {
    processError(message);
  };

  function processOpen(message) {
    messageTextArea.value += "The connection is established .. "+"\n";
  }


  // მესიჯის მიღება
  function processMessage(message) {
    messageTextArea.value += "Server : " + message.data +"\n";
  }

  // მესიჯის გაგზავნა
  function sendMessage() {
    webSocket.send(textMessage.value);
    messageTextArea.value += "Client :  " + textMessage.value+"\n";
  }


  function processClose(message) {
    webSocket.send("Client disconnected ... ")
    messageTextArea.value += "Server Disconnect ... ";
  }

  function processError(message) {
    messageTextArea.value += "error ... ";
  }


</script>


</body>
</html>
