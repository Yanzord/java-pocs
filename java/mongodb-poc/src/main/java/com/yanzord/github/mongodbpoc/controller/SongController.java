package com.yanzord.github.mongodbpoc.controller;

import com.yanzord.github.mongodbpoc.model.Song;
import com.yanzord.github.mongodbpoc.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/song")
public class SongController {

    @Autowired
    private SongService songService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    private List<Song> findAll() {
        return songService.getAllSongs();
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    private Song save(@RequestBody Song song) {
        return songService.addNewSong(song);
    }

    @RequestMapping(value = "/songTitle/{songTitle}", method = RequestMethod.GET)
    public List<Song> findBySongTitle(@PathVariable("songTitle") String songTitle) {
        return songService.getSongByTitle(songTitle);
    }

    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
    private Song findSongById(@PathVariable("id") String id) {
        return songService.getSongById(id);
    }
}
