package com.example.exchangerate;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.DecimalFormat;

@Controller
public class ExchangeRateController {

    // 간단한 환율 데이터
    private final double USD_TO_KRW = 1469.0;
    private final double JPY_TO_KRW = 9.35;
    private final double EUR_TO_KRW = 1514.0;
    private final double CNY_TO_KRW = 200.0;

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @PostMapping("/convert")
    public String convert(@RequestParam("amount") double amount,
                          @RequestParam("currency") String currency,
                          Model model)
    {
        double result = 0;
        String targetCurrency = "KRW";

        if (currency.equals("USD")) {
            result = amount * USD_TO_KRW;
        } else if (currency.equals("JPY")) {
            result = amount * JPY_TO_KRW;
        }else if(currency.equals("CNY")) {
            result = amount * CNY_TO_KRW;
        }else {
            result = amount * EUR_TO_KRW;
        }
        DecimalFormat decimalFormat = new DecimalFormat("#,###.##");
        String formattedResult = decimalFormat.format(result);

        model.addAttribute("amount", amount);
        model.addAttribute("currency", currency);
        model.addAttribute("result", formattedResult);
        model.addAttribute("targetCurrency", targetCurrency);

        return "index";
    }
}
