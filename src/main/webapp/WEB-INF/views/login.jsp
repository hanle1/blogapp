<!DOCTYPE html>
<html>
<head>
<title>Testing websockets</title>
</head>
<body>
	<div>
		<input type="submit" value="Start" onclick="start()" />
	</div>
	<div id="messages"></div>
	<script type="text/javascript">
		var webSocket = new WebSocket(
				'ws://localhost:8080/blogapp/websocket');

		webSocket.onerror = function(event) {
			onError(event)
		};
		//这个是好我知道了 这
		webSocket.onopen = function(event) {
			onOpen(event)
		};
		//这个是接受服务器发送的消息操作
		webSocket.onmessage = function(event) {
			onMessage(event)
		};

		function onMessage(event) {
			document.getElementById('messages').innerHTML += '<br />'//这个看得懂把
					+ event.data;//这个是接受的数据
		}

		function onOpen(event) {
			document.getElementById('messages').innerHTML = 'Connection established';
		}

		function onError(event) {
			alert(event.data);
		}

		function start() {
			webSocket.send('hello');
			return false;
		}
	</script>
</body>
</html>