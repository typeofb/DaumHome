package com.components.chat;

import java.util.HashMap;

public class ChatServer implements java.lang.Runnable {

	private Thread thread;
	
	public ChatServer() {
		System.out.println("Start ChatServer...");
		thread = new Thread(this);
	}
	
	public void start() {
		thread.start();
	}
	
	@Override
	public void run() {
		while (true) {
			execute();
			Thread.currentThread();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				System.out.println(e);
			}
		}
	}
	
	public void execute() {
		ChatMessageQueue chatMessageQueue = new ChatMessageQueue();
		String message = chatMessageQueue.getMessage();
		if (message != null) {
			boolean escape = false;
			while (escape == false) {
				HashMap<String, Object> client = chatMessageQueue.getClient();
				if (client != null) {
					client.put("result", message);
					try {
						((Thread) client.get("thread")).interrupt();
					} catch (Exception e) {
						System.out.println(e);
					}
				} else {
					escape = true;
				}
			}
		}
	}
}
