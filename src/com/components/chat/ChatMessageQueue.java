package com.components.chat;

import java.util.HashMap;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ChatMessageQueue {

	private static Queue<String> messageQueue = new ConcurrentLinkedQueue<>();
	private static Queue<HashMap<String, Object>> clientQueue = new ConcurrentLinkedQueue<>();
	
	public String getMessage() {
		return messageQueue.poll();
	}
	
	public boolean setMessage(String message) {
		return messageQueue.add(message);
	}
	
	public HashMap<String, Object> getClient() {
		return clientQueue.poll();
	}
	
	public boolean setClient(HashMap<String, Object> client) {
		return clientQueue.add(client);
	}
}
