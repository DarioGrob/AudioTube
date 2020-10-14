package com.modul152.projekt.views.playlist;

import com.modul152.projekt.backend.service.ConvertMp4ToMp3;
import com.modul152.projekt.backend.service.DownloadYoutube;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PlaylistPresenter {

    private final DownloadYoutube downloadYoutube;
    private final ConvertMp4ToMp3 converter;

    @Autowired
    public PlaylistPresenter(DownloadYoutube downloadYoutube, ConvertMp4ToMp3 converter) {
        this.downloadYoutube = downloadYoutube;
        this.converter = converter;
    }
}
