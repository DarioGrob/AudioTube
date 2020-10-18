package com.modul152.projekt.components.playlist;

import com.modul152.projekt.views.playlist.PlaylistPresenter;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.templatemodel.TemplateModel;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@CssImport(value = "./styles/editPlaylistDialogStyle.css")
@CssImport(value = "./styles/dialogOverlayStyle.css", themeFor = "vaadin-dialog-overlay")
@Tag("download-dialog")
@JsModule("./src/components/playlist/download-dialog.js")
public class DownloadDialog extends PolymerTemplate<DownloadDialog.Model> {

    private PlaylistPresenter presenter;
    private Dialog dialog;

    public DownloadDialog(PlaylistPresenter presenter) {
        this.presenter = presenter;
        createDialog();
    }

    private void createDialog() {
        dialog = new Dialog();

        TextField youtubeUrl = new TextField();
        youtubeUrl.setLabel("Youtube url");
        youtubeUrl.setClearButtonVisible(true);
        youtubeUrl.setClassName("youtubeUrl");
        dialog.add(youtubeUrl);

        Button downloadButton = new Button("Download");
        downloadButton.setClassName("addButton");
        downloadButton.addClickListener(event -> {
            if (!youtubeUrl.getValue().isEmpty()) {
                Pattern pattern = Pattern.compile("http(?:s?):\\/\\/(?:www\\.)?youtu(?:be\\.com\\/watch\\?v=|\\.be\\/)([\\w\\-\\_]*)(&(amp;)?\u200C\u200B[\\w\\?\u200C\u200B=]*)?");
                Matcher matcher = pattern.matcher(youtubeUrl.getValue());
                if (matcher.matches()) {
                    if (presenter.downloadSong(youtubeUrl.getValue())) {
                        Notification notification = new Notification(
                                "Download abgeschlossen", 3000);
                        notification.open();
                    } else {
                        Notification notification = new Notification(
                                "Download fehlgeschlagen", 3000);
                        notification.open();
                    }
                } else {
                    Notification notification = new Notification(
                            "Youtube url falsch", 3000);
                    notification.open();
                }
            } else {
                Notification notification = new Notification(
                        "Bitte Url eingeben", 3000);
                notification.open();
            }
        });
        dialog.add(downloadButton);

        dialog.setCloseOnOutsideClick(true);
        dialog.setCloseOnEsc(true);

        dialog.setHeight("200px");
        dialog.setWidth("400px");

        dialog.open();
    }

    public interface Model extends TemplateModel {

    }
}
