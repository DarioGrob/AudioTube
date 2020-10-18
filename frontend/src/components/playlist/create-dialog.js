import {html, PolymerElement} from "@polymer/polymer/polymer-element";

class CreateDialog extends PolymerElement{
    // language=HTML
    static get template() {
        return html`
        
        `;
    }

    static get is() {
        return "create-dialog";
    }

}

customElements.define(CreateDialog.is, CreateDialog);