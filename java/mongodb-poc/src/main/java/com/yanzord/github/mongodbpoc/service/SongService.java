package com.yanzord.github.mongodbpoc.service;

import com.yanzord.github.mongodbpoc.model.Song;
import com.yanzord.github.mongodbpoc.repository.SongDALImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongService {

    @Autowired
    private SongDALImpl songDALImpl;

    public List<Song> getAllSongs() {
        return songDALImpl.getAllSongs();
    }

    public Song addNewSong(Song song) {
        return songDALImpl.addNewSong(song);
    }

    public List<Song> getSongByTitle(String songTitle) {
        return songDALImpl.getSongByTitle(songTitle);
    }

    public Song getSongById(String id) {
        return songDALImpl.getSongById(id);
    }
}
