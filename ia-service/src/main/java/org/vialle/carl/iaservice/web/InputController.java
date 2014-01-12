package org.vialle.carl.iaservice.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.vialle.carl.iaservice.services.CarlAiAnswer;
import org.vialle.carl.iaservice.services.CarlBrain;

import javax.inject.Inject;

/**
 * Created by eric on 29/12/2013.
 */
@Controller
public class InputController {

    @Inject
    private CarlBrain carlBrain;

    @RequestMapping("/process/sentence")
    public @ResponseBody
    CarlAiAnswer processSentence(
            @RequestParam(value = "content", required = true) String content, @RequestParam(value="location", required = false) String location) {
        return carlBrain.think(content);
    }
}
