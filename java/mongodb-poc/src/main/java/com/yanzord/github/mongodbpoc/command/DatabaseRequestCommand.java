//package com.yanzord.github.mongodbpoc.command;
//
//import com.netflix.hystrix.HystrixObservableCommand;
//import com.yanzord.github.mongodbpoc.model.Song;
//import com.yanzord.github.mongodbpoc.service.SongService;
//import rx.Observable;
//
//import java.util.List;
//
//public class DatabaseRequestCommand extends HystrixObservableCommand<List<Song>> {
//
//    private final SongService songService;
//    private final String string;
//
//    protected DatabaseRequestCommand(SongService songService, String string) {
//        super(group);
//        this.songService = songService;
//        this.string = string;
//    }
//
//    @Override
//    protected Observable<List<Song>> construct() {
//        return null;
//    }
//}
