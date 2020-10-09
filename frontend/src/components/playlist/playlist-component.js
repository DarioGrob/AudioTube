import {html, PolymerElement} from "@polymer/polymer/polymer-element";

class PlaylistComponent extends PolymerElement {
    // language=HTML
    static get template() {
        return html`
            <style>
                #content {
                    width: 200px;
                    height: 325px;
                    margin-left: 5px;
                    margin-right: 5px;
                    background-color: #001233;
                    color: #979DAC;
                    border-radius: 40px 40px 40px 40px;
                    padding-right: 30px;
                    padding-left: 30px;
                }
                
                #playlistTitle {
                    height: 50px;
                    overflow: hidden;
                    position: relative;
                    border-bottom: 2px solid;
                    text-align: center;
                }
                
                #playlistTitle h1 {
                    margin-bottom: 0;
                    margin-top: 0;
                }
                
                #song {
                    height: calc(100% - 150px);
                    width: 100%;
                    text-align: center;
                    padding-top: 30px;
                }
                
                #song h3 {
                    font-weight: bold;
                    font-style: italic;
                    margin-top: 0;
                    margin-bottom: 0;
                }
                
                vaadin-button {
                    background-color: #979DAC;
                    width: 100%;
                    height: 40px;
                    color: #001233
                }
                
                #back {
                    height: 50px;
                    width: 50px;
                    margin-left: auto;
                }
                
                #next {
                    height: 50px;
                    width: 50px;
                    margin-right: auto;
                }
                
                #playAndStop {
                    height: 50px;
                    width: 50px;
                    margin-right: 15px;
                    margin-left: 15px;
                }
                
                #controlButtons {
                    margin-top: 15px;
                    margin-bottom: 15px;
                }
                
                #startTime {
                    float: left;
                    position: relative;
                    color: #979DAC;
                }
                
                #endTime {
                    float: right;
                    position: relative;
                    color: #979DAC;
                }
                
                #progressBar {
                    width: 100%;
                }

                #progressBar[value] {
                    -webkit-appearance: none;
                    appearance: none;
                    background-color: #001233;
                    color: #979DAC;
                    height: 5px;
                }

                #progressBar[value]::-webkit-progress-bar {
                    background-color: #001233;
                    border-radius: 2px;
                    border: 1px solid #5C677D;
                    color: #979DAC;
                }

                #progressBar::-webkit-progress-value {
                    background-color: #979DAC;
                }
                
                #grid-container {
                    display: grid;
                    grid-template-columns: auto auto auto auto;
                    grid-column-gap: 30px;
                    grid-row-gap: 30px;
                    margin-top: 50px;
                    margin-left: auto;
                    margin-right: auto;
                    width: 1170px;
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
                
            </style>
            <div id="content">
                <div id="playlistTitle">
                    <h1>[[playlist.name]]</h1>
                </div>
                <div id="song">
                    <h3>[[playlist.currentSong.name]]</h3>
                    <div id="player-container">
                        <audio id ="player">
                            <source scr="[[playlist.currentSong.url]]" type="audio/mp3"></audio>
                        </audio>
                    </div>
                    <div id="audioControl">
                        <vaadin-horizontal-layout id="controlButtons">
                            <iron-icon id="back" icon="vaadin:angle-left"></iron-icon>
                            <iron-icon id="playAndStop" on-click="changePlayAndStopButton" icon="[[playlist.controllButton]]"></iron-icon>
                            <iron-icon id="next" icon="vaadin:angle-right"></iron-icon>
                        </vaadin-horizontal-layout>
                        <progress id="progressBar" value="0" max="1"></progress>
                        <small id="startTime"></small>
                        <small id="endTime"></small>
                    </div>
                </div>
                <vaadin-button>
                    <iron-icon icon="vaadin:cog"></iron-icon>
                </vaadin-button>
            </div>
        `;
    }

    changePlayAndStopButton(e) {
        console.log("hallo");
        let test = document.getElementById("player");
        console.log(test);

        let test1 = this.shadowRoot.getElementById("player");
        console.log(test1);
        test1.play();
    }

    static get properties() {
        return {
            playlist: {

            }
        }
    }

    static get is() {
        return "playlist-component";
    }
}

customElements.define(PlaylistComponent.is, PlaylistComponent);