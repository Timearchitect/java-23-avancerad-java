package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;


public class WordleJFrame extends JFrame {
    // skapa classer för sam-atbete
    // mer ord randomisa det
    //features : läs från file ,lägg till fler game modes
    private final String[] wordArray = {"TEST","ROAD","POLE","DOWN","QUIT","BLUE","COOL","BEER","DART","JAVA","PLAY","GAME","WORD","TYPE","DONE","HARD","EASY"};
    private final String dailyWord = wordArray[new Random().nextInt(0,wordArray.length)];
    private ArrayList<JTextField> jfArray = new ArrayList<>();

    private JPanel gamearea;
    private String inputText = "";
    private int amountOfTries = 10;

    WordleJFrame() {
        System.err.println("Daily word is : "+dailyWord);

        gamearea = new JPanel();
        gamearea.setBounds(200, 200, 520, 200);
        JLabel label = new JLabel("Wordle");

        gamearea.add(label);
        gamearea.setLayout(new BoxLayout(gamearea, BoxLayout.LINE_AXIS));


        for (char c : wordArray[0].toCharArray()) {
            JTextField jt = new JTextField("", 1);
            jfArray.add(jt);

            jt.addKeyListener(
                    new KeyAdapter() {
                        /* public void keyTyped(KeyEvent e) {
                        }
*/
                        public void keyPressed(KeyEvent e) {
                            if (e.getKeyCode() == 37) { //vänster piltangent
                                if(jfArray.get(0)!=e.getComponent()) {
                                    System.out.println("left");
                                    KeyboardFocusManager.getCurrentKeyboardFocusManager().focusPreviousComponent();
                                    e.consume();
                                }
                            } else if (e.getKeyCode() == 39) { // höger piltangent
                                if(jfArray.get(3)!=e.getComponent()) {
                                    System.out.println("right");
                                    KeyboardFocusManager.getCurrentKeyboardFocusManager().focusNextComponent();
                                    e.consume();
                                }
                            }else if(e.getKeyCode() == 16)
                                e.consume();
                                else
                            jt.setText("");

                        }

                        public void keyReleased(KeyEvent e) {
                            System.out.println(e.getKeyCode());
                            if (!(e.getKeyCode() == 37 || e.getKeyCode() == 39|| e.getKeyCode() == 16)) { //16 = shift
                                char c = e.getKeyChar();
                                if ('A' <= c && c <= 'z') // a-Z
                                    jt.setText(String.valueOf(c).toUpperCase());
                                else
                                    jt.setText("");
                            }
                        }
                    });
            jt.setHorizontalAlignment(JTextField.CENTER);
            jt.setFont(new Font(Font.SERIF, Font.BOLD, 50));
            jt.setAlignmentX(0.5f);
            gamearea.add(jt);
        }

        JButton b = new JButton("submit word");
        b.addKeyListener(
                new KeyAdapter() {
                    public void keyPressed(KeyEvent e) {
                        if (e.getKeyCode() == 10) { //ENTER
                            b.doClick();
                           // e.consume();
                        }
                    }
                });

        b.addActionListener(e -> {
            inputText="";
            for (JTextField jf : jfArray) {
                inputText += jf.getText();
            }
            if(inputText.length()==4) {
                amountOfTries--;
                System.out.println(inputText);
                feedback();
            }
            else
                System.out.println("You are missing letters!!!");
        });
        gamearea.add(b);
        gamearea.setFocusable(true);
        gamearea.setVisible(true);
        this.add(gamearea);
        this.setBounds(200, 200, 600, 600);
    }

    private void feedback() {
        System.err.println("cheating println: "+dailyWord +":"+ inputText);

        if (dailyWord.equals(inputText)) {
            System.out.println(" 100% CORRECT WORD GUESSED !!! ");
        } else {
            for (int i = 0; i < 4; i++) { // loopa igenom alla bokstäver
                char exactLetter = dailyWord.charAt(i);
                char compareLetter = inputText.charAt(i);

                if (exactLetter == inputText.charAt(i)) {
                    System.out.println("letter " + compareLetter + " :is on the correct place");
                } else {
                    boolean otherMatching = false;
                    for (char otherChar : dailyWord.toCharArray()) {
                        if (compareLetter == otherChar) {
                            System.out.println("letter " + compareLetter + " :is on the wrong place");
                            otherMatching = true;
                            break;
                        }
                    }
                    if (!otherMatching)
                        System.out.println("letter " + compareLetter + " :is doesnt exist");

                }
            }
            System.out.println("submitted , tries left: " + amountOfTries);

        }

    }


    void makeHistory(int amountOfTries) {
        //skapa rutor med färger som beskriver om ordet är korrekt
        //grön rätt bokstav + rätt plats
        //Gul rätt bokstav + fel plats
        //Vit är fel bokstav + existerar ej i ordet
    }

}
