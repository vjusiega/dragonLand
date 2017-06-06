package game;

import java.applet.Applet;
import java.applet.AudioClip;

public class Sound {

	public static final AudioClip AMBIANCE = Applet.newAudioClip(Sound.class.getResource("intro1.wav"));
	public static final AudioClip EAT1 = Applet.newAudioClip(Sound.class.getResource("DragonEat.wav"));
	public static final AudioClip EAT2 = Applet.newAudioClip(Sound.class.getResource("DragonEat2.wav"));
	public static final AudioClip EAT3 = Applet.newAudioClip(Sound.class.getResource("DragonEat3.wav"));
	public static final AudioClip EAT4 = Applet.newAudioClip(Sound.class.getResource("eat1.wav"));
	public static final AudioClip EAT5 = Applet.newAudioClip(Sound.class.getResource("eat2.wav"));
	public static final AudioClip CLICK = Applet.newAudioClip(Sound.class.getResource("click.wav"));
	public static final AudioClip BOUGHT = Applet.newAudioClip(Sound.class.getResource("Purchase.wav"));

}
