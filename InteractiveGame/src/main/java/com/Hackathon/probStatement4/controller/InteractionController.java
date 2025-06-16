package com.Hackathon.probStatement4.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.Hackathon.probStatement4.model.GameSession;
import com.Hackathon.probStatement4.repo.GameSessionRepository;

@Controller
public class InteractionController {

    private final OllamaChatModel client;
    private final GameSessionRepository repo;

    //constructor
    public InteractionController(OllamaChatModel client, GameSessionRepository repo) {
        this.client = client;
        this.repo = repo;
    }

    @RequestMapping("/")
    public String open(@RequestParam(value = "sessionId", required = false) String sessionId, Model model) {
        if (sessionId == null || sessionId.isBlank()) {
            sessionId = UUID.randomUUID().toString();
        }
        model.addAttribute("sessionId",sessionId);
        return "Index";
    }
    

    @RequestMapping("/message_response")
    public String promptResponse(@RequestParam("query") String query , @RequestParam(value = "sessionId", required = false) String sessionId,
            Model model) {

        if (query == null || query.trim().isEmpty()) {
            model.addAttribute("response", "Please enter a valid action to continue the adventure.");
            return "Index";
        }

        if (sessionId == null || sessionId.isBlank()) {
            sessionId = UUID.randomUUID().toString();
        }
               
        String finalSessionId = sessionId;
        GameSession session = repo.findById(finalSessionId)
                .orElseGet(() -> {
                    GameSession newSession = new GameSession();
                    newSession.setSessionId(finalSessionId);
                    newSession.setChatHistory("The story begins... \n");  // Start with initial story prompt
                    return newSession;
                });

        String updatedHistory = session.getChatHistory() + "\n<b style='color:blue;'>Player: </b> " + query + "\n \n";

        String storyPrompt = """
        	    You are an AI Storyteller. Craft an original story in any genre, developing compelling characters, vivid settings, and engaging plotlines.
        	    Continue the story based on the player's input below.

        	    Instructions:
        	    - Describe the scene and emotions with rich but concise detail.
        	    - Evolve the characters and relationships (new or existing).
        	    - Introduce plot developments, twists, or decisions when appropriate.
        	    - Reflect the tone and pacing of the story so far.
        	    - Present meaningful choices, consequences, or dilemmas when natural.
        	    - Keep the writing immersive, imaginative, and easy to follow.
        	    - Only respond with the continuation of the story. Do NOT include the player's sentence. Make the stories unique , creative.
        	    
        	    Important Note:
        	    - Keep the story short , entertaining and give chocies for next input.

        	    Current story so far:
        	    """ + updatedHistory + "\n Storyteller:";


        String aiResponse = client.call(storyPrompt);
       
        updatedHistory += "<b style='color:blue'>Storyteller:</b> " + aiResponse + "\n";
        session.setChatHistory(updatedHistory);
        session.setLastUpdated(LocalDateTime.now());

        repo.save(session);

        model.addAttribute("response", aiResponse);
        model.addAttribute("sessionId", finalSessionId);
        model.addAttribute("chatHistory", updatedHistory); // Add the full history to the model

        return "Index";
    }

    // Start a new game: generate a new sessionId and reset the story
    @RequestMapping("/start_new_game")
    public String startNewGame(Model model) {
        String newSessionId = UUID.randomUUID().toString();
        return "redirect:/?sessionId=" + newSessionId;
    }
    
   @RequestMapping("/FindPreviousStories")
   public String ShowData(@ModelAttribute GameSession gameSession , Model m) {
	    List<GameSession> list = repo.findAll();
	   m.addAttribute("data", list);
	   return "AllStories";
   } 
    
   @RequestMapping("/continueStory")
   public String ContinueStory(@RequestParam("sessionId") String id , Model m) {
	   Optional<GameSession> gameSession = repo.findById(id);
	   m.addAttribute("chatHistory",gameSession.get().getChatHistory());// .get() helps with fetching details from its class id
	   m.addAttribute("sessionId", id);
	   return "Index";
   }
   
   @RequestMapping("/delete")
   public String DeleteStory(@RequestParam("sessionId") String id , Model m) {
	   repo.deleteById(id);
	   m.addAttribute("data", repo.findAll());
	   return "AllStories";
   }
    
}
