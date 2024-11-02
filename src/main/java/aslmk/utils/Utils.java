package aslmk.utils;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class Utils {
    public static int getPageNumber(String requestedPageNumber) {
        int pageNumber;
        try {
            pageNumber = Integer.parseInt(requestedPageNumber);
        } catch (NumberFormatException e) {
            pageNumber = 1;
        }
        return pageNumber <= 0 ? 1 : pageNumber;
    }

    public static boolean isValidString(String str) {
        if (str == null || str.isEmpty() || str.equals(" ") || str.equals("\n") || str.equals("\t")) {
            return false;
        }
        return true;
    }

    public static void redirectToErrorPage(int statusCode, String message, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setStatus(statusCode);
        request.setAttribute("errorMessage", message);
        request.setAttribute("statusCode", statusCode);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/errorPage.jsp");
        dispatcher.forward(request, response);
    }
}
