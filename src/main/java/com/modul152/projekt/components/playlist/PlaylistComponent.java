package com.modul152.projekt.components.playlist;

import com.modul152.projekt.model.Playlist;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.polymertemplate.EventHandler;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.templatemodel.TemplateModel;

@Tag("playlist-component")
@JsModule("./src/components/playlist/playlist-component.js")
public class PlaylistComponent extends PolymerTemplate<PlaylistComponent.Model> {

    private Playlist playlist;

    public PlaylistComponent(Playlist playlist) {
        this.playlist = playlist;
        setPlaylist(playlist);
    }

    public void setPlaylist(Playlist playlist) {
        getModel().setPlaylist(playlist);
    }

    @EventHandler
    private void editPlaylist() {
        EditDialog editDialog = new EditDialog(playlist, this);
    }

    public interface Model extends TemplateModel {
        void setPlaylist(Playlist playlist);
    }
}
