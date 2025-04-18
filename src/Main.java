import java.util.List;
import spotify.app.InvalidOperationException;
import spotify.app.Music;
import spotify.app.Playlist;
import spotify.app.User;

public class Main {
    public static void main(String[] args) {

        User user1 = new User("user1", "password123");
        User user2 = new User("user2", "password456");
        User singer1 = new User("Adele", "adele123");
        User singer2 = new User("EdSheeran", "edsheeran");


        System.out.println("\nTesting follow system");
        testFollowSystem(user1, user2, singer1);


        Music song1 = new Music("Hello", singer1);
        Music song2 = new Music("Azizam", singer2);
        Music song3 = new Music("Hello", singer2);

        System.out.println("\nTesting Music.play()");
        song1.play();
        song1.play();


        System.out.println("\nTesting Music.search()");
        testMusicSearch("Hello", singer1);


        System.out.println("\nTesting RegularUser");
        testRegularUser(user1, song1);


        System.out.println("\nTesting Premium upgrade");
        user1.buyPremium(3);
        testPremiumUser(user1, song2);


        System.out.println("\nTesting Playlist");
        testPlaylist(user1, song1, song2, song3);

    }

    private static void testFollowSystem(User follower, User userToFollow, User singer) {
        try {

            follower.follow(userToFollow);
            System.out.println(follower.getUsername() + " successfully followed " + userToFollow.getUsername());


            follower.follow(follower);
        }
        catch (InvalidOperationException e) {
            System.out.println("Expected error when following self: " + e.getMessage());
        }

        try {

            follower.follow(userToFollow);
        }
        catch (InvalidOperationException e) {
            System.out.println("Expected error when duplicate follow: " + e.getMessage());
        }


        follower.follow(singer);
        System.out.println(follower.getUsername() + " successfully followed singer " + singer.getUsername());


        System.out.println("\nFollow relationships:");
        System.out.println(follower.getUsername() + " is following:");
        for (User followed : follower.getFollowing()) {
            System.out.println("- " + followed.getUsername());
        }

        System.out.println("\n" + userToFollow.getUsername() + " is followed by:");
        for (User followerUser : userToFollow.getFollowers()) {
            System.out.println("- " + followerUser.getUsername());
        }
    }

    private static void testMusicSearch(String title, User singer) {
        List<Music> results = Music.search(title);
        if (results.isEmpty()) {
            System.out.println("No songs found with title '" + title + "'");
        }
        else
        {
            System.out.println("Found " + results.size() + " songs with title '" + title + "'");
        }

        Music exactResult = Music.search(title, singer);
        if (exactResult != null) {
            System.out.println("Exact search successful for '" + title + "' by " + singer.getUsername());
        }
        else {
            System.out.println("Exact search failed for '" + title + "' by " + singer.getUsername());
        }
    }

    private static void testRegularUser(User user, Music music) {
        try {
            System.out.println("Attempting to create playlist");
            user.createPlaylist("Favorites");
        } catch (InvalidOperationException e) {
            System.out.println("Expected error: " + e.getMessage());
        }

        System.out.println("Playing song (5 allowed)");
        for (int i = 0; i < 6; i++) {
            try {
                user.playMusic(music);
                System.out.println((i+1) + ". Play successful");
            } catch (InvalidOperationException e) {
                System.out.println("Expected play limit error at " + (i+1) + ": " + e.getMessage());
            }
        }
    }

    private static void testPremiumUser(User user, Music music) {
        try {
            user.createPlaylist("Favorites");
            System.out.println("Playlist creation with premium successful");
            System.out.println("Unlimited plays");
            for (int i = 0; i < 3; i++) {
                user.playMusic(music);
                System.out.println((i+1) + ". Play successful");
            }
        } catch (InvalidOperationException e) {
            System.out.println("Unexpected error: " + e.getMessage());
        }
    }

    private static void testPlaylist(User user, Music... songs) {
        try {
            Playlist playlist = new Playlist("My Playlist", user);

            System.out.println("Adding songs");
            for (int i = 0; i < songs.length; i++) {
                try {
                    playlist.addMusic(songs[i], user.getPassword());
                    System.out.println((i+1) + ". Added: " + songs[i].getTitle());
                }
                catch (InvalidOperationException e) {
                    System.out.println((i+1) + ". Error adding: " + e.getMessage());
                }
            }

            System.out.println("\nSequential play");
            playlist.playPlaylist();

        }
        catch (InvalidOperationException e) {
            System.out.println("Playlist creation error: " + e.getMessage());
        }
    }



    }
