package com.modul152.projekt.views.playlist;

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

@Tag("playlist-view")
@JsModule("./src/views/playlist/playlist-view.js")
@Route(value = "playlist", layout = MainView.class)
@RouteAlias(value = "", layout = MainView.class)
public class PlaylistView extends PolymerTemplate<PlaylistView.Model> {

    @Id("content")
    private Div content;

    private PlaylistPresenter presenter;

    @Autowired
    public PlaylistView(PlaylistPresenter presenter) {
        this.presenter = presenter;
        setPageTitle();
    }

    public void setPageTitle() {
        getModel().setTitle("Playlists");
    }

    public interface Model extends TemplateModel {
        void setTitle(String title);
    }
}
