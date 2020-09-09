package main;

import net.dv8tion.jda.api.entities.TextChannel;

public class Logger {
	
	private TextChannel channel = null;
	
	public void sendInfo(String message) {
		if(channel != null) {
			channel.sendMessage(":information_source: " + message).queue();
		}else {
			System.out.println("The channel of the logger is null, so it can't log with this method!");
		}
	}
	
	public void sendInfo(String message, TextChannel channel) {
		channel.sendMessage(":information_source: " + message).queue();
	}
	
	public void send(String message) {
		if(channel != null) {
			channel.sendMessage(message).queue();
		}else {
			System.out.println("The channel of the logger is null, so it can't log with this method!");
		}
	}
	
	public void setChannel(TextChannel channel) {
		this.channel = channel;
	}


}
