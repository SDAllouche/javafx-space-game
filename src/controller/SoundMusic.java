package controller;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class SoundMusic {


    private Clip clip;
    private boolean isOn;
    
    public SoundMusic(String path) {

        try {
            AudioInputStream InputStream = AudioSystem.getAudioInputStream(new File(path));
            clip = AudioSystem.getClip();
            clip.open(InputStream) ;
            isOn = true;


        } catch (UnsupportedAudioFileException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        }
    }
    public Clip getClip(){

        return clip;
    }
    public void Run(boolean b){
        if (b){
            getClip().start();
            //System.out.println(1);
        }
        else {
            getClip().stop();
            //System.out.println(0);
        }
    }
    public boolean active(){
        return isOn;
    }
    public void setActive(boolean b){
        isOn = b;
    }
}