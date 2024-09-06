package com.paypal.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import com.paypal.service.PayPalService;

@Controller
public class PaymentController {

    @Autowired
    private PayPalService payPalService;

    @GetMapping("/pay")
    public String pay(@RequestParam("amount") double amount, RedirectAttributes redirectAttributes) {
        try {
            Payment payment = payPalService.createPayment(
                amount, 
                "USD", 
                "paypal", 
                "sale", 
                "Payment description", 
                "http://localhost:8080/cancel", 
                "http://localhost:8080/success"
            );
            
            for (com.paypal.api.payments.Links link : payment.getLinks()) {
                if (link.getRel().equals("approval_url")) {
                    return "redirect:" + link.getHref();
                }
            }
        } catch (PayPalRESTException e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("errorMessage", "Error occurred while processing payment.");
            return "redirect:/error";
        }

        return "redirect:/error";
    }
    @GetMapping("/success")
    public String success(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId, RedirectAttributes redirectAttributes) {
        try {
            // Execute the payment using the PayPalService class
            Payment payment = payPalService.executePayment(paymentId, payerId);

            if (payment.getState().equals("approved")) {
                // If payment is successful, redirect to success page
                redirectAttributes.addFlashAttribute("successMessage", "Payment successful!");
                return "redirect:/success";
            }
        } catch (PayPalRESTException e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("errorMessage", "Payment execution failed.");
            return "redirect:/error";
        }

        return "redirect:/error";
    }

    @GetMapping("/cancel")
    public String cancel() {
        // Handle payment cancellation
        return "cancel";
    }

    @GetMapping("/error")
    public String error() {
        return "error";
    }
}
