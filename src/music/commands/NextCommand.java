package music.commands;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.VoiceChannel;

public class NextCommand extends MusicCommand{

	public NextCommand(VoiceChannel channel) {
		super(channel);
	}

	@Override
	public void performCommand(Member member, TextChannel channel, Message message) {
		controller.playNext();
	}

}
