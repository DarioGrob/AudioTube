import {html, PolymerElement} from "@polymer/polymer/polymer-element";

class AddDialog extends PolymerElement{
    // language=HTML
    static get template() {
        return html`
        
        `;
    }

    static get is() {
        return "add-dialog"
    }

}

customElements.define(AddDialog.is, AddDialog)