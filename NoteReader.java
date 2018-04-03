package pianoplayer;

/**
 *
 * @author Callum Johnston
 */
public class NoteReader {

    public int notes[][];
    public String myNotes[];
    private int[] theOctave;
    private int[] cNoteInOctave;
    private int timeSignature;
    private int defaultTempo = 50;
    private int pickupEighthNotes;

    //and now we're going to do the same thing but in a different constructor. there's probably a better way to do this?
    public NoteReader(int bro) {
        FileReader reader;
        if (bro == 0) {
            reader = new FileReader("src/pianoplayer/FurElise.txt");
        } else if (bro == 1) {
            reader = new FileReader("src/pianoplayer/PiratesOfTheCaribbean.txt");
        } else if (bro == 2) {
            reader = new FileReader("src/pianoplayer/iPhone.txt");
        } else {
            reader = new FileReader("src/pianoplayer/MaryHadALittleLamb.txt");
        }
        myNotes = reader.data;
        notes = new int[myNotes.length][2];//the first number is the note value (1 to 88) the second is the length (0 to infinity)
        theOctave = new int[myNotes.length];
        cNoteInOctave = new int[myNotes.length];
        //OK, this is all converting music to numbers:
        for (int i = 0; i < notes.length; i++) {
            if (!myNotes[i].substring(0, 2).equals("##")) {
                String noteName = myNotes[i].substring(0, 2);
                int noteInOctave;
                if (noteName.equals("CN")) {
                    noteInOctave = 0;
                    cNoteInOctave[i] = 0;
                } else if (noteName.equals("C#") || noteName.equals("DB")) {
                    noteInOctave = 1;
                    cNoteInOctave[i] = 0;
                } else if (noteName.equals("DN")) {
                    noteInOctave = 2;
                    cNoteInOctave[i] = 1;
                } else if (noteName.equals("D#") || noteName.equals("EB")) {
                    noteInOctave = 3;
                    cNoteInOctave[i] = 1;
                } else if (noteName.equals("EN")) {
                    noteInOctave = 4;
                    cNoteInOctave[i] = 2;
                } else if (noteName.equals("FN")) {
                    noteInOctave = 5;
                    cNoteInOctave[i] = 3;
                } else if (noteName.equals("F#") || noteName.equals("GB")) {
                    noteInOctave = 6;
                    cNoteInOctave[i] = 3;
                } else if (noteName.equals("GN")) {
                    noteInOctave = 7;
                    cNoteInOctave[i] = 4;
                } else if (noteName.equals("G#") || noteName.equals("AB")) {
                    noteInOctave = 8;
                    cNoteInOctave[i] = 4;
                } else if (noteName.equals("AN")) {
                    noteInOctave = 9;
                    cNoteInOctave[i] = 5;
                } else if (noteName.equals("A#") || noteName.equals("BB")) {
                    noteInOctave = 10;
                } else if (noteName.equals("BN")) {
                    noteInOctave = 11;
                    cNoteInOctave[i] = 6;
                } else {
                    noteInOctave = 0;
                    cNoteInOctave[i] = 0;
                }

                theOctave[i] = (int) (Double.parseDouble(myNotes[i].substring(2, 3)));
                notes[i][0] = (theOctave[i] * 12 + noteInOctave);//get that note
                int a = (int) (100 * Double.parseDouble(myNotes[i].substring(3)));
                notes[i][1] = a;//get that length
            } else {
                timeSignature = (int) (Double.parseDouble(myNotes[i].substring(2, 3)));
                defaultTempo = (int) (Double.parseDouble(myNotes[i].substring(3, 6)));
                pickupEighthNotes = (int) (Double.parseDouble(myNotes[i].substring(6, 7)) + 2);
            }
        }

    }

    public void setNotes(int[][] notes) {
        this.notes = notes;
    }

    public int[] getTheOctave() {
        return theOctave;
    }

    public int[] getcNoteInOctave() {
        return cNoteInOctave;
    }

    public int getTimeSignature() {
        return timeSignature;
    }

    public int getDefaultTempo() {
        return defaultTempo;
    }

    public int getPickupEighthNotes() {
        return pickupEighthNotes;
    }

    public int[][] getNotes() {
        int[][] copyCat;
        copyCat = new int[myNotes.length][2];
        for (int i = 0; i < copyCat.length; i++) {
            for (int j = 0; j < 2; j++) {
                copyCat[i][j] = notes[i][j];
            }
        }
        return notes;
    }

    public String toString() {
        String ans = "";
        for (int i = 0; i < notes.length; i++) {
            ans += notes[i][1] + "\n";
        }
        return ans;
    }
}
