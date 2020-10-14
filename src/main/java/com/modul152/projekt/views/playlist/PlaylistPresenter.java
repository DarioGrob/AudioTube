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
}
