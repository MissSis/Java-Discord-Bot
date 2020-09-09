package listener;

import commands.ExitCommand;
import music.commands.NextCommand;
import music.commands.PauseCommand;
import music.commands.PlayCommand;
import music.commands.QueueCommand;
import music.commands.StopCommand;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class CommandListener extends ListenerAdapter{

	@Override
	public void onMessageReceived(MessageReceivedEvent event) {
		
		if(event.isFromType(ChannelType.TEXT ) && !event.getAuthor().isBot()) {
			
			TextChannel channel = event.getTextChannel();
			String msg = event.getMessage().getContentDisplay();
			Member member = event.getMember();
			Message message = event.getMessage();
			VoiceChannel vc = member.getVoiceState().getChannel();
			
			String[] args = msg.split(" ");
			
			switch(args[0]) {
			case "^^play":
				new PlayCommand(vc).performCommand(member, channel, message);
				return;
			case "^^stop":
				new StopCommand(vc).performCommand(member, channel, message);
				return;
			case "^^queue":
				new QueueCommand(vc).performCommand(member, channel, message);
				return;
			case "^^exit":
				new ExitCommand().performCommand(member, channel, message);
				return;
			case "^^skip":
			case "^^next":
				new NextCommand(vc).performCommand(member, channel, message);;
				return;
			case "^^pause":
				new PauseCommand(vc).performCommand(member, channel, message);
				return;
			}
			
			channel.sendMessage("Du hast geschrieben: " + msg + "\n" + event.getMember().getAsMention()).queue();
		}
	}
}
