package spotify.app;

import java.util.ArrayList;

public class User {
    private String username;
    private String password;
    private ArrayList<User> followerList;
    private ArrayList<User> followingList;
    private UserBehavior behavior;
    private ArrayList<Playlist> playlists;
    private static ArrayList<User> allUsers = new ArrayList<>() ;

    public User(String username, String password) {
        validateUsername(username);
        validatePassword(password);

        this.username = username;
        this.password = password;

        followerList = new ArrayList<>();
        followingList = new ArrayList<>();
        playlists = new ArrayList<>();

        this.behavior = new RegularBehavior();

        allUsers.add(this);
    }

    private void validateUsername(String username) {
        for (User user : allUsers) {
            if (user.getUsername().equals(username)) {
                throw new InvalidOperationException("Oops, username already exists!");
            }
        }
    }


    private void validatePassword(String password) {
        if (password.length() < 8) {
            throw new InvalidOperationException("Password should be at least 8 characters!");
        }
    }


    public void follow(User user) {
        if (user == this) {
            throw new InvalidOperationException(" You cannot follow yourself!");
        }
        if (followingList.contains(user)) {
            throw new InvalidOperationException("You are already following this user!");
        }
        followingList.add(user);
        user.followerList.add(this);
    }



    public void createPlaylist (String title){
        this.behavior.createPlaylist(title, this);
    }

    public void playMusic(Music music) {
        this.behavior.playMusic(music);
    }

    public void buyPremium(int month) {
        this.behavior.buyPremium(this, month);
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public UserBehavior getBehavior() {
        return behavior;
    }

    public ArrayList<User> getFollowers() {
        return followerList;
    }

    public ArrayList<User> getFollowing() {
        return followingList;
    }

    public ArrayList<Playlist> getPlaylists() {
        return playlists;
    }

    public static ArrayList<User> getAllUsers() {
        return allUsers;
    }

    public void setBehavior(UserBehavior behavior) {
        this.behavior = behavior;
    }
}
