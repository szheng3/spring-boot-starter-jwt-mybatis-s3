package com.digitalsoftware.accounting.request.controllers;

import com.digitalsoftware.accounting.domain.generated.Billing;
import com.digitalsoftware.accounting.domain.generated.BillingPerson;
import com.digitalsoftware.accounting.domain.models.Account;
import com.digitalsoftware.accounting.emun.domain.StatusState;
import com.digitalsoftware.accounting.request.Request;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.oauth2.common.exceptions.InvalidRequestException;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateBillRequest extends Request {


    private Billing billing;

    private BillingPerson billingPerson;


    @Override
    public void isValid() {

        if (billing == null || billingPerson == null) {
            throw new InvalidRequestException("Please fill in the billing and billing person object");
        }


        if (billing.getId() != null) {
            throw new InvalidRequestException("IdBill is not required");
        }


    }

    @Override
    public void unWrap(Account account) {
        billing.setId(null);
        billing.setUserId(account.getId());
        billing.setStatus(StatusState.ACTIVE);

        billingPerson.setStatus(StatusState.ACTIVE);
    }


}
