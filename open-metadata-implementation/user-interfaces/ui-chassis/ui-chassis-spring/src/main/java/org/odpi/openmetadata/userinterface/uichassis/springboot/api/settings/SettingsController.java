/* SPDX-License-Identifier: Apache-2.0 */
/* Copyright Contributors to the ODPi Egeria project. */
package org.odpi.openmetadata.userinterface.uichassis.springboot.api.settings;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;


@RestController
public class SettingsController {

    @Value("${theme:default}")
    String theme;

    @GetMapping( path = "/css/theme")
    public RedirectView getThemeCss(){
        return new RedirectView("/themes/" + theme + "/css/style.css");
    }
}