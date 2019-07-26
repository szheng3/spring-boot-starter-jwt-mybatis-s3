//package com.digitalsoftware.accounting.controllers;
//
//import com.digitalsoftware.accounting.request.controllers.CreateBillRequest;
//import com.digitalsoftware.accounting.response.Response;
//import com.digitalsoftware.accounting.response.ResponseBuilder;
//import com.digitalsoftware.accounting.services.BillService;
//import com.digitalsoftware.accounting.util.AccountUtil;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.oauth2.provider.OAuth2Authentication;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import springfox.documentation.annotations.ApiIgnore;
//
//@RestController
//@RequestMapping("/api/bill")
//public class BillController {
//
//
//    private final BillService billService;
//
//    @Autowired
//    public BillController(BillService billService) {
//        this.billService = billService;
//    }
//
//    @PostMapping("/create")
//    public Response createBill(@RequestBody CreateBillRequest createBillRequest, @ApiIgnore OAuth2Authentication oAuth2Authentication) {
//
//        createBillRequest.isValidAndUnWrap(AccountUtil.unWrap(oAuth2Authentication));
//
//        billService.createBillService(createBillRequest);
//
//        return ResponseBuilder.builder().build().block();
//    }
//
//
//}
