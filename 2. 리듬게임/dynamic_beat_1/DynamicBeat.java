package dynamic_beat_1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

public class DynamicBeat extends JFrame {

    // 더블 버퍼링을 위해 전체 화면에 대한 이미지를 담는 인스턴스
    private Graphics screenGraphic;

    private Image screenImage;

    private ImageIcon exitButtonBasicImage = new ImageIcon(Main.class.getResource("../images/ExitButton_Basic.png"));
    private ImageIcon exitButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/ExitButton_Entered.png"));
    private ImageIcon StartButtonBasicImage = new ImageIcon(Main.class.getResource("../images/StartButtonBasic.png"));
    private ImageIcon StartButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/StartButtonEntered.png"));
    private ImageIcon QuitButtonBasicImage = new ImageIcon(Main.class.getResource("../images/QuitButtonBasic.png"));
    private ImageIcon QuitButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/QuitButtonEntered.png"));
    private ImageIcon leftButtonBasicImage = new ImageIcon(Main.class.getResource("../images/leftButtonBasic.png"));
    private ImageIcon leftButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/leftButtonEntered.png"));
    private ImageIcon rightButtonBasicImage = new ImageIcon(Main.class.getResource("../images/rightButtonBasic.png"));
    private ImageIcon rightButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/rightButtonEntered.png"));
    private ImageIcon easyButtonBasicImage = new ImageIcon(Main.class.getResource("../images/EasyButtonBasic.png"));
    private ImageIcon easyButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/EasyButtonEntered.png"));
    private ImageIcon hardButtonBasicImage = new ImageIcon(Main.class.getResource("../images/HardButtonBasic.png"));
    private ImageIcon hardButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/HardButtonEntered.png"));
    private ImageIcon backButtonBasicImage = new ImageIcon(Main.class.getResource("../images/BackButtonBasic.png"));
    private ImageIcon backButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/BackButtonEntered.png"));

    private Image Background = new ImageIcon(Main.class.getResource("../images/intro_background_title.jpg")).getImage();

    private JLabel menuBar = new JLabel(new ImageIcon(Main.class.getResource("../images/menuBar.png")));

    private JButton exitButton = new JButton(exitButtonBasicImage);
    private JButton startButton = new JButton(StartButtonBasicImage);
    private JButton quitButton = new JButton(QuitButtonBasicImage);
    private JButton leftButton = new JButton(leftButtonBasicImage);
    private JButton rightButton = new JButton(rightButtonBasicImage);
    private JButton easyButton = new JButton(easyButtonBasicImage);
    private JButton hardButton = new JButton(hardButtonBasicImage);
    private JButton backButton = new JButton(backButtonBasicImage);

    private int mouseX, mouseY;    

    private boolean isMainScreen = false;
    private boolean isGameScreen = false;

    ArrayList<Track> trackList = new ArrayList<Track>();

    private Music selectedMusic;
    private Music introMusic = new Music("intro_music.mp3", true);
    private Image titleImage;
    private Image selectedImage;

    private int nowSelected = 0;

    public static Game Game;

    public DynamicBeat() {  
        trackList.add(new Track("Fighting_title_image.png", "Fighting_start_image.jpg", "Fighting_game_image.jpg", "Fighting Selected.mp3", "BSS - Fighting.mp3", "BSS - Fighting"));
        trackList.add(new Track("HypeBoy_title_image.png", "HypeBoy_start_image.jpg", "HypeBoy_game_image.jpg", "HypeBoy Selected.mp3", "New Jeans - Hype Boy.mp3", "New Jeans - Hype Boy"));
        trackList.add(new Track("Flower_title_image.png", "Flower_start_image.jpg", "Flower_game_image.jpg", "Flower Selected.mp3", "JISOO - Flower.mp3", "JISOO - Flower"));

        setUndecorated(true);
        setTitle("Dynamic Beat");
        setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
        setResizable(false);
        setLocationRelativeTo(null); // 사용자가 임의로 크기 조정 불가능
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 게임창을 끄면 프로그램도 완전히 종료되게 하는 것
        setVisible(true); // 정상적으로 돌아가면 눈에 보이게 해주는 것
        setBackground(new Color(0, 0, 0, 0));
        setLayout(null);

        addKeyListener(new KeyListener());

        introMusic.start();

        exitButton.setBounds(1245, 0, 30, 30);
        exitButton.setBorderPainted(false);
        exitButton.setContentAreaFilled(false);
        exitButton.setFocusPainted(false);
        exitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e){
                exitButton.setIcon(exitButtonEnteredImage);
                exitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
                Music ButtonEnteredMusic = new Music("ButtonEnteredMusic.mp3", false);
                ButtonEnteredMusic.start();
            }

            @Override
            public void mouseExited(MouseEvent e){
                exitButton.setIcon(exitButtonBasicImage);
                exitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }

