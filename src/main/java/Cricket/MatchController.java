package Cricket;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.json.JSONArray;

@RestController
public class MatchController {
    @RequestMapping("/")
    public JSONArray index() {
        return Match.go();
    }
}
