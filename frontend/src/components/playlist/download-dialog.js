import {html, PolymerElement} from "@polymer/polymer/polymer-element";

class DownloadDialog extends PolymerElement {
    // language=HTML
    static get template() {
        return html`
        
        `;
    }

    static get is() {
        return "download-dialog";
    }
}

customElements.define(DownloadDialog.is, DownloadDialog);