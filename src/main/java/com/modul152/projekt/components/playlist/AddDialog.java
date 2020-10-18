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
import com.vaadin.flow.component.grid.HeaderRow;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.value.ValueChangeMode;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@CssImport("./styles/editPlaylistDialogStyle.css")
@CssImport(value = "./styles/dialogOverlayStyle.css", themeFor = "vaadin-dialog-overlay")
@Tag("add-dialog")
@JsModule("./src/components/playlist/add-dialog.js")
public class AddDialog {

    private PlaylistPresenter presenter;
    private PlaylistComponent playlistComponent;
    private EditDialog editDialog;
    private Playlist playlist;
    private Dialog dialog;
    private Grid<Song> grid;
    private HeaderRow filterRow;
    private List<Song> selectedSongs = new ArrayList<>();

    public AddDialog(PlaylistPresenter presenter, PlaylistComponent playlistComponent, Playlist playlist, EditDialog editDialog) {
        this.presenter = presenter;
        this.playlistComponent = playlistComponent;
        this.editDialog = editDialog;
        this.playlist = playlist;
        createGrid();
        createDialog();
    }

    private void createDialog() {
        dialog = new Dialog();

        dialog.add(grid);

        Button addButton = new Button("Add");
        addButton.setClassName("addButton");
        addButton.addClickListener(event -> {
            if (selectedSongs != null && !selectedSongs.isEmpty()) {
                editDialog.updateSongList(selectedSongs);
                playlistComponent.setPlaylist(playlist);
                dialog.close();
            }
        });
        dialog.add(addButton);

        dialog.setWidth("670px");
        dialog.setHeight("410px");

        dialog.setCloseOnEsc(true);
        dialog.setCloseOnOutsideClick(true);

        dialog.open();
    }

    private void createGrid() {
        grid = new Grid<>();
        grid.setItems(getSongs(""));
        Grid.Column<Song> songColumn = grid.addColumn(Song::getName).setHeader("Songname").setSortable(true);
        filterRow = grid.appendHeaderRow();
        initTextFieldFilter(songColumn);
        grid.setHeight("300px");

        grid.setSelectionMode(Grid.SelectionMode.MULTI);
        grid.asMultiSelect().addValueChangeListener(event -> {
            selectedSongs.clear();
            selectedSongs.addAll(event.getValue());
        });
    }

    private void initTextFieldFilter(Grid.Column<?> column) {
        TextField textField = new TextField();
        textField.setClearButtonVisible(true);
        textField.addValueChangeListener(event -> {
            ListDataProvider<Song> dataProvider = (ListDataProvider) grid.getDataProvider();
            dataProvider.getItems().clear();
            dataProvider.getItems().addAll(getSongs(textField.getValue()));
            dataProvider.refreshAll();
        });
        textField.setValueChangeMode(ValueChangeMode.LAZY);
        filterRow.getCell(column).setComponent(textField);
    }

    private List<Song> getSongs(String filter) {
        List<Song> testsongs = new ArrayList<>();
        testsongs.add(new Song("song20", "songs/real.mp3"));
        testsongs.add(new Song("song21", "songs/testAudio.mp3"));
        testsongs.add(new Song("song22", "songs/real.mp3"));
        testsongs.add(new Song("song23", "songs/testAudio.mp3"));
        testsongs.add(new Song("song24", "songs/real.mp3"));
        testsongs.add(new Song("song25", "songs/testAudio.mp3"));
        testsongs.add(new Song("song26", "songs/real.mp3"));
        testsongs.add(new Song("song27", "songs/testAudio.mp3"));
        testsongs.add(new Song("song28", "songs/real.mp3"));
        testsongs.add(new Song("song29", "songs/testAudio.mp3"));
        testsongs.add(new Song("song30", "songs/real.mp3"));
        testsongs.add(new Song("song31", "songs/testAudio.mp3"));
        testsongs.add(new Song("song32", "songs/real.mp3"));
        testsongs.add(new Song("song33", "songs/testAudio.mp3"));
        testsongs.add(new Song("song34", "songs/real.mp3"));

        testsongs.add(new Song("song35", "songs/real.mp3"));
        testsongs.add(new Song("song36", "songs/testAudio.mp3"));
        testsongs.add(new Song("song37", "songs/real.mp3"));
        testsongs.add(new Song("song38", "songs/testAudio.mp3"));
        testsongs.add(new Song("song39", "songs/real.mp3"));
        testsongs.add(new Song("song40", "songs/testAudio.mp3"));
        testsongs.add(new Song("song41", "songs/real.mp3"));
        testsongs.add(new Song("song42", "songs/testAudio.mp3"));
        testsongs.add(new Song("song43", "songs/real.mp3"));
        testsongs.add(new Song("song44", "songs/testAudio.mp3"));
        testsongs.add(new Song("song45", "songs/real.mp3"));
        testsongs.add(new Song("song46", "songs/testAudio.mp3"));
        testsongs.add(new Song("song47", "songs/real.mp3"));
        testsongs.add(new Song("song48", "songs/testAudio.mp3"));
        testsongs.add(new Song("song49", "songs/real.mp3"));

        if (filter.isEmpty()){
            return testsongs;
        } else {
            return testsongs.stream().filter(song -> song.getName().equals(filter)).collect(Collectors.toList());
        }
    }
}
