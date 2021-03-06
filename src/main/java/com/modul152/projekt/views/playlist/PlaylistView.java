package com.modul152.projekt.views.playlist;

import com.modul152.projekt.components.playlist.CreateDialog;
import com.modul152.projekt.components.playlist.DownloadDialog;
import com.modul152.projekt.components.playlist.PlaylistComponent;
import com.modul152.projekt.model.Playlist;
import com.modul152.projekt.model.Song;
import com.modul152.projekt.views.main.MainView;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.router.HasDynamicTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.templatemodel.TemplateModel;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Tag("playlist-view")
@JsModule("./src/views/playlist/playlist-view.js")
@Route(value = "playlist", layout = MainView.class)
@RouteAlias(value = "", layout = MainView.class)
public class PlaylistView extends PolymerTemplate<PlaylistView.Model> {

    List<Playlist> playlists = new ArrayList<>();

    @Id("grid-container")
    private HorizontalLayout content;

    @Id("createPlaylist")
    private Button button;

    @Id("downloadButton")
    private Button downloadButton;

    private PlaylistPresenter presenter;

    @Autowired
    public PlaylistView(PlaylistPresenter presenter) {
        this.presenter = presenter;
        initData();

        button.addClickListener(event -> {
            CreateDialog createDialog = new CreateDialog(presenter);
        });

        downloadButton.addClickListener(event -> {
            DownloadDialog downloadDialog = new DownloadDialog(presenter);
        });
    }

    private void initData() {
        List<Song> songs = new ArrayList<>();
        songs.add(new Song("song1", "songs/real.mp3"));
        songs.add(new Song("song2", "songs/testAudio.mp3"));
        songs.add(new Song("song3", "songs/test.mp3"));
        songs.add(new Song("song4", "songs/real.mp3"));
        songs.add(new Song("song5", "songs/testAudio.mp3"));

        Playlist p1 = new Playlist("name1", songs);
        Playlist p2 = new Playlist("name2", songs);
        Playlist p3 = new Playlist("name3", songs);
        Playlist p4 = new Playlist("name4", songs);
        Playlist p5 = new Playlist("name5", songs);
        Playlist p6 = new Playlist("name5", songs);
        Playlist p7 = new Playlist("name5", songs);
        Playlist p8 = new Playlist("name5", songs);

        playlists.add(p1);
        playlists.add(p2);
        playlists.add(p3);
        playlists.add(p4);
        playlists.add(p5);
        playlists.add(p6);
        playlists.add(p7);
        playlists.add(p8);

        for (Playlist playlist: playlists) {
            PlaylistComponent playlistComponent = new PlaylistComponent(playlist, presenter);
            content.add(playlistComponent);
        }
    }

    public interface Model extends TemplateModel {

    }
}
