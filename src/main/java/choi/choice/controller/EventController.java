package choi.choice.controller;

import choi.choice.domain.Evt;
import choi.choice.domain.Good;
import choi.choice.repository.EventRepository;
import choi.choice.service.EventService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Controller
@RequestMapping("/evt")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;
    private final EventRepository eventRepository;

    @GetMapping("evt")
       public String createEvtForm() {
           return "evt/createForm";
       }

   @PostMapping("evt/add")
   public String createEvt(@ModelAttribute Evt evt, HttpServletRequest request) {
       eventService.add(evt, request);
       return "ok";
   }
}
