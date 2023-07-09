package dynamic_beat_1;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyListener extends KeyAdapter {

    @Override
    public void keyPressed(KeyEvent e){
        if(DynamicBeat.Game == null){
            return;
        }

        if(e.getKeyCode() == KeyEvent.VK_S){
            DynamicBeat.Game.pressS();
        }else if(e.getKeyCode() == KeyEvent.VK_D){
            DynamicBeat.Game.pressD();
        }else if(e.getKeyCode() == KeyEvent.VK_F){
            DynamicBeat.Game.pressF();
        }else if(e.getKeyCode() == KeyEvent.VK_SPACE){
            DynamicBeat.Game.pressSpace();
        }else if(e.getKeyCode() == KeyEvent.VK_J){
            DynamicBeat.Game.pressJ();
        }else if(e.getKeyCode() == KeyEvent.VK_K){
            DynamicBeat.Game.pressK();
        }else if(e.getKeyCode() == KeyEvent.VK_L){
            DynamicBeat.Game.pressL();
        }
    }

    @Override
    public void keyReleased(KeyEvent e){
        if(DynamicBeat.Game == null){
            return;
        }

        if(e.getKeyCode() == KeyEvent.VK_S){
            DynamicBeat.Game.releaseS();
        }else if(e.getKeyCode() == KeyEvent.VK_D){
            DynamicBeat.Game.releaseD();
        }else if(e.getKeyCode() == KeyEvent.VK_F){
            DynamicBeat.Game.releaseF();
        }else if(e.getKeyCode() == KeyEvent.VK_SPACE){
            DynamicBeat.Game.releaseSpace();
        }else if(e.getKeyCode() == KeyEvent.VK_J){
            DynamicBeat.Game.releaseJ();
        }else if(e.getKeyCode() == KeyEvent.VK_K){
            DynamicBeat.Game.releaseK();
        }else if(e.getKeyCode() == KeyEvent.VK_L){
            DynamicBeat.Game.releaseL();
        }
    }
    
}
