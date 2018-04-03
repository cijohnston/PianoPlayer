package pianoplayer;

import java.awt.Color;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Synthesizer;

/**
 *
 * @author cijohnston
 */
public class NotePlayer implements Runnable {

    private NoteReader notes;
    private int duration;
    private int volume;
    private Piano pianoKeys;
    private boolean cancel = false;
    private SheetMusic sheet;

    public NotePlayer(NoteReader notes, Piano pianoKeys, SheetMusic sheet) {
        this.sheet = sheet;
        this.notes = notes;
        this.pianoKeys = pianoKeys;
    }

    public Thread thread() {

        Thread myThread = new Thread(new NotePlayer(notes, pianoKeys, sheet));

        return myThread;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public NoteReader getNotes() {
        return notes;
    }

    public void setNotes(NoteReader notes) {
        this.notes = notes;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setCancel(boolean cancel) {
        this.cancel = cancel;
    }

    public void run() {
        int channel = 3; // 0 is a piano, 9 is percussion, other channels are for other instruments

        try {
            Synthesizer synth = MidiSystem.getSynthesizer();//make a synthesizer
            synth.open();//turn it on!!
            MidiChannel[] channels = synth.getChannels();
            
            sheet.setPlayLine(0);
            sheet.setPlayNoteHeight(2130);
            sheet.setPage(0);
            SheetMusic.setCati(0);
            
            pianoKeys.setKeyPlayed1(notes.notes[0][0] - 21);
            pianoKeys.repaint();
            for (int i = 0; i < notes.notes.length; i++) {
                if (cancel == true) {
                    i = notes.notes.length-3;
                    synth.close();
                    cancel = false;
                }
                
                
                
                channels[channel].noteOn(notes.notes[i][0], volume);//turn that note 
                Thread.sleep(duration * notes.notes[i][1]);//hold out the note for desired length
                pianoKeys.setKeyPlayed1(notes.notes[i + 1][0] - 21);
                if (notes.notes[i][1] >= 50) {//this be so you you can play chords and shit
                    channels[channel].noteOff(notes.notes[i][0]);//turn d note off yo
                    sheet.setPlayLine(sheet.getPlayLine()+1);
                    sheet.repaint();
                }
                //OK haha. so this mess is so that when you play a chord, it isn't held out forever:
                if (i - 1 >= 0 && notes.notes[i][1] >= 50 && notes.notes[i - 1][1] < 50) {
                    pianoKeys.setKeyPlayed2(notes.notes[i][0] - 21);
                    channels[channel].noteOff(notes.notes[i - 1][0]);
                    if (i - 2 >= 0 && notes.notes[i - 2][1] < 50) {
                        pianoKeys.setKeyPlayed3(notes.notes[i - 1][0] - 21);
                        channels[channel].noteOff(notes.notes[i - 2][0]);
                        if (i - 3 >= 0 && notes.notes[i - 3][1] < 50) {
                            pianoKeys.setKeyPlayed3(notes.notes[i - 1][0] - 21);
                            channels[channel].noteOff(notes.notes[i - 3][0]);
                            if (i - 4 >= 0 && notes.notes[i - 4][1] < 50) {
                                pianoKeys.setKeyPlayed4(notes.notes[i - 2][0] - 21);
                                channels[channel].noteOff(notes.notes[i - 4][0]);
                                if (i - 5 >= 0 && notes.notes[i - 5][1] < 50) {
                                    pianoKeys.setKeyPlayed5(notes.notes[i - 3][0] - 21);
                                    channels[channel].noteOff(notes.notes[i - 5][0]);
                                    if (i - 6 >= 0 && notes.notes[i - 6][1] < 50) {
                                        pianoKeys.setKeyPlayed6(notes.notes[i - 4][0] - 21);
                                        channels[channel].noteOff(notes.notes[i - 6][0]);
                                    }
                                }
                            }
                        }
                    }
                }
                pianoKeys.repaint();
            }
            pianoKeys.setKeyPlayed1(100);
            pianoKeys.setKeyPlayed2(100);
            pianoKeys.setKeyPlayed3(100);
            pianoKeys.setKeyPlayed4(100);
            pianoKeys.setKeyPlayed5(100);
            pianoKeys.setKeyPlayed6(100);
            pianoKeys.repaint();

            synth.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        pianoKeys.setKeyPlayed1(100);
            pianoKeys.setKeyPlayed2(100);
            pianoKeys.setKeyPlayed3(100);
            pianoKeys.setKeyPlayed4(100);
            pianoKeys.setKeyPlayed5(100);
            pianoKeys.setKeyPlayed6(100);
            pianoKeys.repaint();
    }

}
