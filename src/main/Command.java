package main;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

public interface Command {

	/**
	 * This method is called when the command is executed.
	 * @param member The member who wrote the command
	 * @param channel The textchannel where the command was written
	 * @param message The message that was written
	 */
	public void performCommand(Member member, TextChannel channel, Message message);
}
