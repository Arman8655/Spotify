package spotify.app;

public class PremiumBehavior implements UserBehavior {
    private int month;


    public PremiumBehavior(int month) {
        if (month <= 0) {
            throw new InvalidOperationException("month should be positive.");
        }

        this.month = month;
    }

    @Override
    public void createPlaylist(String title, User owner) {

        Playlist newPlaylist = new Playlist(title, owner);
        owner.getPlaylists().add(newPlaylist);


    }

    @Override
    public void playMusic(Music music) {

        music.play();

    }

    @Override
    public void buyPremium(User owner, int month) {

        if (month <= 0) {
            throw new InvalidOperationException("month should be positive.");
        }

        ((PremiumBehavior)owner.getBehavior()).month += month;

    }
}
