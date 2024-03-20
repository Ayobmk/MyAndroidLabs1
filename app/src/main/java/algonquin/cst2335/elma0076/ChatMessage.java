package algonquin.cst2335.elma0076;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class ChatMessage {
    @PrimaryKey (autoGenerate = true)
    @ColumnInfo (name="id")
    public int id;
    @ColumnInfo (name="message")
    protected String message;
    @ColumnInfo (name="timeSent")
    String timeSent;
    @ColumnInfo (name="isSentButton")
    boolean isSentButton;
    public ChatMessage(){}
    ChatMessage(String m, String t, boolean sent) {
        message = m;
        timeSent = t;
        isSentButton = sent;
    }

    public String getMessage() {
        return message;
    }
    public String getTimeSent() {
        return timeSent;
    }
    public boolean isSentButton() {
        return isSentButton;
    }
}