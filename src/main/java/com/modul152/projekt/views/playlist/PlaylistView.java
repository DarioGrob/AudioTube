package com.modul152.projekt.views.playlist;

import com.modul152.projekt.components.playlist.PlaylistComponent;
import com.modul152.projekt.model.Playlist;
import com.modul152.projekt.model.Song;
import com.modul152.projekt.views.main.MainView;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.router.HasDynamicTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.templatemodel.TemplateModel;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Tag("playlist-view")
@JsModule("./src/views/playlist/playlist-view.js")
@Route(value = "playlist", layout = MainView.class)
@RouteAlias(value = "", layout = MainView.class)
public class PlaylistView extends PolymerTemplate<PlaylistView.Model> {

    List<Playlist> playlists = new ArrayList<>();

    @Id("content")
    private Div content;

    private PlaylistPresenter presenter;

    @Autowired
    public PlaylistView(PlaylistPresenter presenter) {
        this.presenter = presenter;
        initData();
        getModel().setPlaylists(playlists);
    }

    private void initData() {
        List<Song> songs = new ArrayList<>();
        songs.add(new Song("song1", "C:\\Users\\Dario Grob\\Desktop\\test.mp3"));
        songs.add(new Song("song2", "C:\\Users\\Dario Grob\\Desktop\\test.mp3"));
        songs.add(new Song("song3", "C:\\Users\\Dario Grob\\Desktop\\test.mp3"));
        songs.add(new Song("song4", "C:\\Users\\Dario Grob\\Desktop\\test.mp3"));
        songs.add(new Song("song5", "C:\\Users\\Dario Grob\\Desktop\\test.mp3"));

        Playlist p1 = new Playlist("name1", songs, "vaadin:play-circle-o", songs.get(0));
        Playlist p2 = new Playlist("name2", songs, "vaadin:play-circle-o", songs.get(0));
        Playlist p3 = new Playlist("name3", songs, "vaadin:play-circle-o", songs.get(0));
        Playlist p4 = new Playlist("name4", songs, "vaadin:play-circle-o", songs.get(0));
        Playlist p5 = new Playlist("name5", songs, "vaadin:play-circle-o", songs.get(0));
        Playlist p6 = new Playlist("name5", songs, "vaadin:play-circle-o", songs.get(0));
        Playlist p7 = new Playlist("name5", songs, "vaadin:play-circle-o", songs.get(0));
        Playlist p8 = new Playlist("name5", songs, "vaadin:play-circle-o", songs.get(0));

        playlists.add(p1);
        playlists.add(p2);
        playlists.add(p3);
        playlists.add(p4);
        playlists.add(p5);
        playlists.add(p6);
        playlists.add(p7);
        playlists.add(p8);
    }

    public interface Model extends TemplateModel {
        void setPlaylists(List<Playlist> playlists);
    }
}
