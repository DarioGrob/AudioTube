package com.modul152.projekt.views.song;

import com.modul152.projekt.views.main.MainView;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.templatemodel.TemplateModel;
import org.springframework.beans.factory.annotation.Autowired;

@Tag("song-view")
@JsModule("./src/views/song/song-view.js")
@Route(value = "song", layout = MainView.class)
public class SongView extends PolymerTemplate<SongView.Model> {

    @Id("content")
    private Div content;

    private SongPresenter presenter;

    @Autowired
    public SongView(SongPresenter presenter) {
        this.presenter = presenter;
        setPageTitle();
    }

    public void setPageTitle() {
        getModel().setTitle("Songs");
    }

    public interface Model extends TemplateModel {
        void setTitle(String title);
    }
}
