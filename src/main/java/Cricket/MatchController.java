package Cricket;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class MatchController {
    @RequestMapping("/")
    public String index() {
        return Match.go();
    }
}