            @Override
            public void mousePressed(MouseEvent e){
                Music ButtonPressedMusic = new Music("ButtonPressedMusic.mp3", false);
                ButtonPressedMusic.start();
                try{
                    Thread.sleep(1000);
                }catch(InterruptedException ex){
                    ex.printStackTrace();
                }
                System.exit(0);
            }
        });
        add(exitButton);

        startButton.setBounds(270, 530, 300, 75);
        startButton.setBorderPainted(false);
        startButton.setContentAreaFilled(false);
        startButton.setFocusPainted(false);
        startButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e){
                startButton.setIcon(StartButtonEnteredImage);
                startButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
                Music ButtonEnteredMusic = new Music("ButtonEnteredMusic.mp3", false);
                ButtonEnteredMusic.start();
            }

            @Override
            public void mouseExited(MouseEvent e){
                startButton.setIcon(StartButtonBasicImage);
                startButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }

            @Override
            public void mousePressed(MouseEvent e){
                Music ButtonPressedMusic = new Music("ButtonPressedMusic.mp3", false);
                ButtonPressedMusic.start();
                enterMain();
            }
        });
        add(startButton);

        quitButton.setBounds(615, 530, 300, 75);
        quitButton.setBorderPainted(false);
        quitButton.setContentAreaFilled(false);
        quitButton.setFocusPainted(false);
        quitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e){
                quitButton.setIcon(QuitButtonEnteredImage);
                quitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
                Music ButtonEnteredMusic = new Music("ButtonEnteredMusic.mp3", false);
                ButtonEnteredMusic.start();
            }

            @Override
            public void mouseExited(MouseEvent e){
                quitButton.setIcon(QuitButtonBasicImage);
                quitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }

            @Override
            public void mousePressed(MouseEvent e){
                Music ButtonPressedMusic = new Music("ButtonPressedMusic.mp3", false);
                ButtonPressedMusic.start();
                try{
                    Thread.sleep(1000);
                }catch(InterruptedException ex){
                    ex.printStackTrace();
                }
                System.exit(0);
            }
        });
        add(quitButton);

        leftButton.setVisible(false);
        leftButton.setBounds(140, 310, 60, 60);
        leftButton.setBorderPainted(false);
        leftButton.setContentAreaFilled(false);
        leftButton.setFocusPainted(false);
        leftButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e){
                leftButton.setIcon(leftButtonEnteredImage);
                leftButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
                Music ButtonEnteredMusic = new Music("ButtonEnteredMusic.mp3", false);
                ButtonEnteredMusic.start();
            }

            @Override
            public void mouseExited(MouseEvent e){
                leftButton.setIcon(leftButtonBasicImage);
                leftButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }

            @Override
            public void mousePressed(MouseEvent e){
                Music ButtonPressedMusic = new Music("ButtonPressedMusic.mp3", false);
                ButtonPressedMusic.start();
                selectLeft();
            }
        });
        add(leftButton);

        rightButton.setVisible(false);
        rightButton.setBounds(1080, 310, 60, 60);
        rightButton.setBorderPainted(false);
        rightButton.setContentAreaFilled(false);
        rightButton.setFocusPainted(false);
        rightButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e){
                rightButton.setIcon(rightButtonEnteredImage);
                rightButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
                Music ButtonEnteredMusic = new Music("ButtonEnteredMusic.mp3", false);
                ButtonEnteredMusic.start();
            }

            @Override
            public void mouseExited(MouseEvent e){
                rightButton.setIcon(rightButtonBasicImage);
                rightButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }

            @Override
            public void mousePressed(MouseEvent e){
                Music ButtonPressedMusic = new Music("ButtonPressedMusic.mp3", false);
                ButtonPressedMusic.start();
                selectRight();
            }
        });
        add(rightButton);

        easyButton.setVisible(false);
        easyButton.setBounds(375, 580, 250, 67);
        easyButton.setBorderPainted(false);
        easyButton.setContentAreaFilled(false);
        easyButton.setFocusPainted(false);
        easyButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e){
                easyButton.setIcon(easyButtonEnteredImage);
                easyButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
                Music ButtonEnteredMusic = new Music("ButtonEnteredMusic.mp3", false);
                ButtonEnteredMusic.start();
            }

            @Override
            public void mouseExited(MouseEvent e){
                easyButton.setIcon(easyButtonBasicImage);
                easyButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }

            @Override
            public void mousePressed(MouseEvent e){
                Music ButtonPressedMusic = new Music("ButtonPressedMusic.mp3", false);
                ButtonPressedMusic.start();
                gameStart(nowSelected, "Easy");
            }
        });
        add(easyButton);

        hardButton.setVisible(false);
        hardButton.setBounds(655, 580, 250, 67);
        hardButton.setBorderPainted(false);
        hardButton.setContentAreaFilled(false);
        hardButton.setFocusPainted(false);
        hardButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e){
                hardButton.setIcon(hardButtonEnteredImage);
                hardButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
                Music ButtonEnteredMusic = new Music("ButtonEnteredMusic.mp3", false);
                ButtonEnteredMusic.start();
            }

            @Override
            public void mouseExited(MouseEvent e){
                hardButton.setIcon(hardButtonBasicImage);
                hardButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }

            @Override
            public void mousePressed(MouseEvent e){
                Music ButtonPressedMusic = new Music("ButtonPressedMusic.mp3", false);
                ButtonPressedMusic.start();
                gameStart(nowSelected, "Hard");
            }
        });
        add(hardButton);

        backButton.setVisible(false);
        backButton.setBounds(20, 50, 50, 60);
        backButton.setBorderPainted(false);
        backButton.setContentAreaFilled(false);
        backButton.setFocusPainted(false);
        backButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e){
                backButton.setIcon(backButtonEnteredImage);
                backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
                Music ButtonEnteredMusic = new Music("ButtonEnteredMusic.mp3", false);
                ButtonEnteredMusic.start();
            }

            @Override
            public void mouseExited(MouseEvent e){
                backButton.setIcon(backButtonBasicImage);
                backButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }

            @Override
            public void mousePressed(MouseEvent e){
                Music ButtonPressedMusic = new Music("ButtonPressedMusic.mp3", false);
                ButtonPressedMusic.start();
                backMain();
            }
        });
        add(backButton);

        menuBar.setBounds(0, 0, 1280, 30);
        menuBar.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e){
                mouseX = e.getX();
                mouseY = e.getY();
            }
        });
        menuBar.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e){
                int x = e.getXOnScreen();
                int y = e.getYOnScreen();
                setLocation(x - mouseX, y - mouseY);
            }
        });
        add(menuBar);
    }

    // JFrame에서 상속받은 gui 게임에서 가장 첫 번째로 화면에서 그려주는 함수
    public void paint(Graphics g){
        screenImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
        screenGraphic = screenImage.getGraphics();
        screenDraw((Graphics2D)screenGraphic);
        g.drawImage(screenImage, 0, 0, null);
    }

    public void screenDraw(Graphics2D g){
        g.drawImage(Background, 0, 0, null);
        if(isMainScreen){
            g.drawImage(selectedImage, 340, 100, null);
            g.drawImage(titleImage, 340, 18, null);
        }
        if(isGameScreen){
            Game.screenDraw(g);
        }
        paintComponents(g);
        try {
            Thread.sleep(5);
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.repaint(); 
    }

    public void selectTrack(int nowSelected){
        if(selectedMusic != null)
            selectedMusic.close();
        titleImage = new ImageIcon(Main.class.getResource("../images/" + trackList.get(nowSelected).getTitleImage())).getImage();
        selectedImage = new ImageIcon(Main.class.getResource("../images/" + trackList.get(nowSelected).getStartImage())).getImage();
        selectedMusic = new Music(trackList.get(nowSelected).getStartMusic(), true);
        selectedMusic.start();
    }

    public void selectLeft(){
        if(nowSelected == 0)
            nowSelected = trackList.size() - 1;
        else
            nowSelected--;
        selectTrack(nowSelected);
    }

    public void selectRight(){
        if(nowSelected == trackList.size() - 1)
            nowSelected = 0;
        else
            nowSelected++;
        selectTrack(nowSelected);
    }

    public void gameStart(int nowSelected, String difficulty){
        if(selectedMusic != null)
            selectedMusic.close();
        isMainScreen = false;
        leftButton.setVisible(false);
        rightButton.setVisible(false);
        easyButton.setVisible(false);
        hardButton.setVisible(false);
        Background = new ImageIcon(Main.class.getResource("../images/" + trackList.get(nowSelected).getGameImage())).getImage();
        backButton.setVisible(true);
        isGameScreen = true;
        Game = new Game(trackList.get(nowSelected).getTitleName(), difficulty, trackList.get(nowSelected).getGameMusic());
        Game.start();
        setFocusable(true);
    }

    public void backMain() {
        isMainScreen = true;
        leftButton.setVisible(true);
        rightButton.setVisible(true);
        easyButton.setVisible(true);
        hardButton.setVisible(true);
        Background = new ImageIcon(Main.class.getResource("../images/main_background.jpg")).getImage();
        backButton.setVisible(false);
        selectTrack(nowSelected);
        isGameScreen = false;
        Game.close();
    }

    public void enterMain(){
        startButton.setVisible(false);
        quitButton.setVisible(false);
        Background = new ImageIcon(Main.class.getResource("../images/main_background.jpg")).getImage();
        isMainScreen = true;
        leftButton.setVisible(true);
        rightButton.setVisible(true);
        easyButton.setVisible(true);
        hardButton.setVisible(true);
        introMusic.close();
        selectTrack(0);
    }

}