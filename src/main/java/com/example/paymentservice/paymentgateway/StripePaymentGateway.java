package com.example.paymentservice.paymentgateway;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentLink;
import com.stripe.model.Price;
import com.stripe.param.PaymentLinkCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.awt.image.ImageProducer;
import java.util.*;

@Primary
@Service
public class StripePaymentGateway implements PaymentGateway {
    @Value("${STRIPE_KEY_SECRET}")
    private String stripeKey;
    @Override
    public String generatePaymentLink(String orderId, Long amount, String phoneNumber, String email) throws StripeException {
        Stripe.apiKey = stripeKey;



        //List<map> lineItems;
//        PaymentLinkCreateParams params =
//                PaymentLinkCreateParams.builder()
//                        .addLineItem(
//                                PaymentLinkCreateParams.LineItem.builder()
//                                        .setPrice("price_1MoC3TLkdIwHu7ixcIbKelAC")
//                                        .setQuantity(1L)
//                                        .build()
//                        )
//                        .build();

        Map<String, Object> priceData = new HashMap<>();
        priceData.put("unit_amount", amount);
        priceData.put("currency", "inr");

        Map<String, Object> productData = new HashMap<>();
        productData.put("name", "iPhone");
        //productData.put("quantity", 2);
        priceData.put("product_data", productData);

        Price price = Price.create(priceData);

        //params.put("line_items", );
        Map<String, Object> lineItem1 = new HashMap<>();
        lineItem1.put("price", price.getId());
        lineItem1.put("quantity", 5);

        Map<String, Object> afterPayment = new HashMap<>();
        afterPayment.put("type", "redirect");

        Map<String, Object> redirect = new HashMap<>(); //lineItem2
        redirect.put("url", "https://scaler.com/");

        afterPayment.put("redirect", redirect);

        List<Object> lineItems = new ArrayList<>();
        lineItems.add(lineItem1);

        Map<String, Object> params = new HashMap<>();
        params.put("line_items", lineItems);
        params.put("after_completion", afterPayment);
        //params.put("generate_invoice", map);

        PaymentLink paymentLink = PaymentLink.create(params);

        return paymentLink.getUrl();
    }
}
