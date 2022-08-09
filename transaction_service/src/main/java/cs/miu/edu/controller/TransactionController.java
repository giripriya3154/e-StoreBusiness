package cs.miu.edu.controller;

import cs.miu.edu.dto.TransactionDto;
import cs.miu.edu.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;


    @PostMapping
    public ResponseEntity<?> saveTransaction(@RequestBody TransactionDto transactionDto) {
        ResponseEntity<TransactionDto> response = new ResponseEntity<>(transactionService.saveTransaction(transactionDto), HttpStatus.OK);
        return response;
    }

}
