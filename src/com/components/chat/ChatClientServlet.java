package com.components.chat;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ChatClientServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	// chatClient.do
	protected void service(HttpServletRequest request, HttpServletResponse response) {
		ChatMessageQueue chatMessageQueue = new ChatMessageQueue();
		
		HashMap<String, Object> client = new HashMap<>();
		client.put("thread", Thread.currentThread());
		client.put("result", "");
		chatMessageQueue.setClient(client);
		
		String message = request.getParameter("message");
		if (message != null && !message.equals("")) {
			chatMessageQueue.setMessage(message);
		} else {
			Thread.currentThread();
			try {
				Thread.sleep(1000 * 60 * 10);
			} catch (InterruptedException e) {
				System.out.println(e);
			}
		}
		
		response.setContentType("text/html; charset=UTF-8");
		try {
			response.getWriter().print(client.get("result").toString().replaceAll("\n", ""));
		} catch (IOException e) {
			System.out.println(e);
		}
	}
}
