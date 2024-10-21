package ec.com.banking.demo.account.mov.controllers;

import ec.com.banking.demo.account.mov.dtos.DepositWithdrawals;
import ec.com.banking.demo.account.mov.dtos.ReporteDto;
import ec.com.banking.demo.account.mov.errors.AccountError;
import ec.com.banking.demo.account.mov.services.MovementService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author cesarsevilla
 * bankingDemoJava
 */

@RestController
@RequestMapping("/reports")
public class ReportController {

    private final MovementService movementService;

    private final AccountError accountError;

    public ReportController(MovementService movementService, AccountError accountError) {
        this.movementService = movementService;
        this.accountError = accountError;
    }

    @PostMapping
    public ResponseEntity<?> listReportMovements(@Valid @RequestBody DepositWithdrawals findData,
            BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(accountError.validationErrors(result));
        }
        List<Object[]> listReport = movementService.listMovements(findData);
        if (listReport.isEmpty())
            return ResponseEntity.notFound().build();

        List<ReporteDto> newReport = listReport.stream()
                .map(ss -> new ReporteDto(
                        String.valueOf(ss[0]),
                        String.valueOf(ss[1]),
                        String.valueOf(ss[2]),
                        String.valueOf(ss[3]),
                        String.valueOf(ss[4]),
                        String.valueOf(ss[5]),
                        String.valueOf(ss[6]),
                        String.valueOf(ss[7])))
                .collect(Collectors.toList());

        return ResponseEntity.ok(newReport);

    }
}
