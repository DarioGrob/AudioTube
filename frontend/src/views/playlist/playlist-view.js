import {html, PolymerElement} from "@polymer/polymer/polymer-element";

import '@vaadin/vaadin-button/vaadin-button.js';
import '@vaadin/vaadin-text-field/vaadin-text-field.js';

import '../../../styles/shared-styles.js';

class PlaylistView extends PolymerElement {
    // language=HTML
    static get template() {
        return html`
            <style>
                :host {
                    display: flex;
                    flex-direction: column;
                    width: 100%;
                    margin-left: auto;
                    margin-right: auto;
                    max-width: 1500px;
                }

                #content {
                    background: var(--lumo-base-color);
                    box-shadow: var(--lumo-box-shadow-m);
                    padding: var(--lumo-space-m) var(--lumo-space-m) var(--lumo-space-l) var(--lumo-space-m);
                    margin: var(--lumo-space-m);
                }

                #grid-container {
                    display: grid;
                    grid-template-columns: 250px 250px 250px 250px;
                    grid-column-gap: 30px;
                    grid-row-gap: 30px;
                    margin-top: 20px;
                    margin-left: auto;
                    margin-right: auto;
                    width: 1170px;
                }

                @media only screen and (max-width: 1200px) {
                    #grid-container {
                        grid-template-columns: 250px 250px 250px;
                    }
                }

                @media only screen and (max-width: 930px) {
                    #grid-container {
                        grid-template-columns: 250px 250px;
                    }
                }

                @media only screen and (max-width: 650px) {
                    #grid-container {
                        grid-template-columns: 250px;
                    }
                }

                .hr-text {
                    line-height: 1em;
                    position: relative;
                    outline: 0;
                    border: 0;
                    color: black;
                    text-align: center;
                    height: 1.5em;
                    opacity: .5;
                    margin-top: 40px;
                }

                .hr-text::before {
                    content: '';
                    background: linear-gradient(to right, transparent, #001233, transparent);
                    position: absolute;
                    left: 0;
                    top: 50%;
                    width: 100%;
                    height: 1px;
                }

                .hr-text::after {
                    content: attr(data-content);
                    position: relative;
                    display: inline-block;
                    color: black;
                    padding: 0 .5em;
                    line-height: 1.5em;
                    color: #001233;
                    background-color: white;
                }

                #grid-container::after, #grid-container::before {
                    display: none;
                    !important;
                }
                
                #createPlaylist {
                    
                }
                
                #downloadButton {
                    
                }
                
                #playlistSearch {
                    margin-top: 30px;
                }
            </style>
            
            <div id="content">
                <h1>Playlists</h1>
                <vaadin-button id="createPlaylist">+ Create Playlist</vaadin-button>
                <vaadin-button id="downloadButton">Download Song</vaadin-button>
                <br>
                <vaadin-text-field placeholder="search Playlistname ..." clear-button-visible id="playlistSearch"></vaadin-text-field>
                <vaadin-horizontal-layout id="grid-container"></vaadin-horizontal-layout>
                <hr class="hr-text" data-content="Mehr anzeigen">
            </div>
        `;
    }

    static get properties() {
        return {

        }
    }

    static get is() {
        return "playlist-view"
    }
}

customElements.define(PlaylistView.is, PlaylistView);