package spotify.app;

public class RegularBehavior implements UserBehavior{
    private int playingLimit = 5;

    @Override
    public void createPlaylist(String title, User Owner) {
        throw new InvalidOperationException("Regular users cannot create playlists!");
    }

    @Override
    public void playMusic(Music music) {
        if (playingLimit == 0) {
            throw new InvalidOperationException("Playing limit reached! Buy premium for unlimited plays.");
        }
        music.play();
        playingLimit--;

    }

    @Override
    public void buyPremium(User owner, int month) {
        owner.setBehavior( new PremiumBehavior(month));

    }
}
