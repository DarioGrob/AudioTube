package com.modul152.projekt.components.playlist;

import com.modul152.projekt.model.Playlist;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.templatemodel.TemplateModel;

import java.util.List;

@Tag("playlist-component")
@JsModule("./src/components/playlist/playlist-component.js")
public class PlaylistComponent extends PolymerTemplate<PlaylistComponent.Model> {

    public interface Model extends TemplateModel {

    }
}
