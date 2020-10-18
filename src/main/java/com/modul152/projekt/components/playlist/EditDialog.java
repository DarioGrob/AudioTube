package com.modul152.projekt.components.playlist;

import com.modul152.projekt.model.Playlist;
import com.modul152.projekt.model.Song;
import com.modul152.projekt.views.playlist.PlaylistPresenter;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.templatemodel.TemplateModel;

import java.util.ArrayList;
import java.util.List;

@CssImport("./styles/editPlaylistDialogStyle.css")
@CssImport(value = "./styles/dialogOverlayStyle.css", themeFor = "vaadin-dialog-overlay")
@Tag("edit-dialog")
@JsModule("./src/components/playlist/edit-dialog.js")
public class EditDialog extends PolymerTemplate<EditDialog.Model> {

    private PlaylistPresenter presenter;
    private Playlist playlist;
    private Grid<Song> grid;
    private Dialog dialog;
    private PlaylistComponent playlistComponent;
    private List<Song> selectedSongs = new ArrayList<>();

    public EditDialog(Playlist playlist, PlaylistComponent playlistComponent, PlaylistPresenter presenter) {
        this.presenter = presenter;
        this.playlist = playlist;
        this.playlistComponent = playlistComponent;
        generateGrid();
        createDialog();
    }

    private void createDialog() {
        dialog = new Dialog();

        H1 playlistName = new H1(playlist.getName());
        playlistName.setClassName("playlistName");
        dialog.add(playlistName);

        dialog.add(grid);

        Button addDialogButton = new Button("add Songs");
        addDialogButton.setClassName("addButton");
        addDialogButton.addClickListener(event -> {
            AddDialog addDialog = new AddDialog(presenter, playlistComponent, playlist, this);
        });
        dialog.add(addDialogButton);

        Button deleteButton = new Button("Delete");
        deleteButton.setClassName("deleteButton");
        deleteButton.addClickListener(event -> {
            if (selectedSongs != null && !selectedSongs.isEmpty()) {
                ListDataProvider<Song> dataProvider = (ListDataProvider<Song>) grid.getDataProvider();
                dataProvider.getItems().removeAll(selectedSongs);
                dataProvider.refreshAll();
                playlist.getSongs().removeAll(selectedSongs);
                playlistComponent.setPlaylist(playlist);
            }
        });
        dialog.add(deleteButton);

        dialog.setWidth("670px");
        dialog.setHeight("560px");

        dialog.setCloseOnOutsideClick(true);
        dialog.setCloseOnEsc(true);

        dialog.open();
    }

    private void generateGrid() {
        grid = new Grid<>();
        grid.setItems(playlist.getSongs());

        grid.addColumn(Song::getName).setHeader("Songname").setWidth("100px").setSortable(true);

        grid.setHeight("300px");

        grid.setSelectionMode(Grid.SelectionMode.MULTI);
        grid.asMultiSelect().addValueChangeListener(event -> {
            selectedSongs.clear();
            selectedSongs.addAll(event.getValue());
        });

    }

    public void updateSongList(List<Song> selectedSongs) {
        ListDataProvider<Song> dataProvider = (ListDataProvider<Song>) grid.getDataProvider();
        dataProvider.getItems().addAll(selectedSongs);
        dataProvider.refreshAll();
    }

    public interface Model extends TemplateModel {

    }

}
