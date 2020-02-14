package com.yanzord.cloud.springcalculator.servlet;

import com.yanzord.cloud.springcalculator.config.AppConfig;
import com.yanzord.cloud.springcalculator.exception.InvalidNumberException;
import com.yanzord.cloud.springcalculator.exception.InvalidOperationException;
import com.yanzord.cloud.springcalculator.service.Calculator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Calculator", urlPatterns = {"/calculator"}, loadOnStartup = 1)
public class CalculatorServlet extends HttpServlet {

    private ApplicationContext ac;
    private Calculator calculator;
    private final static String NUMBER_REGEX = "-?\\d+(\\.\\d+)?";

    public CalculatorServlet() {
        ac = new AnnotationConfigApplicationContext(AppConfig.class);
        calculator = (Calculator) ac.getBean("calculator");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter writer = response.getWriter();

        if(request.getParameterNames().hasMoreElements()) {
            if(request.getParameterMap().containsKey("log")) {
                calculator.getLogs().forEach(writer::println);
            } else if (request.getParameterMap().containsKey("value1") && request.getParameterMap().containsKey("op") && request.getParameterMap().containsKey("value2")) {
                String firstNumber = request.getParameter("value1");
                String operation = request.getParameter("op");
                String secondNumber = request.getParameter("value2");

                if (firstNumber.matches(NUMBER_REGEX) && secondNumber.matches(NUMBER_REGEX)) {
                    try {
                        Double result = calculator.doOperation(Double.valueOf(firstNumber), operation, Double.valueOf(secondNumber));

                        writer.println(result);
                    } catch (InvalidNumberException | InvalidOperationException e) {
                        writer.println(e.getMessage());
                    }
                } else {
                    writer.println("Value inserted is not a number.");
                }
            }
        } else {
            writer.println("Welcome to your web calculator!");
        }

        ((AnnotationConfigApplicationContext) ac).close();
    }
}