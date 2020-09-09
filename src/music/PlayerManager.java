package music;

import java.util.concurrent.ConcurrentHashMap;

import main.DiscordBot;

public class PlayerManager {

	public ConcurrentHashMap<Long, MusicController> controller;
	
	public PlayerManager() {
		controller = new ConcurrentHashMap<Long, MusicController>();
	}
	
	public MusicController getController(long guildid) {
		if(controller.containsKey(guildid)) {
			return controller.get(guildid);
		}else {
			MusicController mc = new MusicController(DiscordBot.INSTANCE.bot.getGuildById(guildid));
			controller.put(guildid, mc);
			return mc;
		}
	}
}
