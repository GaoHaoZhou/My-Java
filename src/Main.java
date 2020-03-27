import it.sauronsoftware.jave.*;

import java.io.File;

public class Main {

    public static void changeToMp3(String sourcePath, String targetPath) {
        File source = new File(sourcePath);
        File target = new File(targetPath);
        AudioAttributes audio = new AudioAttributes();
        Encoder encoder = new Encoder();
        audio.setCodec("libmp3lame");
        EncodingAttributes attrs = new EncodingAttributes();
        attrs.setFormat("mp3");
        attrs.setAudioAttributes(audio);
        try {
            encoder.encode(source, target, attrs);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InputFormatException e) {
            e.printStackTrace();
        } catch (EncoderException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String sourcePath="I:\\1我的地盘\\宝贝音频\\lover.amr";
        String targetPath="I:\\1我的地盘\\宝贝音频\\lover.mp3";
        changeToMp3(sourcePath,targetPath);
    }
}
