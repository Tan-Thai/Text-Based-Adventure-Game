package Core;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;

public class AudioManager {
    private static Clip clip;
    private static FloatControl volumeControl;

    // Can't say I fully understand the whole audio-stream and float-control.
    // But I'll try to explain as much as possible.
    public static void initialiseAudio() {
        try {
            if (clip == null) {
                clip = AudioSystem.getClip();
                System.out.println("AudioManager initialized successfully.");
            }
        } catch (LineUnavailableException e) {
            System.err.println("Failed to initialize AudioManager.");
            e.printStackTrace();
        }
    }


    public static void playBgm (String filePath) {
        try {
            stopBgm();
            File file = new File(filePath);
            AudioInputStream audioStream=AudioSystem.getAudioInputStream(file);

            clip.open(audioStream);

            // notable during startup to set the volume to 50%.
            if (volumeControl == null) {
                volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                setVolumeToPercentage(50);
            }

            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            // just starting with stackTrace for now. Will need to be more specific for each case later.
            e.printStackTrace();
            System.err.println("Something went wrong when trying to play audio.");
        }
    }

    // We don't close the clip until the end similarly to Scanner. Just .flush to remove the current audio.
    public static void stopBgm () {
        if (clip != null && clip.isRunning()) {
            clip.stop();
            clip.flush();
        }
    }

    // Took this from a random StackOverflow post, I don't claim this.
    public static void setVolumeToPercentage(int percentage) {
        if (volumeControl != null) {
            float min = volumeControl.getMinimum();
            float max = volumeControl.getMaximum();

            // Convert percentage to decibels
            float range = max - min;
            float targetVolume = min + (range * (percentage / 100.0f));

            volumeControl.setValue(targetVolume);
        } else {
            System.err.println("Volume control is not initialized. Make sure to play a track first.");
        }
    }
}
