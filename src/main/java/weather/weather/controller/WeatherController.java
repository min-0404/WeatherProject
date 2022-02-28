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

    WeatherRepository weatherRepository = new WeatherRepository(); // static 으로 선언되었던 저장소

    //1. 메인 페이지 그려주는 컨트롤러
    @GetMapping("/weather")
    public String getMain(){
        return "main";
    }

    //2. 메인페이지에서 fetch 로 호출한 데이터를 받아주고, repository 에 저장해주는 컨트롤러
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

    //3. 메인페이지의 Form 에 입력된 데이터를 파라미터 형식으로 받아오고, 원하는 데이터를 바디에 넣어주고 & 결과 페이지를 그려주는 컨트롤러
    @PostMapping("/weather-result")
    public String getResult(@RequestParam String location, Model model){
        Integer result = weatherRepository.findTemp(location);
        model.addAttribute("result", result);
        model.addAttribute("location", location);
        return "result";
    }

}
