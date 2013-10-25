package com.example.dummywifi.models;

import java.io.IOException;

// The real connection object & the mock object both should implement this interface
public interface Connection {
	public void send(byte[] Message) throws IOException;
	public byte[] receive() throws IOException;	
}
