package com.modul152.projekt.backend.service;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

@Service
public class DownloadYoutube {

    public int download(String url) {
        String programmFolder = System.getenv("AudioTube_KonProgramm");
        String projectFolder = System.getenv("AudioTube_Projekt");

        String[] splitUrl = url.split("v=");
        String identifier = splitUrl[splitUrl.length -1];
        String fileName = "";

        try {
            Runtime runTime = Runtime.getRuntime();
            String[] command = new String[]{
                    programmFolder + "\\youtube-dl.exe",
                    "-o",
                    projectFolder + "\\youtubeVideo\\%(title)s-%(id)s.%(ext)s",
                    url,
            };
            String line;
            Process process = runTime.exec(command, new String[]{}, new File(projectFolder + "\\youtubeVideo"));
            BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));
            while ((line = input.readLine()) != null) {
                System.out.println(line);
            }
            input.close();
            process.waitFor();
            System.out.println(process.exitValue());
            return process.exitValue();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return 1;
        }
    }
}
