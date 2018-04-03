package pianoplayer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

/*
 * By Callum Johnston
 */
/**
 *
 * @author cijohnston
 */
public class SheetMusic extends javax.swing.JPanel {

    private int song;
    private int page = 0;
    private int playLine = 0;
    private int playNoteHeight = 2130;
    private static int cati = 0;

    /**
     * Creates new form SheetMusic
     */
    public SheetMusic() {
        initComponents();
    }

    public void setSong(int song) {
        this.song = song;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPage() {
        return page;
    }

    public void setPlayLine(int playLine) {
        this.playLine = playLine;
    }

    public int getPlayLine() {
        return playLine;
    }

    public static void setCati(int cati) {
        SheetMusic.cati = cati;
    }
    
    

    public void setPlayNoteHeight(int playNoteHeight) {
        this.playNoteHeight = playNoteHeight;
    }

    public void paintComponent(Graphics g1) {
        System.out.println("painting...");

        // call constructor of JPanel   
        super.paintComponent(g1);

        // Graphics2D has more functions so let's use it.
        Graphics2D g = (Graphics2D) g1;
        // set the drawing pen to light blue
        g.setColor(Color.white);
        // fill entire panel with a white rectangle
        g.fillRect(0, 0, this.getWidth(), this.getHeight());

        // These two lines flip image upside down so that (0,0) 
        // is at lower left corner of the panel
        g.scale(1, -1);
        g.translate(0, -this.getHeight());

        // create and draw the picture
        g.scale(.20, .20); // scales the whole picture
        g.translate(0, 15);
        NoteReader notes = new NoteReader(song);
        int numberOfNotes = notes.notes.length;
        int noteLines = numberOfNotes / 15;
        noteLines = 7;//delete this line
        g.setColor(Color.BLACK);
        boolean measureTester;
        double measureTime = notes.getPickupEighthNotes() * 100;
        for (int i = 0; i < noteLines; i++) {
            g.fillRect(100, 2700 - 400 * i, 2500, 4);
            g.fillRect(100, 2675 - 400 * i, 2500, 4);
            g.fillRect(100, 2650 - 400 * i, 2500, 4);
            g.fillRect(100, 2625 - 400 * i, 2500, 4);
            g.fillRect(100, 2600 - 400 * i, 2500, 4);

            g.fillRect(100, 2550 - 400 * i, 2500, 4);
            g.fillRect(100, 2525 - 400 * i, 2500, 4);
            g.fillRect(100, 2500 - 400 * i, 2500, 4);
            g.fillRect(100, 2475 - 400 * i, 2500, 4);
            g.fillRect(100, 2450 - 400 * i, 2500, 4);

        }
        int octave[] = notes.getTheOctave();
        int cNote[] = notes.getcNoteInOctave();
        int noteDistance = 0;
        int noteHeight = 2130;

        boolean downTailTester = false;
        int i = cati;
        g.setColor(Color.BLUE);

            g.fillRect(180 + 70 * playLine, playNoteHeight + 320, 7, 250);

            if (playLine > 34) {
                playNoteHeight = playNoteHeight - 400;
                playLine = 0;
            }

            if (playNoteHeight < -400) {
                playNoteHeight = 2130;
                page = page + 1;
                

            }
//        if (playLine == 33 && playNoteHeight < 0) {
//                cati = cati;//////////////////////////////////////////////////////////???????????
//                System.out.println("######################################");
//        }
        boolean close = false;
        while (i < notes.notes.length - 1 && close == false) {
            
            
            
            if (playLine == 34 && playNoteHeight < 0 && playLine+30>noteDistance && playLine-30<noteDistance) {
                cati = i-52;
                System.out.println("######################################");
        }
            g.setColor(Color.BLACK);
//            System.out.println(i+" "+notes.notes[i][1]);

            noteDistance++;
            if (i > 0 && notes.notes[i - 1][1] == 0) {
                noteDistance = noteDistance - 1;
            }
            if (noteDistance > 34) {
                noteHeight = noteHeight - 400;
                noteDistance = 0;
            }

            if (noteDistance > 33 && noteHeight < -400) {
                close = true;
            }

            if (notes.notes[i][1] >= 400 || notes.notes[i][1] == 0 && notes.notes[i + 1][1] >= 400
                    || notes.notes[i][1] == 0 && notes.notes[i + 1][1] == 0 && notes.notes[i + 2][1] >= 400
                    || notes.notes[i][1] == 0 && notes.notes[i + 1][1] == 0 && notes.notes[i + 2][1] == 0
                    && notes.notes[i + 3][1] >= 400) {
                g.drawOval(150 + 70 * noteDistance, (int) (noteHeight + octave[i] * 87.5 + cNote[i] * 12.5), 30, 25);
            } else {
                g.fillOval(150 + 70 * noteDistance, (int) (noteHeight + octave[i] * 87.5 + cNote[i] * 12.5), 30, 25);
            }

            if (notes.notes[i][0] < 60 && notes.notes[i][0] > 50 || notes.notes[i][0] > 70
                    || i - 1 >= 0 && notes.notes[i - 1][1] == 0 && downTailTester == true) {
                g.fillRect(150 + 70 * noteDistance, (int) (noteHeight + octave[i] * 87.5 + cNote[i] * 12.5 - 85), 4, 100);
                if (notes.notes[i][1] > 90 && notes.notes[i][1] < 121) {
                    int xtail[] = {150 + 70 * noteDistance, 175 + 70 * noteDistance,
                        175 + 70 * noteDistance, 150 + 70 * noteDistance};
                    int ytail[] = {(int) (noteHeight + octave[i] * 87.5 + cNote[i] * 12.5 - 85) + 5,
                        (int) (noteHeight + octave[i] * 87.5 + cNote[i] * 12.5 - 85) + 20,
                        (int) (noteHeight + octave[i] * 87.5 + cNote[i] * 12.5 - 85) + 28,
                        (int) (noteHeight + octave[i] * 87.5 + cNote[i] * 12.5 - 85) + 13};
                    g.fillPolygon(xtail, ytail, xtail.length);
                } //else if (notes.notes[i][1] > 90 && notes.notes[i][1] < 121)

                if (notes.notes[i][1] > 25 && notes.notes[i][1] < 85) {
                    int xtail1[] = {150 + 70 * noteDistance, 175 + 70 * noteDistance,
                        175 + 70 * noteDistance, 150 + 70 * noteDistance};
                    int ytail1[] = {(int) (noteHeight + octave[i] * 87.5 + cNote[i] * 12.5 - 85) + 5,
                        (int) (noteHeight + octave[i] * 87.5 + cNote[i] * 12.5 - 85) + 20,
                        (int) (noteHeight + octave[i] * 87.5 + cNote[i] * 12.5 - 85) + 28,
                        (int) (noteHeight + octave[i] * 87.5 + cNote[i] * 12.5 - 85) + 13};
                    g.fillPolygon(xtail1, ytail1, xtail1.length);

                    int xtail2[] = {150 + 70 * noteDistance, 175 + 70 * noteDistance,
                        175 + 70 * noteDistance, 150 + 70 * noteDistance};
                    int ytail2[] = {(int) (noteHeight + octave[i] * 87.5 + cNote[i] * 12.5 - 85) + 17,
                        (int) (noteHeight + octave[i] * 87.5 + cNote[i] * 12.5 - 85) + 32,
                        (int) (noteHeight + octave[i] * 87.5 + cNote[i] * 12.5 - 85) + 40,
                        (int) (noteHeight + octave[i] * 87.5 + cNote[i] * 12.5 - 85) + 25};
                    g.fillPolygon(xtail2, ytail2, xtail2.length);
                }

                downTailTester = true;
            } else {
                g.fillRect(180 + 70 * noteDistance, (int) (noteHeight + octave[i] * 87.5 + cNote[i] * 12.5 + 5), 4, 100);
                if (notes.notes[i][1] > 85 && notes.notes[i][1] < 121) {
                    int xtail[] = {180 + 70 * noteDistance, 205 + 70 * noteDistance,
                        205 + 70 * noteDistance, 180 + 70 * noteDistance};
                    int ytail[] = {(int) (noteHeight + octave[i] * 87.5 + cNote[i] * 12.5 + 10) + 100,
                        (int) (noteHeight + octave[i] * 87.5 + cNote[i] * 12.5 + 10) + 85,
                        (int) (noteHeight + octave[i] * 87.5 + cNote[i] * 12.5 + 10) + 77,
                        (int) (noteHeight + octave[i] * 87.5 + cNote[i] * 12.5 + 10) + 92};
                    g.fillPolygon(xtail, ytail, xtail.length);
                }
                if (notes.notes[i][1] > 25 && notes.notes[i][1] < 85) {
                    int xtail1[] = {180 + 70 * noteDistance, 205 + 70 * noteDistance,
                        205 + 70 * noteDistance, 180 + 70 * noteDistance};
                    int ytail1[] = {(int) (noteHeight + octave[i] * 87.5 + cNote[i] * 12.5 + 10) + 100,
                        (int) (noteHeight + octave[i] * 87.5 + cNote[i] * 12.5 + 10) + 85,
                        (int) (noteHeight + octave[i] * 87.5 + cNote[i] * 12.5 + 10) + 77,
                        (int) (noteHeight + octave[i] * 87.5 + cNote[i] * 12.5 + 10) + 92};
                    g.fillPolygon(xtail1, ytail1, xtail1.length);

                    int xtail2[] = {180 + 70 * noteDistance, 205 + 70 * noteDistance,
                        205 + 70 * noteDistance, 180 + 70 * noteDistance};
                    int ytail2[] = {(int) (noteHeight + octave[i] * 87.5 + cNote[i] * 12.5 + 10) + 88,
                        (int) (noteHeight + octave[i] * 87.5 + cNote[i] * 12.5 + 10) + 73,
                        (int) (noteHeight + octave[i] * 87.5 + cNote[i] * 12.5 + 10) + 65,
                        (int) (noteHeight + octave[i] * 87.5 + cNote[i] * 12.5 + 10) + 80};
                    g.fillPolygon(xtail2, ytail2, xtail2.length);
                }
                downTailTester = false;
            }

            measureTester = false;
            measureTime += notes.notes[i][1];
            if (measureTime >= notes.getTimeSignature() * 200) {
                measureTime = 0;
                measureTester = true;
            }
            if (measureTester == true) {
                g.drawLine(200 + 70 * noteDistance, noteHeight + 320, 200 + 70 * noteDistance, noteHeight + 570);
            }

            if (notes.notes[i][0] == 60 || notes.notes[i][0] < 42 && (octave[i] * 7 + cNote[i]) % 2 == 1
                    || notes.notes[i][0] > 77 && (octave[i] * 7 + cNote[i]) % 2 == 1) {
                g.fillRect(140 + 70 * noteDistance,
                        (int) (noteHeight + octave[i] * 87.5 + cNote[i] * 12.5 + 6), 55, 4);
            }//do the same for none line notes above and below staff, plus add guide lines

//            g.setColor(Color.BLUE);
//
//            g.fillRect(180 + 70 * playLine, playNoteHeight + 320, 7, 250);
//
//            if (playLine > 34) {
//                playNoteHeight = playNoteHeight - 400;
//                playLine = 0;
//            }
//
//            if (playNoteHeight < -400) {
//                playNoteHeight = 2130;
//                page = page + 1;
//                
//
//            }

            i++;
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
