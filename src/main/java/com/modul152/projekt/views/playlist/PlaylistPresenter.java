package com.modul152.projekt.views.playlist;

import com.modul152.projekt.backend.service.VideoToAudioConverter;
import com.modul152.projekt.backend.service.DownloadYoutube;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PlaylistPresenter {

    private final DownloadYoutube downloadYoutube;
    private final VideoToAudioConverter converter;

    @Autowired
    public PlaylistPresenter(DownloadYoutube downloadYoutube, VideoToAudioConverter converter) {
        this.downloadYoutube = downloadYoutube;
        this.converter = converter;
    }

    public boolean downloadSong(String url) {
        int exitCode = 0;
        exitCode = downloadYoutube.download(url);

        if (exitCode == 0) {
            String[] splitUrl = url.split("v=");
            String videoId = splitUrl[splitUrl.length -1];
            exitCode = converter.convert(videoId);
            if (exitCode == 0) {
                return true;
            }
        }
        return false;
    }
}
