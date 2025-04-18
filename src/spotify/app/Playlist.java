package spotify.app;

import java.util.ArrayList;

public class Playlist {
    private String title;
    private ArrayList<Music> playlist;
    private User owner;

    public Playlist(String title, User owner) {
        this.title = title;
        playlist = new ArrayList<>();
        this.owner = owner;
    }

    public void editTitle(String newTitle, String password) {
        if (!owner.getPassword().equals(password)) {
            throw new InvalidOperationException("Incorrect password!");
        }
        this.title = newTitle;
    }

    public void addMusic(Music music, String password) {
        if (!owner.getPassword().equals(password)) {
            throw new InvalidOperationException("Incorrect password!");
        }

        if (playlist.contains(music)) {
            throw new InvalidOperationException("Music already exists in the playlist!");

        }

        playlist.add(music);

    }

    public void removeMusic(Music music, String password) {
        if (!owner.getPassword().equals(password)) {
            throw new InvalidOperationException("Incorrect password!");
        }

        if (!playlist.contains(music)) {
            throw new InvalidOperationException("Music not found in the playlist!");
        }

        playlist.remove(music);
    }


    public ArrayList<Music> searchInPlaylist(String title) {
        ArrayList<Music> result = new ArrayList<>();
        for (Music music : playlist) {

            if (music.getTitle().equals(title)) {
                result.add(music);
            }
        }

        if (result.isEmpty()) {
            return null;
        }
        return result;

    }

    public Music searchInPlaylist(String title, User singer) {
        for (Music music : playlist) {
            if (music.getTitle().equals(title) && music.getSinger().equals(singer)) {
                return music;
            }
        }
        return null;
    }
    public void playPlaylist() {
        for (Music music : playlist) {
            music.play();
        }
    }

    public String getTitle() {
        return title;
    }
}
