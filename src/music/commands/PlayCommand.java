package music.commands;

import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.VoiceChannel;

public class PlayCommand extends MusicCommand{

	public PlayCommand(VoiceChannel channel) {
		super(channel);
	}

	@Override
	public void performCommand(Member member, TextChannel channel, Message message) {
		
		VoiceChannel vc = member.getVoiceState().getChannel();
		
		if(vc != null) {
			String src = message.getContentDisplay().substring(6).strip();
			
			vc.getGuild().getAudioManager().openAudioConnection(vc);
			
			apm.loadItem(src, new AudioLoadResultHandler() {
				
				@Override
				public void trackLoaded(AudioTrack track) {
					logger.send(":arrow_forward:");
					controller.play(track);
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
		}else {
			System.out.println("You are not in a voice channel");
		}
	}
}
