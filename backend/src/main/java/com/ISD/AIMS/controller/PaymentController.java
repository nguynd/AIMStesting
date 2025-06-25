package com.ISD.AIMS.controller;

import com.ISD.AIMS.config.VnPayConfig;
import com.ISD.AIMS.model.Order;
import com.ISD.AIMS.repository.OrderRepository;
import com.ISD.AIMS.service.OrderService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {
    
    @Autowired
    private OrderService orderService;
    
    @Autowired
    private OrderRepository orderRepository;

    @PostMapping("/create-payment")
    public ResponseEntity<?> createPayment(HttpServletRequest req) throws UnsupportedEncodingException {

        // Tạo đơn hàng PENDING trước để lấy tổng số tiền chính xác từ giỏ hàng
        String vnp_TxnRef = VnPayConfig.getRandomNumber(8);
        Order tempOrder = orderService.createOrderFromCart(vnp_TxnRef);
        long amount = (long) (tempOrder.getTotalPrice() * 100); // VNPAY yêu cầu nhân 100

        String vnp_Version = "2.1.0";
        String vnp_Command = "pay";
        String orderType = "other";
        String vnp_IpAddr = VnPayConfig.getIpAddress(req);

        Map<String, String> vnp_Params = new HashMap<>();
        vnp_Params.put("vnp_Version", vnp_Version);
        vnp_Params.put("vnp_Command", vnp_Command);
        vnp_Params.put("vnp_TmnCode", VnPayConfig.vnp_TmnCode);
        vnp_Params.put("vnp_Amount", String.valueOf(amount));
        vnp_Params.put("vnp_CurrCode", "VND");
        vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
        vnp_Params.put("vnp_OrderInfo", "Thanh toan don hang:" + vnp_TxnRef);
        vnp_Params.put("vnp_OrderType", orderType);
        vnp_Params.put("vnp_Locale", "vn");
        vnp_Params.put("vnp_ReturnUrl", VnPayConfig.vnp_ReturnUrl);
        vnp_Params.put("vnp_IpAddr", vnp_IpAddr);
        
        // **KHÔNG GỬI** tham số vnp_BankCode để VNPAY hiển thị giao diện lựa chọn chung
        // (bao gồm cả thẻ nội địa và quốc tế)

        Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String vnp_CreateDate = formatter.format(cld.getTime());
        vnp_Params.put("vnp_CreateDate", vnp_CreateDate);

        cld.add(Calendar.MINUTE, 15);
        String vnp_ExpireDate = formatter.format(cld.getTime());
        vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);

        List<String> fieldNames = new ArrayList<>(vnp_Params.keySet());
        Collections.sort(fieldNames);
        StringBuilder hashData = new StringBuilder();
        StringBuilder query = new StringBuilder();
        Iterator<String> itr = fieldNames.iterator();
        while (itr.hasNext()) {
            String fieldName = itr.next();
            String fieldValue = vnp_Params.get(fieldName);
            if ((fieldValue != null) && (fieldValue.length() > 0)) {
                hashData.append(fieldName);
                hashData.append('=');
                hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
                query.append('=');
                query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                if (itr.hasNext()) {
                    query.append('&');
                    hashData.append('&');
                }
            }
        }
        String queryUrl = query.toString();
        String vnp_SecureHash = VnPayConfig.hmacSHA512(VnPayConfig.vnp_HashSecret, hashData.toString());
        queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
        String paymentUrl = VnPayConfig.vnp_Url + "?" + queryUrl;

        return ResponseEntity.status(HttpStatus.OK).body(Map.of("paymentUrl", paymentUrl));
    }

    @GetMapping("/vnpay-return")
    public ResponseEntity<?> vnpayReturn(HttpServletRequest request) {
        String vnpTxnRef = request.getParameter("vnpTxnRef");
        String vnp_ResponseCode = request.getParameter("vnp_ResponseCode");

        Optional<Order> orderOpt = orderRepository.findByVnpTxnRef(vnpTxnRef);
        if (orderOpt.isPresent()) {
            Order order = orderOpt.get();
            // Ở đây bạn có thể kiểm tra thêm vnp_SecureHash để đảm bảo dữ liệu không bị thay đổi
            if ("00".equals(vnp_ResponseCode)) {
                order.setStatus("SUCCESS");
                 // Redirect về trang thành công của frontend
                return ResponseEntity.status(HttpStatus.FOUND).header("Location", "http://localhost:5173/payment-success?orderId=" + order.getId()).build();
            } else {
                order.setStatus("FAILED");
                // Redirect về trang thất bại của frontend
                return ResponseEntity.status(HttpStatus.FOUND).header("Location", "http://localhost:5173/payment-failed?orderId=" + order.getId()).build();
            }
        }
        return ResponseEntity.badRequest().body("Lỗi: Không tìm thấy đơn hàng.");
    }
}