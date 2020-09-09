package commands;

import main.Command;
import main.DiscordBot;
import main.Logger;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

public class ExitCommand implements Command{

	@Override
	public void performCommand(Member member, TextChannel channel, Message message) {
		if(member.getId().equals("186869409946664961")) {
			DiscordBot.INSTANCE.exitListener.interrupt();
			DiscordBot.INSTANCE.bot.shutdown();
			System.exit(0);
		}else {
			Logger logger = new Logger();
			logger.sendInfo("stfu lil cunt", channel);
		}
	}
}
