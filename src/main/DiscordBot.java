package main;

import java.util.Scanner;

import javax.security.auth.login.LoginException;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers;
import com.sedmelluq.discord.lavaplayer.source.local.LocalAudioSourceManager;

import listener.CommandListener;
import music.PlayerManager;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;

public class DiscordBot {

	public static DiscordBot INSTANCE;
	
	public JDA bot;
	public AudioPlayerManager audioPlayerManager;
	public PlayerManager playerManager;
	public Thread exitListener;
	
	public static void main(String[] args) {
		new DiscordBot();
	}

	public DiscordBot() {
		INSTANCE = this;
		audioPlayerManager = new DefaultAudioPlayerManager();
		audioPlayerManager.registerSourceManager(new LocalAudioSourceManager());
		
		playerManager = new PlayerManager();
		AudioSourceManagers.registerRemoteSources(audioPlayerManager);
		
		JDABuilder builder = JDABuilder.createDefault("NjkxOTQ4MTY4MDg2MTU5NDQw.XnnY8w.WPXjfc7MyWny9xqBHCElkjab8l4");

		builder.setActivity(Activity.playing("wüxen"));
		builder.addEventListeners(new CommandListener());

		try {
			bot = builder.build();
		} catch (LoginException e) {
			e.printStackTrace();
		}
		
		shutdownListener();
	}
	
	private void shutdownListener() {
		exitListener = new Thread(() ->{
			Scanner sc = new Scanner(System.in);
			String input = sc.nextLine();
			
			if(input.equalsIgnoreCase("exit")) {
				
				bot.shutdown();
				sc.close();
				System.out.println("Shutting down bot");
			}
			
			
		});
		
		exitListener.start();
	}
}
