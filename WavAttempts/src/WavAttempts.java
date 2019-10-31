import javax.sound.sampled.*;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class WavAttempts {

    private Clip clip;
    FloatControl volume;

    public WavAttempts(){
        try {
//            AudioInputStream inputStream =
//                  AudioSystem.getAudioInputStream(new File("cymbal.wav"));
//            AudioFormat audioFormat = inputStream.getFormat();
//            int bytesPerFrame = inputStream.getFormat().getFrameSize();
//            int numBytes = 1024 * bytesPerFrame;
//
//            byte[] audioBytes = new byte[numBytes];
//
//            int numBytesRead = 0;
//            int numFramesRead = 0;
//            int totalFramesRead = 0;
//
//
//
//            while ((numBytesRead =
//                    inputStream.read(audioBytes)) != -1) {
//
//                numFramesRead = numBytesRead / bytesPerFrame;
//                totalFramesRead += numFramesRead;
//            }





            int totalFramesRead = 0;
            File fileIn = new File("wav.wav");
            AudioInputStream audioInputStream =
                    AudioSystem.getAudioInputStream(fileIn);
            AudioFormat audioFormat = audioInputStream.getFormat();
            int bytesPerFrame =
                    audioInputStream.getFormat().getFrameSize();
            if (bytesPerFrame == AudioSystem.NOT_SPECIFIED) {
                // some audio formats may have unspecified frame size
                // in that case we may read any amount of bytes
                bytesPerFrame = 1;

            }
            // Set an arbitrary buffer size of 1024 frames.
            int numBytes = 1024 * bytesPerFrame;
            byte[] audioBytes = new byte[numBytes];
            int numBytesRead = 0;
            int numFramesRead = 0;


            // Try to read numBytes bytes from the file.
            while ((numBytesRead =
                    audioInputStream.read(audioBytes)) != -1) {
                // Calculate the number of frames actually read.
                numFramesRead = numBytesRead / bytesPerFrame;
                totalFramesRead += numFramesRead;
                // Here, do something useful with the audio data that's
                // now in the audioBytes array...
            }










            System.out.println("Read Successful");
//
//
//
//
//
//
            clip = AudioSystem.getClip();
//
            InputStream byteStream = new ByteArrayInputStream(audioBytes);
            AudioInputStream audioStream = new AudioInputStream(byteStream, audioFormat, audioBytes.length);
            clip.open(audioStream);

            volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            volume.setValue(0);

        } catch (UnsupportedAudioFileException e) {
            System.err.println("Unsupported Audio File");
        } catch (LineUnavailableException e) {
            System.err.println("Line unavailable");
//        } catch (InterruptedException e){
//            System.err.println("Interruption");
        } catch (IOException e){
            System.err.println("IOE");
        }
    }

    public void play(){
        if (clip.isRunning()) {
            clip.stop();
        } else {
            clip.start();
        }
    }
}
