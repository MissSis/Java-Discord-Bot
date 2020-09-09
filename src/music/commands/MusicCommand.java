package music.commands;

import java.util.List;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;

import main.Command;
import main.DiscordBot;
import main.Logger;
import music.MusicController;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.VoiceChannel;

public abstract class MusicCommand implements Command{

	protected MusicController controller;
	protected AudioPlayerManager apm;
	protected Logger logger;
	
	public MusicCommand(VoiceChannel channel) {
		controller = DiscordBot.INSTANCE.playerManager.getController(channel.getGuild().getIdLong());
		apm = DiscordBot.INSTANCE.audioPlayerManager;
		
		logger = new Logger();
		List<TextChannel> channels = DiscordBot.INSTANCE.bot.getTextChannelsByName("dev", true);
		
		if(channels.get(0) != null) {
			logger.setChannel(channels.get(0));
		}else {
			System.out.println("no music channel has been found, no logs will be sent");
		}
	}
	
}
