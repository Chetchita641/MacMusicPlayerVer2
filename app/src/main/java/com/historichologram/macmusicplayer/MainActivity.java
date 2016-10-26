package com.historichologram.macmusicplayer;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int albumNum = 0;
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Intents for the Nav bar
        TextView albumsNavButton = (TextView) findViewById(R.id.albums_nav_button);
        albumsNavButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, AlbumsActivity.class);
                startActivity(i);
            }
        });

        TextView artistsNavButton = (TextView) findViewById(R.id.artists_nav_button);
        artistsNavButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ArtistsActivity.class);
                startActivity(i);
            }
        });

        TextView playlistsNavButton = (TextView) findViewById(R.id.playlists_nav_button);
        playlistsNavButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, PlaylistActivity.class);
                startActivity(i);
            }
        });

        TextView songsNavButton = (TextView) findViewById(R.id.songs_nav_button);
        songsNavButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, SongsActivity.class);
                startActivity(i);
            }
        });

        //Fake control button outputs. Back and forward change the album covers. Play and pause bring up toast messages.
        ImageView playButton = (ImageView) findViewById(R.id.play_button);
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Playing Music", Toast.LENGTH_SHORT).show();
                switch (albumNum) {
                    case 0:
                        mediaPlayer = mediaPlayer.create(getApplicationContext(), R.raw.song_megalopolis);
                        mediaPlayer.start();
                        break;
                    case 1:
                        mediaPlayer = mediaPlayer.create(getApplicationContext(), R.raw.song_cream_on_chrome);
                        mediaPlayer.start();
                        break;
                    case 2:
                        mediaPlayer = mediaPlayer.create(getApplicationContext(), R.raw.song_for_reasons_unknown);
                        mediaPlayer.start();
                        break;
                }

            }
        });

        ImageView pauseButton = (ImageView) findViewById(R.id.pause_button);
        pauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Pausing Music", Toast.LENGTH_SHORT).show();
                mediaPlayer.pause();
            }
        });

        ImageView backButton = (ImageView) findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backAlbumArt();
            }
        });

        ImageView forwardButton = (ImageView) findViewById(R.id.forward_button);
        forwardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                forwardAlbumArt();
            }
        });
    }

    public void backAlbumArt() {
        albumNum = albumNum - 1;
        //If at the beginning of the sequence, go to the last
        if (albumNum == -1)
            albumNum = 2;

        changeAlbumArt(albumNum);
    }


    public void forwardAlbumArt() {
        albumNum = albumNum + 1;
        //If at the end of the sequence, go to the first
        if (albumNum == 3)
            albumNum = 0;

        changeAlbumArt(albumNum);
    }

    public void changeAlbumArt(int albumNum) {
        // 0 = Nine Alive
        // 1 = Magnifique
        // 2 = Sam's Town
        ImageView albumArt = (ImageView) findViewById(R.id.album_art);
        if (albumNum == 0)
            albumArt.setImageResource(R.drawable.nine_dead_alive_cover);
        if (albumNum == 1)
            albumArt.setImageResource(R.drawable.magnifique_cover);
        if (albumNum == 2)
            albumArt.setImageResource(R.drawable.sams_town_cover);
    }
}


