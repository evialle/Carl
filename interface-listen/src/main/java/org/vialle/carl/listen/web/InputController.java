package org.vialle.carl.listen.web;

import org.springframework.stereotype.Controller;
import org.vialle.carl.listen.services.CarlEars;

import javax.inject.Inject;

/**
 * Created by eric on 29/12/2013.
 */
@Controller
public class InputController {

    @Inject
    private CarlEars carlEars;

}
