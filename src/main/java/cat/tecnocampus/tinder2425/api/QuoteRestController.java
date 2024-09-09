package cat.tecnocampus.tinder2425.api;

import cat.tecnocampus.tinder2425.application.QuoteService;
import cat.tecnocampus.tinder2425.application.dto.quote.ValueDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/quotes")
@RestController
public class QuoteRestController {
    private QuoteService quoteService;

    public QuoteRestController(QuoteService quoteService) {
        this.quoteService = quoteService;
    }

    @GetMapping("/{id}")
    public ValueDTO getQuoteId(@PathVariable Long id) {
        return quoteService.getSingleQuote(id);
    }

    @GetMapping("/random")
    public ValueDTO getQuoteRandom() {
        return quoteService.getRandomQuote();
    }

    @GetMapping("")
    public List<ValueDTO> getQuotes() {
        return quoteService.getQuotes();
    }
}
