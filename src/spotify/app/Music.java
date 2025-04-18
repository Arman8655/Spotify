package spotify.app;

import java.util.ArrayList;
import java.util.List;

public class Music {

    private String title;
    private User singer;
    private int numberOfStream = 0;
    public static ArrayList<Music> allMusics = new ArrayList<>();


    public Music(String title, User singer) {
        this.title = title;
        this.singer = singer;
        allMusics.add(this);
    }

    public void play() {
        numberOfStream++;
        System.out.println("Playing: '" + title + "' by " + singer.getUsername() + "Streams: " + numberOfStream);
    }

    public static List<Music> search(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new InvalidOperationException("It cannot be null or empty");
        }

        List<Music> results = new ArrayList<>();
        for (Music music : allMusics) {
            if (music.title.equals(title)) {
                results.add(music);
            }
        }
        if (results.isEmpty()) {
            return null;
        }

        return results;

    }



    public static Music search(String title, User singer) {
        if (title == null) {
            throw new InvalidOperationException("Singer or title should not ne null");
        }

        for (Music music : allMusics) {
            if (music.title.equals(title) && music.singer.equals(singer)) {
                return music;
            }
        }
        return null;

    }
    public String getTitle() {
        return title;
    }

    public User getSinger() {
        return singer;
    }

    public int getNumberOfStream() {
        return numberOfStream;
    }
}
