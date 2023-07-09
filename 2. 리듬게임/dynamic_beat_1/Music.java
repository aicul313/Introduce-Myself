package dynamic_beat_1;

//import javazoom.jl.*;
import javazoom.jl.player.*;

import java.io.*;

public class Music extends Thread {

    private Player player;
    private boolean isLoop; // 곡이 무한반복인지 한 번만 돌아가고 꺼지는지의 설정
    private File file;
    private FileInputStream fis;
    private BufferedInputStream bis;

    public Music(String name, boolean isLoop){
        try{
            this.isLoop = isLoop;
            file = new File(Main.class.getResource("../music/" + name).toURI());
            fis = new FileInputStream(file);
            bis = new BufferedInputStream(fis);
            player = new Player(bis);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public int getTime() {
        if(player == null)
            return 0;
        return player.getPosition();
    }

    public void close() {
        isLoop = false;
        player.close();
        this.interrupt(); // 곡 종료
    }

    @Override
    public void run() {
        try{
            do{
                player.play();
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                player = new Player(bis);
            } while (isLoop);
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
}
