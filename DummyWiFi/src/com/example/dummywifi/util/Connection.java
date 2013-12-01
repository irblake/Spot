package com.example.dummywifi.util;

import java.io.IOException;
import java.net.Socket;

/*
 * Abstraction layer over socket
 * Useful for sending & receiving data with less code & no need to understand sockets
*/
public class Connection {
	private Socket connectionSocket;
	
	public Connection(Socket source) {
		this.connectionSocket = source;
	}
	
	public boolean isOpen() {
		return (!connectionSocket.isClosed()) && connectionSocket.isConnected() && (!connectionSocket.isInputShutdown()) && (!connectionSocket.isOutputShutdown());
	}
	
	/*
	 * Returns true if the data was successfully sent to the destination
	 */
	public boolean sendData(byte[] data) {
		if (!this.isOpen()) {
			return false;
		}
		
		try {
			connectionSocket.getOutputStream().write(data);
		} catch (IOException e) {
			return false;
		}
		return true;
	}
	
	public boolean receiveData(byte[] buffer) {
		if (!this.isOpen()) {
			return false;
		}
		
		int result;
		try {
			result = connectionSocket.getInputStream().read(buffer, 0, 1023);
		} catch (IOException e) {
			return false;
		}
		
		if (result == -1) {
			return false; // no data was read
		}
		
		return true; // data was read and put into buffer		
	}

	// wrapper for sending text
	public boolean sendText(String text) {
		return sendData(text.getBytes());
	}
	
	public void close() {
		try {
			connectionSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
