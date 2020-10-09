package com.modul152.projekt.backend.service;

import com.github.axet.vget.VGet;
import org.springframework.stereotype.Service;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

@Service
public class DownloadYoutube {

    public void download(String url) {
        try {
            String path = "/Video/";
            VGet v = new VGet(new URL(url), new File(path));
            v.download();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }

}
