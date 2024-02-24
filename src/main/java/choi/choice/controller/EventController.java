package choi.choice.controller;

import choi.choice.domain.Evt;
import choi.choice.domain.Good;
import choi.choice.repository.EventRepository;
import choi.choice.service.EventService;
import choi.choice.service.SessionManager;
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
    private final SessionManager sessionManager;

    @RequestMapping(value = "/addForm", method = RequestMethod.GET)
       public String createEvtForm() {
           return "evt/createForm";
       }

   @RequestMapping(value = "/creat", method = RequestMethod.POST)
   public String createEvt(@ModelAttribute Evt evt, HttpServletRequest request) {
       eventService.createEvt(evt, request);
       return "ok";
   }

    @RequestMapping(value = "/detail", method = RequestMethod.POST)
    public String evtDetail(String evtNo, Model model, HttpServletRequest request) {
        Evt evt = eventService.findEvtByNo(evtNo);
        model.addAttribute("evt", evt);

        return "evtDetail/" + evtNo;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public void evtUpdate(@ModelAttribute Evt evt, HttpServletRequest request) {
        String loginId = sessionManager.getSession(request).getMbrId();

        evt.setUdterId(loginId);

        eventService.updateEvt(evt);
    }

}
