package com.yanzord.github.mongodbpoc.repository;

import com.yanzord.github.mongodbpoc.model.Song;

import java.util.List;

public interface SongDAL {

    List<Song> getAllSongs();

    Song getSongById(String id);

    Song addNewSong(Song song);

    List<Song> getSongByTitle(String songTitle);

}
