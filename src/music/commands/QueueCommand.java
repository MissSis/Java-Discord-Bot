package music.commands;

import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.VoiceChannel;

public class QueueCommand extends MusicCommand{

	public QueueCommand(VoiceChannel channel) {
		super(channel);
	}

	@Override
	public void performCommand(Member member, TextChannel channel, Message message) {
		String src = message.getContentDisplay().substring(7).strip();
		
		apm.loadItem(src, new AudioLoadResultHandler() {
			
			@Override
			public void trackLoaded(AudioTrack track) {
				logger.sendInfo("Queuing track: " + track.toString());
				controller.queue(track);
			}
			
			@Override
			public void playlistLoaded(AudioPlaylist playlist) {
			}
			
			@Override
			public void noMatches() {
				System.out.println("there were no matches");
			}
			
			@Override
			public void loadFailed(FriendlyException exception) {
			}
		});
	}

}
