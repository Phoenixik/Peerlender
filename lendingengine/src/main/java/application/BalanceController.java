package application;

import domain.model.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.BalanceService;

@RestController
@RequestMapping("/balance")
public class BalanceController {

    @Autowired
    private BalanceService balanceService;

    @PostMapping("/topup")
    public void topUp(@RequestBody Money money, @RequestHeader String authorization) {
        balanceService.topUpBalance(money, authorization);
    }

    @PostMapping("/withdraw")
    public void withdraw(@RequestBody Money money, @RequestHeader String authorization ){
        balanceService.withdrawFromBalance(money, authorization);
    }

}
