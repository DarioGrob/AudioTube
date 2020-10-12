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
                
            </style>
            <div id="content">
                <div id="playlistTitle">
                    <h1>[[playlist.name]]</h1>
                </div>
                <div id="song">
                    <h3>[[playlist.currentSong.name]]</h3>
                    <div id="player-container">
                        <audio on-timeupdate="initProgressBar" on-ended="endSong" preload="metadata" id="player" src="[[playlist.currentSong.url]]"></audio>
                    </div>
                    <div id="audioControl">
                        <vaadin-horizontal-layout id="controlButtons">
                            <iron-icon id="back" on-click="lastSong" icon="vaadin:angle-left"></iron-icon>
                            <iron-icon id="playAndStop" on-click="changePlayAndStopButton" icon="vaadin:play-circle-o"></iron-icon>
                            <iron-icon id="next" on-click="nextSong" icon="vaadin:angle-right"></iron-icon>
                        </vaadin-horizontal-layout>
                        <progress id="progressBar" on-click="setCurrentTimeOfSong" value="0" max="1"></progress>
                        <small id="startTime">00:00</small>
                        <small id="endTime"></small>
                    </div>
                </div>
                <vaadin-button>
                    <iron-icon icon="vaadin:cog"></iron-icon>
                </vaadin-button>
            </div>
        `;
    }

    constructor() {
        super();
    }

    ready() {
        super.ready();

        let songList = this.get("playlist.songs");
        this.set("playlist.currentSong", songList[0]);
        this.set("currentSongIndex", 0);
        this.set("songListLenght", songList.length);

        this.setEndTimeForSong();
    }

    setEndTimeForSong() {
        let player = this.shadowRoot.getElementById("player");
        let endTime = this.shadowRoot.getElementById("endTime");
        player.onloadedmetadata = function() {
            let duration = player.duration;
            let minute = parseInt(duration / 60) % 60;
            let seconds_long = duration % 60;
            let seconds = seconds_long.toFixed();
            let time = (minute < 10 ? "0" + minute : minute) + ":" + (seconds < 10 ? "0" + seconds : seconds);
            endTime.innerHTML = time;
        }
    }

    initProgressBar() {
        console.log("test");
        let playButton = this.shadowRoot.getElementById("playAndStop");
        if (playButton.getAttribute("icon") == "vaadin:pause") {
            let player = this.shadowRoot.getElementById("player");
            console.log(player);
            console.log(player.currentTime);
            let current_time = player.currentTime;

            let currentTime = this.calculateCurrentValue(current_time);
            this.shadowRoot.getElementById("startTime").innerHTML = currentTime;

            if (!isNaN(player.duration)) {
                let progressbar = this.shadowRoot.getElementById("progressBar");
                progressbar.value = (player.currentTime / player.duration);
            }
        }
    }

    setCurrentTimeOfSong(event) {
        let progressbar = this.shadowRoot.getElementById("progressBar");
        let player = this.shadowRoot.getElementById("player");

        let percent = event.offsetX / progressbar.offsetWidth;
        player.currentTime = percent * player.duration;
        progressbar.value = percent;
    }

    calculateCurrentValue(currentTime) {
        let current_minute = parseInt(currentTime / 60) % 60;
        let current_seconds_long = currentTime % 60;
        let current_seconds = current_seconds_long.toFixed();
        let current_time = (current_minute < 10 ? "0" + current_minute : current_minute) + ":" + (current_seconds < 10 ? "0" + current_seconds : current_seconds);

        return current_time;
    }

    changePlayAndStopButton(e) {
        let button = this.shadowRoot.getElementById("playAndStop");
        let player = this.shadowRoot.getElementById("player");
        if (button.getAttribute("icon") == "vaadin:play-circle-o") {
            button.setAttribute("icon", "vaadin:pause");
            player.play();
        } else if (button.getAttribute("icon") == "vaadin:pause") {
            button.setAttribute("icon", "vaadin:play-circle-o");
            player.pause();
        }
    }

    lastSong(e) {
        let songList = this.get("playlist.songs");
        let currentSongIndex = this.get("currentSongIndex");
        let songListLenght = this.get("songListLenght");

        let player = this.shadowRoot.getElementById("player");
        let playButton = this.shadowRoot.getElementById("playAndStop");

        if (currentSongIndex == 0) {
            this.set("playlist.currentSong", songList[songListLenght - 1]);
            this.set("currentSongIndex", songListLenght - 1);
            this.setEndTimeForSong();
            player.currentTime = 0;
        } else {
            this.set("playlist.currentSong", songList[currentSongIndex - 1]);
            this.set("currentSongIndex", currentSongIndex - 1);
            this.setEndTimeForSong();
            player.currentTime = 0;
        }

        if (playButton.getAttribute("icon") == "vaadin:pause") {
            player.play();
        }
    }

    nextSong(e) {
        let songList = this.get("playlist.songs");
        let currentSongIndex = this.get("currentSongIndex");
        let songListLenght = this.get("songListLenght");

        let player = this.shadowRoot.getElementById("player");
        let playButton = this.shadowRoot.getElementById("playAndStop");

        if (currentSongIndex == songListLenght - 1) {
            this.set("playlist.currentSong", songList[0]);
            this.set("currentSongIndex", 0);
            this.setEndTimeForSong();
            player.currentTime = 0;
        } else {
            this.set("playlist.currentSong", songList[currentSongIndex + 1]);
            this.set("currentSongIndex", currentSongIndex + 1);
            this.setEndTimeForSong();
            player.currentTime = 0;
        }

        if (playButton.getAttribute("icon") == "vaadin:pause") {
            player.play();
        }
    }

    endSong() {
        let songList = this.get("playlist.songs");
        let currentSongIndex = this.get("currentSongIndex");
        let songListLenght = this.get("songListLenght");

        let player = this.shadowRoot.getElementById("player");

        if (currentSongIndex == songListLenght - 1) {
            this.set("playlist.currentSong", songList[0]);
            this.set("currentSongIndex", 0);
            this.setEndTimeForSong();
            player.currentTime = 0;
            player.play();
        } else {
            this.set("playlist.currentSong", songList[currentSongIndex + 1]);
            this.set("currentSongIndex", currentSongIndex + 1);
            this.setEndTimeForSong();
            player.currentTime = 0;
            player.play();
        }
    }

    static get properties() {
        return {
            playlist: {
                type: Object,
                value() {

                }
            }
        }
    }

    static get is() {
        return "playlist-component";
    }
}

customElements.define(PlaylistComponent.is, PlaylistComponent);