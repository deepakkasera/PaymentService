package com.example.paymentservice.services;

import com.example.paymentservice.paymentgateway.PaymentGateway;
import com.razorpay.RazorpayException;
import com.stripe.exception.StripeException;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    private PaymentGateway paymentGateway;

    public PaymentService(PaymentGateway paymentGateway) {
        this.paymentGateway = paymentGateway;
    }

    public String initiatePayment(String orderId, Long amount, String phoneNumber, String email) throws RazorpayException, StripeException {
//        Order order = orderService.getOrderDetails(orderId);
//        int amount = order.getAmount;
//        String productName = order.getProductName();
        //Long amount = 198767L; // 1987.67
        //Long amount = 10000L; // 100.00

        //Generate the payment link.
        return paymentGateway.generatePaymentLink(
                orderId, amount, phoneNumber, email
        );
    }
}
