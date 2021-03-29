package Music.musicPlayer;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import Music.music.MusicJFugue;

class MusicPlayerTest {
	
	

	@Test
	void testSetMusic() {
		MusicJFugue music = new MusicJFugue("T120 I37 60 62 65 69 :CON(7,80) 60 62 65 69");
		MusicPlayer mf = new MusicPlayer();
		mf.setMusic(music);
		assertEquals(music.toJFuguePlayableString(), mf.getMusic());
	}

}
