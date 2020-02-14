package com.yanzord.github.mongodbpoc.service;

import com.yanzord.github.mongodbpoc.hystrix.AddNewSongCommand;
import com.yanzord.github.mongodbpoc.hystrix.GetAllCommand;
import com.yanzord.github.mongodbpoc.hystrix.GetSongByIdCommand;
import com.yanzord.github.mongodbpoc.hystrix.GetSongByTitleCommand;
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
        return new GetAllCommand(songDALImpl).observe().toBlocking().first();
    }

    public Song addNewSong(Song song) {
        return new AddNewSongCommand(songDALImpl, song).observe().toBlocking().first();
    }

    public List<Song> getSongByTitle(String songTitle) {
        return new GetSongByTitleCommand(songDALImpl, songTitle).observe().toBlocking().first();
    }

    public Song getSongById(String id) {
        return new GetSongByIdCommand(songDALImpl, id).observe().toBlocking().first();
    }
}
