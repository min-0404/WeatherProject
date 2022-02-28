package weather.weather.controller;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import weather.weather.domain.Weather;
import weather.weather.repository.WeatherRepository;

import java.util.List;

@Controller
public class WeatherController {
    WeatherRepository weatherRepository = new WeatherRepository();

    //1.
    @GetMapping("/weather")
    public String getMain(){
        return "main";
    }

    //2.
    @ResponseBody
    @PostMapping("/weather")
    public String getData(@RequestBody List<JSONObject> jsonArr){
        for(JSONObject json : jsonArr){
            json.forEach((key, value) ->{
                weatherRepository.save(new Weather((String) key, (Integer) value));
            });
        }
        return "fetch complete";
    }

    //3.
    @PostMapping("/weather-result")
    public String getResult(@RequestParam String location, Model model){
        Integer result = weatherRepository.findTemp(location);
        model.addAttribute("result", result);
        model.addAttribute("location", location);
        return "result";
    }

}
