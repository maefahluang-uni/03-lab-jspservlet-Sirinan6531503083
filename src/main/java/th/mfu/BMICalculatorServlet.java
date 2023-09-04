package th.mfu;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//TODO: add webservlet to "/calbmi"
public class BMICalculatorServlet extends HttpServlet{

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //TODO: get parameter from request: "weight" and "height"
        String weightStr = request.getParameter("weight");
        String heightStr = request.getParameter("height");

        try {
        //TODO: calculate bmi
        double weight = Double.parseDouble(weightStr);
        double height = Double.parseDouble(heightStr);

        double bmi = calculateBMI(weight, height);

        //TODO: determine the built from BMI
        String builtType = determineBuiltType(bmi);
      
        //TODO: add bmi and built to the request's attribute
        request.setAttribute("bmi", bmi);
        request.setAttribute("builtType", builtType);

        //TODO: forward to jsp
        request.getRequestDispatcher("/bmi_result.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Invalid input. Please enter numeric values for weight and height.");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }
    private double calculateBMI(double weight, double height) {
        // Calculate BMI (as an integer without decimal points)
        double bmi = (weight / (height * height));
        return Math.round(bmi);
    }

    private String determineBuiltType(double bmi) {
        if (bmi < 18.5) {
            return "Underweight";
        } else if (bmi < 25) {
            return "Normal";
        } else if (bmi < 30) {
            return "Overweight";
        } else if (bmi < 35) {
            return "Obese";
        } else {
            return "Extremely Obese";
        }
    }
}

