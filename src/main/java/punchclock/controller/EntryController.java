 package punchclock.controller;



import java.util.List;

import javax.validation.Valid;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import punchclock.domain.Entry;
import punchclock.service.EntryService;

// https://docs.spring.io/spring/docs/current/javadoc-api/org/springframework/web/bind/annotation/ResponseStatus.html
// https://www.baeldung.com/spring-response-status
// https://www.baeldung.com/spring-response-entity

@RestController
@RequestMapping("/entries")
@CrossOrigin(origins = "http://localhost:3000")
public class EntryController { 
    private EntryService entryService;

    public EntryController(EntryService entryService) {
        this.entryService = entryService;
    }
    
    @ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
    @GetMapping("/teapot")
    public void teaPot() {
    	
    }
    
    // https://spring.io/guides/gs/rest-service-cors/
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<Entry> getAllEntries() {
        return entryService.findAllEntries();
    }
    
    @GetMapping("/allresponses")
    @Produces({ "application/json" })
    public Response getAllEntriesResponse() {
		return Response.status(200)
				.entity(entryService.findAllEntries())
				.build();
	}
    
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Entry getEntryById(@PathVariable Long id) {
        return entryService.findEntryById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Entry createEntry(@Valid @RequestBody Entry entry) {
        return entryService.createEntry(entry);
    }
    
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Entry changeEntryById(@RequestBody Entry newEntry, @PathVariable Long id) {
        return entryService.changeEntryById(newEntry, id);
    }
    
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void removeEntryById(@PathVariable Long id) {
        entryService.removeEntryById(id);
    }
    
    
    

}
