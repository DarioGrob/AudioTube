import {html, PolymerElement} from "@polymer/polymer/polymer-element";

class EditDialog extends PolymerElement {
    // language=HTML
    static get template() {
        return html`
            <style>
                div#content {
                    padding: 0;
                }
            </style>
        `;
    }

    static get is() {
        return "edit-dialog";
    }
}

customElements.define(EditDialog.is, EditDialog);