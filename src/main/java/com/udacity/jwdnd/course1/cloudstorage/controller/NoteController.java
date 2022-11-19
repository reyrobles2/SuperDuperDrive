package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.dto.NotesForm;
import com.udacity.jwdnd.course1.cloudstorage.services.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/note")
public class NoteController {

    private NotesService notesService;
    private UsersService usersService;

    // Constructor
    public NoteController(NotesService notesService, UsersService usersService) {
        this.notesService = notesService;
        this.usersService = usersService;
    }

    @PostMapping("/save")
    public String createNote(Authentication authentication,
                             @ModelAttribute("note") NotesForm notesForm,
                             Model model) {
        Boolean isOk;
        String message = null;

        if (notesService.addNote(notesForm.getNoteId(),
                                 notesForm.getNoteTitle(),
                                 notesForm.getNoteDescription(),
                                 usersService.getLoggedInUserId(authentication)) < 1) {
            isOk = false;
            message = "Notes not saved successfully";
        }
        else{
            isOk = true;
        }
        return "redirect:/result?isOk=" + isOk+"&message="+message;
    }

    @GetMapping("/delete")
    public String deleteNote(@RequestParam("noteId") Integer noteId) {
        String message = null;
        Boolean isOk = noteId > 0;

        if (isOk) {
            notesService.delNote(noteId);
        }
        else{
            message = "Notes not deleted successfully";
        }
        return "redirect:/result?isOk=" + isOk+"&message="+message;
    }
}
