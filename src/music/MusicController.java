package music;

import java.util.ArrayList;
import java.util.List;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.event.AudioEventAdapter;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackEndReason;

import main.DiscordBot;
import net.dv8tion.jda.api.entities.Guild;

public class MusicController {

	private Guild guild;
	private AudioPlayer player;
	private List<AudioTrack> playlist;
	private int index;
	
	public MusicController(Guild guild) {
		this.guild = guild;
		player = DiscordBot.INSTANCE.audioPlayerManager.createPlayer();
		
		this.guild.getAudioManager().setSendingHandler(new AudioPlayerSendHandler(player));
		player.setVolume(20);
		player.addListener(new AudioEventAdapter() {
			
			@Override
			public void onTrackEnd(AudioPlayer player, AudioTrack track, AudioTrackEndReason endReason) {
				if(endReason.equals(AudioTrackEndReason.FINISHED) && index < playlist.size()-1) {
					player.playTrack(playlist.get(++index));
				}
			}
			
		});
		
		playlist = new ArrayList<>();
		index = -1;
	}
	
	public void play(AudioTrack track) {
		player.playTrack(track);
	}
	
	public void stop() {
		player.stopTrack();
	}
	
	public void pause() {
		player.setPaused(!player.isPaused());
	}
	
	public void queue(AudioTrack track) {
		playlist.add(track);
	}
	
	public void playNext() {
		if(index < playlist.size()-1) {
			player.playTrack(playlist.get(++index));
		}else {
			player.stopTrack();
			index = -1;
		}
	}
	
	
	
	
	public AudioPlayer getPlayer() {
		return player;
	}
	
	public Guild getGuild() {
		return guild;
	}
	
	public List<AudioTrack> getPlaylist() {
		return playlist;
	}
}
