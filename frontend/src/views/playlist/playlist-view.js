import {html, PolymerElement} from "@polymer/polymer/polymer-element";

class PlaylistView extends PolymerElement {
    // language=HTML
    static get template() {
        return html`
            <style>
                #content {
                    margin-left: 20px;
                    margin-top: 20px;
                }
                
                #content h1 {
                    text-decoration: underline;
                }
            </style>
            
            <div id="content">
                <h1>[[title]]</h1>
            </div>
        `;
    }

    static get is() {
        return 'playlist-view';
    }

}

customElements.define(PlaylistView.is, PlaylistView);