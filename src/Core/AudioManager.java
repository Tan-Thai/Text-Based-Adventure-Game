package Core;
import Global.Utility;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javax.sound.sampled.*;

public class AudioManager {
    private static Clip clip;
    private static FloatControl volumeControl;

    // Can't say I fully understand the whole audio-stream and float-control.
    // But I'll try to explain as much as possible. - TT
    public static void initialiseAudio() {
        try {
            if (clip == null) {
                clip = AudioSystem.getClip();
            }
        } catch (LineUnavailableException e) {
            System.err.println("Failed to initialize AudioManager.");
            // Keeping it simple for now with stack trace alone.
            // Should honestly be more detailed with the error message.
            e.printStackTrace();
        }
    }


    // Currently only has 1 path we use due to quite literally only using 1 track.
    // But foundation is sorted for expanding.
    private static void playBgm (String filePath) {
        try {
            stopBgm();

            File file = new File(filePath);
            AudioInputStream audioStream=AudioSystem.getAudioInputStream(file);

            clip.open(audioStream);

            // notable during startup to set the volume to 40%.
            if (volumeControl == null) {
                volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                setVolumeToPercentage(40);
            }

            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            // just starting with stackTrace for now. Will need to be more specific for each case later.
            e.printStackTrace();
            System.err.println("Something went wrong when trying to play audio.");
        }
    }

    private static void stopBgm () {
        if (clip != null && clip.isRunning()) {
            clip.stop();
            clip.flush();
            clip.close();
        }
    }

    // Took this from a random StackOverflow post, I don't claim this.
    private static void setVolumeToPercentage(int percentage) {
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

    //region public audio utility
    public static void audioCheck(Scanner sc) {
        /* Checker for user.dir
            System.out.println("Working Directory = " + System.getProperty("user.dir"));
         */
        System.out.print("Do you wish to have bgm playing? (Y/N): ");
        if (Utility.checkYesOrNo(sc)) {
            System.out.println("----Bgm turned on----");
            AudioManager.playBgm("Core/Resources/MusicForGame.wav");
        } else {
            System.out.println("----Bgm turned off----");
            stopBgm();
        }

        Utility.promptEnterKey(sc);
    }

    public static void audioSettingsMenu (Scanner sc) {

        while (true) {
            Utility.clearConsole();
            System.out.println("Audio Settings: \n");
            System.out.println(clip.isRunning() ? "1. Stop BGM" : "1. Play BGM");
            System.out.println("2. Adjust Volume");

            System.out.print(Config.MENU_CHOICE_STRING);

            switch (Utility.checkIfNumber(sc)) {
                case 1:
                    if (clip.isRunning())
                        stopBgm();
                    else
                        playBgm("Core/Resources/MusicForGame.wav");
                    break;
                case 2:
                    System.out.print("Please input the wanted volume percentage (0 - 100): ");
                    int choice = Utility.checkIfNumber(sc);
                    setVolumeToPercentage(choice);
                    System.out.println("Volume set to: " + choice);
                    Utility.promptEnterKey(sc);
                    break;
                case 0:
                    Utility.clearConsole();
                    System.out.println("Exiting Audio Settings");
                    return;
                default:
                    System.err.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }
    //endregion
}
