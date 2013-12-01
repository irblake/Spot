package com.example.dummywifi.MessengerCommands;

import com.example.dummywifi.models.Client;

// so we can add commands to the command executor function in the worker threads

public interface CommandExecutor {
	public boolean executeCommand(Client caller, String command, String[] args);
}
