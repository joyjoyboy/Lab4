/**
 * Excerpted from the book, "Pragmatic Unit Testing"
 * ISBN 0-9745140-1-2
 * Copyright 2003 The Pragmatic Programmers, LLC.	All Rights Reserved.
 * Visit www.PragmaticProgrammer.com
 */

import junit.framework.*;
import java.util.ArrayList;
import static org.easymock.EasyMock.*;

public class TestMp3Player extends TestCase {

	private Mp3Player mp3;
	private Mp3Player mockMp3Player_control;

	private ArrayList<String> list = new ArrayList<String>();

	public void setUp() {
		mockMp3Player_control = createMock(Mp3Player.class);
		mp3 = mockMp3Player_control;

		list = new ArrayList();
		list.add("Bill Chase -- Open Up Wide");
		list.add("Jethro Tull -- Locomotive Breath");
		list.add("The Boomtown Rats -- Monday");
		list.add("Carl Orff -- O Fortuna");
	}

	public void testPlay() {
		
		mp3.loadSongs(list);

		expectLastCall().times(1);
		expect(mp3.isPlaying()).andReturn(false);
		mp3.play();
		expectLastCall().times(1);
		expect(mp3.isPlaying()).andReturn(true);
		expect(mp3.currentPosition()).andReturn(3.0);
		mp3.pause();
		expectLastCall().times(1);
		expect(mp3.currentPosition()).andReturn(4.0);
		mp3.stop();
		expectLastCall().times(1);
		expect(mp3.currentPosition()).andReturn(5.0);

		replay(mockMp3Player_control);

		mp3.loadSongs(list);
		assertFalse(mp3.isPlaying());
		mp3.play();
		assertTrue(mp3.isPlaying());
		assertTrue(mp3.currentPosition() != 0.0);
		mp3.pause();
		assertTrue(mp3.currentPosition() != 0.0);
		mp3.stop();
		assertEquals(mp3.currentPosition(), 0.0, 0.1);

	}

	public void testPlayNoList() {

		expectLastCall().times(1);
		expect(mp3.isPlaying()).andReturn(false);
		mp3.play();
		expectLastCall().times(1);
		expect(mp3.isPlaying()).andReturn(false);
		expect(mp3.currentPosition()).andReturn(3.0);
		mp3.pause();
		expectLastCall().times(1);
		expect(mp3.isPlaying()).andReturn(false);
		expect(mp3.currentPosition()).andReturn(4.0);
		mp3.stop();
		expectLastCall().times(1);
		expect(mp3.isPlaying()).andReturn(false);
		expect(mp3.currentPosition()).andReturn(5.0);
		
		replay(mockMp3Player_control);

		// Don't set the list up
		assertFalse(mp3.isPlaying());
		mp3.play();
		assertFalse(mp3.isPlaying());
		assertEquals(mp3.currentPosition(), 0.0, 0.1);
		mp3.pause();
		assertEquals(mp3.currentPosition(), 0.0, 0.1);
		assertFalse(mp3.isPlaying());
		mp3.stop();
		assertEquals(mp3.currentPosition(), 0.0, 0.1);
		assertFalse(mp3.isPlaying());
	}

	public void testAdvance() {

		mp3.loadSongs(list);

		expectLastCall().times(1);
		expect(mp3.isPlaying()).andReturn(true);
		mp3.play();
		expectLastCall().times(1);
		expect(mp3.isPlaying()).andReturn(true);
		mp3.prev();
		expectLastCall().times(1);
		expect(mp3.isPlaying()).andReturn(true);
		expect(mp3.currentSong()).andReturn(list.get(0));
		mp3.next();
		expectLastCall().times(1);
		expect(mp3.currentSong()).andReturn(list.get(1));
		mp3.next();
		expectLastCall().times(1);
		expect(mp3.currentSong()).andReturn(list.get(2));
		mp3.prev();
		expectLastCall().times(1);
		expect(mp3.currentSong()).andReturn(list.get(1));
		mp3.next();
		expectLastCall().times(1);
		expect(mp3.currentSong()).andReturn(list.get(2));
		mp3.next();
		expectLastCall().times(1);
		expect(mp3.currentSong()).andReturn(list.get(3));
		mp3.next();
		expectLastCall().times(1);
		expect(mp3.isPlaying()).andReturn(true);
		expect(mp3.currentSong()).andReturn(list.get(3));

		replay(mockMp3Player_control);

		mp3.loadSongs(list);

		mp3.play();

		assertTrue(mp3.isPlaying());

		mp3.prev();
		assertEquals(mp3.currentSong(), list.get(0));
		assertTrue(mp3.isPlaying());

		mp3.next();
		assertEquals(mp3.currentSong(), list.get(1));
		mp3.next();
		assertEquals(mp3.currentSong(), list.get(2));
		mp3.prev();

		assertEquals(mp3.currentSong(), list.get(1));
		mp3.next();
		assertEquals(mp3.currentSong(), list.get(2));
		mp3.next();
		assertEquals(mp3.currentSong(), list.get(3));
		mp3.next();
		assertEquals(mp3.currentSong(), list.get(3));
		assertTrue(mp3.isPlaying());
	}

}
