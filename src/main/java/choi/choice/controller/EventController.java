package choi.choice.controller;

import choi.choice.domain.Evt;
import choi.choice.domain.Good;
import choi.choice.repository.EventRepository;
import choi.choice.service.EventService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Controller
@RequestMapping("/evt")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;
    private final EventRepository eventRepository;

    @GetMapping("/addForm")
       public String createEvtForm() {
           return "evt/createForm";
       }

   @PostMapping("/create")
   public String createEvt(@ModelAttribute Evt evt, HttpServletRequest request) {
       eventService.createEvt(evt, request);
       return "ok";
   }

    @PostMapping("/detail")
    public String evtDetail(String evtNo, Model model, HttpServletRequest request) {
        Evt evt = eventService.findEvtByNo(evtNo);
        model.addAttribute("evt", evt);

        return "evtDetail/" + evtNo;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public void evtUpdate(@ModelAttribute Evt evt, HttpServletRequest request) {

    }

}
