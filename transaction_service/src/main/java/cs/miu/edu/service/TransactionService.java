package cs.miu.edu.service;


import cs.miu.edu.domain.Transaction;
import cs.miu.edu.dto.TransactionDto;
import cs.miu.edu.mapper.Mapper;
import cs.miu.edu.repository.TransactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cs.miu.edu.utils.TransactionCodeGenerator;


@Service
public class TransactionService {

    @Autowired
    private TransactionRepo transactionRepo;
    @Autowired
    private TransactionCodeGenerator transactionCodeGenerator;


    @Autowired
    private Mapper mapper;

    public TransactionDto saveTransaction(TransactionDto transactionDto) {
        Transaction transaction = Transaction.builder()
                .transactionCode(transactionCodeGenerator.transactionCodeGenerator())
                .paymentMethod(transactionDto.getPaymentMethod())
                .total(transactionDto.getTotal())
                .orderId(transactionDto.getOrderId())
                .build();
        transaction.setTransactionCode(transaction.getTransactionCode());
        transactionRepo.save(transaction);
        transactionDto.setTransactionCode(transaction.getTransactionCode());

        return transactionDto;
    }
}
