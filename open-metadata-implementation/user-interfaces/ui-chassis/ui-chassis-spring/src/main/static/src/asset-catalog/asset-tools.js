/* SPDX-License-Identifier: Apache-2.0 */
/* Copyright Contributors to the ODPi Egeria project. */
import { PolymerElement, html } from '@polymer/polymer';

import '../shared-styles.js';
import '../common/props-table';

class AssetTools extends PolymerElement {
    static get template() {
        return html`
      
      <style include="shared-styles">
        :host {
          display: block;
          padding: 10px 24px;
          background-color:  var(--egeria-background-color);
          --iron-icon-fill-color: var(--egeria-primary-color);
        }
        ul#menu, ul#menu li {
          display:inline;
          padding: 0;
          margin: 0 10pt;
        }
      </style>

    <div style="border: solid 1px var(--egeria-primary-color); padding: 5pt;"> 
        <ul id="menu"> 
            <li> 
                <a href="#/asset-lineage/ultimateSource/[[guid]]" title="Ultimate Source Lineage"><iron-icon icon="vaadin:connect-o" style="transform: rotate(180deg)"></iron-icon></a>
            </li>
            <li> 
                <a href="#/asset-lineage/endToEnd/[[guid]]" title="End2End Lineage"><iron-icon icon="vaadin:cluster"></iron-icon></a>
            </li>
            <li> 
                <a href="#/asset-lineage/ultimateDestination/[[guid]]" title="Ultimate Destination Lineage"><iron-icon icon="vaadin:connect-o"></iron-icon></a>
            </li>
            <li> 
                <a href="#/asset-lineage/glossaryLineage/[[guid]]" title="Glossary Lineage"><iron-icon icon="vaadin:file-tree"></iron-icon></a>
            </li>
            <li> 
                <a href="#/asset-lineage/sourceAndDestination/[[guid]]" title="Source and Destination Lineage"><iron-icon icon="vaadin:exchange"></iron-icon></a>
            </li>
        </ul>
    </div>
    `;
    }

}

window.customElements.define('asset-tools', AssetTools);