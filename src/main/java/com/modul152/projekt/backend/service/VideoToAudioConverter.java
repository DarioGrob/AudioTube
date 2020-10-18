package com.modul152.projekt.backend.service;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class VideoToAudioConverter {

    public int convert(String youtubeId) {
        String programmFolder = System.getenv("AudioTube_KonProgramm");
        String projectFolder = System.getenv("AudioTube_Projekt");

        String videoFileName = "";
        Path dir = Paths.get(projectFolder + "\\youtubeVideo");
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir, "*" + youtubeId + "*")) {
            for (Path entry: stream) {
                videoFileName = entry.getFileName().toString();
            }
        }catch (IOException e) {
            e.printStackTrace();
        }

        try {
            String videoFile = projectFolder + "\\youtubeVideo\\" + videoFileName;
            String[] splitVideoFileName = videoFileName.split("\\.");
            String newAudioFile = projectFolder + "\\youtubeAudio\\" + splitVideoFileName[0] + ".mp3";

            Runtime runtime = Runtime.getRuntime();
            String[] command = new String[]{
                    programmFolder + "\\ffmpeg.exe",
                    "-i",
                    videoFile,
                    newAudioFile,
            };
            String line;
            Process process = runtime.exec(command, new String[]{}, new File(projectFolder + "\\youtubeAudio"));
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
