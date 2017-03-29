package com.aheizi.socket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/websocket")
public class WebSocketTest {

	private static Queue<Session> queue = new ConcurrentLinkedQueue<Session>();

	@OnMessage
	public void onMessage(String message, Session session) throws IOException,
			InterruptedException {

		// Print the client message for testing purposes
		System.out.println("Received: " + message);

		// Send the first message to the client
//		session.getBasicRemote().sendText(message);
		sendAll(message);
		// // Send 3 messages to the client every 5 seconds
		// int sentMessages = 0;
		// while (sentMessages < 3) {
		// Thread.sleep(5000);
		// session.getBasicRemote().sendText(
		// "This is an intermediate server message. Count: "
		// + sentMessages);
		// sentMessages++;
		// }

		// Send a final message to the client
		// session.getBasicRemote().sendText("This is the last server message");
	}

	@OnOpen
	public void onOpen(Session session) {
		queue.add(session);
		System.out.println("Client connected");
	}
	//关闭操作
	@OnClose
	public void closedConnection(Session session) {
		queue.remove(session);
		System.out.println("session closed: " + session.getId());
	}
//发送所有
	private static void sendAll(String msg) {
		try {
			/* Send the new rate to all open WebSocket sessions */
			ArrayList<Session> closedSessions = new ArrayList<>();
			for (Session session : queue) {
				if (!session.isOpen()) {
					System.err.println("Closed session: " + session.getId());
					closedSessions.add(session);
				} else {
					session.getBasicRemote().sendText(msg);
				}
			}
			queue.removeAll(closedSessions);
			System.out.println("Sending " + msg + " to " + queue.size()
					+ " clients");
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

}