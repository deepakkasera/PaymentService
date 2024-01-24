package com.example.paymentservice.paymentgateway;

import com.razorpay.RazorpayException;
import com.stripe.exception.StripeException;

public interface PaymentGateway {
    String generatePaymentLink(String orderId, Long amount, String phoneNumber, String email) throws RazorpayException, StripeException;
}
