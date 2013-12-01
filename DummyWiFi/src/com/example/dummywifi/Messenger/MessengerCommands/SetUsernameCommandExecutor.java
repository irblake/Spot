package com.example.dummywifi.Messenger.MessengerCommands;

import com.example.dummywifi.Messenger.ChatSession;
import com.example.dummywifi.models.Client;

public class SetUsernameCommandExecutor implements CommandExecutor {

	public static final String COMMAND_MESSAGE = "!setusername";
	
	@Override
	public boolean executeCommand(ChatSession session, Client caller, String command, String[] args) {
		
		if (args.length == 2) { // !setusername <username> 
			caller.setUserName(args[1]);
		}
		else if (args.length == 3) { // !setusername <username> <deviceid>
			caller.setUserName(args[1]);
			caller.setUserName(args[2]);
		}
		else 
			return false;
		
		return true;
	}

}
